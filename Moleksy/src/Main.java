import javax.swing.*;

/**
 * @author Max Myasikov PIN-34
 */
public class Main {

    public static void main(String[] args) {

        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            MainFrame.init();
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainFrame.run();
                }
            });
        }
        catch (Exception e){
            new Exceptions.JException(e.getMessage());
        }

    }
}