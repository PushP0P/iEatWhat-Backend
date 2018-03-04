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
    @OneToOne(mappedBy = "report")
    private Description description;
    @OneToMany(mappedBy = "report")
    private Set<Nutrient> nutrients;

    public Report(){}

    public Report(String ndbno, Description description, Set<Nutrient> nutrients) {
        this.ndbno = ndbno;
        this.description = description;
        this.nutrients = nutrients;
    }

    public String getNdbno() {
        return ndbno;
    }

    public void setNdbno(String ndbno) {
        this.ndbno = ndbno;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Set<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(Set<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    public static String add(Session session, String ndbno, Description desc, Set<Nutrient> nutrients ) {
        session.getTransaction().begin();
        Report report = new Report(ndbno, desc, nutrients);
        return (String) session.save(report);
    }
}
