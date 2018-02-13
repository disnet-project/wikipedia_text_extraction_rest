package edu.ctb.upm.disnet.service;

import edu.ctb.upm.disnet.common.util.TimeProvider;
import edu.ctb.upm.disnet.component.ExtractionWikipedia;
import edu.ctb.upm.disnet.enums.ApiErrorEnum;
import edu.ctb.upm.disnet.enums.StatusHttpEnum;
import edu.ctb.upm.disnet.model.Response;
import edu.ctb.upm.disnet.model.document_structure.Source;
import edu.ctb.upm.disnet.model.document_structure.code.Resource;
import edu.ctb.upm.disnet.model.xml.XmlLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gerardo on 29/01/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className ExtractService
 * @see
 */
@Service
public class ExtractService {

    @Autowired
    private TimeProvider timeProvider;
    @Autowired
    private ExtractionWikipedia extractionWikipedia;



    public Response extract(List<XmlLink> externalDiseaseLinkList) throws Exception {
        Response response = new Response();
        List<Source> sourceList = null;

        String start = timeProvider.getTimestampFormat();
        String end = null;
        Date version = timeProvider.getSqlDate();
        try {
            sourceList = extractionWikipedia.extract(externalDiseaseLinkList);
            if (sourceList!=null) {
                response.setResponseCode(StatusHttpEnum.OK.getClave());
                response.setResponseMessage(StatusHttpEnum.OK.getDescripcion());
            }else{
                response.setResponseCode(ApiErrorEnum.RESOURCES_NOT_FOUND.getKey());
                response.setResponseMessage(ApiErrorEnum.RESOURCES_NOT_FOUND.getDescription());
            }
        }catch (Exception e){
            response.setSources(new ArrayList<>());
            response.setResponseCode(ApiErrorEnum.INTERNAL_SERVER_ERROR.getKey());
            response.setResponseMessage(ApiErrorEnum.INTERNAL_SERVER_ERROR.getDescription());
        }
        response.setSources(sourceList);
        response.setStart_time(start);
        end = timeProvider.getTimestampFormat();
        response.setEnd_time(end);

        System.out.println("Inicio:" + start + " | Termino: " + end);

        return response;
    }


    public Response extractResources(List<XmlLink> externalDiseaseLinkList) throws Exception {
        Response response = new Response();
        HashMap<String, Resource> resourceHashMap = null;

        String start = timeProvider.getTimestampFormat();
        String end = null;
        Date version = timeProvider.getSqlDate();
        try {
            resourceHashMap = extractionWikipedia.extractResource(externalDiseaseLinkList);
            if (resourceHashMap!=null) {
                response.setResponseCode(StatusHttpEnum.OK.getClave());
                response.setResponseMessage(StatusHttpEnum.OK.getDescripcion());
            }else{
                response.setResponseCode(ApiErrorEnum.RESOURCES_NOT_FOUND.getKey());
                response.setResponseMessage(ApiErrorEnum.RESOURCES_NOT_FOUND.getDescription());
            }
        }catch (Exception e){
            response.setResponseCode(ApiErrorEnum.INTERNAL_SERVER_ERROR.getKey());
            response.setResponseMessage(ApiErrorEnum.INTERNAL_SERVER_ERROR.getDescription());
        }
        response.setResourceHashMap(resourceHashMap);
        response.setStart_time(start);
        end = timeProvider.getTimestampFormat();
        response.setEnd_time(end);

        System.out.println("Inicio:" + start + " | Termino: " + end);

        return response;
    }

    public boolean extractionReport(List<XmlLink> externalDiseaseLinkList) throws Exception {
        boolean res = false;
        String inicio = timeProvider.getTimestampFormat();
        extractionWikipedia.extractionReport(externalDiseaseLinkList);
        System.out.println("Inicio:" + inicio + " | Termino: " +timeProvider.getTimestampFormat());

        return res;



//        boolean res = false;
//        String inicio = utilDate.getTime();
//        Date version = utilDate.getSqlDate();
//        //List<XmlLink> externalDiseaseLinkList = populateDbNative.getDiseaseLinkListFromDBPedia(version);
//
//        //if (externalDiseaseLinkList!=null) {
//            populateDbNative.populateResource(null);
//            populateDbNative.populateSemanticTypes();
//            populateDbNative.populate(null, version);
//            res = true;
//        //}else{
//        //    System.out.println("ERROR disease album");
//        //}
//        System.out.println("Inicio:" + inicio + " | Termino: " +utilDate.getTime());
//
//        return res;
    }

    public void checkCodes() throws Exception {
        String inicio = timeProvider.getTime();
        extractionWikipedia.extract(null);
        extractionWikipedia.extractResource(null);
        System.out.println("Inicio:" + inicio + " | Termino: " +timeProvider.getTime());
    }

    public void checkLinks() throws Exception {
        extractionWikipedia.checkWikiPages();
    }

}
