package USDA;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Nutrient {
    @Id
    private String nutrient_id;
    @Column
    private String derivation;
    private int dp;
    private String group;
    private String name;
    private String se;
    private String sourcecode;
    private String unit;
    private float value;

    public Nutrient() {

    }

    public Nutrient(String nutrient_id, String derivation,
                    int dp, String group, String name,
                    String se, String sourcecode,
                    String unit, float value) {
        this.nutrient_id = nutrient_id;
        this.derivation = derivation;
        this.dp = dp;
        this.group = group;
        this.name = name;
        this.se = se;
        this.sourcecode = sourcecode;
        this.unit = unit;
        this.value = value;
    }

    public static Nutrient addOrUpdate(Session session, String nutrient_id, String derivation,
                                       int dp, String group, String name,
                                       String se, String sourcecode,
                                       String unit, float value) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Nutrient nutrient = new Nutrient(nutrient_id, derivation, dp,  group, name,  se,  sourcecode, unit,  value);
        session.saveOrUpdate(nutrient);
        transaction.commit();
        return nutrient;
    }

    public static Nutrient retrieveById(Session session, String nutrient_id) throws NoResultException {
        return session.find(Nutrient.class, nutrient_id);
    }

    public static List getAll(Session session) throws NoResultException {
        Query query = session.createQuery("from Nutrient");
        return query.getResultList();
    }

    public static void updateWithInstance(Session session, Nutrient nutrient) {
        session.getTransaction().begin();
        session.update(nutrient);
        session.getTransaction().commit();
    }
}
