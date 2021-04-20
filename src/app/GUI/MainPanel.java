package app.GUI;
import app.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MainPanel {
    JFrame window;
    JPanel loginPanel,buttonPanel,registerPanel,registerTextPanel;
    JLabel loginTitle,registerTitle;
    JButton loginButton,registerButton;
    JTextField userName,fName,lName,email;
    JComboBox occupation;
    JPasswordField passwordField = new JPasswordField(20);
    Font loginFont = new Font("Apple Casual",Font.BOLD, 60);
    Font buttonFont = new Font("Apple Casual",Font.BOLD, 30);

    //Creates the Main Page which will later change when buttons are clicked
    public void createUI(Main.ChoiceHandler choiceHandler){

        //Window Frame
        window = new JFrame();
        window.setSize(1500,1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(new Color(241, 250, 238));
        window.setLayout(null);

        //==============Login Screen=============================

        //The Text that says "CSE 205 Shopping Project"
        loginPanel = new JPanel();
        loginPanel.setBounds(300,300,800,200);
        loginPanel.setBackground(new Color(241, 250, 238));
//        loginPanel.setBackground(Color.RED); //Used to see where on page
        loginTitle = new JLabel("CSE 205 Shopping Project");
        loginTitle.setForeground(Color.BLACK);
        loginTitle.setFont(loginFont);
        loginPanel.add(loginTitle);

        //Username TextField that is connected to the loginPanel
        userName = new JTextField("UserName",20);
        userName.setFont(buttonFont);
        userName.setPreferredSize(new Dimension(40,50));
        userName.setBorder(new LineBorder(Color.BLACK,2));


        //Password is connected to the login panel
        passwordField = new JPasswordField("Password");
        passwordField.setFont(buttonFont);
        char passwordChar = passwordField.getEchoChar();
        passwordField.setEchoChar((char)0);
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
        passwordField.setPreferredSize(new Dimension(40,50));
        passwordField.setColumns(20);
        passwordField.setBorder(new LineBorder(Color.BLACK,2));

        //Adds the username and password fields to the login panel
        loginPanel.add(userName);
        loginPanel.add(passwordField);

        //Sets parts for the button panel and buttons
        buttonPanel = new JPanel();
        buttonPanel.setBounds(500,500,400,100);
        buttonPanel.setBackground(new Color(241, 250, 238));
//        buttonPanel.setBackground(Color.BLACK); //Used to see where on page

        //Login Button
        loginButton = new JButton("Login");
        loginButton.setSize(20,20);
        loginButton.setBackground(new Color(168, 218, 220));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(buttonFont);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(choiceHandler);
        loginButton.setActionCommand("Login");
        buttonPanel.add(loginButton);

        //Register Button
        registerButton = new JButton("Register");
        registerButton.setSize(20,20);
        registerButton.setBackground(new Color(168, 218, 220));
        registerButton.setForeground(Color.BLACK);
        registerButton.setFont(buttonFont);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(choiceHandler);
        registerButton.setActionCommand("Register");
        buttonPanel.add(registerButton);

        //Adds all the panels to the window
        window.add(loginPanel);
        window.add(buttonPanel);



        //=====================Register Panel==================================
        registerPanel = new JPanel();
        registerPanel.setBounds(300,100,1000,100);
        registerPanel.setBackground(new Color(241, 250, 238));
//        registerPanel.setBackground(Color.RED); //Used to see where on page
        registerTitle = new JLabel("Register");
        registerTitle.setForeground(Color.BLACK);
        registerTitle.setFont(loginFont);
        registerPanel.add(registerTitle);

        //For all the textfields
        registerTextPanel= new JPanel();
        registerTextPanel.setBounds(300,200,1000,400);
        registerPanel.setBackground(new Color(241, 250, 238));
//        registerTextPanel.setBackground(Color.red);

        //User textfield
        userName = new JTextField("UserName",20);
        userName.setFont(buttonFont);
        userName.setPreferredSize(new Dimension(40,50));
        userName.setBorder(new LineBorder(Color.BLACK,2));

        //Password field
        passwordField = new JPasswordField("Password");
        passwordField.setFont(buttonFont);
        passwordField.setEchoChar((char)0);
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
        passwordField.setPreferredSize(new Dimension(40,50));
        passwordField.setColumns(20);
        passwordField.setBorder(new LineBorder(Color.BLACK,2));

        //First name textfield
        fName = new JTextField("First Name",20);
        fName.setFont(buttonFont);
        fName.setPreferredSize(new Dimension(40,50));
        fName.setBorder(new LineBorder(Color.BLACK,2));

        //Last name textfield
        lName = new JTextField("Last Name",20);
        lName.setFont(buttonFont);
        lName.setPreferredSize(new Dimension(40,50));
        lName.setBorder(new LineBorder(Color.BLACK,2));

        //Email textfield
        email = new JTextField("Email Address", 20);
        email.setFont(buttonFont);
        email.setPreferredSize(new Dimension(40,50));
        email.setBorder(new LineBorder(Color.BLACK,2));

        //Combobox textfield *Broken and dont know why
        String positions[] = {"Customer","Employee", "Manager"};
        occupation = new JComboBox(positions);
        occupation.setFont(buttonFont);
        occupation.setSize(40,50);
        occupation.setPreferredSize(new Dimension(40,50));


        //Adds them all to the panel
        registerTextPanel.add(userName);
        registerTextPanel.add(passwordField);
        registerTextPanel.add(fName);
        registerTextPanel.add(lName);
        registerTextPanel.add(email);
        registerTextPanel.add(occupation);

        //Add the registerText panel to the registerPanel
        registerPanel.add(registerTextPanel);

        //Added them to the window
        window.add(registerPanel);
        window.add(registerTextPanel);
        window.add(buttonPanel);

        //Also Buttons are being weird by poping up and going away

        window.setVisible(true);    //Sets it visible
    }
}
