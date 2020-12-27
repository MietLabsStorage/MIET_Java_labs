package Radicals;

import java.awt.*;

/**
 * extends RadicalComponent as oxygen
 */
public class Oxygen extends RadicalComponent{
    /**
     * radius 8
     * color blue
     * valence 24
     */
    public Oxygen() {
        super(new Rectangle(0,0,16,16), Color.blue, "O", Stat.Atom, 2);
    }
}
