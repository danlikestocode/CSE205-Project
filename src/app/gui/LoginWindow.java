package app.gui;

import app.database.Cart;
import app.database.Database;
import app.database.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends Window {
    ChoiceHandler choiceHandler = new ChoiceHandler();

    JTextField usernameTextField;
    JPasswordField passwordField;

    JLabel errorLabel;

    public LoginWindow() {
        super();


        // TITLE
        label = new JLabel("CSE 205 Shopping Project");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(30, 10, 50, 10)); //Basically Padding
        window.add(label);


        // USERNAME
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Username ");
        label.setFont(smallFont);
        panel.add(label);

        usernameTextField = new JTextField();
        usernameTextField.setFont(smallFont);
        usernameTextField.setPreferredSize(new Dimension(400, 50));
        usernameTextField.setMaximumSize(new Dimension(400, 50));
        usernameTextField.setBorder(new LineBorder(Color.BLACK, 2));
        usernameTextField.addActionListener(choiceHandler); // pressing enter also attempts to log in
        usernameTextField.setActionCommand("Login");
        panel.add(usernameTextField);

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
        passwordField.addActionListener(choiceHandler);
        passwordField.setActionCommand("Login");
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

        // Error Label
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        errorLabel = new JLabel("Error: Incorrect password");
        errorLabel.setFont(smallFont);
        errorLabel.setVisible(false);
        panel.add(errorLabel);


        window.add(panel);


        window.setVisible(true);    //Sets it visible
    }

    private class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice){
                case "Login": /*when the user logs in*/

                    String password = "";
                    String username = usernameTextField.getText();

                    if (!usernameTextField.getText().equals("") && !new String(passwordField.getPassword()).equals("")) {
                        //If both the username and password boxes are filled
                        // this line below is giving a postgres error, idk why but it still works
                        if (Database.searchForString("users", "usernames", username)) {
                            password = Database.returnCurrentString("passwords");
                        }
                    } else {
                        errorLabel.setText("Error: Please enter a username and password");
                        errorLabel.setVisible(true);
                        break;
                    }

                    if (password.equals(new String(passwordField.getPassword()))) {
                        //If the database password is equal to the user input
                        int designation = Database.returnCurrentInt("designation");
                        //static user class updated username and cart
                        User.setUsername(username);
                        User.setDesignation(designation);

                        if (designation != 1 && designation != 2) { // the user is neither a mananager nor employee
                            //regular old customer, go to the catalog
                            window.dispose();
                            new CatalogWindow();


                            Database.searchForString("users", "usernames", User.getUsername());
                            Cart.loadCart(Database.returnArray("cart"));
                        } else { //employee / manager


                            // send them to the orders
                            window.dispose();
                            new PendingOrdersWindow();


                        }

                    } else {
                        errorLabel.setText("Error: Incorrect password");
                        errorLabel.setVisible(true);
                        break;
                    }

                break;
                case "Register":
                    window.dispose();
                    new RegisterWindow(0);
                break;
            }
        }
    }

}
