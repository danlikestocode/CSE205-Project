package app.gui;

import app.database.Database;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow extends Window {
    ButtonHandler buttonHandler = new ButtonHandler();

    JTextField usernameTextField, firstNameTextField, lastNameTextField, emailTextField, addressTextField;
    JPasswordField passwordField;
    JComboBox positionComboBox;

    JLabel errorLabel;

    //Creates the Main Page which will later change when buttons are clicked
    public RegisterWindow() {

        super();

        //==============Login Screen===========================
        // I wasn't sure which parts of the Login Screen affected the Register Screen,
        // so I copied everything that wasn't useless
        // -Ethan

        //Title: Register
        label = new JLabel("Register");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10)); //Basically Padding
        window.add(label);

        //Username
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Username: ");
        label.setFont(smallFont);
        panel.add(label);

        usernameTextField = new JTextField();
        usernameTextField.setFont(smallFont);
        usernameTextField.setPreferredSize(new Dimension(400, 50));
        usernameTextField.setMaximumSize(new Dimension(400, 50));
        usernameTextField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(usernameTextField);


        window.add(panel);

        // PASSWORD
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Password: ");
        label.setFont(smallFont);
        panel.add(label);

        passwordField = new JPasswordField();
        passwordField.setFont(smallFont);
        passwordField.setPreferredSize(new Dimension(400, 50));
        passwordField.setMaximumSize(new Dimension(400, 50));
        passwordField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(passwordField);

        window.add(panel);

        //First Name
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("First Name:");
        label.setFont(smallFont);
        panel.add(label);

        firstNameTextField = new JTextField();
        firstNameTextField.setFont(smallFont);
        firstNameTextField.setPreferredSize(new Dimension(400, 50));
        firstNameTextField.setMaximumSize(new Dimension(400, 50));
        firstNameTextField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(firstNameTextField);

        window.add(panel);

        //Last Name
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Last Name:");
        label.setFont(smallFont);
        panel.add(label);

        lastNameTextField = new JTextField();
        lastNameTextField.setFont(smallFont);
        lastNameTextField.setPreferredSize(new Dimension(400, 50));
        lastNameTextField.setMaximumSize(new Dimension(400, 50));
        lastNameTextField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(lastNameTextField);

        window.add(panel);

        //Email
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Email:         ");
        label.setFont(smallFont);
        panel.add(label);

        emailTextField = new JTextField();
        emailTextField.setFont(smallFont);
        emailTextField.setPreferredSize(new Dimension(400, 50));
        emailTextField.setMaximumSize(new Dimension(400, 50));
        emailTextField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(emailTextField);

        window.add(panel);


        //Email
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Address:       ");
        label.setFont(smallFont);
        panel.add(label);

        addressTextField = new JTextField();
        addressTextField.setFont(smallFont);
        addressTextField.setPreferredSize(new Dimension(400, 50));
        addressTextField.setMaximumSize(new Dimension(400, 50));
        addressTextField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(addressTextField);

        window.add(panel);


        //Diffrent Positions
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Position:    ");
        label.setFont(smallFont);
        panel.add(label);

        String position[] = {"Customer", "Employee", "Manager"};
        positionComboBox = new JComboBox(position);
        positionComboBox.setFont(smallFont);
        positionComboBox.setPreferredSize(new Dimension(200,50));
        positionComboBox.setMaximumSize(new Dimension(200, 50));
        positionComboBox.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(positionComboBox);

        window.add(panel);


        // Register Button
        button = new JButton("Register");
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(buttonHandler);
        button.setActionCommand("Register");
        panel.add(button);

        window.add(panel);

        // Error Label
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        errorLabel = new JLabel("Error: Username already exists");
        errorLabel.setFont(smallFont);
        errorLabel.setVisible(false);
        panel.add(errorLabel);


        window.add(panel);


        window.setVisible(true);

    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice){
                case "Register":

                    Boolean create = Database.createUser(usernameTextField.getText(), new String(passwordField.getPassword()),
                        emailTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(),
                        addressTextField.getText(), positionComboBox.getSelectedIndex());

                    System.out.println(create);

                    if (!create) {
                        //failed to create the user
                        //this occurs when a user with the same username already exists
                        errorLabel.setVisible(true);
                    } else {
                        window.dispose();
                        //new CatalogWindow();    //Sends them to catalog after registering
                        new LoginWindow();    //Or we could send them to the login screen
                    }

                break;
            }
        }
    }

}
