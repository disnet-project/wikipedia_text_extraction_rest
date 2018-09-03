package edu.ctb.upm.midas.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ctb.upm.midas.common.util.TimeProvider;
import edu.ctb.upm.midas.component.ExtractionWikipedia;
import edu.ctb.upm.midas.constants.Constants;
import edu.ctb.upm.midas.enums.ApiErrorEnum;
import edu.ctb.upm.midas.enums.StatusHttpEnum;
import edu.ctb.upm.midas.model.Request;
import edu.ctb.upm.midas.model.RequestJSON;
import edu.ctb.upm.midas.model.Response;
import edu.ctb.upm.midas.model.ResponseJSON;
import edu.ctb.upm.midas.model.document_structure.Source;
import edu.ctb.upm.midas.model.document_structure.code.Resource;
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
        String snapshot = timeProvider.getNowFormatyyyyMMdd();

        response.setSources(sourceList);
        System.out.println(snapshot + " == " + request.getSnapshot());
        if (snapshot.equals(request.getSnapshot())) {
            try {
                sourceList = extractionWikipedia.extract(request.getWikipediaLinks(), request.getSnapshot());
                if (sourceList != null) {
                    response.setResponseCode(StatusHttpEnum.OK.getClave());
                    response.setResponseMessage(StatusHttpEnum.OK.getDescripcion());
                } else {
                    response.setResponseCode(ApiErrorEnum.RESOURCES_NOT_FOUND.getKey());
                    response.setResponseMessage(ApiErrorEnum.RESOURCES_NOT_FOUND.getDescription());
                }
            } catch (Exception e) {
                response.setSources(new ArrayList<>());
                response.setResponseCode(ApiErrorEnum.INTERNAL_SERVER_ERROR.getKey());
                response.setResponseMessage(ApiErrorEnum.INTERNAL_SERVER_ERROR.getDescription());
            }
        }else{
            response.setResponseCode(ApiErrorEnum.INVALID_SNAPSHOT.getKey());
            response.setResponseMessage(ApiErrorEnum.INVALID_SNAPSHOT.getDescription());
        }
        response.setSources(sourceList);
        response.setStart_time(start);
        end = timeProvider.getTimestampFormat();
        response.setEnd_time(end);

        if (request.isJson()) {
            try {
                logger.info("Saving initiated Wikipedia texts");
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                writeJSONFile(gson.toJson(response), snapshot /*timeProvider.dateFormatyyyMMdd(version)*/, Constants.RETRIEVAL_FILE_NAME);
                logger.info("Saving of finished Wikipedia texts");
            } catch (Exception e) {
                logger.error("Error while save json {} ", snapshot /*timeProvider.dateFormatyyyMMdd(version)*/ + Constants.RETRIEVAL_FILE_NAME + Constants.DOT_JSON);
            }
        }

        System.out.println("Inicio:" + start + " | Termino: " + end);

        return response;
    }


    public ResponseJSON extractJSON(RequestJSON request, String extractionType) throws Exception {
        ResponseJSON response = new ResponseJSON();

        String start = timeProvider.getTimestampFormat();
        String end = null;
        Gson gson = new Gson();
        gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            if (extractionType.equals(Constants.TEXTS)) {//System.out.println("entra texts: " + request.getSnapshot());
                response = readWikipediaRetrievalJSON(request.getSnapshot(), Constants.RETRIEVAL_FILE_NAME);
            }else{
                response = readWikipediaRetrievalJSON(request.getSnapshot(), Constants.RETRIEVAL_RESOURCES_FILE_NAME);
            }
//            System.out.println(gson.toJson(response));
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
//        String version = request.getSnapshot();
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
                writeJSONFile(gson.toJson(response), version /*timeProvider.dateFormatyyyMMdd(version)*/, Constants.RETRIEVAL_RESOURCES_FILE_NAME);
                logger.info("Saving of finished Wikipedia resources");
            } catch (Exception e) {
                logger.error("Error while save json {} ", version /*timeProvider.dateFormatyyyMMdd(version)*/ + Constants.RETRIEVAL_RESOURCES_FILE_NAME + Constants.DOT_JSON);
            }
        }

        System.out.println("Inicio:" + start + " | Termino: " + end);

        return response;
    }


    public boolean extractionReport(Request request) throws Exception {
        boolean res = false;
        String inicio = timeProvider.getTimestampFormat();
        extractionWikipedia.extractionReport(request.getWikipediaLinks(), request.getSnapshot());
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
        extractionWikipedia.extractionReport(null, "");
//        extractionWikipedia.extract(null, "");
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
        System.out.println(path);
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
    public ResponseJSON readWikipediaRetrievalJSON(String version, String file_name) throws Exception {
        ResponseJSON response = null;
        System.out.println("Read JSON!... ");
        Gson gson = new Gson();
        String fileName = version + file_name + Constants.DOT_JSON;
        String path = Constants.RETRIEVAL_HISTORY_FOLDER + fileName;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            response = gson.fromJson(br, ResponseJSON.class);
        }catch (Exception e){
            System.out.println("Error to read or convert JSON!..." + e.getLocalizedMessage() + e.getMessage() + e.getCause());
        }

        /*for (edu.upm.midas.data.validation.metamap.model.response.Text text: resp.getTexts()) {
            System.out.println("TextId: " + text.getId() + " | Concepts: " + text.getConcepts().toString());
        }*/

        return response;
    }

}
