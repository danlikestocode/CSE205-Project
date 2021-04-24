package app.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.database.Cart;
import app.database.Database;
import app.database.User;

public class CatalogWindow extends Window {
    ChoiceHandler choiceHandler = new ChoiceHandler();

    JTextField searchTextField;
    JPanel productPanels;


    public CatalogWindow() {
        super();

        // HEADER
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        // TITLE
        label = new JLabel("Catalog");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100)); //Basically Padding
        panel.add(label);


        if(User.getDesignation() != 0){
            button = new JButton("Pending Orders");
            button.setSize(20, 20);
            button.setBackground(new Color(168, 218, 220));
            button.setForeground(Color.BLACK);
            button.setFont(smallFont);
            button.setFocusPainted(false);
            button.addActionListener(choiceHandler);
            button.setActionCommand("PendingOrders");
            panel.add(button);

            window.add(panel);
        }
        else{

        }

        // Logout
        button = new JButton("Logout");
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Logout");
        panel.add(button);

        window.add(panel);


        // SEARCH
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        // Text Field
        searchTextField = new JTextField();
        searchTextField.setFont(smallFont);
        searchTextField.setPreferredSize(new Dimension(400, 50));
        searchTextField.setMaximumSize(new Dimension(400, 50));
        searchTextField.setBorder(new LineBorder(Color.BLACK, 2));
        searchTextField.addActionListener(choiceHandler);
        searchTextField.setActionCommand("Search");
        panel.add(searchTextField);

        // Button
        button = new JButton("Search");
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Search");
        panel.add(button);

        window.add(panel);


        // PRODUCT PANELS
        window.add(showProductPanels(""));

        window.getContentPane().setLayout(new FlowLayout());
        window.setVisible(true);
    }

    private class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice){
                //TODO
                case "Search":
                    window.remove(productPanels);
                    window.add(showProductPanels(searchTextField.getText()));
                    window.setVisible(true);
                    window.repaint();
                break;
                case "Logout":
                    window.dispose();
                    new LoginWindow();
                    break;
                case "PendingOrders":

                    window.dispose();
                    new PendingOrdersWindow();
                    break;
            }
        }
    }

    private class ProductAddButtonHandler implements ActionListener {
        // for + buttons
        public void actionPerformed(ActionEvent e) {
            // ad to cart 1
            Cart.addProduct(Integer.parseInt(e.getActionCommand()));
        }
    }

    private class ProductSubtractButtonHandler implements ActionListener {
        // for - buttons
        public void actionPerformed(ActionEvent e) {
            // remove from cart 1
            Cart.subtractProduct(Integer.parseInt(e.getActionCommand()));
        }
    }

    private JPanel showProductPanels(String search) {
        // reinit the panel every time to start fresh
        productPanels = new JPanel();
        productPanels.setLayout(new BoxLayout(productPanels, BoxLayout.Y_AXIS));
        productPanels.setBackground(new Color(241, 250, 238));
        JPanel productPanel;
        ProductAddButtonHandler addButtonHandler = new ProductAddButtonHandler();
        ProductSubtractButtonHandler subtractButtonHandler = new ProductSubtractButtonHandler();
        ResultSet rs = Database.productResultSet(search);
        while (true) {
            try {
                rs.next();

                // A product panel
                productPanel = new JPanel();
                productPanel.setBackground(new Color(200, 200, 200));

                label = new JLabel(rs.getString("productName"));
                label.setFont(smallFont);
                productPanel.add(label);

                button = new JButton("+1");
                button.setSize(20, 20);
                button.setBackground(new Color(30, 30, 30));
                button.setForeground(Color.WHITE);
                button.setFont(smallFont);
                button.setFocusPainted(false);
                button.addActionListener(addButtonHandler);
                button.setActionCommand(Integer.toString(rs.getInt("productid")));
                productPanel.add(button);

                button = new JButton("-1");
                button.setSize(20, 20);
                button.setBackground(new Color(30, 30, 30));
                button.setForeground(Color.WHITE);
                button.setFont(smallFont);
                button.setFocusPainted(false);
                button.addActionListener(subtractButtonHandler);
                button.setActionCommand(Integer.toString(rs.getInt("productid")));
                productPanel.add(button);

                productPanels.add(productPanel);




            } catch (SQLException e) {
                break;
            }
        }

        //this is when nothing was added to it
        if (productPanels.getComponents().length == 0) {
            label = new JLabel("No products found.");
            label.setFont(smallFont);
            productPanels.add(label);
        }

        return productPanels;

    }

}
