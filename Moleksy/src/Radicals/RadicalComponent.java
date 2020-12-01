package Radicals;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class RadicalComponent extends JComponent {

    public Color color;
    public String name;
    public boolean isAtomic;
    public int valence;
    public int[] links;
    public RadicalComponent(Rectangle bounds, Color color, String name, boolean isAtomic, int valence){
        super();
        setBounds(bounds);
        this.color = color;
        this.name = name;
        this.isAtomic = isAtomic;
        this.valence = valence;
        links = new int[valence];
    }

    public RadicalComponent(RadicalComponent component){
        super();
        setBounds(component.getBounds());
        this.color = component.color;
        this.name = component.name;
        this.isAtomic = component.isAtomic;
        this.valence = component.valence;
        links = new int[component.valence];
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        if(isAtomic){
            g2.setColor(color);
            Ellipse2D circle = new Ellipse2D.Double(0,0,getBounds().width,getBounds().height);
            g2.draw(circle);
            g2.fill(circle);

        }
        else{
            g2.setBackground(Color.red);
            g2.setColor(Color.WHITE);
            Rectangle2D rect = new Rectangle2D.Double(4,4,getBounds().width-4,getBounds().height-4);
            g2.draw(rect);
            g2.setColor(Color.black);
            Font R = new Font("Arial", Font.PLAIN, getBounds().height);
            g2.setFont(R);
            g.drawString("R", 4,4);
        }
    }
}
