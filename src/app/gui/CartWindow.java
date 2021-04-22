package app.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

        // Item
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Item: ");
        label.setFont(smallFont);
        panel.add(label);
        window.add(panel);

        //Quantity
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Quantity: ");
        label.setFont(smallFont);
        panel.add(label);
        window.add(panel);

        // Item
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Item: ");
        label.setFont(smallFont);
        panel.add(label);
        window.add(panel);

        //Quantity
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Quantity: ");
        label.setFont(smallFont);
        panel.add(label);
        window.add(panel);

        // Item
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Item: ");
        label.setFont(smallFont);
        panel.add(label);
        window.add(panel);

        //Quantity
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        label = new JLabel("Quantity: ");
        label.setFont(smallFont);
        panel.add(label);
        window.add(panel);


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

    private class ChoiceHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            switch (choice){
                case "Catalog":
                        window.dispose();
                        new CatalogWindow();
                    break;
            }
        }
    }
}
