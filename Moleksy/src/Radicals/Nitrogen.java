package Radicals;

import java.awt.*;

/**
 * extends RadicalComponent as nitrogen
 */
public class Nitrogen extends RadicalComponent{
    /**
     * radius 7
     * color magenta
     * valence 3
     */
    public Nitrogen() {
        super(new Rectangle(0,0,14,14), Color.magenta, "N", Stat.Atom, 3);
    }
}
