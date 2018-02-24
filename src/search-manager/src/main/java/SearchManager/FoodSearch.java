package SearchManager;

import DBManager.DBManager;
import Models.Category;
import Models.Model;
import Models.Review;
import Workers.FoodData;

import java.util.List;

public class FoodSearch {

    public FoodSearch() {

    }

    public static String searchFood() throws Exception {
        /*Review review0 = new Review("Rock", "Bar", "Bas", "Bat", "Metal");
        Category category0 = new Category("Name", "Tag", "Desc", "Icon");
        DBManager.insert(review0);
        DBManager.insert(category0);
        String stringResult = "";
        List<Model> reviews = DBManager.find(new Review());
        for (Model i: reviews) {
            stringResult += i.toString();
            System.out.println(i.toString());
        }
        List<Model> categories = DBManager.find(new Category());
        for (Model i: categories) {
            stringResult += i.toString();
            System.out.println(i.toString());
        }*/
        String stringResult = FoodData.getFullReportUSDA("21272").toString();

        System.out.printf(FoodData.getFullReportUSDA("21272").toString());
        return FoodData.getFullReportUSDA("21272").toString();
    }

}
