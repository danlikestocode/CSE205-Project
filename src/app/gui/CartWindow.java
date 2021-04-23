package app.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartWindow extends Window{
    ChoiceHandler choiceHandler = new ChoiceHandler();

    public CartWindow(){
        super();

        // Panel that holds the title and the buttons
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        // TITLE
        label = new JLabel("Shopping Cart");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100)); //Basically Padding
        panel.add(label);

        // Catalog Button
        button = new JButton("Catalog");
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

        itemQuantity("Apple", 4);
        itemQuantity("Pear", 2);
        itemQuantity("Soda", 1);

        // BUTTONS
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        // Login Button
        button = new JButton("Checkout");
        // button.setHorizontalAlignment(0);
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("Checkout");
        panel.add(button);

        window.add(panel);

        window.setVisible(true);    //Sets it visible
    }

    //Method to add items into the gui
    public void itemQuantity(String name, int quantity){
        // Item
        panel = new JPanel();
        panel.setBackground(new Color(211,211,211));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        String item = name;
        label = new JLabel("Item: " + item);
        label.setFont(smallFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); //Basically Padding
        panel.add(label);
        window.add(panel);

        //Quantity
        int amount = quantity;
        label = new JLabel("Quantity: " + amount);
        label.setFont(smallFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); //Basically Padding
        panel.add(label);
        window.add(panel);

        // Add to item Button
        button = new JButton("+1");
        button.setSize(10, 10);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("addItem");
        panel.add(button);

        // Subtract to item Button
        button = new JButton("-1");
        button.setSize(10, 10);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand("subtractItem");
        panel.add(button);

        window.add(panel);
    }

    private class ChoiceHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            switch (choice){
                case "Catalog":
                    window.dispose();
                    new CatalogWindow();
                    break;
                case "Logout":
                    window.dispose();
                    new LoginWindow();
                    break;
                case "Checkout":
                    //window.dispose();
                    //new PendingOrdersWindow();
                    //for now this won't do anything
                    // to access pending orders, log in as a manager
            }
        }
    }
}
