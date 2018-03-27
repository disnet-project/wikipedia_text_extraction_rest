package edu.ctb.upm.disnet.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ctb.upm.disnet.common.util.TimeProvider;
import edu.ctb.upm.disnet.component.ExtractionWikipedia;
import edu.ctb.upm.disnet.constants.Constants;
import edu.ctb.upm.disnet.enums.ApiErrorEnum;
import edu.ctb.upm.disnet.enums.StatusHttpEnum;
import edu.ctb.upm.disnet.model.Request;
import edu.ctb.upm.disnet.model.RequestJSON;
import edu.ctb.upm.disnet.model.Response;
import edu.ctb.upm.disnet.model.document_structure.Source;
import edu.ctb.upm.disnet.model.document_structure.code.Resource;
import edu.ctb.upm.disnet.model.xml.XmlLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
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

    private static final Logger logger = LoggerFactory.getLogger(ExtractService.class);

    @Autowired
    private TimeProvider timeProvider;
    @Autowired
    private ExtractionWikipedia extractionWikipedia;



    public Response extract(Request request) throws Exception {
        Response response = new Response();
        List<Source> sourceList = null;

        String start = timeProvider.getTimestampFormat();
        String end = null;
        String version = timeProvider.getNowFormatyyyyMMdd();
        try {
            sourceList = extractionWikipedia.extract(request.getWikipediaLinks());
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

        if (request.isJson()) {
            try {
                logger.info("Saving initiated Wikipedia texts");
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                writeJSONFile(gson.toJson(response), timeProvider.dateFormatyyyMMdd(version), Constants.RETRIEVAL_FILE_NAME);
                logger.info("Saving of finished Wikipedia texts");
            } catch (Exception e) {
                logger.error("Error while save json {} ", timeProvider.dateFormatyyyMMdd(version) + Constants.RETRIEVAL_FILE_NAME + Constants.DOT_JSON);
            }
        }

        System.out.println("Inicio:" + start + " | Termino: " + end);

        return response;
    }


    public Response extractJSON(RequestJSON request, String extractionType) throws Exception {
        Response response = new Response();

        String start = timeProvider.getTimestampFormat();
        String end = null;
        try {
            if (extractionType.equals(Constants.TEXTS)) {//System.out.println("entra texts: " + request.getSnapshot());
                response = readWikipediaRetrievalJSON(request.getSnapshot(), Constants.RETRIEVAL_FILE_NAME);
            }else{
                response = readWikipediaRetrievalJSON(request.getSnapshot(), Constants.RETRIEVAL_RESOURCES_FILE_NAME);
            }
        }catch (Exception e){
            response.setSources(new ArrayList<>());
            response.setResponseCode(ApiErrorEnum.INTERNAL_SERVER_ERROR.getKey());
            response.setResponseMessage(ApiErrorEnum.INTERNAL_SERVER_ERROR.getDescription());
        }
        response.setStart_time(start);
        end = timeProvider.getTimestampFormat();
        response.setEnd_time(end);

        System.out.println("Inicio:" + start + " | Termino: " + end);

        return response;
    }




    public Response extractResources(Request request) throws Exception {
        Response response = new Response();
        HashMap<String, Resource> resourceHashMap = null;

        String start = timeProvider.getTimestampFormat();
        String end = null;
        String version = timeProvider.getNowFormatyyyyMMdd();
        try {
            resourceHashMap = extractionWikipedia.extractResource(request.getWikipediaLinks());
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

        if (request.isJson()) {
            try {
                logger.info("Saving initiated Wikipedia resources");
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                writeJSONFile(gson.toJson(response), timeProvider.dateFormatyyyMMdd(version), Constants.RETRIEVAL_RESOURCES_FILE_NAME);
                logger.info("Saving of finished Wikipedia resources");
            } catch (Exception e) {
                logger.error("Error while save json {} ", timeProvider.dateFormatyyyMMdd(version) + Constants.RETRIEVAL_RESOURCES_FILE_NAME + Constants.DOT_JSON);
            }
        }

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


    /**
     * @param jsonBody
     * @param version
     * @throws IOException
     */
    public void writeJSONFile(String jsonBody, String version, String file_name) throws IOException {
        String fileName = version + file_name + Constants.DOT_JSON;
        String path = Constants.RETRIEVAL_HISTORY_FOLDER + fileName;
        InputStream in = getClass().getResourceAsStream(path);
        //BufferedReader bL = new BufferedReader(new InputStreamReader(in));
        File file = new File(path);
        BufferedWriter bW;

        if (!file.exists()){
            bW = new BufferedWriter(new FileWriter(file));
            bW.write(jsonBody);
            bW.close();
        }
    }


    /**
     * @param version
     * @return
     * @throws Exception
     */
    public Response readWikipediaRetrievalJSON(String version, String file_name) throws Exception {
        Response response = null;
        System.out.println("Read JSON!... ");
        Gson gson = new Gson();
        String fileName = version + file_name + Constants.DOT_JSON;
        String path = Constants.RETRIEVAL_HISTORY_FOLDER + fileName;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            response = gson.fromJson(br, Response.class);
        }catch (Exception e){
            System.out.println("Error to read or convert JSON!..." + e.getLocalizedMessage() + e.getMessage() + e.getCause());
        }

        /*for (edu.upm.midas.data.validation.metamap.model.response.Text text: resp.getTexts()) {
            System.out.println("TextId: " + text.getId() + " | Concepts: " + text.getConcepts().toString());
        }*/

        return response;
    }

}