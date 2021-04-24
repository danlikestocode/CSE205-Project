package app.gui;

import app.database.Database;
import app.database.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeCatalogWindow extends Window{

    ButtonHandler buttonHandler = new ButtonHandler();

    JPanel productPanels;


    JTextField name, price, stock;
    public EmployeeCatalogWindow() {
        super();


        // HEADER
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        // TITLE
        label = new JLabel("Employee Catalog");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 100)); //Basically Padding
        panel.add(label);




        // Cart
        button = new JButton("Cart");
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(buttonHandler);
        button.setActionCommand("Cart");
        panel.add(button);

        if(User.getDesignation() != 0){
            button = new JButton("Pending Orders");
            button.setSize(20, 20);
            button.setBackground(new Color(168, 218, 220));
            button.setForeground(Color.BLACK);
            button.setFont(smallFont);
            button.setFocusPainted(false);
            button.addActionListener(buttonHandler);
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
        button.addActionListener(buttonHandler);
        button.setActionCommand("Logout");
        panel.add(button);

        window.add(panel);


        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Product Name:");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); //Basically Padding
        panel.add(label);

        name = new JTextField();
        name.setFont(smallFont);
        name.setPreferredSize(new Dimension(400, 50));
        name.setMaximumSize(new Dimension(400, 50));
        name.setBorder(new LineBorder(Color.BLACK, 2));
        name.addActionListener(buttonHandler);
        name.setActionCommand("Name");
        panel.add(name);

        window.add(panel);


        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Products Price:");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); //Basically Padding
        panel.add(label);

        price = new JTextField();
        price.setFont(smallFont);
        price.setPreferredSize(new Dimension(400, 50));
        price.setMaximumSize(new Dimension(400, 50));
        price.setBorder(new LineBorder(Color.BLACK, 2));
        price.addActionListener(buttonHandler);
        price.setActionCommand("Price");
        panel.add(price);

        window.add(panel);


        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Products Stock:");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); //Basically Padding
        panel.add(label);

        stock = new JTextField();
        stock.setFont(smallFont);
        stock.setPreferredSize(new Dimension(400, 50));
        stock.setMaximumSize(new Dimension(400, 50));
        stock.setBorder(new LineBorder(Color.BLACK, 2));
        stock.addActionListener(buttonHandler);
        stock.setActionCommand("Stock");
        panel.add(stock);

        window.add(panel);



    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice) {
                case "Cart":
                    window.dispose();
                    new CartWindow();
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

    private JPanel showProductPanels(String search) {
        // reinit the panel every time to start fresh
        productPanels = new JPanel(new FlowLayout());
        productPanels.setBackground(new Color(241, 250, 238));
        JPanel productPanel;
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

                button.setActionCommand(Integer.toString(rs.getInt("productid")));
                productPanel.add(button);


                name.setText(rs.getString("productName"));

                price.setText(rs.getString("productPrice"));

                stock.setText(rs.getString("productStock"));

                button = new JButton("-1");
                button.setSize(20, 20);
                button.setBackground(new Color(30, 30, 30));
                button.setForeground(Color.WHITE);
                button.setFont(smallFont);
                button.setFocusPainted(false);

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
