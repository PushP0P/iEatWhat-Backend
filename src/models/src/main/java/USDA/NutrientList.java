package USDA;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class NutrientList {
    @Id @GeneratedValue
    int id;
    int total;
    int sr;
    @Embedded
    Set<Nutrient> nutrients;
    @Temporal(TemporalType.TIMESTAMP)
    public Date updatedOn;

    public NutrientList() {

    }

    public NutrientList(int total, int sr, Set<Nutrient> nutrients) {
        this.total = total;
        this.sr = sr;
        this.nutrients = nutrients;

    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    public Set<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(Set<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    public int add(Session session, int total, int sr, Set<Nutrient> nutrients) {
        session.getTransaction().begin();
        NutrientList nutrientList = new NutrientList(total, sr, nutrients);
        int result = (int) session.save(nutrientList);
        session.getTransaction().commit();
        return result;
    }

    public NutrientList getList() {

    }
}
