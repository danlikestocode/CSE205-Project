package app.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductWindow extends Window{

    ChoiceHandler choiceHandler = new ChoiceHandler();

    public ProductWindow(){

        super();

        //

    }

    private class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            //Changes with your choice with a switch statement
            switch (choice){
                case "":
                    break;
            }
        }
    }
}
