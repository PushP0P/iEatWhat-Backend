package UserManager;

import DBManager.DBManager;

import iEatWhat.IEW_User;
import iEatWhat.Review;

import org.hibernate.Session;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Users {
    // Return Response here? With try catch.
    public static String newUser(HashMap payload) {
        Session session = DBManager.getSession();
        Set<String> reviews = Collections.emptySet();
        Set<String> categories = Collections.emptySet();
        return IEW_User.add(session, payload.get("idToken").toString(), reviews, categories);
    }

    public static void removeUser(Map<String, String> payload) {
        Session session = DBManager.getSession();
        String idToken = payload.get("idToken");
        IEW_User.destroyByTokenId(session, idToken);
    }

    public static String newReview(Map<String, Object> payload) {
        Session session = DBManager.getSession();
        String idToken = (String) payload.get("idToken");
        String reviewText = (String) payload.get("text");
        int stars = (int) payload.get("stars");
        String foodItemNDBNo = (String) payload.get("ndbo");
        String reviewId = (String) Review.add(session, idToken, reviewText, foodItemNDBNo, stars);
        IEW_User.insertReviewId(session, idToken, reviewId);
        return reviewId;
    }

    public static int updateReview(Map<String, Object> payload) {
        Session session = DBManager.getSession();
        String idToken = (String) payload.get("idToken");
        IEW_User user = IEW_User.getUser(session, idToken);
        String reviewId = (String) payload.get("reviewId");
        if (user.review_ids.contains(reviewId)) {
            String reviewText = (String) payload.get("text");
            int stars = (int) payload.get("stars");
            Review.updateTextAndStars(session, reviewId, reviewText, stars);
            return 1;
        }
        return -1;
    }

    public static int removeReview(Map<String, String> payload) {
        Session session = DBManager.getSession();
        String idToken = (String) payload.get("idToken");
        String reviewId = (String) payload.get("reviewId");
        int result = Review.destroyByReviewId(session, reviewId);
        if (result > 0) {
            IEW_User.removeReviewId(session, idToken, reviewId);
        }
        return result;
    }


}
