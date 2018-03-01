package Workers;

import Workers.FoodData;

import USDA.Description;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class FoodSearch {

    public static Document getReport(String ndbno) throws Exception {
        return FoodData.getFullReportUSDA(ndbno);
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
//                Review Review = new Review("Rock", "Bar", "Bas", "Bat", "Metal");
//                DBManager.insert(Review);
//                Review.setAuthor_id("New Author");
//                DBManager.insert(Review);