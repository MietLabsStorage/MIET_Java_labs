package Radicals;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * upcast of any radicals
 */
public class RadicalComponent extends JComponent {
    /**
     * get color
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * set color
     *
     * @param color color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * get name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get stat
     *
     * @return stat
     */
    public Stat getAtomicStat() {
        return atomicStat;
    }

    /**
     * get valence
     *
     * @return valence
     */
    public int getValence() {
        return valence;
    }

    /**
     * get link pointer
     *
     * @return link pointer
     */
    public int getLinkPointer() {
        return linkPointer;
    }

    /**
     * get links
     *
     * @return links
     */
    public int[] getLinks() {
        return links;
    }

    /**
     * set choice
     *
     * @param choice choice
     */
    public void setChoice(boolean choice) {
        this.choice = choice;
    }

    /**
     * inc link pointer
     */
    public void incLinkPointer() {
        linkPointer++;
    }

    /**
     * set link[index] = -1
     * @param index index
     */
    public void resetLink(int index){
        links[index] = -1;
    }

    /**
     * decrement link[index]
     * @param index index
     */
    public void decLink(int index){
        links[index] -= 1;
    }

    /**
     * set newVal in links[index]
     * @param index index
     * @param newVal new value
     */
    public void setLink(int index, int newVal){
        links[index] = newVal;
    }

    /**
     * set link pointer
     * @param value value
     */
    public void setLinkPointer(int value){
        linkPointer = value;
    }

    private Color color;
    private String name;
    private Stat atomicStat;
    private int valence;
    private int[] links;
    private int linkPointer;
    private boolean choice;

    // upcast constructor
    protected RadicalComponent(Rectangle bounds, Color color, String name, Stat atomicStat, int valence) {
        super();
        setBounds(bounds);
        this.color = color;
        this.name = name;
        this.atomicStat = atomicStat;
        this.valence = valence;
        links = new int[valence];
        for (int i = 0; i < valence; i++) {
            links[i] = -1;
        }
        linkPointer = 0;
        choice = false;
    }

    /**
     * copy-object constructor
     *
     * @param component copy-ing object
     */
    public RadicalComponent(RadicalComponent component) {
        super();
        setBounds(component.getBounds());
        this.color = component.color;
        this.name = component.name;
        this.atomicStat = component.atomicStat;
        this.valence = component.valence;
        links = new int[component.valence];
        if (valence >= 0) System.arraycopy(component.links, 0, links, 0, valence);
        linkPointer = component.linkPointer;
        choice = component.choice;
    }

    /**
     * decoder of doubleDotString-view of component
     *
     * @param doubleDotsString view of component
     */
    public RadicalComponent(String doubleDotsString) {
        try {
            String[] params = doubleDotsString.split("::");
            color = new Color(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            name = params[3];
            atomicStat = Stat.parseStat(params[4]);
            valence = Integer.parseInt(params[5]);
            setBounds(Integer.parseInt(params[6]), Integer.parseInt(params[7]), Integer.parseInt(params[8]), Integer.parseInt(params[9]));
            links = new int[valence];
            linkPointer = Integer.parseInt(params[10]);
            for (int i = 11; i < params.length; i++) {
                links[i - 11] = Integer.parseInt(params[i]);
            }
            choice = false;
        } catch (Exception e) {
            new Exceptions.JException(e.getMessage());
        }
    }

    //override method paintComponent
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //painting for Stat.Atom
        if (atomicStat == Stat.Atom) {
            g2.setColor(color);
            Ellipse2D circle = new Ellipse2D.Double(0, 0, getBounds().width - 1, getBounds().height - 1);
            g2.draw(circle);
            g2.fill(circle);

            //for chosen element
            if (choice) {
                g2.setColor(Color.BLACK);
                int x = (getBounds().width) / 2 - 2;
                int y = (getBounds().height) / 2 - 2;
                Ellipse2D circleIN = new Ellipse2D.Double(x, y, 4, 4);
                g2.draw(circleIN);
                g2.fill(circleIN);
            }
            return;
        }

        if (atomicStat == Stat.Radical) {
            //red-border
            g2.setColor(Color.RED);
            Rectangle2D rect1 = new Rectangle2D.Double(0, 0, getBounds().width, getBounds().height);
            g2.draw(rect1);
            g2.fill(rect1);
            //white-fill
            g2.setColor(Color.WHITE);
            Rectangle2D rect2 = new Rectangle2D.Double(3, 3, getBounds().width - 7, getBounds().height - 7);
            g2.draw(rect2);
            g2.fill(rect2);
            //"R" in square
            g2.setColor(Color.black);
            Font R = new Font("Arial", Font.PLAIN, getBounds().height / 2);
            g2.setFont(R);
            g.drawString("R", getBounds().width / 3, getBounds().height / 3 * 2);

            //for chosen element
            if (choice) {
                g2.setColor(Color.BLACK);
                int x = (getBounds().width) / 2 - 2;
                int y = (getBounds().height) / 2 - 2;
                Ellipse2D circleIN = new Ellipse2D.Double(x, y, 4, 4);
                g2.draw(circleIN);
                g2.fill(circleIN);
            }
            return;
        }
    }

    /**
     * code component in doubleDotsString view
     *
     * @return doubleDotsString view
     */
    public String toDoubleDotsString() {
        StringBuilder str = new StringBuilder(color.getRed() + "::" + color.getGreen() + "::" + color.getBlue() +
                "::" + name + "::" + atomicStat.name() + "::" + valence +
                "::" + getX() + "::" + getY() + "::" + getWidth() + "::" + getHeight());
        str.append("::").append(linkPointer);
        for (int link : links) {
            str.append("::").append(link);
        }
        return str.toString();
    }
}
