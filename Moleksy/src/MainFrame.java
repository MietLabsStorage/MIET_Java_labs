import Areas.AreaButton;
import Areas.RectangleArea;
import Radicals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

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
    private static JTextArea filenameTextArea;
    private static JButton buttonClear;

    /**
     * init frame and all components
     */
    public static void init() {
        Logs.writeMessage("Start init-ing Frame");

        //first part of init-ing Frame
        mainFrame = new JFrame(Game.getParams().get("mainFrame").get("name"));
        //mainFrame.setSize(450 + 10, 350 + 30);
        mainFrame.setSize(
                Integer.parseInt(Game.getParams().get("mainFrame").get("width")),
                Integer.parseInt(Game.getParams().get("mainFrame").get("height"))
        );

        //init-ing Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(
                new Color(
                        Integer.parseInt(Game.getParams().get("mainPanel").get("clrR")),
                        Integer.parseInt(Game.getParams().get("mainPanel").get("clrG")),
                        Integer.parseInt(Game.getParams().get("mainPanel").get("clrB"))
                )
        );

        //init-ing background of save-load files
        fileArea = new RectangleArea(
                new Rectangle(
                        0,
                        0,
                        mainFrame.getWidth() - 10,
                        Integer.parseInt(Game.getParams().get("fileArea").get("height"))
                ),
                new Color(
                        Integer.parseInt(Game.getParams().get("fileArea").get("clrR")),
                        Integer.parseInt(Game.getParams().get("fileArea").get("clrG")),
                        Integer.parseInt(Game.getParams().get("fileArea").get("clrB"))
                )
        );

        //init-ing background of molecule painting area
        workArea = new RectangleArea(
                new Rectangle(
                        0,
                        fileArea.getHeight(),
                        Integer.parseInt(Game.getParams().get("workArea").get("width")),
                        mainFrame.getHeight() - 30 - fileArea.getX()
                ),
                new Color(
                        Integer.parseInt(Game.getParams().get("workArea").get("clrR")),
                        Integer.parseInt(Game.getParams().get("workArea").get("clrG")),
                        Integer.parseInt(Game.getParams().get("workArea").get("clrB")),
                        Integer.parseInt(Game.getParams().get("workArea").get("clrA"))
                )
        );

        //init-ing buttons of choosing elements
        componentsAreas = new AreaButton[6];
        componentsAreas[0] = new AreaButton(new Rectangle(350, 32 + 53 * 0, 100, 53), new Color(207, 231, 245), new Hydrogen());
        componentsAreas[1] = new AreaButton(new Rectangle(350, 32 + 53 * 1, 100, 53), new Color(207, 231, 245), new Nitrogen());
        componentsAreas[2] = new AreaButton(new Rectangle(350, 32 + 53 * 2, 100, 53), new Color(207, 231, 245), new Carboneum());
        componentsAreas[3] = new AreaButton(new Rectangle(350, 32 + 53 * 3, 100, 53), new Color(207, 231, 245), new Oxygen());
        componentsAreas[4] = new AreaButton(new Rectangle(350, 32 + 53 * 4, 100, 53), new Color(207, 231, 245), new FreeRadical());
        componentsAreas[5] = new AreaButton(new Rectangle(350, 32 + 53 * 5, 100, 53), new Color(207, 231, 245), new Binding());

        //init-ing save button
        buttonSave = new JButton(Game.getParams().get("saveButton").get("name"));
        buttonSave.setBounds(
                mainFrame.getWidth() - Integer.parseInt(Game.getParams().get("saveButton").get("width")) - 15,
                5,
                Integer.parseInt(Game.getParams().get("saveButton").get("width")),
                Integer.parseInt(Game.getParams().get("saveButton").get("height"))
        );
        buttonSave.setVisible(true);

        //init-ing load button
        buttonLoad = new JButton(Game.getParams().get("loadButton").get("name"));
        buttonLoad.setBounds(
                buttonSave.getX() - Integer.parseInt(Game.getParams().get("loadButton").get("width")) - 5,
                5,
                Integer.parseInt(Game.getParams().get("loadButton").get("width")),
                Integer.parseInt(Game.getParams().get("loadButton").get("height")));
        buttonLoad.setVisible(true);

        filenameTextArea = new JTextArea("filename");
        filenameTextArea.setBounds(
                5,
                5,
                buttonLoad.getX() - 5 - 5,
                20
        );
        filenameTextArea.setVisible(true);

        buttonClear = new JButton("Clear");
        buttonClear.setBounds(5, mainFrame.getHeight()-35-20, 100, 20);
        buttonClear.setVisible(true);

        //add components in panel
        mainPanel.add(buttonSave);
        mainPanel.add(buttonLoad);
        mainPanel.add(buttonClear);
        mainPanel.add(filenameTextArea);
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
                    Logs.writeMessage("Click button " + component.getComponent().getName());
                    if (!component.getComponent().getName().equals(new Binding().getName())) {
                        Game.setPaintingComponent(new RadicalComponent(component.getComponent()));
                        if (Game.getChosenComponentIndex() != -1) {
                            Game.getAtomics().get(Game.getChosenComponentIndex()).setChoice(false);
                        }
                        Game.setChosenComponent(null);
                    } else {
                        Game.setPaintingComponent(null);
                    }
                    for (AreaButton component : componentsAreas) {
                        component.pressed(false);
                    }
                    component.pressed(true);
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
                if (Game.getPaintingComponent() != null) {
                    Logs.writeMessage("Click in work area. Current component: " + Game.getPaintingComponent().getName());
                    Game.getPaintingComponent().setLocation(e.getX() - Game.getPaintingComponent().getWidth() / 2, e.getY() + 32 - Game.getPaintingComponent().getHeight() / 2);
                    Game.getAtomics().add(new RadicalComponent(Game.getPaintingComponent()));
                    mainPanel.add(Game.getAtomics().get(Game.getAtomics().size() - 1));
                    mainPanel.repaint();
                } else {
                    Logs.writeMessage("Click in work area. Current component: " + null);
                    for (int i = 0; i < Game.getAtomics().size(); i++) {
                        if (Game.getAtomics().get(i).getX() < e.getX()
                                && e.getX() < Game.getAtomics().get(i).getX() + Game.getAtomics().get(i).getWidth()
                                && Game.getAtomics().get(i).getY() - workArea.getY() < e.getY()
                                && e.getY() < Game.getAtomics().get(i).getY() + Game.getAtomics().get(i).getHeight() - workArea.getY()) {
                            Logs.writeMessage("Choose " + i + "th component: " + Game.getAtomics().get(i).toDoubleDotsString());
                            System.out.println("Choose " + i + "th component: " + Game.getAtomics().get(i).toDoubleDotsString());
                            if (Game.getChosenComponent() == null) {
                                Game.setChosenComponent(new RadicalComponent(Game.getAtomics().get(i)));
                                Game.setChosenComponentIndex(i);
                                Game.getAtomics().get(Game.getChosenComponentIndex()).setChoice(true);
                            } else {
                                if (Game.getAtomics().get(i).getLinkPointer() < Game.getAtomics().get(i).getValence() &&
                                        Game.getChosenComponent().getLinkPointer() < Game.getChosenComponent().getValence() &&
                                        i != Game.getChosenComponentIndex()) {
                                    Game.getAtomics().get(i).getLinks()[Game.getAtomics().get(i).getLinkPointer()] = Game.getChosenComponentIndex();
                                    Game.getAtomics().get(i).incLinkPointer();
                                    Game.getAtomics().get(Game.getChosenComponentIndex()).getLinks()[Game.getChosenComponent().getLinkPointer()] = i;
                                    Game.getAtomics().get(Game.getChosenComponentIndex()).incLinkPointer();
                                    Game.setChosenComponent(null);
                                    System.out.println("Link atom " + i + " with " + Game.getChosenComponentIndex());
                                    Game.getAtomics().get(Game.getChosenComponentIndex()).setChoice(false);
                                }
                            }
                            repaintMolecule();
                            return;
                        }
                    }
                    Game.setChosenComponent(null);
                    if (Game.getChosenComponentIndex() != -1) {
                        Game.getAtomics().get(Game.getChosenComponentIndex()).setChoice(false);
                    }
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
                    Game.Save(filenameTextArea.getText());
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
                    Game.Load(filenameTextArea.getText());
                    Logs.writeMessage("Click load");
                    //repaint all
                    repaintMolecule();
                } catch (Exception exception) {
                    Logs.writeMessage(exception.toString());
                }
            }
        });

        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getAtomics().clear();
                repaintMolecule();
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
        mainPanel.add(buttonClear);
        mainPanel.add(filenameTextArea);
        mainPanel.add(fileArea);
        mainPanel.add(workArea);
        for (AreaButton componentsArea : componentsAreas) {
            mainPanel.add(componentsArea);
        }
        for (RadicalComponent component : Game.getAtomics()) {
            mainPanel.add(component);
        }
        JLine line = new JLine(new Rectangle(0, 0, mainFrame.getWidth(), mainFrame.getHeight()), Game.getAtomics());
        line.setVisible(true);
        mainPanel.add(line);
        mainFrame.setContentPane(mainPanel);
        Logs.writeMessage("Repaint all");
    }
}


