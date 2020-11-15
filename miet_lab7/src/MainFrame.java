import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private static JFrame mainFrame;
    private static JTextField raceAmount;
    private static JPanel buttonsPanel;
    private static JButton[] raceStart;
    private static JPanel contentsPanel;

    public static void init(){
        mainFrame = new JFrame("Гонка");

        raceAmount = new JTextField("Сколько кнопок в гонке?");
        raceAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        raceAmount.setEditable(false);

        buttonsPanel = new JPanel(new GridLayout(2,2));
        raceStart = new JButton[4];
        for (int i = 0; i < raceStart.length; i++){
            raceStart[i] = new JButton(i+2+"");
            buttonsPanel.add(raceStart[i]);
        }
        contentsPanel = new JPanel(new GridLayout(2,1));
        contentsPanel.add(raceAmount);
        contentsPanel.add(buttonsPanel);

        mainFrame.setContentPane(contentsPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setLocation(200,200);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
    }

    public static void run(){
        for (JButton button : raceStart){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RaceFrame.init(Integer.parseInt(button.getText()));
                    mainFrame.setVisible(false);
                }
            });
        }
    }
}
