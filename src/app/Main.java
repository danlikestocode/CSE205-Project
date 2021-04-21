package app;
import app.gui.LoginWindow;

public class Main {

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