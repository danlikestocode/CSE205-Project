package app.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.database.Database;

public class CatalogWindow extends Window {
    ChoiceHandler choiceHandler = new ChoiceHandler();

    JTextField search;
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




        // Cart
        button = new JButton("Cart");
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Cart");
        panel.add(button);

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
        search = new JTextField();
        search.setFont(smallFont);
        search.setPreferredSize(new Dimension(400, 50));
        search.setMaximumSize(new Dimension(400, 50));
        search.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(search);

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
                    window.add(showProductPanels(search.getText()));
                    window.setVisible(true);
                break;
                case "Cart":
                    window.dispose();
                    new CartWindow();
                    break;
                case "Logout":
                    window.dispose();
                    new LoginWindow();
                    break;
            }
        }
    }

    private class ProductAddButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id =  Integer.parseInt(e.getActionCommand());
        }
    }

    private class ProductSubtractButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id =  Integer.parseInt(e.getActionCommand());
        }
    }

    private JPanel showProductPanels(String search) {
        productPanels = new JPanel(new FlowLayout());
        productPanels.setBackground(new Color(241, 250, 238));
        JPanel productPanel = new JPanel();
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

        if (productPanels.getComponents().length == 0) {
            label = new JLabel("No products found.");
            label.setFont(smallFont);
            productPanels.add(label);
        }

        return productPanels;

    }

}
