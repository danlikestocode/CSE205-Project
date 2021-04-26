package app.gui;

import app.database.Database;
import app.database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderSuccessfulWindow extends Window{
    //Responds to button actions
    ButtonHandler buttonHandler = new ButtonHandler();

    //Makes the window
    public OrderSuccessfulWindow(){
        //Calls from the Window class
        super();

        //Canvas of the page
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

        //Successful text displayed
        label = new JLabel("Order Successful");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100)); //Basically Padding
        panel.add(label);

        //Back button added to go back to catalog
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

//        label = new JLabel("Your Cart: ");
//        label.setForeground(Color.BLACK);
//        label.setFont(smallFont);
//        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 500, 750)); //Basically Padding
//        label.setBackground(Color.BLACK);
//        window.add(label);

        //Prints the users total price
        label = new JLabel("Your Total: $" + Database.totalPrice );
        label.setForeground(Color.BLACK);
        label.setFont(smallFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); //Basically Padding
        window.add(label);

        window.setVisible(true);
    }
    //ActionListiner which will check to see if a button is being pushed and preform those actions
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice) {
                case "Catalog":
                window.dispose();
                new CatalogWindow();
                break;
            }
        }
    }

}
