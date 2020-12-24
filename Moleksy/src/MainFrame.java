import Radicals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * main class for all program with init() and run()
 */
public class MainFrame {

    private static JFrame mainFrame;
    private static JPanel mainPanel;
    private static RectangleArea fileArea;
    private static RectangleArea workArea;
    private static AreaButton[] componentsAreas;
    private static JButton buttonSave;
    private static JButton buttonLoad;

    /**
     * init frame and all components
     */
    public static void init() {
        Logs.writeMessage("Start init-ing Frame");

        //first part of init-ing Frame
        mainFrame = new JFrame("Moleksy by mJaJksJ");
        mainFrame.setSize(450 + 10, 350 + 30);

        //init-ing Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        //init-ing background of save-load files
        fileArea = new RectangleArea(new Rectangle(0, 0, 450, 32), new Color(204, 255, 153));

        //init-ing background of molecule painting area
        workArea = new RectangleArea(new Rectangle(0, 32, 350, 318), new Color(255, 255, 255, 1));

        //init-ing buttons of choosing elements
        componentsAreas = new AreaButton[6];
        componentsAreas[0] = new AreaButton(new Rectangle(350, 32 + 53 * 0, 100, 53), new Color(207, 231, 245), new Hydrogen());
        componentsAreas[1] = new AreaButton(new Rectangle(350, 32 + 53 * 1, 100, 53), new Color(207, 231, 245), new Nitrogen());
        componentsAreas[2] = new AreaButton(new Rectangle(350, 32 + 53 * 2, 100, 53), new Color(207, 231, 245), new Carboneum());
        componentsAreas[3] = new AreaButton(new Rectangle(350, 32 + 53 * 3, 100, 53), new Color(207, 231, 245), new Oxygen());
        componentsAreas[4] = new AreaButton(new Rectangle(350, 32 + 53 * 4, 100, 53), new Color(207, 231, 245), new FreeRadical());
        componentsAreas[5] = new AreaButton(new Rectangle(350, 32 + 53 * 5, 100, 53), new Color(207, 231, 245), new Binding());

        //init-ing save button
        buttonSave = new JButton("Save");
        buttonSave.setBounds(20, 10, 100, 20);
        buttonSave.setVisible(true);

        //init-ing load button
        buttonLoad = new JButton("Load");
        buttonLoad.setBounds(130, 10, 100, 20);
        buttonLoad.setVisible(true);

        //add components in panel
        mainPanel.add(buttonSave);
        mainPanel.add(buttonLoad);
        mainPanel.add(fileArea);
        mainPanel.add(workArea);
        for (AreaButton componentsArea : componentsAreas) {
            mainPanel.add(componentsArea);
        }

        //second part of init-ing Frame
        mainFrame.setContentPane(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);

        Logs.writeMessage("Finish init-ing Frame");
    }

