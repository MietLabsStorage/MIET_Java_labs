package Paintings;

import Radicals.RadicalComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * class for drawing line behind to points for list of points
 */
public class JLines extends JComponent {

    //struct for store binds information
    private static class AllBinds {
        public int node1;
        public int node2;
        public int count;

        public AllBinds(int node1, int node2, int count) {
            this.node1 = node1;
            this.node2 = node2;
            this.count = count;
        }
    }

    //list of points
    private final ArrayList<RadicalComponent> atomics;
    //color of line
    private final Color color;

    /**
     * constructor
     * @param bounds bounds
     * @param atomics atomics list of points
     * @param color color
     */
    public JLines(Rectangle bounds, ArrayList<RadicalComponent> atomics, Color color) {
        super();
        this.setBounds(bounds);
        this.atomics = new ArrayList<>();
        this.atomics.addAll(atomics);
        this.color = color;
    }

    //draw one line bind
    private void oneLinePaint(Graphics2D g2, AllBinds bind){
        g2.setColor(color);
        Line2D line1 = new Line2D.Double(atomics.get(bind.node1).getX() + Double.parseDouble(atomics.get(bind.node1).getWidth() / 2 + ""),
                atomics.get(bind.node1).getY() + Double.parseDouble(atomics.get(bind.node1).getHeight() / 2 + ""),
                atomics.get(bind.node2).getX() + Double.parseDouble(atomics.get(bind.node2).getWidth() / 2 +""),
                atomics.get(bind.node2).getY() + Double.parseDouble(atomics.get(bind.node2).getHeight() / 2 + ""));
        g2.draw(line1);
    }

    //draw two line bind
    private void twoLinePaint(Graphics2D g2, AllBinds bind){
        g2.setColor(color);
        //editing coordinates of lines
        int qX2 = 0;
        int qY2 = 0;
        if (Math.abs(atomics.get(bind.node1).getX() - atomics.get(bind.node2).getX()) <
                Math.abs(atomics.get(bind.node1).getY() - atomics.get(bind.node2).getY())) {
            qX2 = 2;
        } else {
            qY2 = 2;
        }
        //first line
        Line2D line21 = new Line2D.Double(atomics.get(bind.node1).getX() + qX2 + Double.parseDouble(atomics.get(bind.node1).getWidth() / 2 +""),
                atomics.get(bind.node1).getY() + qY2 + Double.parseDouble(atomics.get(bind.node1).getHeight() / 2+""),
                atomics.get(bind.node2).getX() + qX2 + Double.parseDouble(atomics.get(bind.node2).getWidth() / 2+""),
                atomics.get(bind.node2).getY() + qY2 + Double.parseDouble(atomics.get(bind.node2).getHeight() / 2+""));
        g2.draw(line21);
        //second line
        Line2D line22 = new Line2D.Double(atomics.get(bind.node1).getX() - qX2 + Double.parseDouble(atomics.get(bind.node1).getWidth() / 2+""),
                atomics.get(bind.node1).getY() - qY2 + Double.parseDouble(atomics.get(bind.node1).getHeight() / 2+""),
                atomics.get(bind.node2).getX() - qX2 + Double.parseDouble(atomics.get(bind.node2).getWidth() / 2+""),
                atomics.get(bind.node2).getY() - qY2 + Double.parseDouble(atomics.get(bind.node2).getHeight() / 2+""));
        g2.draw(line22);
    }

    //draw three line bind
    private void threeLinePaint(Graphics2D g2, AllBinds bind){
        g2.setColor(color);
        //editing coordinates of lines
        int qX3 = 0;
        int qY3 = 0;
        if (Math.abs(atomics.get(bind.node1).getX() - atomics.get(bind.node2).getX()) <
                Math.abs(atomics.get(bind.node1).getY() - atomics.get(bind.node2).getY())) {
            qX3 = 3;
        } else {
            qY3 = 3;
        }
        //first line
        Line2D line31 = new Line2D.Double(atomics.get(bind.node1).getX() + Double.parseDouble(atomics.get(bind.node1).getWidth() / 2+""),
                atomics.get(bind.node1).getY() + Double.parseDouble(atomics.get(bind.node1).getHeight() / 2+""),
                atomics.get(bind.node2).getX() + Double.parseDouble(atomics.get(bind.node2).getWidth() / 2+""),
                atomics.get(bind.node2).getY() + Double.parseDouble(atomics.get(bind.node2).getHeight() / 2+""));
        g2.draw(line31);
        //second line
        Line2D line32 = new Line2D.Double(atomics.get(bind.node1).getX() + qX3 + Double.parseDouble(atomics.get(bind.node1).getWidth() / 2+""),
                atomics.get(bind.node1).getY() + qY3 + Double.parseDouble(atomics.get(bind.node1).getHeight() / 2+""),
                atomics.get(bind.node2).getX() + qX3 + Double.parseDouble(atomics.get(bind.node2).getWidth() / 2+""),
                atomics.get(bind.node2).getY() + qY3 + Double.parseDouble(atomics.get(bind.node2).getHeight() / 2+""));
        g2.draw(line32);
        //third line
        Line2D line33 = new Line2D.Double(atomics.get(bind.node1).getX() - qX3 + Double.parseDouble(atomics.get(bind.node1).getWidth() / 2+""),
                atomics.get(bind.node1).getY() - qY3 + Double.parseDouble(atomics.get(bind.node1).getHeight() / 2+""),
                atomics.get(bind.node2).getX() - qX3 + Double.parseDouble(atomics.get(bind.node2).getWidth() / 2+""),
                atomics.get(bind.node2).getY() - qY3 + Double.parseDouble(atomics.get(bind.node2).getHeight() / 2+""));
        g2.draw(line33);
    }

