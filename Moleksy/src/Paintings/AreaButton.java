package Paintings;

import Radicals.RadicalComponent;
import Radicals.Stat;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * rectangle - special for being alternative button
 */
public class AreaButton extends RectangleArea {

    /**
     * get component
     * @return component
     */
    public RadicalComponent getComponent() {
        return component;
    }

    //component liking this area button
    private final RadicalComponent component;

    /**
     * constructor
     * @param bounds bounds
     * @param color background color
     * @param component component
     */
    public AreaButton(Rectangle bounds, Color color, RadicalComponent component) {
        super(bounds, color);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.component = component;
    }

    //override method paintComponent
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(component.getColor());
        //if not binding
        if(component.getAtomicStat() != Stat.Binding) {
            if (component.getAtomicStat() == Stat.Atom) {
                //painting for Stat.Atom
                Ellipse2D circle = new Ellipse2D.Double(Double.parseDouble((getWidth() / 2 - component.getWidth()) / 2 +""), Double.parseDouble((getHeight() - component.getHeight()) / 2 +""), component.getWidth(), component.getHeight());
                g2.draw(circle);
                g2.fill(circle);
            } else if (component.getAtomicStat() == Stat.Radical) {
                //red-border
                g2.setColor(Color.RED);
                Rectangle2D rect1 = new Rectangle2D.Double(Double.parseDouble((getWidth() / 2 - component.getWidth()) / 2 +""), Double.parseDouble((getHeight() - component.getHeight()) / 2 +""), component.getWidth(), component.getHeight());
                g2.draw(rect1);
                g2.fill(rect1);
                //white-fill
                g2.setColor(Color.WHITE);
                Rectangle2D rect2 = new Rectangle2D.Double(Double.parseDouble((getWidth() / 2 - component.getWidth()) / 2 + 3 + ""), Double.parseDouble((getHeight() - component.getHeight()) / 2 + 3+ ""), component.getWidth() - 6, component.getHeight() - 6);
                g2.draw(rect2);
                g2.fill(rect2);
                //"R" in square
                g2.setColor(Color.black);
                Font R = new Font("Arial", Font.PLAIN, component.getHeight() / 2);
                g2.setFont(R);
                g.drawString("R", (getWidth() / 2 - component.getWidth()) / 2 + component.getWidth() / 3, (getHeight() - component.getHeight()) / 2 + component.getHeight() / 3 * 2);
            }
            //painting name of component
            g2.setColor(Color.BLACK);
            Font name = new Font("Arial", Font.BOLD, 24);
            g2.setFont(name);
            g2.drawString(component.getName(), getWidth() / 2/*(getWidth() / 2 - component.getWidth()) / 2 + getWidth() / 4*/, (getHeight() + component.getHeight()) / 2);
        }
        //if binding
        else{
            g2.setColor(Color.BLACK);
            Font name = new Font("Arial", Font.BOLD, 16);
            g2.setFont(name);
            g2.drawString(component.getName(), component.getWidth()/2, getHeight()/2);

        }
    }
}