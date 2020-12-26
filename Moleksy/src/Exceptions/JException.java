package Exceptions;

import javax.swing.*;

public class JException extends JFrame {
    public JException(String mes){
        super("Exception");
        JTextField message1 = new JTextField("Please, don't try continue working");
        JTextField message2 = new JTextField(mes);
        this.add(message1);
        this.add(message2);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
}
