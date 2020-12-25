import Radicals.RadicalComponent;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * model class
 */
public class Game {
    /**
     * get painting component
     * @return painting component
     */
    public static RadicalComponent getPaintingComponent() {
        return paintingComponent;
    }

    /**
     * set painting component
     * @param paintingComponent painting component
     */
    public static void setPaintingComponent(RadicalComponent paintingComponent) {
        Game.paintingComponent = paintingComponent;
    }

    /**
     * get chosen component
     * @return chosen component
     */
    public static RadicalComponent getChosenComponent() {
        return chosenComponent;
    }

    /**
     * set chosen component
     * @param chosenComponent chosen component
     */
    public static void setChosenComponent(RadicalComponent chosenComponent) {
        Game.chosenComponent = chosenComponent;
    }

    /**
     * get chosen component index
     * @return chosen component index
     */
    public static int getChosenComponentIndex() {
        return chosenComponentIndex;
    }

    /**
     * set chosen component index
     * @param chosenComponentIndex chosen component index
     */
    public static void setChosenComponentIndex(int chosenComponentIndex) {
        Game.chosenComponentIndex = chosenComponentIndex;
    }

    /**
     * get atomics
     * @return atomics
     */
    public static ArrayList<RadicalComponent> getAtomics() {
        return atomics;
    }

    /**
     * get params
     * @return params
     */
    public static HashMap<String, HashMap<String, String>> getParams() {
        return params;
    }

    private static RadicalComponent paintingComponent;
    private static RadicalComponent chosenComponent;
    private static int chosenComponentIndex = -1;
    private static final ArrayList<RadicalComponent> atomics = new ArrayList<>();
    private static final HashMap<String, HashMap<String, String>> params = new HashMap<>();

    /**
     * load constants for sizes in frame from file
     * @throws Exception
     */
    public static void init() throws Exception {
        FileReader fileReader = new FileReader("params.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        String key = "";
        while (line != null) {
            try {
                if (line.equals("$")) {
                    line = reader.readLine();
                    continue;
                }
                if (line.split("%")[0].equals("!")) {
                    key = line.split("%")[1];
                    params.put(key, new HashMap<>());
                } else {
                    params.get(key).put(line.split(": ")[0], line.split(": ")[1]);
                }
            }
            catch(Exception e) {
                Logs.writeMessage("cant read line from params.txt");
            }
            line = reader.readLine();
        }
    }

    /**
     * save molecule
     * @param filename filename for saving (without .txt)
     * @throws Exception
     */
    public static void Save(String filename) throws Exception {
        FileWriter fileWriter = new FileWriter(filename+".txt");
        for(RadicalComponent components : atomics){
            fileWriter.write(components.toDoubleDotsString()+"\n");
        }
        fileWriter.close();
    }

    /**
     * load molecule
     * @param filename filename for loading (without .txt)
     * @throws Exception
     */
    public static void Load(String filename) throws Exception {
        FileReader fileReader = new FileReader(filename+".txt");
        BufferedReader reader = new BufferedReader(fileReader);
        atomics.clear();
        String line = reader.readLine();
        while (line != null) {
            atomics.add(new RadicalComponent(line));
            line = reader.readLine();
        }
    }

}
