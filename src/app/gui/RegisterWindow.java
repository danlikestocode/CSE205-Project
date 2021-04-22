package app.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RegisterWindow extends Window {
    JPanel buttonPanel,registerPanel,registerTextPanel;
    JLabel registerTitle;
    JTextField userName,fName,lName,email;
    JComboBox occupation;
    JPasswordField passwordField = new JPasswordField(20);
    Font loginFont = new Font("Apple Casual",Font.BOLD, 60);
    Font buttonFont = new Font("Apple Casual",Font.BOLD, 30);

    ChoiceHandler choiceHandler = new ChoiceHandler();

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
        label.setBorder(BorderFactory.createEmptyBorder(30, 10, 50, 10)); //Basically Padding
        window.add(label);

        //Username
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

        //First Name
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("First Name ");
        label.setFont(smallFont);
        panel.add(label);

        textField = new JTextField();
        textField.setFont(smallFont);
        textField.setPreferredSize(new Dimension(400, 50));
        textField.setMaximumSize(new Dimension(400, 50));
        textField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(textField);

        window.add(panel);

        //Last Name
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Last Name ");
        label.setFont(smallFont);
        panel.add(label);

        textField = new JTextField();
        textField.setFont(smallFont);
        textField.setPreferredSize(new Dimension(400, 50));
        textField.setMaximumSize(new Dimension(400, 50));
        textField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(textField);

        window.add(panel);

        //Email
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Email");
        label.setFont(smallFont);
        panel.add(label);

        textField = new JTextField();
        textField.setFont(smallFont);
        textField.setPreferredSize(new Dimension(400, 50));
        textField.setMaximumSize(new Dimension(400, 50));
        textField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(textField);

        window.add(panel);


        window.setVisible(true);
    }

    private class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice){

            }
        }
    }
}
