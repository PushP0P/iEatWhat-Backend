package USDA;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.Id;
import java.util.List;

public class SearchItem {
    @Id
    String ndbo;
    String name;
    String group;
    
    public SearchItem() {
        
    }
    
    public SearchItem(String ndbo, String name, String group) {
        this.ndbo = ndbo;
        this.name = name;
        this.group = group;
    }

    public String getNdbo() {
        return ndbo;
    }

    public void setNdbo(String ndbo) {
        this.ndbo = ndbo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public static String add(Session session, String ndbo, String name, String group) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        SearchItem searchItem = new SearchItem(ndbo, name, group);
        String result = (String) session.save(searchItem);
        transaction.commit();
        return  result;
    }

    public static List<SearchItem> findByFoodItemId(Session session, String food_item_id) {
        Query query = session.createQuery("from ShortReport where food_item_id = :food_item_id");
        query.setParameter("food_item_id", food_item_id);
        return query.getResultList();
    }
    
    public static int destroy(Session session, String ndbo) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Query query = session.createQuery("delete ShortReport where shortReport_id = :shortReport_id");
        query.setParameter("shortReport_id", ndbo);
        int result = query.executeUpdate();
        transaction.commit();
        return result;
    }

    public static void update(Session session, String ndbo, String name, String group) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        SearchItem searchItem = session.find(SearchItem.class, ndbo);
        searchItem.setGroup(group);
        searchItem.setName(name);
        session.update(searchItem);
        transaction.commit();
    }
}
