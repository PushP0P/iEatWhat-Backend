package Workers;

import DBManager.DBManager;
import USDA.Description;
import USDA.Report;

import Utilities.HTTPSRequest;
import Utilities.URLBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.Session;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("q", searchTerms);
        params.put("format", "xml");
        return om.readValue(HTTPSRequest.getRequest(new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, "search") + URLBuilders.getParamsString(params))), HashMap.class);
    }

    public static Document getFullReportUSDA(String ndbno) throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("ndbno", ndbno);
        params.put("type", "f");
        params.put("format", "xml");
        Document report = HTTPSRequest.getXMLRequest(new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, "V2/reports") + URLBuilders.getParamsString(params)));
        report.getDocumentElement().normalize();
        System.out.println("Node Name \n" + report.getDocumentElement().getNodeName());
        return report;
    }

    private Report hashMapToReport(HashMap<String,String> USDAData) throws Exception {
        System.out.println(USDAData.toString());
        return new Report();
    }

    public static Description retrieveDescription(String ndbno) throws ParserConfigurationException, SAXException, IOException {
        // test with desc
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
}
