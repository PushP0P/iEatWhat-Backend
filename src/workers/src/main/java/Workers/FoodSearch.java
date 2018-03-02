package Workers;

import Models.ShortReport;
import Workers.FoodData;

import USDA.Description;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.asList;

public class FoodSearch {

    public static Document getReport(String ndbno) throws Exception {
        return FoodData.getFullReportUSDA(ndbno);
    }

    public static Description getDescription(String ndbno) throws IOException, SAXException, ParserConfigurationException {
        return FoodData.retrieveDescription(ndbno);
    }

    public static Set<ShortReport> getByFoodTerm(String searchTerms) throws IOException, SAXException, ParserConfigurationException {
        Document results = FoodData.searchFoodUSDA(searchTerms);
        Set<ShortReport> reports = new HashSet<ShortReport>();
        ShortReport shortReport;
        results.getDocumentElement().normalize();
        NodeList items = results.getElementsByTagName("item");
        for (int i = 0; i < items.getLength(); i++) {
            Element temp = (Element) items.item(i);
            String ndbno = temp.getElementsByTagName("ndbno").item(0).getTextContent();
            String foodGroup = temp.getElementsByTagName("group").item(0).getTextContent();
            String shortDesc = temp.getElementsByTagName("name").item(0).getTextContent();
            shortReport = new ShortReport(ndbno, foodGroup, shortDesc);
            reports.add(shortReport);
        }
        return reports;
    }
}

//
//    List<Model> reviews = DBManager.find(new Review());
//        for (Model i: reviews) {
//                System.out.println(i.());
//                }
//
//                Review Review = new Review("Rock", "Bar", "Bas", "Bat", "Metal");
//                DBManager.insert(Review);
//                Review.setAuthor_id("New Author");
//                DBManager.insert(Review);