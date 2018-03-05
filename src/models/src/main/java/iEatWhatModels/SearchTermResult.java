package iEatWhatModels;

import USDA.SearchItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
public class SearchTermResult {
    @Id
    private String term;
    @ManyToMany(mappedBy = "search_item_ndbno")
    Set<SearchItem> searchItems;
    @Temporal(TemporalType.DATE)
    public Date updatedOn;

    SearchTermResult() {}

    SearchTermResult(String term, Set<SearchItem> SearchItems) {
        this.term = term;
        this.searchItems = SearchItems;
        this.updatedOn = new Date(System.currentTimeMillis());
    }


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Set<SearchItem> getSearchItems() {
        return searchItems;
    }

    public void setSearchItems(Set<SearchItem> searchItems) {
        this.searchItems = searchItems;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public static int add(Session session, String term, List<SearchItem> searchItems) {
        Set<SearchItem> items = Collections.emptySet();
        items.addAll(searchItems);
        Transaction transaction = session.getTransaction();
        SearchTermResult searchTermResult = new SearchTermResult(term, items);
        int result = (int) session.save(searchTermResult);
        transaction.commit();
        return result;
    }

    public static SearchTermResult retrieveByTerm(Session session, String searchTerm) throws NoResultException {
//        Query query = session.createQuery("select s from SearchTermResult s where term = :term");
//        query.setParameter("term", searchTerm);
//        return (SearchTermResult) query.getResultList().get(0);
        return session.find(SearchTermResult.class, searchTerm);
    }
}
