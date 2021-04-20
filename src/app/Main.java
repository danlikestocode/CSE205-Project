package app;
import app.database.*;

public class Main {
    public static void main(String[] args) {
        app.database.Database.databaseconnect();
        int a = app.database.Database.getInt("test.test","test");
        System.out.println(a);
        String b = app.database.Database.getString("test.test","test2");
        System.out.println(b);
        System.out.println("YES: On P.C");
       String username = "UNCATCHABLE";
       String usersdt = "users";
       String usernameColumn = "usernames";
       
       app.database.Database.searchForString(usersdt, usernameColumn, username);
       System.out.println(app.database.Database.returnCurrentString(usernameColumn));
       
    }
}