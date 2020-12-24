import Radicals.Carboneum;
import Radicals.RadicalComponent;

import java.io.*;
import java.util.ArrayList;

public class Game {
    public static RadicalComponent paintingComponent;
    public static RadicalComponent chosenComponent;
    public static int chosenComponentIndex;
    public static ArrayList<RadicalComponent> atomics = new ArrayList<>();

public static int count = 0;

    public static void Save() throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt");
        for(RadicalComponent components : atomics){
            fileWriter.write(components.toDubleDotsString()+"\n");
        }
        fileWriter.close();
    }

    public static void Load() throws IOException {
        FileReader fileReader = new FileReader("output.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        atomics.clear();
        String line = reader.readLine();
        while (line != null) {
            atomics.add(new RadicalComponent(line));
            line = reader.readLine();
        }
        for(RadicalComponent atom : atomics){
            System.out.println(atom.toDubleDotsString());
        }
    }

}
