package SearchManager;

import DBManager.DBManager;
import Models.Review;

public class FoodSearch {

    public FoodSearch() {
    }

    public static void searchFood() throws Exception {
        Review review = new Review("Rock", "Bar", "Bas", "Bat", "Metal");
        DBManager.insert(review);
        review.setAuthor_id("New Author");
        DBManager.insert(review);
    }

}
