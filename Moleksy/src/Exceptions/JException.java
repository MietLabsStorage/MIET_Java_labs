package Exceptions;

import javax.swing.*;

/**
 * show exception messages
 */
public class JException{
    /**
     * show message as show message dialog
     * @param mes exception message
     */
    public JException(String mes){
        JOptionPane.showMessageDialog(null, mes + "\nпожалуйста, перезапустите приложение");
    }
}
