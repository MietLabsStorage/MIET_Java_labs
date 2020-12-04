import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MainFrame {
    //frame
    private static JFrame frame;
    public static JFrame getFrame() {
        return frame;
    }

    //menu frame content
    private static JTextField raceAmount;
    private static JPanel buttonsPanel;
    private static JButton[] raceStart;
    private static JPanel contentsPanel;

    //race frame content
    private static ArrayList<RaceButton> races;
    private static JButton RunButton = new JButton();
    private static JPanel panel;
    public static JPanel getPanel() {
        return panel;
    }
    public static JButton getRunButton() {
        return RunButton;
    }

    //stats
    private static int raceWidth = 100;
    private static int raceHeight = 30;
    private static Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA};
    private static String[] colorsName = new String[]{"red","blue","green","yellow","magenta"};
    public static final int frameWidth = 1000;
    public static final int indent = 150;


    /**
     * initialization
     */
    public static void initMainFrame(){
        Thread mainThread = new Thread();
        mainThread.start();
        frame = new JFrame("Гонка");

        raceAmount = new JTextField("Сколько кнопок в гонке?");
        raceAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        raceAmount.setEditable(false);

        buttonsPanel = new JPanel(new GridLayout(2,2));
        raceStart = new JButton[4];
        for (int i = 0; i < raceStart.length; i++){
            raceStart[i] = new JButton(i+2+"");
            buttonsPanel.add(raceStart[i]);
        }

        races = new ArrayList<>();
        RunButton = new JButton("Run");
        for(int i = 0; i < 5; i++){
            RaceButton tempRace = new RaceButton(colorsName[i],10,(raceHeight+5)*(i+1),raceWidth,raceHeight,colors[i]);
            tempRace.setFinish(frameWidth - indent);
            tempRace.setRedrawer(new Redrawer());
            races.add(tempRace);
        }

        contentsPanel = new JPanel(new GridLayout(2,1));
        contentsPanel.add(raceAmount);
        contentsPanel.add(buttonsPanel);

        frame.setContentPane(contentsPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(200,200);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    //second frame that call when in init frame click raceStart-button
    private static void initRaceFrame(int raceAmount){
        frame.setSize(frameWidth,raceAmount*raceHeight + 5*(raceAmount-1) + 3*(raceHeight+5));

        panel = new JPanel();
        panel.setLayout(null);

        for(int i = 0; i < raceAmount; i++){
            panel.add(races.get(i).getButton());
            races.get(i).start();
        }

        RunButton.setBounds(870,frame.getHeight()/3,100,frame.getHeight()/5);
        RunButton.setVisible(true);
        panel.add(RunButton);

        panel.add(new Finish());

        frame.setContentPane(panel);
        frame.setLocation(200,200);
    }

    /**
     * listeners for buttons
     */
    public static void runAndListen(){

        for (JButton button : raceStart){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initRaceFrame(Integer.parseInt(button.getText()));
                }
            });
        }

        RunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RaceButton.invertWait();
                if(RaceButton.isWait()){
                    RunButton.setText("Continue");
                }
                else{
                    RunButton.setText("Stop");
                }
            }
        });

        for(RaceButton race : races){
            race.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    race.invertRun();
                    race.incSpeed();
                }
            });
        }
    }
}

//draw finish-line
class Finish extends JComponent{

    public Finish(){
        super();
        setBounds(MainFrame.frameWidth-MainFrame.indent,0,MainFrame.frameWidth-MainFrame.indent, MainFrame.getFrame().getHeight());
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.drawLine(0, 0, 0, MainFrame.getFrame().getHeight());
    }
}

/**
 * class with method that will do in RaceButton (delegate-pattern)
 */
class Redrawer implements BackRedrawer{
    @Override
    public void redrawBack(Color color) {
        MainFrame.getPanel().setBackground(color);
        MainFrame.getRunButton().setText("reset");
    }
}