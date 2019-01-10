package edu.ctb.upm.midas.controller;

import edu.ctb.upm.midas.common.util.TimeProvider;
import edu.ctb.upm.midas.constants.Constants;
import edu.ctb.upm.midas.model.Request;
import edu.ctb.upm.midas.model.RequestJSON;
import edu.ctb.upm.midas.model.Response;
import edu.ctb.upm.midas.model.ResponseJSON;
import edu.ctb.upm.midas.model.xml.XmlLink;
import edu.ctb.upm.midas.service.ExtractService;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Autowired
    private TimeProvider timeProvider;

    //@RequestMapping(path = { "/texts" }, //wikipedia document_structure
    //        method = RequestMethod.GET)
    @RequestMapping(path =  {  "${my.service.rest.request.mapping.texts.path}" },
            method = RequestMethod.POST)
    public Response extract(@RequestBody @Valid Request request, HttpServletRequest httpRequest, Device device) throws Exception {
        return extractService.extract(request);
    }

    @RequestMapping(path =  {  "${my.service.rest.request.mapping.texts.json.path}" },
            method = RequestMethod.POST)
    public ResponseJSON extractJSON(@RequestBody @Valid RequestJSON request, HttpServletRequest httpRequest, Device device) throws Exception {
        return extractService.extractJSON(request, Constants.TEXTS);
    }


    @RequestMapping(path =  {  "${my.service.rest.request.mapping.resources.path}" },
            method = RequestMethod.POST)
    public Response extractResources(@RequestBody @Valid Request request, HttpServletRequest httpRequest, Device device) throws Exception {
        return extractService.extractResources(request);
    }

    @RequestMapping(path =  {  "${my.service.rest.request.mapping.resources.json.path}" },
            method = RequestMethod.POST)
    public ResponseJSON extractResourcesJSON(@RequestBody @Valid RequestJSON request, HttpServletRequest httpRequest, Device device) throws Exception {
        return extractService.extractJSON(request, Constants.RESOURCES);
    }


    @RequestMapping(path =  {  "${my.service.rest.request.mapping.texts.all_pages.approach.path}" },
            method = RequestMethod.GET)
    public Response extractAllPages(HttpServletRequest httpRequest, Device device) throws Exception {
        List<XmlLink> webLinks = extractService.readAllWikipediaWebPageTitlesFile("", "enwiki-latest-all-titles-in-ns0");
        Request request = new Request(webLinks, timeProvider.getNowFormatyyyyMMdd(), true);
        return extractService.extract(request);
    }

    @RequestMapping(path =  {  "${my.service.rest.request.mapping.resources.all_pages.approach.path}" },
            method = RequestMethod.GET)
    public Response extractResourcesAllPages(HttpServletRequest httpRequest, Device device) throws Exception {
        List<XmlLink> webLinks = extractService.readAllWikipediaWebPageTitlesFile("", "enwiki-latest-all-titles-in-ns0");
        Request request = new Request(webLinks, timeProvider.getNowFormatyyyyMMdd(), true);
        return extractService.extract(request);
    }

    @RequestMapping(path = { "/check" }, //wikipedia document_structure
            method = RequestMethod.GET)
    public void checkLinks() throws Exception {
//        extractService.checkLinks();
        extractService.readAllWikipediaWebPageTitlesFile("", "enwiki-latest-all-titles-in-ns0");
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
