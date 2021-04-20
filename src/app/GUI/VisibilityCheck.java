package app.GUI;

public class VisibilityCheck {
    MainPanel appUI;

    //Constructor to set mainPanel by the users input
    public VisibilityCheck(MainPanel userInterface){
        appUI = userInterface;
    }

    //Sets the GUI Visible
    public void showLoginScreen(){
        appUI.loginPanel.setVisible(true);
    }
}