    /**
     * listen all listeners in Frame
     */
    public static void run() {
        //listeners for clicking on buttons in componentsAreas
        for (AreaButton component : componentsAreas) {
            component.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Logs.writeMessage("Click button " + component.component.name);
                    if (!component.component.name.equals(new Binding().name)) {
                        Game.paintingComponent = new RadicalComponent(component.component);
                        Game.atomics.get(Game.chosenComponentIndex).choice = false;
                        Game.chosenComponent = null;
                    }
                    else{
                        Game.paintingComponent = null;
                    }
                    for (AreaButton component : componentsAreas) {
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

        //listener for clicking on work area to create graphic-primitive
        workArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Game.paintingComponent != null) {
                    Logs.writeMessage("Click in work area. Current component: " + Game.paintingComponent.name);
                    Game.paintingComponent.setLocation(e.getX() - Game.paintingComponent.getWidth() / 2, e.getY() + 32 - Game.paintingComponent.getHeight() / 2);
                    Game.atomics.add(new RadicalComponent(Game.paintingComponent));
                    mainPanel.add(Game.atomics.get(Game.atomics.size() - 1));
                    mainPanel.repaint();
                } else {
                    Logs.writeMessage("Click in work area. Current component: " + null);
                    for(int i = 0; i < Game.atomics.size(); i++){
                        if(Game.atomics.get(i).getX() < e.getX()
                                && e.getX() < Game.atomics.get(i).getX()+Game.atomics.get(i).getWidth()
                                && Game.atomics.get(i).getY()-workArea.getY() < e.getY()
                                && e.getY() < Game.atomics.get(i).getY()+Game.atomics.get(i).getHeight()-workArea.getY()){
                            Logs.writeMessage("Choose " + i + "th component: " + Game.atomics.get(i).toDubleDotsString());
                            System.out.println("Choose " + i + "th component: " + Game.atomics.get(i).toDubleDotsString());
                            if(Game.chosenComponent == null){
                                Game.chosenComponent = new RadicalComponent(Game.atomics.get(i));
                                Game.chosenComponentIndex = i;
                                Game.atomics.get(Game.chosenComponentIndex).choice = true;
                            }
                            else{
                                if(Game.atomics.get(i).linkPointer < Game.atomics.get(i).valence &&
                                Game.chosenComponent.linkPointer < Game.chosenComponent.valence &&
                                i != Game.chosenComponentIndex) {
                                    Game.atomics.get(i).links[Game.atomics.get(i).linkPointer] = Game.chosenComponentIndex;
                                    Game.atomics.get(i).linkPointer++;
                                    Game.atomics.get(Game.chosenComponentIndex).links[Game.chosenComponent.linkPointer] = i;
                                    Game.atomics.get(Game.chosenComponentIndex).linkPointer++;
                                    Game.chosenComponent = null;
                                    System.out.println("Link atom " + i + " with " + Game.chosenComponentIndex);
                                    Game.atomics.get(Game.chosenComponentIndex).choice = false;
                                }
                            }
                            repaintMolecule();
                            return;
                        }
                    }
                    Game.chosenComponent = null;
                    Game.atomics.get(Game.chosenComponentIndex).choice = false;
                    repaintMolecule();

                }
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

        //listener for clicking on button save
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Logs.writeMessage("Click save");
                    Game.Save();
                } catch (Exception exception) {
                    Logs.writeMessage(exception.toString());
                }
            }
        });

        //listener for clicking on button load
        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Game.Load();
                    Logs.writeMessage("Click load");
                    //repaint all
                    repaintMolecule();
                } catch (Exception exception) {
                    Logs.writeMessage(exception.toString());
                }
            }
        });
    }

    /**
     * repaint all components in Frame
     */
    private static void repaintMolecule() {
        mainPanel.removeAll();
        mainPanel.add(buttonSave);
        mainPanel.add(buttonLoad);
        mainPanel.add(fileArea);
        mainPanel.add(workArea);
        for (AreaButton componentsArea : componentsAreas) {
            mainPanel.add(componentsArea);
        }
        for (RadicalComponent component : Game.atomics) {
            mainPanel.add(component);
        }
        JLine line = new JLine(new Rectangle(0,0,mainFrame.getWidth(), mainFrame.getHeight()), Game.atomics);
        line.setVisible(true);
        mainPanel.add(line);
        mainFrame.setContentPane(mainPanel);
        Logs.writeMessage("Repaint all");
    }
}


class JLine extends JComponent{
    ArrayList<RadicalComponent> atomics;
    public JLine(Rectangle bounds, ArrayList<RadicalComponent> atomics) {
        super();
        this.setBounds(bounds);
        this.atomics = new ArrayList<>();
        this.atomics.addAll(atomics);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);

