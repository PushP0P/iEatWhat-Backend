package DBManager;

import USDA.*;

import iEatWhatModels.*;

import Models.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager {

    private static SessionFactory buildSessionFactory() {
//        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

        Map<String, Object> settings = new HashMap<>();

//        if (System.getenv("JDBC_DATABASE_URL") != null) {
//            System.out.println("Env.JDBC.URL" + System.getenv("JDBC_DATABASE_URL"));
//            settings.put(Environment.URL, System.getenv("JDBC_DATABASE_URL"));
//            settings.put(Environment.JPA_JDBC_URL, System.getenv("JDBC_DATABASE_URL"));
//        } else {
            settings.put(Environment.URL, "jdbc:postgresql://127.0.0.1:5432/testdb");
            settings.put(Environment.JPA_JDBC_URL, "jdbc:postgresql://127.0.0.1:5432/testdb");
//        }
        settings.put(Environment.DRIVER, "org.postgresql.Driver");
//                settings.put(Environment.USER, "postgres");
//                settings.put(Environment.PASS, "admin");
        settings.put(Environment.HBM2DDL_AUTO, "create");
        settings.put(Environment.SHOW_SQL, true);

        // HikariCP settings
        settings.put("hibernate.hikari.connectionTimeout", "20000");
        settings.put("hibernate.hikari.minimumIdle", "10");
        settings.put("hibernate.hikari.maximumPoolSize", "20");
        settings.put("hibernate.hikari.idleTimeout", "300000");
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(settings).configure("hibernate.cfg.xml").build();

        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static Session getSession() {
        return buildSessionFactory().openSession();
    }

    public static List<Model> find(Model model) {
        //System.out.printf("\nIn find function of DBManager: " + String.valueOf(model.getClass()) + "\n");
        Session session = buildSessionFactory().openSession();
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
        Session session = buildSessionFactory().openSession();
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
        Session session = buildSessionFactory().openSession();
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
