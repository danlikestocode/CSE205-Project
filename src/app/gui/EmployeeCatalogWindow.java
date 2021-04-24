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
import java.util.ArrayList;

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


        // Pending Orders
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


        /* panel = new JPanel();
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

        window.add(panel); */

        window.add(showProductPanels(""));

        window.getContentPane().setLayout(new FlowLayout());

        window.setVisible(true);

    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice) {
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

    //for storing all text fields so their values can be obtained later
    private JTextField[] names = new JTextField[] {};
    private JTextField[] prices = new JTextField[] {};
    private JTextField[] stocks = new JTextField[] {};

    private class UpdateHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(e.getActionCommand());
            Database.updateString("products", "productname", Integer.toString(id), names[id].getText(), "productid");
            Database.updateInt("products", "stock", Integer.toString(id), Integer.parseInt(stocks[id].getText()), "productid");
            Database.updateDouble("products", "price", Integer.toString(id), Double.parseDouble(prices[id].getText()), "productid");

        }
    }

    private UpdateHandler updateHandler = new UpdateHandler();



    private JPanel showProductPanels(String search) {
        // reinit the panel every time to start fresh
        productPanels = new JPanel();
        productPanels.setLayout(new BoxLayout(productPanels, BoxLayout.Y_AXIS));
        productPanels.setBackground(new Color(241, 250, 238));
        JPanel productPanel;
        ResultSet rs = Database.productResultSet(search);
        while (true) {
            try {
                rs.next();

                int idInt = rs.getInt("productid");
                String idString = Integer.toString(idInt);

                // A product panel
                productPanel = new JPanel();
                productPanel.setBackground(new Color(200, 200, 200));


                panel = new JPanel();
                panel.setBackground(new Color(241, 250, 238));

                label = new JLabel("Name:");
                label.setForeground(Color.BLACK);
                label.setFont(smallFont);
                label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); //Basically Padding
                panel.add(label);



                name = new JTextField();
                name.setFont(smallFont);
                name.setPreferredSize(new Dimension(150, 50));
                name.setMaximumSize(new Dimension(400, 50));
                name.setBorder(new LineBorder(Color.BLACK, 2));
                panel.add(name);

                if (idInt >= names.length) {
                    JTextField[] temp = names;
                    names = new JTextField[idInt + 1];
                    for (int i = 0; i < temp.length; i++) {
                        names[i] = temp[i];
                    }
                }
                names[idInt] = name;

                productPanel.add(panel);


                panel = new JPanel();
                panel.setBackground(new Color(241, 250, 238));

                label = new JLabel("Price:");
                label.setForeground(Color.BLACK);
                label.setFont(smallFont);
                label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); //Basically Padding
                panel.add(label);


                price = new JTextField();
                price.setFont(smallFont);
                price.setPreferredSize(new Dimension(100, 50));
                price.setMaximumSize(new Dimension(400, 50));
                price.setBorder(new LineBorder(Color.BLACK, 2));
                panel.add(price);

                if (idInt >= prices.length) {
                    JTextField[] temp = prices;
                    prices = new JTextField[idInt + 1];
                    for (int i = 0; i < temp.length; i++) {
                        prices[i] = temp[i];
                    }
                }
                prices[idInt] = price;

                productPanel.add(panel);


                panel = new JPanel();
                panel.setBackground(new Color(241, 250, 238));

                label = new JLabel("Stock:");
                label.setForeground(Color.BLACK);
                label.setFont(smallFont);
                label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); //Basically Padding
                panel.add(label);

                stock = new JTextField();
                stock.setFont(smallFont);
                stock.setPreferredSize(new Dimension(100, 50));
                stock.setMaximumSize(new Dimension(400, 50));
                stock.setBorder(new LineBorder(Color.BLACK, 2));
                panel.add(stock);

                if (idInt >= stocks.length) {
                    JTextField[] temp = stocks;
                    stocks = new JTextField[idInt + 1];
                    for (int i = 0; i < temp.length; i++) {
                        stocks[i] = temp[i];
                    }
                }
                stocks[idInt] = stock;

                productPanel.add(panel);


                panel = new JPanel();
                panel.setBackground(new Color(241, 250, 238));

                button = new JButton("Update");
                button.setSize(20, 20);
                button.setBackground(new Color(168, 218, 220));
                button.setForeground(Color.BLACK);
                button.setFont(smallFont);
                button.setFocusPainted(false);
                button.addActionListener(updateHandler);
                button.setActionCommand(idString);
                panel.add(button);

                productPanel.add(panel);


                name.setText(rs.getString("productname"));
                price.setText(Double.toString(rs.getDouble("price")));
                stock.setText(Integer.toString(rs.getInt("stock")));

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
