package Areas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

//class for painting rectangle
public class RectangleArea extends JComponent {

    /**
     * set pressed
     * @param pressed pressed
     */
    public void pressed(boolean pressed){
        isPressed = pressed;
    }

    private boolean isPressed;
    private final Color color;

    /**
     * constructor
     * @param bounds bounds
     * @param color color
     */
    public RectangleArea(Rectangle bounds, Color color){
        super();
        setBounds(bounds);
        this.color = color;
        isPressed = false;
    }

    //override method paintComponent
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        //set bounds red/black in dependence of pressed
        if(isPressed){
            g2.setColor(Color.RED);
        }
        else {
            g2.setColor(Color.BLACK);
        }
        //paint bounds
        Rectangle2D border = new Rectangle2D.Double(0,0,getBounds().width-1,getBounds().height-1);
        g2.draw(border);

        //set color in dependence of pressed
        if(isPressed){
            int red = color.getRed() > 20 ? color.getRed()-20 : 0;
            int green = color.getGreen() > 20 ? color.getRed()-20 : 0;
            int blue = color.getBlue() > 20 ? color.getRed()-20 : 0;
            g2.setColor(new Color(red, green, blue));
        }
        else {
            g2.setColor(color);
        }
        //paint fill
        Rectangle2D area = new Rectangle2D.Double(1,1,getBounds().width-3,getBounds().height-3);
        g2.draw(area);
        g2.fill(area);
    }
}