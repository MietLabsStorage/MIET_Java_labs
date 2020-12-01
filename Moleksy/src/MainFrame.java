import Radicals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class MainFrame {

    private static JFrame mainFrame;
    private static JPanel mainPanel;
    private static RectangleArea fileArea;
    private static RectangleArea workArea;
    private static AreaButton[] componentsAreas;

    public static void init(){
        mainFrame = new JFrame("Moleksy by mJaJksJ");
        mainFrame.setSize(450+10,350+30);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        fileArea = new RectangleArea(new Rectangle(0,0,450,32),new Color(204,255,153));
        mainPanel.add(fileArea);
        workArea = new RectangleArea(new Rectangle(0,32,350,318),new Color(255,255,255,1));
        mainPanel.add(workArea);
        componentsAreas = new AreaButton[6];
        componentsAreas[0] = new AreaButton(new Rectangle(350,32+53*0,100,53), new Color(207,231,245), new Hydrogen());
        componentsAreas[1] = new AreaButton(new Rectangle(350,32+53*1,100,53), new Color(207,231,245), new Nitrogen());
        componentsAreas[2] = new AreaButton(new Rectangle(350,32+53*2,100,53), new Color(207,231,245), new Carboneum());
        componentsAreas[3] = new AreaButton(new Rectangle(350,32+53*3,100,53), new Color(207,231,245), new Oxygen());
        componentsAreas[4] = new AreaButton(new Rectangle(350,32+53*4,100,53), new Color(207,231,245), new Oxygen());
        componentsAreas[5] = new AreaButton(new Rectangle(350,32+53*5,100,53), new Color(207,231,245), new Oxygen());
        for(int i = 0; i < componentsAreas.length; i++){
            mainPanel.add(componentsAreas[i]);
        }

        Hydrogen h1 = new Hydrogen();
        Oxygen o1 = new Oxygen();

        h1.setLocation(200,200);
        o1.setLocation(100,100);

        mainPanel.add(h1);
        mainPanel.add(o1);
        mainFrame.setContentPane(mainPanel);
        //mainFrame.add(new Drawer());
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    public static void run(){
        for(AreaButton component: componentsAreas){
            component.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    /*RadicalComponent _component = new RadicalComponent(component.component);
                    _component.setLocation(new Random().nextInt(200), new Random().nextInt(200));
                    mainPanel.add(_component);
                    mainPanel.repaint();*/
                    Game.currentComponent = new RadicalComponent(component.component);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        workArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.currentComponent.setLocation(e.getX()-Game.currentComponent.getWidth()/2, e.getY()+32-Game.currentComponent.getHeight()/2);
                mainPanel.add(new RadicalComponent(Game.currentComponent));
                mainPanel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}


class RectangleArea extends JComponent{
    public Color color;
    public RectangleArea(Rectangle bounds, Color color){
        super();
        setBounds(bounds);
        this.color = color;
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        Rectangle2D border = new Rectangle2D.Double(0,0,getBounds().width-1,getBounds().height-1);
        g2.draw(border);
        //g2.fill(border);
        g2.setColor(color);
        Rectangle2D area = new Rectangle2D.Double(0+1,0+1,getBounds().width-1.5*2,getBounds().height-1.5*2);
        g2.draw(area);
        g2.fill(area);
    }
}

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
        Ellipse2D circle = new Ellipse2D.Double((getWidth()/2-component.getWidth())/2,(getHeight()-component.getHeight())/2,component.getWidth(),component.getHeight());
        g2.draw(circle);
        g2.fill(circle);
        g2.setColor(Color.BLACK);
        Font name = new Font("Arial",Font.PLAIN,28);
        g2.setFont(name);
        g2.drawString(component.name, (getWidth()/2-component.getWidth())/2+getWidth()/4, (getHeight()+component.getHeight())/2);
    }
}