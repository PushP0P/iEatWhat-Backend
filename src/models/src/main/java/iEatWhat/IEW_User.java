package iEatWhat;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class IEW_User {
    @Id
    public String id_token;
    @ElementCollection
    public Set<String> category_tags;
    @ElementCollection
    public Set<String> review_ids;


    public IEW_User() {}

    public IEW_User(String id_token, Set<String> category_tags, Set<String> review_ids) {
        this.id_token = id_token;
        this.category_tags = category_tags;
        this.review_ids = review_ids;
    }

    public String getId_token() {
        return this.id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public Set<String> getCategory_tags() {
        return category_tags;
    }

    public void setCategory_tags(Set<String> category_tags) {
        this.category_tags = category_tags;
    }

    public Set<String> getReview_ids() {
        return review_ids;
    }

    public void setReview_ids(Set<String> review_ids) {
        this.review_ids = review_ids;
    }

    public static String add(Session session, String id_token, Set<String> reviews, Set<String> categories) {
        System.out.println("starting transaction \n" +  id_token);
        Transaction transaction = session.getTransaction();
        transaction.begin();
        System.out.println("transaction begun \n");
        IEW_User iewuser = new IEW_User(id_token, reviews, categories);
        System.out.println("saving \n" + iewuser.getId_token());
        String result = (String) session.save(iewuser);
        transaction.commit();
        return result;
    }

    public static void destroyByTokenId(Session session, String id_token) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User iewuser = session.find(IEW_User.class, id_token);
        session.remove(iewuser);
        transaction.commit();
    }

    public static void insertReviewId(Session session, String id_token, String review_id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User user = session.find(IEW_User.class, id_token);
        Set<String> review_ids = user.getReview_ids();
        review_ids.add(review_id);
        user.setReview_ids(review_ids);
        session.save(user);
        transaction.commit();
    }

    public static IEW_User getUser(Session session, String id_token) {
        return session.find(IEW_User.class, id_token);
    }

    public static void removeReviewId(Session session, String id_token, String review_id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User user = session.find(IEW_User.class, id_token);
        Set<String> review_ids = user.getReview_ids();
        review_ids.remove(review_id);
        user.setReview_ids(review_ids);
        session.save(user);
        transaction.commit();
    }

    public static void removeCategoryTag(Session session, String id_token, String category_tag) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User user = session.find(IEW_User.class, id_token);
        Set<String> category_tags = user.getCategory_tags();
        category_tags.remove(category_tag);
        user.setReview_ids(category_tags);
        session.save(user);
        transaction.commit();
    }

    public static void insertCategoryTag(Session session, String id_token, String category_tag) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User user = session.find(IEW_User.class, id_token);
        Set<String> category_tags = user.getCategory_tags();
        category_tags.add(category_tag);
        user.setReview_ids(category_tags);
        session.save(user);
        transaction.commit();
    }

}
