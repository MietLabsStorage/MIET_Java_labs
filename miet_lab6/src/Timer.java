import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class Timer {
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

    public static String showTime() {
        switch (status) {
            case Run:
                if (beginTime == 0) beginTime = System.currentTimeMillis();
                pauseTime = (interval - (System.currentTimeMillis() - beginTime) / 1000) + "";
                if(pauseTime.equals("0")){
                    status = RunStatus.Reset;
                    alarm();
                }
                return pauseTime;

            case Pause:
                interval = Integer.parseInt(pauseTime);
                beginTime = 0;
                return pauseTime;

            case Reset:
                beginTime = 0;
                interval = 60;
                status = RunStatus.Wait;
                return interval + "";
        }
        return "click reset";
    }

    public static void getTime(){
        if(status.equals(RunStatus.Wait))
        try {
            interval = Integer.parseInt(Grapher.getTextClock().getText());
        }
        catch (Exception e){
            interval = 60;
        }
    }

    private static void alarm(){
        Grapher.getMainFrame().setVisible(false);
        JFrame alarmFrame = new JFrame("Alarm");
        alarmFrame.setSize(200,200);
        alarmFrame.setLocation(200,200);
        JButton alarmButton = new JButton("OK");
        JPanel alarmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        alarmPanel.setBackground(Color.red);
        alarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarmFrame.setVisible(false);
                Grapher.getMainFrame().setVisible(true);
            }
        });
        alarmPanel.add(alarmButton);
        alarmFrame.add(alarmPanel);
        alarmFrame.setVisible(true);
    }

}