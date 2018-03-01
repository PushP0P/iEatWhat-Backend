package iEatWhat;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Column;
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
    @Column
    public boolean hasTwitter;
    @Column
    public boolean hasGoogle;
    @Column
    public boolean hasLocal;

    public IEW_User() {}

    public IEW_User(String id_token, Set<String> category_tags, Set<String> review_ids, boolean hasTwitter, boolean hasGoogle, boolean hasLocal) {
        this.id_token = id_token;
        this.category_tags = category_tags;
        this.review_ids = review_ids;
        this.hasTwitter = hasTwitter;
        this.hasGoogle = hasGoogle;
        this.hasLocal = hasLocal;
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

    public boolean isHasTwitter() {
        return hasTwitter;
    }

    public void setHasTwitter(boolean hasTwitter) {
        this.hasTwitter = hasTwitter;
    }

    public boolean isHasGoogle() {
        return hasGoogle;
    }

    public void setHasGoogle(boolean hasGoogle) {
        this.hasGoogle = hasGoogle;
    }

    public boolean isHasLocal() {
        return hasLocal;
    }

    public void setHasLocal(boolean hasLocal) {
        this.hasLocal = hasLocal;
    }

    public static String add(Session session, String id_token, Set<String> reviews, Set<String> categories, boolean hasTwitter, boolean hasGoogle, boolean hasLocal) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User iewUser = new IEW_User(id_token, reviews, categories, hasTwitter, hasGoogle, hasLocal);
        String result = (String) session.save(iewUser);
        transaction.commit();
        return result;
    }

    public static int update(Session session, String id_token, boolean hasTwitter, boolean hasGoogle, boolean hasLocal) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User iewUser = session.find(IEW_User.class, id_token);
        iewUser.setHasTwitter(hasTwitter);
        iewUser.setHasGoogle(hasGoogle);
        iewUser.setHasLocal(hasLocal);
        session.update(iewUser);
        transaction.commit();
        return 1;
    }

    public static int destroyByTokenId(Session session, String id_token) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User iewuser = session.find(IEW_User.class, id_token);
        session.remove(iewuser);
        transaction.commit();
        return 1;
    }

    public static int insertReviewId(Session session, String id_token, String review_id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User user = session.find(IEW_User.class, id_token);
        Set<String> review_ids = user.getReview_ids();
        review_ids.add(review_id);
        user.setReview_ids(review_ids);
        session.save(user);
        transaction.commit();
        return 1;
    }

    public static IEW_User getUser(Session session, String id_token) {
        return session.find(IEW_User.class, id_token);
    }

    public static int removeReviewId(Session session, String id_token, String review_id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User user = session.find(IEW_User.class, id_token);
        Set<String> review_ids = user.getReview_ids();
        review_ids.remove(review_id);
        user.setReview_ids(review_ids);
        session.save(user);
        transaction.commit();
        return 1;
    }

    public static int removeCategoryTag(Session session, String id_token, String category_tag) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User user = session.find(IEW_User.class, id_token);
        Set<String> category_tags = user.getCategory_tags();
        category_tags.remove(category_tag);
        user.setReview_ids(category_tags);
        session.save(user);
        transaction.commit();
        return 1;
    }

    public static int insertCategoryTag(Session session, String id_token, String category_tag) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        IEW_User user = session.find(IEW_User.class, id_token);
        Set<String> category_tags = user.getCategory_tags();
        category_tags.add(category_tag);
        user.setReview_ids(category_tags);
        session.save(user);
        transaction.commit();
        return 1;
    }
}
