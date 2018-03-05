package USDA;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.*;

@Entity
public class NutrientList {
    @Id @GeneratedValue
    public int id;
    public int total;
    public String sr;
    @Embedded
    public Set<Nutrient> nutrients;
    @Temporal(TemporalType.TIMESTAMP)
    public Date updatedOn;

    public NutrientList() {

    }

    public NutrientList(int total, String sr, Set<Nutrient> nutrients) {
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

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public Set<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(Set<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    static public long updatedLastInMili(NutrientList nutrientList) {
        return nutrientList.getUpdatedOn().getTime();
    }

    static public int add(Session session, int total, String sr, Set<Nutrient> nutrients) {
        session.getTransaction().begin();
        NutrientList nutrientList = new NutrientList(total, sr, nutrients);
        int result = (int) session.save(nutrientList);
        session.getTransaction().commit();
        return result;
    }

    static public NutrientList getLatestList(Session session) throws NoResultException {
        List result  = session.createQuery("select n from NutrientList n where n.updatedOn >= all( select nn from NutrientList nn where nn.getId = n.getId )").list();
        NutrientList mostCurrent = null;
        for (int i = 0; i < result.size(); i++) {
            NutrientList nextList =  (NutrientList) result.iterator().next();
            if(mostCurrent == null) {
                mostCurrent = nextList;
            } else if (mostCurrent.getUpdatedOn().before(nextList.getUpdatedOn())) {
                mostCurrent = nextList;
            }
        }
        return mostCurrent;
    }
}

//    @SuppressWarnings("unchecked")
//    Map<String, Number> map = getMap();
//for (String s : map.keySet());
//        for (Number n : map.values());