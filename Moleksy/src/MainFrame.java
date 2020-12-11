import Radicals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
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
        componentsAreas[4] = new AreaButton(new Rectangle(350,32+53*4,100,53), new Color(207,231,245), new FreeRadical());
        componentsAreas[5] = new AreaButton(new Rectangle(350,32+53*5,100,53), new Color(207,231,245), new Binding());
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
                    for(AreaButton component: componentsAreas){
                        component.isPressed = false;
                    }
                    component.isPressed = true;
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

        workArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Game.currentComponent != null){
                    Game.currentComponent.setLocation(e.getX()-Game.currentComponent.getWidth()/2, e.getY()+32-Game.currentComponent.getHeight()/2);
                    Game.atomics.add(new RadicalComponent(Game.currentComponent));
                    mainPanel.add(Game.atomics.get(Game.atomics.size()-1));
                    mainPanel.repaint();
                }
                for(RadicalComponent elem : Game.atomics){
                    System.out.println(elem.toString());
                }
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
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




