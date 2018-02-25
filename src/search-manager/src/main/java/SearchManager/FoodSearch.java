package SearchManager;

import DBManager.DBManager;
import Workers.FoodData;
import USDA.Description;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FoodSearch {

    public static Node getReport(String ndbno) throws Exception {
        Document report = FoodData.getFullReportUSDA(ndbno);
        report.getDocumentElement().normalize();
        System.out.println("doc root" + report.getDocumentElement().getNodeName());

//        Session session = DBManager.getSession();
//        Transaction transaction = session.getTransaction();
//        Description.add(session, transaction, (HashMap) report.get("desc"));

        // Search for cached?

        // Then search USDA
        return report.getElementsByTagName("desc").item(0);
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