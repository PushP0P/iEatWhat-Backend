package DBManager;

import USDA.Report;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import Models.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class DBManager {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static List<Model> find(Model model) {
        //System.out.printf("\nIn find function of DBManager: " + String.valueOf(model.getClass()) + "\n");
        Session session = sessionFactory.openSession();
        //Begin a session transaction
        session.beginTransaction();
        // Form a criteria
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Model> query = builder.createQuery(Model.class);
        Root<Model> root = query.from(Model.class);
        query.select(root);
        Query<Model> q = session.createQuery(query);
        List<Model> list = q.getResultList();
        //Commit the transaction
        session.getTransaction().commit();
        //Close the session
        session.close();
        return list;
    }

    public static void insert(Model model) throws Exception {
        System.out.printf("In Insert");
        //Create a new hibernate session
        Session session = sessionFactory.openSession();
        //Begin a session transaction
        session.beginTransaction();
        //Save it to the session
        session.saveOrUpdate(model);
        //Commit the change to the database
        session.getTransaction().commit();
        //Close the session
        session.close();
        System.out.printf("Finished Insert");
    }

    public static void insertUSDAReport(Report report) throws Exception {
        //Create a new hibernate session
        Session session = sessionFactory.openSession();
        //Begin a session transaction
        session.beginTransaction();
        //Save it to the session
        session.saveOrUpdate(report);
        //Commit the change to the database
        session.getTransaction().commit();
        //Close the session
        session.close();
    }
}
