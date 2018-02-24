package SearchManager;

import DBManager.DBManager;
import Models.Review;

public class FoodSearch {

    public FoodSearch() {
    }

    public static void searchFood() throws Exception {
        DBManager.insert(new Review("foo", "Bar", "Bas", "Bat", "Metal"));
    }

}
