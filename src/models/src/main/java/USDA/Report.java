package USDA;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Report {
    @Id
    private String ndbno;
//    @OneToOne(mappedBy = "report")
//    private Description description;
//    @OneToMany(mappedBy = "report")
//    private Set<Nutrient> nutrients;

    public Report(){}

    public Report(String ndbno, Description description, Set<Nutrient> nutrients){
        this.setNdbno(ndbno);
//        this.setDescription(description);
//        this.setNutrients(nutrients);
    }

    public Report(Session session, HashMap<String, Object> hashMap) {
 //       Transaction transaction = session.getTransaction();
 //       this.description = new Description().add(session, transaction, hashMap.get("desc"));
    }

    private void setNdbno(String ndbno) {
        this.ndbno = ndbno;
    }

//    private void setDescription(Description description) {
//        this.description = description;
//    }
//
//    private void setNutrients(Set<Nutrient> nutrients) {
//        this.nutrients = nutrients;
//    }

    public String getNdbno() {
        return ndbno;
    }

//    public Description getDescription() {
//        return description;
//    }
//
//    public Set<Nutrient> getNutrients() {
//        return nutrients;
//    }

    public static void add(Session session, Transaction transaction, String ndbno, Map<String, Object> desc, Set<Nutrient> nutrients ) {
//        transaction.begin();
//        Report report = new Report(ndbno, new Description(), new Set<Nutrient>()} {
//        })
//        Description.add(desc);

    }
}
