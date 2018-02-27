package USDA;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;

@Entity
public class Nutrient {
    @Id
//    @ManyToOne
    public String nutrient_id;
    @Column
    public String nutrient;

    public Nutrient() {

    }

    public Nutrient(String nutrient_id, String nutrient) {
        this.nutrient_id = nutrient_id;
        this.nutrient = nutrient;
    }

    public static void addOrUpdate(Session session, String nutrient_id, String nutrientName) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Nutrient nutrient = new Nutrient(nutrient_id, nutrientName);
        session.saveOrUpdate(nutrient);
        transaction.commit();
    }

    public static Nutrient retrieveById(Session session, String nutrient_id) {
        return session.find(Nutrient.class, nutrient_id);
    }
}
