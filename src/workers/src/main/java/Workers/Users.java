package Workers;

import DBManager.DBManager;

import iEatWhatModels.IEW_User;
import iEatWhatModels.Review;

import org.hibernate.Session;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class Users {
    // Return Response here? With try catch.
    public static String newUser(HashMap payload) {
        Session session = DBManager.getSession();
        Set<String> reviews = Collections.emptySet();
        Set<String> categories = Collections.emptySet();
        boolean hasTwitter = (boolean) payload.get("hasTwitter");
        boolean hasGoogle = (boolean) payload.get("hasGoogle");
        boolean hasLocal = (boolean) payload.get("hasLocal");
        return IEW_User.add(session, payload.get("idToken").toString(), reviews, categories, hasTwitter, hasGoogle, hasLocal);
    }

    public static int updateUser(HashMap payload) {
        Session session = DBManager.getSession();
        String idToken = (String) payload.get("idToken");
        boolean hasTwitter = (boolean) payload.get("hasTwitter");
        boolean hasGoogle = (boolean) payload.get("hasGoogle");
        boolean hasLocal = (boolean) payload.get("hasLocal");
        return IEW_User.update(session, idToken, hasTwitter, hasGoogle, hasLocal);
    }

    public static int removeUser(HashMap payload) {
        Session session = DBManager.getSession();
        String idToken = (String) payload.get("idToken");
        return IEW_User.destroyByTokenId(session, idToken);
    }

    public static Object newReview(HashMap payload) {
        Session session = DBManager.getSession();
        String idToken = (String) payload.get("idToken");
        String reviewText = (String) payload.get("text");
        int stars = (int) payload.get("stars");
        String foodItemNDBNo = (String) payload.get("ndbo");
        String reviewId = Review.add(session, idToken, reviewText, foodItemNDBNo, stars);
        IEW_User.insertReviewId(session, idToken, reviewId);
        return reviewId;
    }

    public static Object updateReview(HashMap payload) {
        Session session = DBManager.getSession();
        String idToken = (String) payload.get("idToken");
        IEW_User user = IEW_User.getUser(session, idToken);
        String reviewId = (String) payload.get("reviewId");
        if (user.getReview_ids().contains(reviewId)) {
            String reviewText = (String) payload.get("text");
            int stars = (int) payload.get("stars");
            Review.updateTextAndStars(session, reviewId, reviewText, stars);
            return reviewId;
        }
        return -1;
    }

    public static int removeReview(HashMap payload) {
        Session session = DBManager.getSession();
        String idToken = (String) payload.get("idToken");
        String reviewId = (String) payload.get("reviewId");
        int result = Review.destroyByReviewId(session, reviewId);
        if (result > 0) {
            IEW_User.removeReviewId(session, idToken, reviewId);
        }
        return result;
    }

    public static int addCategory(HashMap payload) {
        Session session = DBManager.getSession();
        String idToken = (String) payload.get("idToken");
        String CategoryTag = (String) payload.get("CategoryTag");
        return IEW_User.insertCategoryTag(session, idToken, CategoryTag);
    }

    public static int removeCategory(HashMap payload) {
        Session session = DBManager.getSession();
        String idToken = (String) payload.get("idToken");
        String CategoryTag = (String) payload.get("CategoryTag");
        return IEW_User.removeCategoryTag(session, idToken, CategoryTag);
    }

    public static IEW_User getUser(HashMap payload) {
        String idToken = (String) payload.get("idToken");
        Session session = DBManager.getSession();
        return session.find(IEW_User.class, idToken);
    }
}