class JLine extends JComponent {
    ArrayList<RadicalComponent> atomics;

    public JLine(Rectangle bounds, ArrayList<RadicalComponent> atomics) {
        super();
        this.setBounds(bounds);
        this.atomics = new ArrayList<>();
        this.atomics.addAll(atomics);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        ArrayList<AllBinds> binds = new ArrayList<>();
        binds.add(new AllBinds(-1, -1, -1));
        for (int j = 0; j < atomics.size(); j++) {
            for (int i = 0; i < atomics.get(j).getValence(); i++) {
                if (atomics.get(j).getLinks()[i] != -1) {
                    boolean isBindsEmpty = true;
                    //int q = Game.getAtomics().indexOf(Game.getAtomics().get(i));
                    for (AllBinds bind : binds) {
                        if (/*(bind.node1 == j && bind.node2 == getAtomics().get(j).links[i]) ||*/ (bind.node1 == atomics.get(j).getLinks()[i] && bind.node2 == j)) {
                            bind.count++;
                            //System.out.println("=*=");
                            isBindsEmpty = false;
                            break;
                        } else {
                            isBindsEmpty = true;
                            //binds.add(new AllBinds(j, getAtomics().get(j).links[i], 0));
                            //System.out.println("=|=");
                        }
                    }
                    if (isBindsEmpty) {
                        binds.add(new AllBinds(j, atomics.get(j).getLinks()[i], 0));
                        //System.out.println("=+=");
                    }
                    /*Line2D line = new Line2D.Double(component.getX()+component.getWidth()/2,
                            component.getY()+component.getHeight()/2,
                            getAtomics().get(component.links[i]).getX()+getAtomics().get(component.links[i]).getWidth()/2,
                            getAtomics().get(component.links[i]).getY()+getAtomics().get(component.links[i]).getHeight()/2);
                    g2.draw(line);*/
                }
            }

            //component.paintComponent(g);
        }
        for (AllBinds bind : binds) {
            //System.out.println("="+bind.node1+"="+bind.node2+"="+bind.count);
            switch (bind.count) {
                case 1:
                    g2.setColor(Color.BLACK);
                    Line2D line1 = new Line2D.Double(Game.getAtomics().get(bind.node1).getX() + Game.getAtomics().get(bind.node1).getWidth() / 2,
                            Game.getAtomics().get(bind.node1).getY() + Game.getAtomics().get(bind.node1).getHeight() / 2,
                            Game.getAtomics().get(bind.node2).getX() + Game.getAtomics().get(bind.node2).getWidth() / 2,
                            Game.getAtomics().get(bind.node2).getY() + Game.getAtomics().get(bind.node2).getHeight() / 2);
                    g2.draw(line1);
                    break;
                case 2:
                    g2.setColor(Color.BLACK);
                    int qX2 = 0;
                    int qY2 = 0;
                    if (Math.abs(Game.getAtomics().get(bind.node1).getX() - Game.getAtomics().get(bind.node2).getX()) <
                            Math.abs(Game.getAtomics().get(bind.node1).getY() - Game.getAtomics().get(bind.node2).getY())) {
                        qX2 = 2;
                    } else {
                        qY2 = 2;
                    }
                    Line2D line21 = new Line2D.Double(Game.getAtomics().get(bind.node1).getX() + qX2 + Game.getAtomics().get(bind.node1).getWidth() / 2,
                            Game.getAtomics().get(bind.node1).getY() + qY2 + Game.getAtomics().get(bind.node1).getHeight() / 2,
                            Game.getAtomics().get(bind.node2).getX() + qX2 + Game.getAtomics().get(bind.node2).getWidth() / 2,
                            Game.getAtomics().get(bind.node2).getY() + qY2 + Game.getAtomics().get(bind.node2).getHeight() / 2);
                    g2.draw(line21);
                    Line2D line22 = new Line2D.Double(Game.getAtomics().get(bind.node1).getX() - qX2 + Game.getAtomics().get(bind.node1).getWidth() / 2,
                            Game.getAtomics().get(bind.node1).getY() - qY2 + Game.getAtomics().get(bind.node1).getHeight() / 2,
                            Game.getAtomics().get(bind.node2).getX() - qX2 + Game.getAtomics().get(bind.node2).getWidth() / 2,
                            Game.getAtomics().get(bind.node2).getY() - qY2 + Game.getAtomics().get(bind.node2).getHeight() / 2);
                    g2.draw(line22);
                    break;
                case 3:
                    g2.setColor(Color.BLACK);
                    int qX3 = 0;
                    int qY3 = 0;
                    if (Math.abs(Game.getAtomics().get(bind.node1).getX() - Game.getAtomics().get(bind.node2).getX()) <
                            Math.abs(Game.getAtomics().get(bind.node1).getY() - Game.getAtomics().get(bind.node2).getY())) {
                        qX3 = 2;
                    } else {
                        qY3 = 2;
                    }
                    Line2D line31 = new Line2D.Double(Game.getAtomics().get(bind.node1).getX() + Game.getAtomics().get(bind.node1).getWidth() / 2,
                            Game.getAtomics().get(bind.node1).getY() + Game.getAtomics().get(bind.node1).getHeight() / 2,
                            Game.getAtomics().get(bind.node2).getX() + Game.getAtomics().get(bind.node2).getWidth() / 2,
                            Game.getAtomics().get(bind.node2).getY() + Game.getAtomics().get(bind.node2).getHeight() / 2);
                    g2.draw(line31);
                    Line2D line32 = new Line2D.Double(Game.getAtomics().get(bind.node1).getX() + qX3 + Game.getAtomics().get(bind.node1).getWidth() / 2,
                            Game.getAtomics().get(bind.node1).getY() + qY3 + Game.getAtomics().get(bind.node1).getHeight() / 2,
                            Game.getAtomics().get(bind.node2).getX() + qX3 + Game.getAtomics().get(bind.node2).getWidth() / 2,
                            Game.getAtomics().get(bind.node2).getY() + qY3 + Game.getAtomics().get(bind.node2).getHeight() / 2);
                    g2.draw(line32);
                    Line2D line33 = new Line2D.Double(Game.getAtomics().get(bind.node1).getX() - qX3 + Game.getAtomics().get(bind.node1).getWidth() / 2,
                            Game.getAtomics().get(bind.node1).getY() - qY3 + Game.getAtomics().get(bind.node1).getHeight() / 2,
                            Game.getAtomics().get(bind.node2).getX() - qX3 + Game.getAtomics().get(bind.node2).getWidth() / 2,
                            Game.getAtomics().get(bind.node2).getY() - qY3 + Game.getAtomics().get(bind.node2).getHeight() / 2);
                    g2.draw(line33);
                    break;
            }
        }

    }
}

class AllBinds {
    public int node1;
    public int node2;
    public int count;


    public AllBinds(int node1, int node2, int count) {
        this.node1 = node1;
        this.node2 = node2;
        this.count = count;
    }
}