        ArrayList<AllBinds> binds = new ArrayList<>();
        binds.add(new AllBinds(-1,-1,-1));
        for(int j = 0; j < atomics.size(); j++){
            for(int i = 0; i < atomics.get(j).valence; i++){
                if(atomics.get(j).links[i] != -1){
                    boolean isBindsEmpty = true;
                    //int q = Game.atomics.indexOf(Game.atomics.get(i));
                    for(AllBinds bind : binds){
                        if(/*(bind.node1 == j && bind.node2 == atomics.get(j).links[i]) ||*/ (bind.node1 == atomics.get(j).links[i] && bind.node2 == j) ){
                            bind.count++;
                            //System.out.println("=*=");
                            isBindsEmpty = false;
                            break;
                        }
                        else{
                            isBindsEmpty = true;
                            //binds.add(new AllBinds(j, atomics.get(j).links[i], 0));
                            //System.out.println("=|=");
                        }
                    }
                    if(isBindsEmpty){
                        binds.add(new AllBinds(j, atomics.get(j).links[i], 0));
                        //System.out.println("=+=");
                    }
                    /*Line2D line = new Line2D.Double(component.getX()+component.getWidth()/2,
                            component.getY()+component.getHeight()/2,
                            atomics.get(component.links[i]).getX()+atomics.get(component.links[i]).getWidth()/2,
                            atomics.get(component.links[i]).getY()+atomics.get(component.links[i]).getHeight()/2);
                    g2.draw(line);*/
                }
            }

            //component.paintComponent(g);
        }
        for(AllBinds bind : binds){
            //System.out.println("="+bind.node1+"="+bind.node2+"="+bind.count);
            switch (bind.count){
                case 1:
                    g2.setColor(Color.BLACK);
                    Line2D line1 = new Line2D.Double(Game.atomics.get(bind.node1).getX()+Game.atomics.get(bind.node1).getWidth()/2,
                            Game.atomics.get(bind.node1).getY()+Game.atomics.get(bind.node1).getHeight()/2,
                            Game.atomics.get(bind.node2).getX()+Game.atomics.get(bind.node2).getWidth()/2,
                            Game.atomics.get(bind.node2).getY()+Game.atomics.get(bind.node2).getHeight()/2);
                    g2.draw(line1);
                    break;
                case 2:
                    g2.setColor(Color.BLACK);
                    int qX2 = 0;
                    int qY2 = 0;
                    if(Math.abs(Game.atomics.get(bind.node1).getX() - Game.atomics.get(bind.node2).getX()) <
                            Math.abs(Game.atomics.get(bind.node1).getY() - Game.atomics.get(bind.node2).getY())){
                        qX2 = 2;
                    }
                    else {
                        qY2 = 2;
                    }
                    Line2D line21 = new Line2D.Double(Game.atomics.get(bind.node1).getX()+qX2+Game.atomics.get(bind.node1).getWidth()/2,
                            Game.atomics.get(bind.node1).getY()+qY2+Game.atomics.get(bind.node1).getHeight()/2,
                            Game.atomics.get(bind.node2).getX()+qX2+Game.atomics.get(bind.node2).getWidth()/2,
                            Game.atomics.get(bind.node2).getY()+qY2+Game.atomics.get(bind.node2).getHeight()/2);
                    g2.draw(line21);
                    Line2D line22 = new Line2D.Double(Game.atomics.get(bind.node1).getX()-qX2+Game.atomics.get(bind.node1).getWidth()/2,
                            Game.atomics.get(bind.node1).getY()-qY2+Game.atomics.get(bind.node1).getHeight()/2,
                            Game.atomics.get(bind.node2).getX()-qX2+Game.atomics.get(bind.node2).getWidth()/2,
                            Game.atomics.get(bind.node2).getY()-qY2+Game.atomics.get(bind.node2).getHeight()/2);
                    g2.draw(line22);
                    break;
                case 3:
                    g2.setColor(Color.BLACK);
                    int qX3 = 0;
                    int qY3 = 0;
                    if(Math.abs(Game.atomics.get(bind.node1).getX() - Game.atomics.get(bind.node2).getX()) <
                            Math.abs(Game.atomics.get(bind.node1).getY() - Game.atomics.get(bind.node2).getY())){
                        qX3 = 2;
                    }
                    else {
                        qY3 = 2;
                    }
                    Line2D line31 = new Line2D.Double(Game.atomics.get(bind.node1).getX()+Game.atomics.get(bind.node1).getWidth()/2,
                            Game.atomics.get(bind.node1).getY()+Game.atomics.get(bind.node1).getHeight()/2,
                            Game.atomics.get(bind.node2).getX()+Game.atomics.get(bind.node2).getWidth()/2,
                            Game.atomics.get(bind.node2).getY()+Game.atomics.get(bind.node2).getHeight()/2);
                    g2.draw(line31);
                    Line2D line32 = new Line2D.Double(Game.atomics.get(bind.node1).getX()+qX3+Game.atomics.get(bind.node1).getWidth()/2,
                            Game.atomics.get(bind.node1).getY()+qY3+Game.atomics.get(bind.node1).getHeight()/2,
                            Game.atomics.get(bind.node2).getX()+qX3+Game.atomics.get(bind.node2).getWidth()/2,
                            Game.atomics.get(bind.node2).getY()+qY3+Game.atomics.get(bind.node2).getHeight()/2);
                    g2.draw(line32);
                    Line2D line33 = new Line2D.Double(Game.atomics.get(bind.node1).getX()-qX3+Game.atomics.get(bind.node1).getWidth()/2,
                            Game.atomics.get(bind.node1).getY()-qY3+Game.atomics.get(bind.node1).getHeight()/2,
                            Game.atomics.get(bind.node2).getX()-qX3+Game.atomics.get(bind.node2).getWidth()/2,
                            Game.atomics.get(bind.node2).getY()-qY3+Game.atomics.get(bind.node2).getHeight()/2);
                    g2.draw(line33);
                    break;
            }
        }

    }
}

class AllBinds{
    public int node1;
    public int node2;
    public int count;


    public AllBinds(int node1, int node2, int count) {
        this.node1 = node1;
        this.node2 = node2;
        this.count = count;
    }
}