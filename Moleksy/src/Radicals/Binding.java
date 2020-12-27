package Radicals;

import java.awt.*;

/**
 * extends RadicalComponent as binding
 */
public class Binding extends RadicalComponent {

    /**
     * radius 10
     * color black
     * valence 0
     */
    public Binding() {
        super(new Rectangle(0,0,10,10), Color.BLACK, "choice/bind", Stat.Binding, 0);
    }

}
