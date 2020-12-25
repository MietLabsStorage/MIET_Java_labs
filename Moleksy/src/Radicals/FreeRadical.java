package Radicals;

import java.awt.*;

/**
 * extends RadicalComponent as free radical
 */
public class FreeRadical extends RadicalComponent{
    /**
     * radius 10
     * color non
     * valence 1
     */
    public FreeRadical() {
        super(new Rectangle(0,0,20,20), new Color(0,0,0,0), "R", Stat.Radical, 1);
    }
}
