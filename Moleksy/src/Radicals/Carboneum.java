package Radicals;

import java.awt.*;

/**
 * extends RadicalComponent as Carboneum
 */
public class Carboneum extends RadicalComponent{
    /**
     * radius 6
     * color red
     * valence 4
     */
    public Carboneum() {
        super(new Rectangle(0,0,12,12), Color.RED, "C", Stat.Atom, 4);
    }
}

