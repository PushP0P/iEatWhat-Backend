package Workers;

import DBManager.DBManager;
import USDA.Description;
import USDA.Report;

import Utilities.HTTPSRequest;
import Utilities.URLBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.Session;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FoodData {
    private static ObjectMapper om =  new ObjectMapper();
    private static String USDAUrl = "https://api.nal.usda.gov/ndb";
    private static String apiKey = "JiiJJlr1FvAcye8lkFIJuy8dFjhZcP2x7PNBEcIQ";

    public static HashMap searchFoodUSDA(String searchTerms) throws IOException {
        String type = "search";
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("q", searchTerms);
        params.put("format", "xml");
        return om.readValue(HTTPSRequest.getRequest(new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, type) + URLBuilders.getParamsString(params))), HashMap.class);
    }

    public static Document getFullReportUSDA(String ndbno) throws IOException, ParserConfigurationException, SAXException {
        String type = "V2/reports";
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("ndbno", ndbno);
        params.put("type", "f");
        params.put("format", "xml");
        Document report = HTTPSRequest.getXMLRequest(new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, type) + URLBuilders.getParamsString(params)));
        report.getDocumentElement().normalize();
        System.out.println("Node Name \n" + report.getDocumentElement().getNodeName());
        return report;
    }

    public static Document getNutrientListUSDA() throws IOException, ParserConfigurationException, SAXException {
        String type = "list";
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("max", "300");
        params.put("lt", "n");
        params.put("format", "xml");
        Document list = HTTPSRequest.getXMLRequest(new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, type) + URLBuilders.getParamsString(params)));
        list.getDocumentElement().normalize();
        return list;
    }

    private Report hashMapToReport(HashMap<String,String> USDAData) throws Exception {
        System.out.println(USDAData.toString());
        return new Report();
    }

    public static Description retrieveDescription(String ndbno) throws ParserConfigurationException, SAXException, IOException {
        Session session = DBManager.getSession();
        Description description = Description.findDescription(session, ndbno);
        System.out.println("descNode \n" + om.writeValueAsString(description));
        if (description != null) {
            return description;
        }
        Document report = getFullReportUSDA(ndbno);
        Node descNode = report.getDocumentElement().getElementsByTagName("desc").item(0);
        Description.addOrUpdate(session, descNode);
        return Description.findDescription(session, ndbno);
    }

    public static void updateNutrientList() throws ParserConfigurationException, SAXException, IOException {
        Document results = getNutrientListUSDA();
        NodeList nutrients = results.getDocumentElement().getElementsByTagName("item");
        Session session = DBManager.getSession();
        for (int i = 0; i < nutrients.getLength(); i++) {
            Node id = nutrients.item(i).getChildNodes().item(0);
            Node name = nutrients.item(i).getChildNodes().item(1);
            id.normalize();
            name.normalize();
            System.out.println("Nutrient ID" + id.getNodeValue());
            System.out.println("Nutrient Name" + id.getNodeValue());
//         Nutrient.add(session, nutrients.item(i).getOwnerDocument());
        }
    }

    public static void retrieveNutrient(String nutrient_id) throws ParserConfigurationException, SAXException, IOException {

        updateNutrientList();

//        Session session = DBManager.getSession();
//        Nutrient nutrient = Nutrient.retrieveById(session, nutrient_id);
//        System.out.println("nutrientNode \n" + om.writeValueAsString(nutrient));
//        if (nutrient != null) {
//            return nutrient;
//        }
//
//        Nutrient.add(session, descNode);
//        return Nutrient.retrieveById(session, nutrient_id);
    }

}
