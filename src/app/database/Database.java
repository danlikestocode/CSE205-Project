package app.database;
import java.sql.*;
public class Database {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(
                    "jdbc:postgresql://" + Keys.address + "/usersdb", Keys.username, Keys.password);
            System.out.println("Connected to the database");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}