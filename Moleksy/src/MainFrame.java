import Paintings.AreaButton;
import Paintings.JLines;
import Paintings.RectangleArea;
import Radicals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    public static void init() throws Exception {
        Game.init();
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

        //init-ing text field for naming file for save/load
        filenameTextArea = new JTextArea("filename");
        filenameTextArea.setBounds(
                5,
                5,
                buttonLoad.getX() - 5 - 5,
                20
        );
        filenameTextArea.setVisible(true);

        //init-ing clear button
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
     * set which radical will chosen to painting or do binding
     * @param component area button
     */
    private static void choiceAction(AreaButton component){
        Logs.writeMessage("Click button " + component.getComponent().getName());
        //if not binding
        if (!component.getComponent().getName().equals(new Binding().getName())) {
            Game.setPaintingComponent(new RadicalComponent(component.getComponent()));
            if (Game.getChosenComponentIndex() != -1) {
                Game.getAtomics().get(Game.getChosenComponentIndex()).setChoice(false);
            }
            Game.setChosenComponent(null);
        }
        //if binding
        else {
            Game.setPaintingComponent(null);
        }
        //set all area button as unpressed
        for (AreaButton components : componentsAreas) {
            components.pressed(false);
        }
        //press chosen button area
        component.pressed(true);
        //repainting
        mainPanel.repaint();
    }

    /**
     * paint new component on work area
     * @param e mouse event
     */
    private static void paintComponent(MouseEvent e){
        Logs.writeMessage("Click in work area. Current component: " + Game.getPaintingComponent().getName());
        //set location of component
        Game.getPaintingComponent().setLocation(
                e.getX() - Game.getPaintingComponent().getWidth() / 2,
                e.getY() + fileArea.getHeight() - Game.getPaintingComponent().getHeight() / 2);
        //add in frame and Game.atomics
        Game.getAtomics().add(new RadicalComponent(Game.getPaintingComponent()));
        mainPanel.add(Game.getAtomics().get(Game.getAtomics().size() - 1));
        //repaint
        mainPanel.repaint();
    }

    /**
     * cjoise component or if already bind with
     * @param e mouse event
     */
    private static void choiceOrBindComponent(MouseEvent e){
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

    /**
     * action when click mouse on work area
     * @param e mouse event
     */
    private static void clickToWorkArea(MouseEvent e){
        if (Game.getPaintingComponent() != null) {
            paintComponent(e);
        } else {
            choiceOrBindComponent(e);
        }
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
                   choiceAction(component);
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
                clickToWorkArea(e);
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
                    new Exceptions.JException(exception.getMessage());
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
                    new Exceptions.JException(exception.getMessage());
                }
            }
        });

        //listener for clicking on button clear
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
        //add lines-bindings on frame
        JLines line = new JLines(new Rectangle(0, 0, mainFrame.getWidth(), mainFrame.getHeight()), Game.getAtomics());
        line.setVisible(true);
        mainPanel.add(line);
        mainFrame.setContentPane(mainPanel);
        Logs.writeMessage("Repaint all");
    }
}
