package app.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginWindow extends Window {
    JPanel loginPanel,buttonPanel;
    JLabel loginTitle;
    JButton loginButton,registerButton;
    JTextField userName;
    JPasswordField passwordField = new JPasswordField(20);
    Font loginFont = new Font("Apple Casual",Font.BOLD, 60);
    Font buttonFont = new Font("Apple Casual",Font.BOLD, 30);

    ChoiceHandler choiceHandler = new ChoiceHandler();

    //Creates the Main Page which will later change when buttons are clicked
    public LoginWindow() {

        super();

        //==============Login Screen===========================

        //The Text that says "CSE 205 Shopping Project"
//        loginPanel.setBackground(Color.RED); //Used to see where on page
        loginTitle = new JLabel("CSE 205 Shopping Project");
        loginTitle.setForeground(Color.BLACK);
        loginTitle.setFont(loginFont);
        window.add(loginTitle);

        //Username TextField that is connected to the loginPanel
        userName = new JTextField("UserName", 20);
        userName.setFont(buttonFont);
        userName.setMaximumSize(new Dimension(400, 50));
        userName.setBorder(new LineBorder(Color.BLACK, 2));


        //Password is connected to the login panel
        passwordField = new JPasswordField("Password");
        passwordField.setFont(buttonFont);
        char passwordChar = passwordField.getEchoChar();
        passwordField.setEchoChar((char) 0);
        passwordField.setText("Password");
        passwordField.addFocusListener(new FocusListener() {    //Used to show the word password then disapears
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setText("");
                passwordField.setEchoChar(passwordChar);
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        passwordField.setMaximumSize(new Dimension(400, 50));
        passwordField.setColumns(20);
        passwordField.setBorder(new LineBorder(Color.BLACK, 2));

        //Adds the username and password fields to the login panel
        window.add(userName);
        window.add(passwordField);

        //Sets parts for the button panel and buttons
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(241, 250, 238));
//        buttonPanel.setBackground(Color.BLACK); //Used to see where on page

        //Login Button
        loginButton = new JButton("Login");
        loginButton.setSize(20, 20);
        loginButton.setBackground(new Color(168, 218, 220));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(buttonFont);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(choiceHandler);
        loginButton.setActionCommand("Login");
        buttonPanel.add(loginButton);

        //Register Button
        registerButton = new JButton("Register");
        registerButton.setSize(20, 20);
        registerButton.setBackground(new Color(168, 218, 220));
        registerButton.setForeground(Color.BLACK);
        registerButton.setFont(buttonFont);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(choiceHandler);
        registerButton.setActionCommand("Register");
        buttonPanel.add(registerButton);

        //Adds all the panels to the window
        //window.add(loginPanel);
        window.add(buttonPanel);

        //loginPanel.setVisible(true);
        buttonPanel.setVisible(true);


        window.setVisible(true);    //Sets it visible
    }

    private class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice){
                case "Login": /*when the user logs in*/
                break;
                case "Register":
                    window.dispose();
                    new RegisterWindow();
                break;
            }
        }
    }
}
