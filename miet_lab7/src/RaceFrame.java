import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class RaceFrame {
    public static JFrame getRaceFrame() {
        return raceFrame;
    }

    private static JFrame raceFrame;
    private static RaceButton[] races;
    private static JPanel panel;

    private static int raceWidth = 100;
    private static int raceHeight = 30;
    private static Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA};
    private static String[] colorsName = new String[]{"red","blue","green","yellow","magenta"};

    public static final int frameWidth = 1000;
    public static final int indent = 150;

    public static void init(int raceAmount){
        raceFrame = new JFrame("Race");
        raceFrame.setSize(frameWidth,raceAmount*raceHeight + 5*(raceAmount-1) + 3*(raceHeight+5));
        panel = new JPanel();
        panel.setLayout(null);
        races = new RaceButton[raceAmount];
        for(int i = 0; i < races.length; i++){
            races[i] = new RaceButton(colorsName[i],10,(raceHeight+5)*(i+1),raceWidth,raceHeight,colors[i]);
            panel.add(races[i].getButton());
            races[i].start();
        }
        panel.add(new Finish());
        raceFrame.setContentPane(panel);
        raceFrame.setVisible(true);
        //raceFrame.setResizable(false);
        raceFrame.setLocation(200,200);
        raceFrame.setDefaultCloseOperation(raceFrame.EXIT_ON_CLOSE);
    }
}

class Finish extends JComponent{

    public Finish(){
        super();
        setBounds(RaceFrame.frameWidth-RaceFrame.indent,0,RaceFrame.frameWidth-RaceFrame.indent,RaceFrame.getRaceFrame().getHeight());
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.drawLine(0, 0, 0, RaceFrame.getRaceFrame().getHeight());
    }
}
