package app.gui;

import javax.swing.*;
import java.awt.*;

public abstract class Window {
    protected JFrame window = new JFrame();

    public Window() {
        window.setSize(1280, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(new Color(241, 250, 238));
        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
    }
}
