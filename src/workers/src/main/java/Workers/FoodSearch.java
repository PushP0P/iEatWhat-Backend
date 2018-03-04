package Workers;

import DBManager.DBManager;

import USDA.Description;

import USDA.Report;
import iEatWhatModels.SearchTermResult;
import org.hibernate.Session;

import org.xml.sax.SAXException;

import javax.persistence.NoResultException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class FoodSearch {

    public static Report getReport(String ndbno) throws Exception {
        return FoodData.getFullReportUSDA(ndbno);
    }

    public static Description getDescription(String ndbno) throws IOException, SAXException, ParserConfigurationException {
        return FoodData.retrieveDescription(ndbno);
    }

    public static SearchTermResult getByFoodTerm(String searchTerms) throws IOException, SAXException, ParserConfigurationException {
        Session session = DBManager.getSession();
        try {
            return SearchTermResult.checkForCached(session, searchTerms);

        } catch (NoResultException n) {
            return FoodData.searchFoodUSDA(searchTerms);
        }
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