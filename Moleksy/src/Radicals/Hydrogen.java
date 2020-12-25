package Radicals;

import java.awt.*;

public class Hydrogen extends RadicalComponent{

    /**
     * extends RadicalComponent as hydrogen
     */
    public Hydrogen() {
        /**
         * radius 5
         * color cyan
         * valence 1
         */
        super(new Rectangle(0,0,10,10), Color.CYAN, "H", Stat.Atom, 1);
    }

}
