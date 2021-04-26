package app.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Flow;

import app.database.Cart;
import app.database.Database;
import app.database.User;

public class CatalogWindow extends Window {
    //Responds to button actions
    ChoiceHandler choiceHandler = new ChoiceHandler();

    //Internal Field creations to take in info
    JTextField searchTextField;
    JPanel productPanels;

    //Makes the window
    public CatalogWindow() {
        //Calls from the Window class
        super();
        //Tells what user and cart is being used
        Database.searchForString("users", "usernames", User.getUsername());
        Cart.loadCart(Database.returnArray("cart"));

        // HEADER: Canvas of the page
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        // TITLE: Adds the word catalog to the page
        label = new JLabel("Catalog");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100)); //Basically Padding
        panel.add(label);

        //Depending on the position of the user show or dont the pending orders button
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


        //Checkout button
        button = new JButton("Check-Out");
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Checkout");
        panel.add(button);

        window.add(panel);

        // Logout button
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


        // SEARCH: New Panel that is created
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        // Text Field: Adds a text field for the user to search
        searchTextField = new JTextField();
        searchTextField.setFont(smallFont);
        searchTextField.setPreferredSize(new Dimension(400, 50));
        searchTextField.setMaximumSize(new Dimension(400, 50));
        searchTextField.setBorder(new LineBorder(Color.BLACK, 2));
        searchTextField.addActionListener(choiceHandler);
        searchTextField.setActionCommand("Search");
        panel.add(searchTextField);

        // Button: Once done typing click button to search the database
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
//        window.add(showUserCart());

        window.getContentPane().setLayout(new FlowLayout());
        window.setVisible(true);
    }

    //ActionListiner which will check to see if a button is being pushed and preform those actions
    private class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice){
                //TODO
                //Use the search tree to find a product
                case "Search":
                    window.remove(productPanels);
                    window.add(showProductPanels(searchTextField.getText()));
                    window.setVisible(true);
                    window.repaint();
                break;

                //Logout from the app
                case "Logout":
                    window.dispose();
                    new LoginWindow();
                    break;

                //Check for pending orders
                case "PendingOrders":
                    window.dispose();
                    new PendingOrdersWindow();
                    break;

                //Check out from the catalog when done shopping
                case "Checkout":
                    Database.checkoutCart(User.getUsername());
                    window.dispose();
                    new OrderSuccessfulWindow();
            }
        }
    }

    private class ProductAddButtonHandler implements ActionListener {
        // for + buttons to add to the cart
        public void actionPerformed(ActionEvent e) {
            // ad to cart 1
            Cart.addProduct(Integer.parseInt(e.getActionCommand()));
            window.remove(productPanels);
            window.add(showProductPanels(searchTextField.getText()));
            window.setVisible(true);
            window.repaint();
            //we have to update the panel every time too
        }
    }


    private class ProductSubtractButtonHandler implements ActionListener {
        // for - buttons to subtract from the cart
        public void actionPerformed(ActionEvent e) {
            // remove from cart 1
            Cart.subtractProduct(Integer.parseInt(e.getActionCommand()));
            window.remove(productPanels);
            window.add(showProductPanels(searchTextField.getText()));
            window.setVisible(true);
            window.repaint();
            //we have to update the panel every time too
        }
    }

    //Used to display all products to the page
    private JPanel showProductPanels(String search) {
        // reinit the panel every time to start fresh
        productPanels = new JPanel();
        productPanels.setLayout(new BoxLayout(productPanels, BoxLayout.Y_AXIS));
        productPanels.setBackground(new Color(241, 250, 238));
        JPanel productPanel;

        //Adds those +, - buttons
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

                label = new JLabel("Stock: " + Integer.toString(rs.getInt("stock")));
                label.setFont(smallFont);
                productPanel.add(label);

                /* // just to make sure the array is large enough to handle this product id
                Cart.addProduct(rs.getInt("productid"));
                Cart.subtractProduct(rs.getInt("productid"));
                */

                Cart.updateLength(rs.getInt("productid"));

                label = new JLabel("    Cart: " + Cart.getProducts()[rs.getInt("productid")]);
                label.setFont(smallFont);
                productPanel.add(label);

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

    // DEPRECATED
//    private JPanel showUserCart() {
//
//        userCarts = new JPanel();
//        userCarts.setLayout(new BoxLayout(userCarts, BoxLayout.Y_AXIS));
//        userCarts.setBackground(new Color(241, 250, 238));
//        JPanel userCart;
//
//        userCart = new JPanel();
//        userCart.setBackground(new Color(200, 200, 200));
//
//        label = new JLabel("Cart");
//        label.setForeground(Color.BLACK);
//        label.setFont(largeFont);
//        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));
//        userCart.add(label);
//
//        JTextField text = new JTextField();
//        text.setText("random test");
//        userCart.add(text);
//
//        userCarts.add(userCart);
//
//       return userCarts;
//
//    }

}
