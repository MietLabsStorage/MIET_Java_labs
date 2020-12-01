import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum RunButton{
    Start,
    Pause,
    Continue
}

public class Grapher {


    public static JFrame getMainFrame() {
        return mainFrame;
    }
    public static JButton getButtonRun() {
        return buttonRun;
    }
    public static TimeArea getTextClock() {
        return textClock;
    }

    private static JFrame mainFrame;
    private static JButton buttonRun;
    private static JButton buttonReset;
    private static TimeArea textClock;

    /**
     * init grapher
     */
    public static void init(){
        MyTimer.setStatus(RunStatus.Reset);
        MyTimer.showTime();

        mainFrame = new JFrame("Timer");
        buttonRun = new JButton(RunButton.Start.name());
        buttonReset = new JButton("Reset");
        textClock = new TimeArea(MyTimer.showTime());
        textClock.setBorder(null);
        JTextArea[] textTemp = new JTextArea[2];

        textClock.start();

        for(int i = 0; i < textTemp.length ; i++){
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
        mainPanel.setBackground(Color.BLACK);
        mainFrame.add(mainPanel);

        //mainFrame.setSize(200,300);
        mainFrame.setLocation(600,200);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * run duttons listener
     */
    public static void run(){

        buttonRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buttonRun.getText().equals(RunButton.Start.name()) || buttonRun.getText().equals(RunButton.Continue.name())){
                    buttonRun.setText(RunButton.Pause.name());
                    MyTimer.setStatus(RunStatus.Run);
                    textClock.setEditable(false);
                }
                else if(buttonRun.getText().equals(RunButton.Pause.name())){
                    buttonRun.setText(RunButton.Continue.name());
                    MyTimer.setStatus(RunStatus.Pause);
                }
            }
        });

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonRun.setText(RunButton.Start.name());
                MyTimer.setStatus(RunStatus.Reset);
                textClock.setEditable(true);
            }
        });
    }

}
