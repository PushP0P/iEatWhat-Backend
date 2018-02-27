package Models;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Collections;
import java.util.Set;

@Entity
public class User {
    @Id
    private String id_token;
    private Set<String> review_ids;
    private Set<String> category_tags;

    public void User() {

    }

    public User(String id_token, Set<String> review_ids, Set<String> category_tags) {
        this.id_token = id_token;
        this.review_ids = review_ids;
        this.category_tags = category_tags;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public Set<String> getReview_ids() {
        return review_ids;
    }

    public void setReview_ids(Set<String> review_ids) {
        this.review_ids = review_ids;
    }

    public Set<String> getCategory_tags() {
        return category_tags;
    }

    public void setCategory_tags(Set<String> category_tags) {
        this.category_tags = category_tags;
    }

    public static void addOrUpdate(Session session, String id_token) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Set<String> reviews = Collections.emptySet();
        Set<String> categories = Collections.emptySet();
        User user = new User(id_token, reviews, categories);
        session.saveOrUpdate(user);
        transaction.commit();
    }

    public static void destroyByTokenId(Session session, String id_token) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.find(User.class, id_token);
        session.remove(user);
        transaction.commit();
    }

    public static void insertReviewId(Session session, String id_token, String review_id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.find(User.class, id_token);
        Set<String> review_ids = user.getReview_ids();
        review_ids.add(review_id);
        user.setReview_ids(review_ids);
        session.save(user);
        transaction.commit();
    }

    public static void removeReviewId(Session session, String id_token, String review_id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.find(User.class, id_token);
        Set<String> review_ids = user.getReview_ids();
        review_ids.remove(review_id);
        user.setReview_ids(review_ids);
        session.save(user);
        transaction.commit();
    }

    public static void removeCategoryTag(Session session, String id_token, String category_tag) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.find(User.class, id_token);
        Set<String> category_tags = user.getCategory_tags();
        category_tags.remove(category_tag);
        user.setReview_ids(category_tags);
        session.save(user);
        transaction.commit();
    }

    public static void insertCategoryTag(Session session, String id_token, String category_tag) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.find(User.class, id_token);
        Set<String> category_tags = user.getCategory_tags();
        category_tags.add(category_tag);
        user.setReview_ids(category_tags);
        session.save(user);
        transaction.commit();
    }

}
