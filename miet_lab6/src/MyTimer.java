import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTimer {
    public static RunStatus getStatus() {
        return status;
    }

    public static void setStatus(RunStatus _status) {
        status = _status;
    }

    private static RunStatus status;
    private static long beginTime;
    private static int interval;
    private static String pauseTime;

    /**
     * show time depending on run status (run, pause, reset)
     * @return current rest time as str
     */
    public static String showTime() {
        switch (status) {
            case Run -> {
                if (beginTime == 0) beginTime = System.currentTimeMillis();
                pauseTime = (interval - (System.currentTimeMillis() - beginTime) / 1000) + "";
                if (pauseTime.equals("0")) {
                    status = RunStatus.Reset;
                    alarm();
                }
                return pauseTime;
            }
            case Pause -> {
                interval = Integer.parseInt(pauseTime);
                beginTime = 0;
                return pauseTime;
            }
            case Reset -> {
                beginTime = 0;
                interval = 10;
                status = RunStatus.Wait;
                return interval + "";
            }
        }
        return "click reset";
    }

    /**
     * set interval from textarea or set as 60 if run status is wait
     */
    public static void getTime(){
        if(status.equals(RunStatus.Wait))
        try {
            interval = Integer.parseInt(Grapher.getTextClock().getText());
        }
        catch (Exception e){
            interval = 10;
        }
    }

    /**
     * create alarm-frame when time is 0
     */
    private static void alarm(){
        Grapher.getMainFrame().setVisible(false);
        JFrame alarmFrame = new JFrame("Alarm");
        alarmFrame.setSize(200,200);
        alarmFrame.setLocation(Grapher.getMainFrame().getLocation().x, Grapher.getMainFrame().getLocation().y);
        JButton alarmButton = new JButton("OK");
        JPanel alarmPanel = new JPanel();
        alarmPanel.setBackground(Color.red);
        alarmPanel.setLayout(null);
        alarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarmFrame.setVisible(false);
                Grapher.getMainFrame().setVisible(true);
                Grapher.getButtonRun().setText(RunButton.Start.name());
                Grapher.getMainFrame().setLocation(alarmFrame.getLocation().x,alarmFrame.getLocation().y);
                Grapher.getTextClock().setEditable(true);
            }
        });
        alarmButton.setBounds(65,70,70,30);
        alarmPanel.add(alarmButton);
        alarmFrame.add(alarmPanel);
        alarmFrame.setVisible(true);
        alarmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}