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
    public Stat atomicStat;
    public int valence;
    public int[] links;
    public int linkPointer;
    public boolean choice;

    public RadicalComponent(Rectangle bounds, Color color, String name, Stat atomicStat, int valence, boolean isChosen){
        super();
        setBounds(bounds);
        this.color = color;
        this.name = name;
        this.atomicStat = atomicStat;
        this.valence = valence;
        links = new int[valence];
        for(int i = 0; i < valence; i++){
            links[i] = -1;
        }
        linkPointer = 0;
        choice = isChosen;
    }

    public RadicalComponent(RadicalComponent component){
        super();
        setBounds(component.getBounds());
        this.color = component.color;
        this.name = component.name;
        this.atomicStat = component.atomicStat;
        this.valence = component.valence;
        links = new int[component.valence];
        for(int i = 0; i < valence; i++){
            links[i] = component.links[i];
        }
        linkPointer = component.linkPointer;
        choice = component.choice;
    }

    public RadicalComponent(String dubleDotsString){
        String params[] = dubleDotsString.split("::");
        color = new Color(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]));
        name = params[3];
        atomicStat = Stat.parseStat(params[4]);
        valence = Integer.parseInt(params[5]);
        setBounds(Integer.parseInt(params[6]),Integer.parseInt(params[7]),Integer.parseInt(params[8]),Integer.parseInt(params[9]));
        links = new int[valence];
        linkPointer = Integer.parseInt(params[10]);
        for(int i = 11; i<params.length;i++){
            links[i-11] = Integer.parseInt(params[i]);
        }
        choice = false;
        //linkPointer = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paint(g);
        Graphics2D g2 = (Graphics2D)g;

        if(atomicStat == Stat.Atom){
            g2.setColor(color);
            Ellipse2D circle = new Ellipse2D.Double(0,0,getBounds().width-1,getBounds().height-1);
            g2.draw(circle);
            g2.fill(circle);
            if(choice){
                g2.setColor(Color.BLACK);
                Ellipse2D circleIN = new Ellipse2D.Double((getBounds().width-1)/2,(getBounds().height-1)/2,4,4);
                g2.draw(circleIN);
                g2.fill(circleIN);
            }
            return;
        }
        if(atomicStat == Stat.Radical){
            g2.setColor(Color.RED);
            Rectangle2D rect1 = new Rectangle2D.Double(0,0,getBounds().width-0,getBounds().height-0);
            g2.draw(rect1);
            g2.fill(rect1);
            g2.setColor(Color.WHITE);
            Rectangle2D rect2 = new Rectangle2D.Double(3,3,getBounds().width-7,getBounds().height-7);
            g2.draw(rect2);
            g2.fill(rect2);
            g2.setColor(Color.black);
            Font R = new Font("Arial", Font.PLAIN, getBounds().height/2);
            g2.setFont(R);
            g.drawString("R", getBounds().width/3,getBounds().height/3*2);
            if(choice){
                g2.setColor(Color.BLACK);
                Ellipse2D circleIN = new Ellipse2D.Double((getBounds().width-1)/2,(getBounds().height-1)/2,4,4);
                g2.draw(circleIN);
                g2.fill(circleIN);
            }
            return;
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Coords: (" + getX() + "; " + getY() + ") | Sizes: (" + getWidth() + "; " + getHeight() + ")";
    }

    public String toDubleDotsString(){
        String str = color.getRed()+"::"+color.getGreen()+"::"+color.getBlue()+
                "::"+name+"::"+atomicStat.name()+"::"+valence+
                "::"+getX()+"::"+getY()+"::"+getWidth()+"::"+getHeight();
        str += "::"+linkPointer;
        for(int link : links){
            str+="::"+link;
        }
        return str;
    }
}
