import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RaceButton extends Thread{

    public JButton getButton() {
        return button;
    }

    private static double speedCoef = 0.1;

    private int speed;
    private Thread thread;
        private JButton button;
    public RaceButton(String msg, int x, int y, int width, int height, Color color){
        button = new JButton(msg);
        button.setBounds(x,y,width,height);
        button.setBackground(color);
        speed = (new Random()).nextInt(7)+1;
    }

    private void step() {
        if (button.getBounds().x + button.getWidth() < RaceFrame.frameWidth - RaceFrame.indent) {
            speedCoef = (speedCoef > 1) ? 0.1 : speedCoef + 0.1;
            button.setBounds(button.getBounds().x + speed * (int) Math.round(speedCoef), button.getBounds().y, button.getBounds().width, button.getBounds().height);
        }
        //else{
        //    button.setVisible(false);
        //    new WinFrame(this);
        //}
    }

    public void run() {
        try {
            for (;;) {
                /*if(RaceFrame.isWait){
                    wait();
                }*/
                step();
                sleep(100);

            }
        } catch (InterruptedException e) {
            return;
        }
    }


}
