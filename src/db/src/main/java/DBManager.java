import ratpack.handling.Context;

import javax.sql.DataSource;
import javax.xml.ws.handler.Handler;
import java.sql.*;


public class DBManager implements ratpack.handling.Handler{

    Context ctx;
    Connection c;

    public void createTable(String tableName, String columns[]) throws SQLException {
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
    public String[] readAllFromTable(String tableName) throws SQLException {
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

    public void insertIntoTable(String tableName, String[] data) throws SQLException {
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

    public void deleteAllFromTable(String tableName) throws SQLException {
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
