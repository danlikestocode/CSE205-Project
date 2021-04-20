package app.GUI;
import app.Main;

import javax.swing.*;
import java.awt.*;
public class MainPanel {
    JFrame window;
    JPanel loginPanel,buttonPanel;
    JLabel loginTitle;
    JButton loginButton,registerButton;
    JTextArea mainTextArea;
    JTextField userName;
    JPasswordField passwordField = new JPasswordField(20);
    Font loginFont = new Font("Apple Casual",Font.BOLD, 60);
    Font buttonFont = new Font("Apple Casual",Font.BOLD, 30);

    public void createUI(Main.ChoiceHandler choiceHandler){

        //Window Frame
        window = new JFrame();
        window.setSize(1500,1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(new Color(241, 250, 238));
        window.setLayout(null);

        //Login Screen
        loginPanel = new JPanel();
        loginPanel.setBounds(200,200,1000,200);
        loginPanel.setBackground(new Color(241, 250, 238));
//        loginPanel.setBackground(Color.BLACK);
        loginTitle = new JLabel("CSE 205 Shopping Project");
        loginTitle.setForeground(Color.BLACK);
        loginTitle.setFont(loginFont);
        loginPanel.add(loginTitle);

//        userName = new JTextField();
//        userName.setColumns(40);
//        userName.setBounds(10,10,10,10);
//        userName.setBackground(new Color(241, 250, 238));
//        userName.setText("");
//        loginPanel.add(userName);
//        passwordField.setText("Test");

        buttonPanel = new JPanel();
        buttonPanel.setBounds(400,400,1000,200);
//        buttonPanel.setBackground(new Color(241, 250, 238));
        buttonPanel.setBackground(Color.BLACK);
        loginButton = new JButton("Login");
        loginButton.setSize(20,20);
        loginButton.setBackground(new Color(168, 218, 220));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(buttonFont);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(choiceHandler);
        loginButton.setActionCommand("Start");
        buttonPanel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setSize(20,20);
        registerButton.setBackground(new Color(168, 218, 220));
        registerButton.setForeground(Color.BLACK);
        registerButton.setFont(buttonFont);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(choiceHandler);
        registerButton.setActionCommand("Start");
        buttonPanel.add(registerButton);



        window.add(loginPanel);
        window.add(buttonPanel);

        window.setVisible(true);
    }
}
