import javax.swing.*;

/**
 * @author Max Myasikov PIN-34
 * @version lab6
 */
public class Main {

    public static void main(String[] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        Grapher.init();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Grapher.run();
            }
        });

    }
}