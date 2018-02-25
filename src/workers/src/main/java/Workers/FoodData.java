package Workers;

//import HibernateManager.HibernateUtil;
//import Models.FoodItem;
import USDA.Report;
import Utilities.HTTPSRequest;
import Utilities.URLBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
        return HTTPSRequest.getXMLRequest(new URL(URLBuilders.buildBaseUSDAURL(USDAUrl, "V2/reports") + URLBuilders.getParamsString(params)));
    }

    private Report hashMapToReport(HashMap<String,String> USDAData) throws Exception {
        System.out.printf(USDAData.toString());
        return new Report();
    }

    public static void updateDBWithReport(Map<String, Object> report) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.getTransaction();
//
//        FoodItem.add(session, transaction,"1234", "foo123142", "Test", "lorem ipsum" );
    }
}
