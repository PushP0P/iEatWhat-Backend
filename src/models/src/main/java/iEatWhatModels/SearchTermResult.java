package iEatWhatModels;

import USDA.SearchItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.*;

@Entity
public class SearchTermResult {
    @Id @GeneratedValue
    public int Id;
    @Column
    public String term;
    @ManyToMany(mappedBy = "description_ndbno")
    Set<SearchItem> SearchItems;
    @Temporal(TemporalType.DATE)
    public Date updatedOn;


    public SearchTermResult() {}


    public SearchTermResult(String term, Set<SearchItem> SearchItems) {
        this.term = term;
        this.SearchItems = SearchItems;
        this.updatedOn = new Date(System.currentTimeMillis());
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Set<SearchItem> getSearchItems() {
        return SearchItems;
    }

    public void setSearchItems(Set<SearchItem> SearchItems) {
        this.SearchItems = SearchItems;
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

    public static SearchTermResult checkForCached(Session session, String searchTerm) throws NoResultException {
        Query query = session.createQuery("from SearchResult where searchTerm = :searchTerm");
        query.setParameter(":searchTerm", searchTerm);
        return (SearchTermResult) query.getResultList().get(0);
    }
}
