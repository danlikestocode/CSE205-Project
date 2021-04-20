package app.GUI;

public class VisibilityCheck {
    MainPanel LoginUI;

    //Constructor to set mainPanel by the users input
    public VisibilityCheck(MainPanel userInterface){
        LoginUI = userInterface;
    }


    //Sets the GUI Visible
    public void showLoginScreen(){

        LoginUI.loginPanel.setVisible(true);

        LoginUI.registerPanel.setVisible(false);
        LoginUI.registerTextPanel.setVisible(false);
    }

    public void showRegisterScreen(){
        LoginUI.loginPanel.setVisible(false);

        LoginUI.registerPanel.setVisible(true);
        LoginUI.registerTextPanel.setVisible(true);
    }
}
