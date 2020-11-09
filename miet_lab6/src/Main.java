
import javax.swing.*;

import static java.lang.Thread.sleep;

/**
 * @author Max Myasikov PIN-34
 * @version lab2
 */
public class Main {

    public static void main(String[] args) throws Exception {

        JFrame.setDefaultLookAndFeelDecorated(true);
        /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Grapher.initAndRun();
            }
        });*/
        Grapher.init();
        while(true){
            Grapher.run();
        }

    }
}