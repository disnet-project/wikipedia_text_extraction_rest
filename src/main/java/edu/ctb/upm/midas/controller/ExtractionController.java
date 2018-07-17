package edu.ctb.upm.midas.controller;

import edu.ctb.upm.midas.constants.Constants;
import edu.ctb.upm.midas.model.Request;
import edu.ctb.upm.midas.model.RequestJSON;
import edu.ctb.upm.midas.model.Response;
import edu.ctb.upm.midas.service.ExtractService;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by gerardo on 05/07/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project validation_medical_term
 * @className ValidationController
 * @see
 */
@RestController
//@RequestMapping("/api/extract/wikipedia")
@RequestMapping("${my.service.rest.request.mapping.general.url}")
public class ExtractionController {

    @Autowired
    private ExtractService extractService;

    //@RequestMapping(path = { "/texts" }, //wikipedia document_structure
    //        method = RequestMethod.GET)
    @RequestMapping(path =  {  "${my.service.rest.request.mapping.texts.path}" },
            method = RequestMethod.POST)
    public Response extract(@RequestBody @Valid Request request, HttpServletRequest httpRequest, Device device) throws Exception {
        return extractService.extract(request);
/*
        String g ="http://en.wikipedia.org/wiki/Odonto–tricho–ungual–digital–palmar_syndrome";
        String m = "http://en.wikipedia.org/wiki/Bannayan–Riley–Ruvalcaba_syndrome";
        System.out.println(g + " | " + m);
        g = StringEscapeUtils.escapeJava(g);
        m = common.replaceSpecialCharactersToUnicode(m);
        System.out.println(g + " | " + m);
        System.out.println(common.replaceUnicodeToSpecialCharacters(g) + " | " + common.replaceUnicodeToSpecialCharacters(m));
        String t = "http://en.wikipedia.org/wiki/Bannayan\\u00E2\\u20AC\\u201CRiley\\u00E2\\u20AC\\u201CRuvalcaba_syndrome";
        System.out.println(t.replace("\\", "\\\\"));
        System.out.println(common.replaceUnicodeToSpecialCharacters(t));
*/

/*
        String s = "en.wikipedia.org/wiki/Yush\u014D_disease-Δ";
        System.out.println(s);
        s = common.replaceUnicodeToSpecialCharacters(s);
        System.out.println(s);
        System.out.println(common.replaceSpecialCharactersToUnicode(s));
        diseaseService.insertNative("glg3", s, "");

*/
/*
        for (String s:
        Constants.URLS) {
            System.out.println(common.replaceUnicodeToSpecialCharacters(s));
        }
*/

    }

    @RequestMapping(path =  {  "${my.service.rest.request.mapping.texts.json.path}" },
            method = RequestMethod.POST)
    public Response extractJSON(@RequestBody @Valid RequestJSON request, HttpServletRequest httpRequest, Device device) throws Exception {
        return extractService.extractJSON(request, Constants.TEXTS);
    }


    @RequestMapping(path =  {  "${my.service.rest.request.mapping.resources.path}" },
            method = RequestMethod.POST)
    public Response extractResources(@RequestBody @Valid Request request, HttpServletRequest httpRequest, Device device) throws Exception {
        return extractService.extractResources(request);
    }

    @RequestMapping(path =  {  "${my.service.rest.request.mapping.resources.json.path}" },
            method = RequestMethod.POST)
    public Response extractResourcesJSON(@RequestBody @Valid RequestJSON request, HttpServletRequest httpRequest, Device device) throws Exception {
        return extractService.extractJSON(request, Constants.RESOURCES);
    }


    @RequestMapping(path = { "/check" }, //wikipedia document_structure
            method = RequestMethod.GET)
    public void checkLinks() throws Exception {
        extractService.checkLinks();
    }


    @RequestMapping(path = { "/codes" }, //wikipedia document_structure
            method = RequestMethod.GET)
    public void checkCodes() throws Exception {
        extractService.checkCodes();
    }

    @RequestMapping(path =  {  "${my.service.rest.request.mapping.report.path}" },
            method = RequestMethod.GET,
            params = {"snapshot", "json"})
    public void extractionReport(
            @RequestParam(value = "snapshot") @Valid @NotBlank @NotNull @NotEmpty String snapshot,
            @RequestParam(value = "json") @Valid @NotBlank @NotNull @NotEmpty boolean json,
            HttpServletRequest httpRequest, Device device) throws Exception {
        Request request = new Request(null, snapshot, json);
        extractService.extractionReport(request);
    }




}
