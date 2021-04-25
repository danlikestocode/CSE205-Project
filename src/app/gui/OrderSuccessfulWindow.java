package app.gui;

import app.database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderSuccessfulWindow extends Window{
    ButtonHandler buttonHandler = new ButtonHandler();
    public OrderSuccessfulWindow(){
        super();

        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        //Successful
        label = new JLabel("Order Successful");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100)); //Basically Padding
        panel.add(label);

        button = new JButton("Back");
        button.setSize(20, 20);
        button.setBackground(new Color(168, 218, 220));
        button.setForeground(Color.BLACK);
        button.setFont(smallFont);
        button.setFocusPainted(false);
        button.addActionListener(buttonHandler);
        button.setActionCommand("Catalog");
        panel.add(button);

        window.add(panel);

        label = new JLabel("Your Cart: ");
        label.setForeground(Color.BLACK);
        label.setFont(smallFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 500, 750)); //Basically Padding
        label.setBackground(Color.BLACK);
        window.add(label);

        label = new JLabel("Your Total: ");
        label.setForeground(Color.BLACK);
        label.setFont(smallFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); //Basically Padding
        window.add(label);

        window.setVisible(true);
    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice) {
                case "Catalog":
                window.dispose();
                new EmployeeCatalogWindow();
                break;
            }
        }
    }

}
