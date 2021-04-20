package app.GUI;

public class VisibilityCheck {
    MainPanel appUI;

    public VisibilityCheck(MainPanel userInterface){
        appUI = userInterface;
    }

    public void showLoginScreen(){
        appUI.loginPanel.setVisible(true);
    }
}
