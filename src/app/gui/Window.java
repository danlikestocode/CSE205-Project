package app.gui;

import javax.swing.*;
import java.awt.*;

public abstract class Window {
    protected JFrame window = new JFrame();

    protected JPanel panel;
    protected JLabel label;
    protected JButton button;

    protected Font largeFont = new Font("Apple Casual",Font.BOLD, 60);
    protected Font smallFont = new Font("Apple Casual",Font.BOLD, 30);

    public Window() {
        window.setSize(1280, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(new Color(241, 250, 238));
        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
    }
}
