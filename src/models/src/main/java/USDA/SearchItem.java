package USDA;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

public class SearchItem {
    @Id
    @ManyToMany
    public String search_item_ndbno;
    @Column
    public String name;
    @Column
    public String group;
    
    public SearchItem() {
        
    }
    
    public SearchItem(String search_item_ndbno, String name, String group) {
        this.search_item_ndbno = search_item_ndbno;
        this.name = name;
        this.group = group;
    }

    public String getSearch_item_ndbno() {
        return search_item_ndbno;
    }

    public void setSearch_item_ndbno(String search_item_ndbno) {
        this.search_item_ndbno = search_item_ndbno;
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

    public static int destroy(Session session, String ndbo) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Query query = session.createQuery("delete SearchItem where search_item_ndbono= :search_item_ndbno");
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
