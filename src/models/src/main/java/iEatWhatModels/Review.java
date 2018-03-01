package iEatWhat;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Review {
    @Id @GeneratedValue
    private String review_id;
    // todo Look up indexing and mapping relations
    private String author_id;
    private String food_item_id;
    private String text;
    private int stars;

    public Review(){

    }

    public Review(String author_id, String text, String food_item_id, int stars) {
        this.author_id = author_id;
        this.text = text;
        this.food_item_id = food_item_id;
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFood_item_id() {
        return food_item_id;
    }

    public void setFood_item_id(String food_item_id) {
        this.food_item_id = food_item_id;
    }

    public static String add(Session session, String author_id, String text, String food_item_id, int stars) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Review Review = new Review(author_id, text, food_item_id, stars);
        String result = (String) session.save(Review);
        transaction.commit();
        return  result;
    }

    public static List<Review> findByFoodItemId(Session session, String food_item_id) {
        Query query = session.createQuery("from Review where food_item_id = :food_item_id");
        query.setParameter("food_item_id", food_item_id);
        return query.getResultList();
    }

    public static int destroyByReviewId(Session session, String review_id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Query query = session.createQuery("delete Review where review_id = :review_id");
        query.setParameter("review_id", review_id);
        int result = query.executeUpdate();
        transaction.commit();
        return result;
    }

    public static void updateTextAndStars(Session session, String review_id, String text, int stars) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Review review = session.find(Review.class, review_id);
        review.setText(text);
        review.setStars(stars);
        session.update(review);
        transaction.commit();
    }
}