package app.gui;

import javax.swing.*;
import java.awt.*;

public abstract class Window {
    protected JFrame window = new JFrame();

    protected JPanel panel;
    protected JLabel label; //if you plan to change the text of the label, make a new one
    protected JButton button;
    // text fields should be unique, so not initialized here
    // otherwise it would be very difficult to obtain the values from them
    // for basic things like buttons and labels, this one variable can be reused
    // just make sure to add it to the window/panel before reinitializing it

    protected Font largeFont = new Font("Apple Casual",Font.BOLD, 60);
    protected Font smallFont = new Font("Apple Casual",Font.BOLD, 30);

    protected Window() {
        window.setSize(1280, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(new Color(241, 250, 238));
        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
    }
}
