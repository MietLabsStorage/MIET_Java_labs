import javax.swing.*;

public class WinFrame extends JFrame {
    private static int winPlace = 1;
    private JTextField msg;
    public WinFrame(RaceButton winner){
        super(winPlace+"");
        setBackground(winner.getButton().getBackground());
        msg = new JTextField(winner.getButton().getText() + " занял " + winPlace + "место");
        winPlace++;
        setContentPane(msg);

        pack();
        setVisible(true);
    }
}
