package app;
import app.database.*;

public class Main {
    public static void main(String[] args) {
        app.database.Database.databaseconnect();
        int a = app.database.Database.getInt("test.test","test");
        System.out.println(a);
        String b = app.database.Database.getString("test.test","test2");
        System.out.println(b);
        System.out.println("YES: On P.C!");
    }
}