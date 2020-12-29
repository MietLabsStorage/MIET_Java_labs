import javax.swing.*;
import java.awt.*;

/**
 * @author Max Myasikov PIN-34
 */
public class Main {

    public static void main(String[] args) {

        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            MainFrame.init();
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainFrame.run();
                    Logs.writeMessage("MainFrame.run is listening");
                }
            });
        }
        catch (Exception e){
            System.out.println(e.toString());
            new Exceptions.JException(e.getMessage());
        }

    }
}