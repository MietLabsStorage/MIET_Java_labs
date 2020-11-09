import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

enum RunButton{
    Start,
    Pause,
    Continue
}

public class Grapher {

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    private static JFrame mainFrame;
    private static JButton buttonRun;
    private static JButton buttonReset;

    public static JTextArea getTextClock() {
        return textClock;
    }

    private static JTextArea textClock;
    private static JTextArea[] textTemp;

    public static void init(){
        Timer.setStatus(RunStatus.Reset);
        Timer.showTime();

        mainFrame = new JFrame("Timer");
        buttonRun = new JButton(RunButton.Start.name());
        buttonReset = new JButton("Reset");
        textClock = new JTextArea(Timer.showTime(),1,3);
        textTemp = new JTextArea[2];

        for(int i = 0; i < textTemp.length ;i++){
            textTemp[i] = new JTextArea("==========",1,3);
            textTemp[i].setFont(new Font("Courier", Font.PLAIN, 30));
            textTemp[i].setEditable(false);
        }
        textClock.setFont(new Font("Courier", Font.PLAIN, 30));

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel componentsPanel = new JPanel(new GridLayout(4,1));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));

        componentsPanel.add(textTemp[0]);
        componentsPanel.add(textClock);
        componentsPanel.add(textTemp[1]);
        buttonsPanel.add(buttonRun);
        buttonsPanel.add(buttonReset);
        componentsPanel.add(buttonsPanel);
        mainPanel.add(componentsPanel);

        mainFrame.add(mainPanel);

        //mainFrame.setSize(200,300);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void run(){
        if(!Timer.getStatus().equals(RunStatus.Wait)) textClock.setText(Timer.showTime());
        else Timer.getTime();
        buttonRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buttonRun.getText().equals(RunButton.Start.name()) || buttonRun.getText().equals(RunButton.Continue.name())){
                    buttonRun.setText(RunButton.Pause.name());
                    Timer.setStatus(RunStatus.Run);
                    textClock.setEditable(false);
                }
                else if(buttonRun.getText().equals(RunButton.Pause.name())){
                    buttonRun.setText(RunButton.Continue.name());
                    Timer.setStatus(RunStatus.Pause);
                }
            }
        });

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonRun.setText(RunButton.Start.name());
                Timer.setStatus(RunStatus.Reset);
                textClock.setEditable(true);
            }
        });
    }

}
