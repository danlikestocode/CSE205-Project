package app.database;
import java.sql.*;
public class Database {

    public static void databaseconnect() {
        // TODO Auto-generated method stub
        Connection c = null;
        Statement s = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(
                    "jdbc:postgresql://" + IP.address + "/usersdb", "postgres", "qwerty");
            System.out.println("Connected to the database");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}