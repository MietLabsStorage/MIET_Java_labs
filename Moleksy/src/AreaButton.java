import Radicals.RadicalComponent;
import Radicals.Stat;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

class AreaButton extends RectangleArea{

    RadicalComponent component;
    public AreaButton(Rectangle bounds, Color color, RadicalComponent component) {
        super(bounds, color);
        this.component = component;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*component.setLocation((getWidth()/2-component.getWidth())/2,(getHeight()/2-component.getHeight())/2);
        component.paintComponent(g);*/
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(component.color);
        if(component.atomicStat == Stat.Atom) {
            Ellipse2D circle = new Ellipse2D.Double((getWidth()/2-component.getWidth())/2,(getHeight()-component.getHeight())/2,component.getWidth(),component.getHeight());
            g2.draw(circle);
            g2.fill(circle);
        }
        else if(component.atomicStat == Stat.Radical){
            g2.setColor(Color.RED);
            Rectangle2D rect1 = new Rectangle2D.Double((getWidth()/2-component.getWidth())/2+0,(getHeight()-component.getHeight())/2+0,component.getWidth()-0,component.getHeight()-0);
            g2.draw(rect1);
            g2.fill(rect1);
            g2.setColor(Color.WHITE);
            Rectangle2D rect2 = new Rectangle2D.Double((getWidth()/2-component.getWidth())/2+3,(getHeight()-component.getHeight())/2+3,component.getWidth()-6,component.getHeight()-6);
            g2.draw(rect2);
            g2.fill(rect2);
            g2.setColor(Color.black);
            Font R = new Font("Arial", Font.PLAIN, component.getHeight()/2);
            g2.setFont(R);
            g.drawString("R", (getWidth()/2-component.getWidth())/2+component.getWidth()/3,(getHeight()-component.getHeight())/2+component.getHeight()/3*2);
        }
        g2.setColor(Color.BLACK);
        Font name = new Font("Arial",Font.PLAIN,28);
        g2.setFont(name);
        g2.drawString(component.name, (getWidth()/2-component.getWidth())/2+getWidth()/4, (getHeight()+component.getHeight())/2);
    }
}