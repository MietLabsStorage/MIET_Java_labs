import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

class RectangleArea extends JComponent {
    public boolean isPressed;
    public Color color;
    public RectangleArea(Rectangle bounds, Color color){
        super();
        setBounds(bounds);
        this.color = color;
        isPressed = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        if(isPressed){
            g2.setColor(Color.RED);
        }
        else {
            g2.setColor(Color.black);
        }
        Rectangle2D border = new Rectangle2D.Double(0,0,getBounds().width-1,getBounds().height-1);
        g2.draw(border);
        //g2.fill(border);
        if(isPressed){
            g2.setColor(new Color(color.getRed()-20, color.getGreen()-20, color.getBlue()-20));
        }
        else {
            g2.setColor(color);
        }
        Rectangle2D area = new Rectangle2D.Double(0+1,0+1,getBounds().width-1.5*2,getBounds().height-1.5*2);
        g2.draw(area);
        g2.fill(area);
    }
}