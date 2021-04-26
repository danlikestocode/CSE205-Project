package app.gui;

import app.database.Database;
import app.database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PendingOrdersWindow extends Window{
    //Responds to button actions
    ChoiceHandler choiceHandler = new ChoiceHandler();
    ApproveHandler approveHandler = new ApproveHandler();

    //Internal Field creations to take in info
    JPanel productPanels;

    //Makes the window
    public PendingOrdersWindow(){
        //Calls from the Window class
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

        //Register button added to window
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

        window.add(showPurchasePanels());

        window.setVisible(true);
    }

    /*public void product(int orderId, double costOfProduct){
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
    }*/

    //Add a button to pending order
    private class ChoiceHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            switch (choice){
                //Sends user to the employee catalog
                case "Catalog":
                    window.dispose();
                    new EmployeeCatalogWindow();
                    break;

                //Sends the user back to the login window
                case "Logout":
                    window.dispose();
                    new LoginWindow();
                    break;

                //Sends the user to the register window
                case "Register":
                    window.dispose();
                    new RegisterWindow(User.getDesignation());
            }
        }
    }

    //Adds function to the approve button to allow a purchase
    private class ApproveHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Database.searchForInt("purchases", "ordernumber", Integer.parseInt(e.getActionCommand()));
            Database.updateBoolean("purchases", "completed", e.getActionCommand(), true, "ordernumber");

            window.remove(productPanels);
            window.add(showPurchasePanels());
            window.setVisible(true);
            window.repaint();
        }
    }

    //Shows the product of the page
    private JPanel showPurchasePanels() {
        // reinit the panel every time to start fresh
        productPanels = new JPanel();
        productPanels.setLayout(new BoxLayout(productPanels, BoxLayout.Y_AXIS));
        productPanels.setBackground(new Color(241, 250, 238));
        JPanel productPanel;
        ResultSet rs = Database.purchaseResultSet();
        while (true) {
            try {
                rs.next();

                // A product panel
                productPanel = new JPanel();
                productPanel.setBackground(new Color(200, 200, 200));

                label = new JLabel(Integer.toString(rs.getInt("ordernumber")));
                label.setFont(smallFont);
                productPanel.add(label);

                button = new JButton("Approve");
                button.setSize(60, 20);
                button.setBackground(new Color(30, 30, 30));
                button.setForeground(Color.WHITE);
                button.setFont(smallFont);
                button.setFocusPainted(false);
                button.addActionListener(approveHandler);
                button.setActionCommand(Integer.toString(rs.getInt("ordernumber")));
                productPanel.add(button);

                productPanels.add(productPanel);
            } catch (SQLException e) {
                break;
            }
        }
        //this is when nothing was added to it
        if (productPanels.getComponents().length == 0) {
            label = new JLabel("No purchases found.");
            label.setFont(smallFont);
            productPanels.add(label);
        }
        return productPanels;
    }

}
