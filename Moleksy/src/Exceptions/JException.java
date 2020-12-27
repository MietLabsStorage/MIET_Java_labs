package Exceptions;

import javax.swing.*;

public class JException{
    public JException(String mes){
        JOptionPane.showMessageDialog(null, mes + "\nпожалуйста, перезапустите приложение");
    }
}
