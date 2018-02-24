package DBManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import ratpack.handling.Context;
import Models.*;
import javax.sql.DataSource;
import java.sql.*;
import java.io.*;


public class DBManager implements ratpack.handling.Handler {

    private Context ctx;
    private Connection c;
    private String[] tables;
    private String[] defaultColumns;
    public static int currentUniqeID = 1;

    private static final SessionFactory sessionFactory = buildSessionFactory();


    DBManager() throws SQLException {
//        tables = new String[] {"articles", "articleSections", "categories", "comments",
//                "foodItems", "userAccounts", "ingredients", "settings"};
//        defaultColumns = new String[] {"id", "int", "model", "longBlob"};
//        this.createSchema();
    }

    private void createSchema() throws SQLException {
        for (String x : tables) {
            this.createTable(x, defaultColumns);
        }
    }

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void insert(Model model) throws Exception {
        System.out.printf("In Insert");
        //Create a new hibernate session
        Session session = sessionFactory.openSession();
        //Begin a session transaction
        session.beginTransaction();
        //Save it to the session
        session.save(model);
        //Commit the change to the database
        session.getTransaction().commit();
        //Close the session
        session.close();
        System.out.printf("Finished Insert");

        /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(model);
        oos.flush();
        oos.close();
        bos.close();
        byte[] data = bos.toByteArray(); // Typo next line down 'unique'
        String dataString[] = { "id", Integer.toString(currentUniqeID), "model", toString(data)};
        this.insertIntoTable(name, dataString);*/
    }

    public void update(Model model) {

    }

    public Model lookUp(String type, int id) {
        Model model = new Model();
        return model;
    }

    private void createTable(String tableName, String columns[]) throws SQLException {
        int size = columns.length;
        String query = "CREATE TABLE IF NOT EXISTS " + tableName + " ( ";
        for (int i = 0; i < size; i++) {
            if (columns[i] == "string") {
                query += "varchar(255)";
            }
            else {
                query += columns[i];
            }
            if (((i + 1) % 2 == 0) && ((i + 1) != size)) {
                query += ",";
            }
            query += " ";
        }
        query += ")";
        System.out.println(query);
        Statement statement = c.createStatement();
        statement.executeUpdate(query);
        if (statement != null) {
            statement.close();
        }
    }

    private int getRowCountFromTable(String tableName) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + tableName + ";";
        System.out.println(query);
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        int count = rs.getInt(1);
        return count;
    }

    private String[] readAllFromTable(String tableName) throws SQLException {
        int numberOfRows = getRowCountFromTable(tableName);
        String query = "SELECT * FROM " + tableName + ";";
        System.out.println(query);
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();
        String[] results = new String[numberOfRows*numberOfColumns];
        int j = 0;
        while (rs.next()) {
            for (int i = 0; i < numberOfColumns; i++) {
                results[j++] = rs.getString(i + 1);
            }
        }
        /*for (int i = 0; i < j; i++) {
            System.out.println(results[i]);
        }*/
        if (statement != null) {
            statement.close();
        }
        return results;
    }

    //public void connect() {
    //  System.out.println("Opened database successfully");
    //}

    //public void disconnect() throws SQLException {
    //   if (c != null) {
    //        c.close();
    //        System.out.println("Closed database successfully");
    //    }
    //}

    private void insertIntoTable(String tableName, String[] data) throws SQLException {
        int size = data.length;
        String query = "INSERT INTO " + tableName + " VALUES ( ";
        for (int i = 0; i < size; i++) {
            query += "'" + data[i] + "' ";
            if (i + 1 == size) {
                query += ")";
            }
            else {
                query += ", ";
            }
        }
        System.out.println(query);
        Statement statement = c.createStatement();
        statement.executeUpdate(query);
        if (statement != null) {
            statement.close();
        }
    }

    private void deleteAllFromTable(String tableName) throws SQLException {
        String query = "DELETE FROM " + tableName + ';';
        System.out.println(query);
        Statement statement = c.createStatement();
        statement.executeUpdate(query);
        if (statement != null) {
            statement.close();
        }
    }

    @Override
    public void handle(Context ctx) throws Exception {
        this.ctx = ctx;
        c = ctx.get(DataSource.class).getConnection();
    }
}
