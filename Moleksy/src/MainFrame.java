import Paintings.AreaButton;
import Paintings.JLines;
import Paintings.RectangleArea;
import Radicals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private static JButton buttonClear;
    private static JButton buttonDel;
    private static JFileChooser fileChooser;

    private static int keyPressedCode = 0;

    /**
     * init frame and all components
     */
    public static void init() throws Exception {
        Game.init();
        Logs.writeMessage("Start init-ing Frame");

        //first part of init-ing Frame
        mainFrame = new JFrame(Game.getParams().get("mainFrame").get("name"));
        mainFrame.setSize(
                Integer.parseInt(Game.getParams().get("mainFrame").get("width")),
                Integer.parseInt(Game.getParams().get("mainFrame").get("height"))
        );
        mainFrame.setFocusable(true);

        //init-ing file chooser
        fileChooser = new JFileChooser(System.getProperty("user.dir") + "/Molecules");

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
                        Integer.parseInt(Game.getParams().get("fileArea").get("x-indent")),
                        Integer.parseInt(Game.getParams().get("fileArea").get("y-indent")),
                        mainFrame.getWidth() - Integer.parseInt(Game.getParams().get("fileArea").get("width-indent")),
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
                        Integer.parseInt(Game.getParams().get("workArea").get("x-indent")),
                        fileArea.getHeight() + Integer.parseInt(Game.getParams().get("workArea").get("y-indent")),
                        Integer.parseInt(Game.getParams().get("workArea").get("width")),
                        mainFrame.getHeight() - Integer.parseInt(Game.getParams().get("workArea").get("height-indent")) - fileArea.getHeight()
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
        Color colorAreaButton = new Color(
                Integer.parseInt(Game.getParams().get("areaButton").get("clrR")),
                Integer.parseInt(Game.getParams().get("areaButton").get("clrG")),
                Integer.parseInt(Game.getParams().get("areaButton").get("clrB"))
        );
        int xAreaButton = workArea.getX() + workArea.getWidth();
        int yAreaButtonStep = workArea.getHeight() / componentsAreas.length;
        int widthAreaButton = mainFrame.getWidth() - workArea.getWidth() - Integer.parseInt(Game.getParams().get("fileArea").get("width-indent"));
        componentsAreas[0] = new AreaButton(new Rectangle(xAreaButton, fileArea.getHeight(), widthAreaButton, yAreaButtonStep), colorAreaButton, new Hydrogen());
        componentsAreas[1] = new AreaButton(new Rectangle(xAreaButton, fileArea.getHeight() + yAreaButtonStep, widthAreaButton, yAreaButtonStep), colorAreaButton, new Nitrogen());
        componentsAreas[2] = new AreaButton(new Rectangle(xAreaButton, fileArea.getHeight() + yAreaButtonStep * 2, widthAreaButton, yAreaButtonStep), colorAreaButton, new Carboneum());
        componentsAreas[3] = new AreaButton(new Rectangle(xAreaButton, fileArea.getHeight() + yAreaButtonStep * 3, widthAreaButton, yAreaButtonStep), colorAreaButton, new Oxygen());
        componentsAreas[4] = new AreaButton(new Rectangle(xAreaButton, fileArea.getHeight() + yAreaButtonStep * 4, widthAreaButton, yAreaButtonStep), colorAreaButton, new FreeRadical());
        componentsAreas[5] = new AreaButton(new Rectangle(xAreaButton, fileArea.getHeight() + yAreaButtonStep * 5, widthAreaButton, yAreaButtonStep), colorAreaButton, new Binding());
        componentsAreas[5].pressed(true);

        //init-ing save button
        buttonSave = new JButton(Game.getParams().get("saveButton").get("name"));
        buttonSave.setBounds(
                mainFrame.getWidth() - Integer.parseInt(Game.getParams().get("saveButton").get("width")) - Integer.parseInt(Game.getParams().get("saveButton").get("x-indent")),
                Integer.parseInt(Game.getParams().get("saveButton").get("y-indent")),
                Integer.parseInt(Game.getParams().get("saveButton").get("width")),
                Integer.parseInt(Game.getParams().get("saveButton").get("height"))
        );
        buttonSave.setVisible(true);

        //init-ing load button
        buttonLoad = new JButton(Game.getParams().get("loadButton").get("name"));
        buttonLoad.setBounds(
                buttonSave.getX() - Integer.parseInt(Game.getParams().get("loadButton").get("width")) - Integer.parseInt(Game.getParams().get("loadButton").get("x-indent")),
                Integer.parseInt(Game.getParams().get("loadButton").get("y-indent")),
                Integer.parseInt(Game.getParams().get("loadButton").get("width")),
                Integer.parseInt(Game.getParams().get("loadButton").get("height")));
        buttonLoad.setVisible(true);

        //init-ing clear button
        buttonClear = new JButton(Game.getParams().get("clearButton").get("name"));
        buttonClear.setBounds(
                Integer.parseInt(Game.getParams().get("clearButton").get("x-indent")),
                Integer.parseInt(Game.getParams().get("clearButton").get("y-indent")),
                Integer.parseInt(Game.getParams().get("clearButton").get("width")),
                Integer.parseInt(Game.getParams().get("clearButton").get("height"))
        );
        buttonClear.setVisible(true);

        //init-ing delete button
        buttonDel = new JButton(Game.getParams().get("deleteButton").get("name"));
        buttonDel.setBounds(
                buttonClear.getX() + buttonClear.getWidth() + Integer.parseInt(Game.getParams().get("deleteButton").get("x-indent")),
                Integer.parseInt(Game.getParams().get("deleteButton").get("y-indent")),
                Integer.parseInt(Game.getParams().get("deleteButton").get("width")),
                Integer.parseInt(Game.getParams().get("deleteButton").get("height"))
        );
        buttonDel.setVisible(true);

        //add components in panel
        mainPanel.add(buttonSave);
        mainPanel.add(buttonLoad);
        mainPanel.add(buttonClear);
        mainPanel.add(buttonDel);
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
     *
     * @param component area button
     */
    private static void choiceAction(AreaButton component) {
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
     *
     * @param e mouse event
     */
    private static void paintComponent(MouseEvent e) {
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
     *
     * @param e mouse event
     */
    private static void choiceOrBindComponent(MouseEvent e) {
        Logs.writeMessage("Click in work area. Current component: " + null);
        //try choice component
        for (int i = 0; i < Game.getAtomics().size(); i++) {
            if (Game.getAtomics().get(i).getX() < e.getX()
                    && e.getX() < Game.getAtomics().get(i).getX() + Game.getAtomics().get(i).getWidth()
                    && Game.getAtomics().get(i).getY() - workArea.getY() < e.getY()
                    && e.getY() < Game.getAtomics().get(i).getY() + Game.getAtomics().get(i).getHeight() - workArea.getY()) {
                Logs.writeMessage("Choose " + i + "th component: " + Game.getAtomics().get(i).toDoubleDotsString());
                //if not chosen
                if (Game.getChosenComponent() == null) {
                    //set chosen in Game
                    Game.setChosenComponent(new RadicalComponent(Game.getAtomics().get(i)));
                    Game.setChosenComponentIndex(i);
                    //set chosen into component
                    Game.getAtomics().get(Game.getChosenComponentIndex()).setChoice(true);
                }
                //if chosen (binding)
                else {
                    //if exist free valence-binds to bind
                    if (Game.getAtomics().get(i).getLinkPointer() < Game.getAtomics().get(i).getValence() &&
                            Game.getChosenComponent().getLinkPointer() < Game.getChosenComponent().getValence() &&
                            i != Game.getChosenComponentIndex()) {
                        //bind components
                        Game.getAtomics().get(i).getLinks()[Game.getAtomics().get(i).getLinkPointer()] = Game.getChosenComponentIndex();
                        Game.getAtomics().get(i).incLinkPointer();
                        Game.getAtomics().get(Game.getChosenComponentIndex()).getLinks()[Game.getChosenComponent().getLinkPointer()] = i;
                        Game.getAtomics().get(Game.getChosenComponentIndex()).incLinkPointer();
                        //reset chosen component
                        Game.setChosenComponent(null);
                        Game.getAtomics().get(Game.getChosenComponentIndex()).setChoice(false);
                    }
                }
                repaintMolecule();
                return;
            }
        }
        //if click on non-component - reset chosen component
        Game.setChosenComponent(null);
        if (Game.getChosenComponentIndex() != -1) {
            Game.getAtomics().get(Game.getChosenComponentIndex()).setChoice(false);
        }
        repaintMolecule();
    }

    private static void carryComponent(MouseEvent e) {
        for (int i = 0; i < Game.getAtomics().size(); i++) {
            if (Game.getAtomics().get(i).getX() < e.getX()
                    && e.getX() < Game.getAtomics().get(i).getX() + Game.getAtomics().get(i).getWidth()
                    && Game.getAtomics().get(i).getY() - workArea.getY() < e.getY()
                    && e.getY() < Game.getAtomics().get(i).getY() + Game.getAtomics().get(i).getHeight() - workArea.getY()){

                Game.getAtomics().get(i).setLocation(e.getX() - Game.getAtomics().get(i).getWidth() / 2,
                        e.getY() + fileArea.getHeight() - Game.getAtomics().get(i).getHeight() / 2);
                repaintMolecule();
                break;
            }
        }
    }

    /**
     * action when click mouse on work area
     *
     * @param e mouse event
     */
    private static void clickToWorkArea(MouseEvent e) {
        if (Game.getPaintingComponent() != null) {
            paintComponent(e);
        } else {
            choiceOrBindComponent(e);
        }
    }

    /**
     * delete component
     */
    private static void deleteComponent() {
        if (Game.getChosenComponent() != null) {
            Game.getAtomics().remove(Game.getChosenComponentIndex());
            for (int i = 0; i < Game.getAtomics().size(); i++) {
                //change in binds
                for (int j = 0; j < Game.getAtomics().get(i).getValence(); j++) {
                    if (Game.getAtomics().get(i).getLinks()[j] == Game.getChosenComponentIndex()) {
                        Game.getAtomics().get(i).resetLink(j);
                    }
                    if (Game.getAtomics().get(i).getLinks()[j] > Game.getChosenComponentIndex()) {
                        Game.getAtomics().get(i).decLink(j);
                    }
                }
                //replace -1 links in end
                for (int j = 0; j < Game.getAtomics().get(i).getValence(); j++) {
                    for (int k = j; k < Game.getAtomics().get(i).getValence(); k++) {
                        if (Game.getAtomics().get(i).getLinks()[j] == -1) {
                            int temp = Game.getAtomics().get(i).getLinks()[j];
                            Game.getAtomics().get(i).setLink(j, Game.getAtomics().get(i).getLinks()[k]);
                            Game.getAtomics().get(i).setLink(k, temp);
                        }
                    }
                }
                //set link pointer
                for (int j = 0; j < Game.getAtomics().get(i).getValence(); j++) {
                    if (Game.getAtomics().get(i).getLinks()[j] == -1) {
                        Game.getAtomics().get(i).setLinkPointer(j);
                        break;
                    }
                }
            }
            Game.setChosenComponent(null);
            Game.setChosenComponentIndex(-1);
            repaintMolecule();
        }
    }

    /**
     * save molecule
     */
    private static void save() {
        try {
            Logs.writeMessage("Click save");
            fileChooser.setDialogTitle("Сохранение файла");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(mainFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                Game.Save(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(mainFrame,
                        "Файл '" + fileChooser.getSelectedFile() + "' сохранен");
            }
        } catch (Exception exception) {
            Logs.writeMessage(exception.toString());
            new Exceptions.JException(exception.getMessage());
        }
    }

    /**
     * load molecule
     */
    private static void load() {
        try {
            Logs.writeMessage("Click load");
            fileChooser.setDialogTitle("Загрузка файла");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(mainFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                Game.Load(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(mainFrame,
                        "Файл '" + fileChooser.getSelectedFile() + "' загружен");
            }
            //repaint all
            repaintMolecule();
        } catch (Exception exception) {
            Logs.writeMessage(exception.toString());
            new Exceptions.JException(exception.getMessage());
        }
    }

    /**
     * key on keyboard pressed
     * @param e
     */
    private static void keyboardPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            keyPressedCode = KeyEvent.VK_CONTROL;
        }
        if (e.getKeyCode() == KeyEvent.VK_S && keyPressedCode == KeyEvent.VK_CONTROL) {
            save();
            keyPressedCode = 0;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_O && keyPressedCode == KeyEvent.VK_CONTROL) {
            load();
            keyPressedCode = 0;
            return;
        }
    }

    /**
     * clear work area
     */
    private static void clearAllComponents() {
        Game.getAtomics().clear();
        repaintMolecule();
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
            public void mousePressed(MouseEvent e) { /*carryComponent(e); repaintMolecule();*/ }

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

        workArea.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                carryComponent(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        //listener for clicking on button save
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        //listener for clicking on button load
        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });

        //listener for clicking on button clear
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllComponents();
            }
        });

        //listener for keyboard clicking
        mainFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyboardPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyPressedCode = 0;
            }
        });

        //listener for clicking on button delete
        buttonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteComponent();
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
        mainPanel.add(buttonDel);
        mainPanel.add(fileArea);
        mainPanel.add(workArea);
        for (AreaButton componentsArea : componentsAreas) {
            mainPanel.add(componentsArea);
        }
        for (RadicalComponent component : Game.getAtomics()) {
            mainPanel.add(component);
        }
        //add lines-bindings on frame
        JLines line = new JLines(new Rectangle(0, 0, mainFrame.getWidth(), mainFrame.getHeight()), Game.getAtomics(), new Binding().getColor());
        line.setVisible(true);
        mainPanel.add(line);
        mainFrame.setContentPane(mainPanel);
        Logs.writeMessage("Repaint all");
    }

}