    //draw four line bind
    private void fourLinePaint(Graphics2D g2, AllBinds bind){
        g2.setColor(color);
        //editing coordinates of lines
        int qX4 = 0;
        int qY4 = 0;
        if (Math.abs(atomics.get(bind.node1).getX() - atomics.get(bind.node2).getX()) <
                Math.abs(atomics.get(bind.node1).getY() - atomics.get(bind.node2).getY())) {
            qX4 = 2;
        } else {
            qY4 = 2;
        }
        //first line
        Line2D line41 = new Line2D.Double(atomics.get(bind.node1).getX() + qX4 + Double.parseDouble(atomics.get(bind.node1).getWidth() / 2 + ""),
                atomics.get(bind.node1).getY() + qY4 + Double.parseDouble(atomics.get(bind.node1).getHeight() / 2 +""),
                atomics.get(bind.node2).getX() + qX4 + Double.parseDouble(atomics.get(bind.node2).getWidth() / 2+""),
                atomics.get(bind.node2).getY() + qY4 + Double.parseDouble(atomics.get(bind.node2).getHeight() / 2+""));
        g2.draw(line41);
        //second line
        Line2D line42= new Line2D.Double(atomics.get(bind.node1).getX() - qX4 + Double.parseDouble(atomics.get(bind.node1).getWidth() / 2+""),
                atomics.get(bind.node1).getY() - qY4 + Double.parseDouble(atomics.get(bind.node1).getHeight() / 2+""),
                atomics.get(bind.node2).getX() - qX4 + Double.parseDouble(atomics.get(bind.node2).getWidth() / 2+""),
                atomics.get(bind.node2).getY() - qY4 + Double.parseDouble(atomics.get(bind.node2).getHeight() / 2+""));
        g2.draw(line42);
        //editing coordinates of lines
        qX4 *= 3;
        qY4 *= 3;
        //third line
        Line2D line43 = new Line2D.Double(atomics.get(bind.node1).getX() + qX4 + Double.parseDouble(atomics.get(bind.node1).getWidth() / 2+""),
                atomics.get(bind.node1).getY() + qY4 + Double.parseDouble(atomics.get(bind.node1).getHeight() / 2+""),
                atomics.get(bind.node2).getX() + qX4 + Double.parseDouble(atomics.get(bind.node2).getWidth() / 2+""),
                atomics.get(bind.node2).getY() + qY4 + Double.parseDouble(atomics.get(bind.node2).getHeight() / 2+""));
        g2.draw(line43);
        //fourth line
        Line2D line44= new Line2D.Double(atomics.get(bind.node1).getX() - qX4 + Double.parseDouble(atomics.get(bind.node1).getWidth() / 2+""),
                atomics.get(bind.node1).getY() - qY4 + Double.parseDouble(atomics.get(bind.node1).getHeight() / 2+""),
                atomics.get(bind.node2).getX() - qX4 + Double.parseDouble(atomics.get(bind.node2).getWidth() / 2+""),
                atomics.get(bind.node2).getY() - qY4 + Double.parseDouble(atomics.get(bind.node2).getHeight() / 2+""));
        g2.draw(line44);
    }

    //override method paintComponent
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        //creating dict-struct of binds
        ArrayList<AllBinds> binds = new ArrayList<>();
        binds.add(new AllBinds(-1, -1, -1));
        for (int j = 0; j < atomics.size(); j++) {
            for (int i = 0; i < atomics.get(j).getValence(); i++) {
                //if bind exist
                if (atomics.get(j).getLinks()[i] != -1) {
                    boolean isBindsEmpty = true;
                    for (AllBinds bind : binds) {
                        //inc count of bind
                        if ((bind.node1 == atomics.get(j).getLinks()[i] && bind.node2 == j)) {
                            bind.count++;
                            isBindsEmpty = false;
                            break;
                        } else {
                            isBindsEmpty = true;
                        }
                    }
                    //init new bind in dict
                    if (isBindsEmpty) {
                        binds.add(new AllBinds(j, atomics.get(j).getLinks()[i], 0));
                    }
                }
            }
        }
        //painting binds
        for (AllBinds bind : binds) {
            switch (bind.count) {
                case 1 -> oneLinePaint(g2, bind);
                case 2 -> twoLinePaint(g2, bind);
                case 3 -> threeLinePaint(g2, bind);
                case 4 -> fourLinePaint(g2, bind);
            }
        }

    }
}
