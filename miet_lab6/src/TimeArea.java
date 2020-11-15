import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * unlike JTextArea updates the Text value according to MyTimer
 */
public class TimeArea extends JTextArea implements ActionListener {

    private final Timer timer;

    public TimeArea(String text, int row, int column){
        super(text,row,column);
        timer = new Timer(100, this);
    }

    /**
     * start updatings
     */
    public void start() {
        timer.start();
    }

    /*public void stop() {
        timer.stop();
    }*/

    /**
     * overrided method
     * @param arg0 arg
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(!MyTimer.getStatus().equals(RunStatus.Wait)) Grapher.getTextClock().setText(MyTimer.showTime());
        else MyTimer.getTime();
    }

}
