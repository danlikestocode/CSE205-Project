package app.gui;

import app.database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PendingOrdersWindow extends Window{

    ChoiceHandler choiceHandler = new ChoiceHandler();

    public PendingOrdersWindow(){
        super();

        // Panel that holds the title and the buttons
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Pending Orders");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100)); //Basically Padding
        panel.add(label);

        // Catalog Button
        button = new JButton("Catalog");
        // button.setHorizontalAlignment(0);
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Catalog");
        panel.add(button);

        window.add(panel);

        // BUTTONS
//        panel = new JPanel();
//        panel.setBackground(new Color(241, 250, 238));

        // Logout Button
        button = new JButton("Logout");
        // button.setHorizontalAlignment(0);
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Logout");
        panel.add(button);

        window.add(panel);

        button = new JButton("Register");
        // button.setHorizontalAlignment(0);
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Register");
        panel.add(button);

        window.add(panel);

        product(234543532, 3.5);
        product(1234324,4.8);

        window.setVisible(true);
    }

    public void product(int orderId, double costOfProduct){
        panel = new JPanel();
        panel.setBackground(new Color(211,211,211));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));   //Adds a border around each product

        int orderID = orderId;
        label = new JLabel("Order ID:"+ orderID);
        label.setForeground(Color.BLACK);
        label.setFont(smallFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20)); //Basically Padding

        panel.add(label);

        double cost = costOfProduct;
        label = new JLabel("Cost: $" + cost);
        label.setForeground(Color.BLACK);
        label.setFont(smallFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); //Basically Padding
        panel.add(label);

        // Catalog Button
        button = new JButton("Approve");
        // button.setHorizontalAlignment(0);
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Approve");
        panel.add(button);

        window.add(panel);
    }
    //Add a button to pending order
    private class ChoiceHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            switch (choice){
                case "Catalog":
                    window.dispose();
                    new EmployeeCatalogWindow();
                    break;
                case "Logout":
                    window.dispose();
                    new LoginWindow();
                    break;
                case "Register":
                    window.dispose();
                    new RegisterWindow(User.getDesignation());
                case "Checkout":
                    window.dispose();

            }
        }
    }
}
