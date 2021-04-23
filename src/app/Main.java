package app;
import app.gui.*;
import app.database.*;

public class Main {
	static Database database = new Database();
    String nextPosition1, nextPosition2, nextPosition3, nextPosition4;
    public static void main(String[] args) {
        //initializes connection to the database
    	System.out.println(database.updateString("users", "fname", "UNCATCHABLE","uncatchable"));
    	System.out.println(database.createProduct("Banana", 69, 6.50, "A not as good fruit"));
    	
        new LoginWindow(); // GUI
    }

}