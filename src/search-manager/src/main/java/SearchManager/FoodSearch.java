package SearchManager;

import Workers.FoodData;
import USDA.Description;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class FoodSearch {

    public static Document getReport(String ndbno) throws Exception {
        Document report = FoodData.getFullReportUSDA(ndbno);
        return report;
    }

    public static Description getDescription(String ndbno) throws IOException, SAXException, ParserConfigurationException {
        return FoodData.retrieveDescription(ndbno);
    }

}

//
//    List<Model> reviews = DBManager.find(new Review());
//        for (Model i: reviews) {
//                System.out.println(i.());
//                }
//
//                Review review = new Review("Rock", "Bar", "Bas", "Bat", "Metal");
//                DBManager.insert(review);
//                review.setAuthor_id("New Author");
//                DBManager.insert(review);