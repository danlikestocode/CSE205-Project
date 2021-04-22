package app.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatalogWindow extends Window {
    ChoiceHandler choiceHandler = new ChoiceHandler();

    public CatalogWindow() {
        super();


        // TITLE
        label = new JLabel("Catalog");
        label.setForeground(Color.BLACK);
        label.setFont(largeFont);
        window.add(label);


        // HEADER
        panel = new JPanel();
        panel.setBackground(new Color(241, 250, 238));

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
        textField = new JTextField();
        textField.setFont(smallFont);
        textField.setPreferredSize(new Dimension(400, 50));
        textField.setMaximumSize(new Dimension(400, 50));
        textField.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(textField);

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


        window.setVisible(true);
    }

    private class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice){
                //TODO
            }
        }
    }

}
