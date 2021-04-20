package app;
import app.GUI.MainPanel;
import app.GUI.VisibilityCheck;

import java.awt.event.*;

public class Main {
    MainPanel ui = new MainPanel();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    VisibilityCheck visibilityCheck = new VisibilityCheck(ui);

    String nextPosition1, nextPosition2, nextPosition3, nextPosition4;
    public static void main(String[] args) {
        //intializes connection to the database
    	app.database.Database.databaseconnect();
        int a = app.database.Database.getInt("test.test","test");
        System.out.println(a);
        String b = app.database.Database.getString("test.test","test2");
        System.out.println(b);
        System.out.println("YES: On P.C");
       
        //testing
        app.database.Database.searchForString( "users","usernames" , "UNCATCHABLE");
        Integer[][] toPrint = app.database.Database.return2DArray("cart");
        System.out.println(toPrint[0][0]);
        System.out.println(toPrint[0][1]);
        System.out.println(toPrint[1][0]);
        System.out.println(toPrint[1][1]);
        boolean test = app.database.Database.createUser("'shopper'","'shopping'","'shopping.gmail.com'","'shop'","'per'","'Buyers'",2);
        System.out.println(test);
        
        new Main(); //GUI is made here
    }

    public Main(){
        ui.createUI(choiceHandler);
        visibilityCheck.showLoginScreen();
    }

    public class ChoiceHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            switch (choice){
                case "Login": visibilityCheck.showLoginScreen();break;
                case "Register": visibilityCheck.showRegisterScreen(); break;
            }
        }
    }
}