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

    private class UpdateHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(e.getActionCommand());
            //Database.searchForInt("products", "productid", id);
            //Database.updateString("products", "productname", id, )
        }
    }

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

                productPanel.add(panel);


                panel = new JPanel();
                panel.setBackground(new Color(241, 250, 238));

                button = new JButton("Update");
                button.setSize(20, 20);
                button.setBackground(new Color(168, 218, 220));
                button.setForeground(Color.BLACK);
                button.setFont(smallFont);
                button.setFocusPainted(false);
                button.addActionListener(buttonHandler);
                button.setActionCommand(Integer.toString(rs.getInt("productid")));
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
