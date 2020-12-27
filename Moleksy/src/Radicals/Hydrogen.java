package Radicals;

import java.awt.*;

/**
 * extends RadicalComponent as hydrogen
 */
public class Hydrogen extends RadicalComponent{
    /**
     * radius 5
     * color cyan
     * valence 1
     */
    public Hydrogen() {
        super(new Rectangle(0,0,10,10), Color.CYAN, "H", Stat.Atom, 1);
    }

}
