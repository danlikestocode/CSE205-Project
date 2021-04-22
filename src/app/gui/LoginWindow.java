package app.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends Window {
    ChoiceHandler choiceHandler = new ChoiceHandler();

    //Creates the Main Page which will later change when buttons are clicked
    public LoginWindow() {

        super();

        // TITLE
        label = new JLabel("CSE 205 Shopping Project");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        window.add(label);


        // USERNAME
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Username ");
        label.setFont(smallFont);
        panel.add(label);

        textField = new JTextField();
        textField.setFont(smallFont);
        textField.setPreferredSize(new Dimension(400, 50));
        textField.setMaximumSize(new Dimension(400, 50));
        textField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(textField);

        window.add(panel);

        // PASSWORD
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Password ");
        label.setFont(smallFont);
        panel.add(label);

        passwordField = new JPasswordField();
        passwordField.setFont(smallFont);
        passwordField.setPreferredSize(new Dimension(400, 50));
        passwordField.setMaximumSize(new Dimension(400, 50));
        passwordField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(passwordField);

        window.add(panel);

        // BUTTONS
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        // Login Button
        button = new JButton("Login");
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Login");
        panel.add(button);

        // Register Button
        button = new JButton("Register");
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Register");
        panel.add(button);

        window.add(panel);

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
