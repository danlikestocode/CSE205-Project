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
        //initializes connection to the database
    	app.database.Database.databaseconnect();
    	
    	
        new Main(); //GUI is made here
    }

    public Main(){
        ui.createUI(choiceHandler);
        visibilityCheck.showLoginScreen();
    }

    public class ChoiceHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice){
                case "Login": visibilityCheck.showLoginScreen();break;
                case "Register": visibilityCheck.showRegisterScreen(); break;
            }
        }
    }
}