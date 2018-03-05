package Workers;

import USDA.Description;

import USDA.Report;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class FoodSearch {

    public static Report getReport(String ndbno) throws Exception {
        return FoodData.getFullReportUSDA(ndbno);
    }

    public static Description getDescription(String ndbno) throws IOException, SAXException, ParserConfigurationException {
        return FoodData.retrieveDescription(ndbno);
    }

    public static List getByFoodTerm(String searchTerms) throws IOException, SAXException, ParserConfigurationException {
        System.out.println("hit \n" + searchTerms);
//        Session session = DBManager.getSession();
//        try {
//            return SearchTermResult.retrieveByTerm(session, searchTerms);
//
//        } catch (NoResultException n) {
            return FoodData.searchFoodUSDA(searchTerms);
//        }
    }

    public static List getNutrientList() throws ParserConfigurationException, SAXException, IOException {
        return (List) FoodData.getNutrientListUSDA();
    }
}

//    List<Model> reviews = DBManager.find(new Review());
//        for (Model i: reviews) {
//                System.out.println(i.());
//                }
//
//                Review Review = new Review("Rock", "Bar", "Bas", "Bat", "Metal");
//                DBManager.insert(Review);
//                Review.setAuthor_id("New Author");
//                DBManager.insert(Review);