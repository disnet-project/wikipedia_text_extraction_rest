package edu.ctb.upm.disnet.controller;

import edu.ctb.upm.disnet.constants.Constants;
import edu.ctb.upm.disnet.model.Request;
import edu.ctb.upm.disnet.model.RequestJSON;
import edu.ctb.upm.disnet.model.Response;
import edu.ctb.upm.disnet.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
            method = RequestMethod.GET)
    public void extractionReport(@RequestBody @Valid Request request, HttpServletRequest httpRequest, Device device) throws Exception {
        extractService.extractionReport(request);
    }


}
