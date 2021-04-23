package app;
import app.gui.*;
import app.database.*;

public class Main {
	static Database database = new Database();

	// what are these? they aren't being used anywhere
    //String nextPosition1, nextPosition2, nextPosition3, nextPosition4;

    public static void main(String[] args) {
        //initializes connection to the database

        // ---- for debugging ----
    	//System.out.println(database.updateString("users", "fname", "UNCATCHABLE","uncatchable"));
        //System.out.println(database.createProduct("Chips", 99, 0.30, "You cant stop eating them"));
    	//database.searchForString("products" , "productName" , "Banana");
    	//System.out.println(database.returnCurrentDouble("stock"));
    	
        new LoginWindow(); // GUI
    }

}