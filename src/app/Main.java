package app;
import app.GUI.*;
import app.database.*;

public class Main {
	Database database = new Database();
    String nextPosition1, nextPosition2, nextPosition3, nextPosition4;
    public static void main(String[] args) {
        //initializes connection to the database
    	app.database.Database.databaseconnect();


        new Main(); //GUI is made here
    }

    public Main(){
        new LoginWindow();
    }

}