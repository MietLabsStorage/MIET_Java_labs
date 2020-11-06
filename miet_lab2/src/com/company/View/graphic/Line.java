package com.company.View.graphic;

import java.awt.*;

/**
 * line class
 */
public class Line{
    public final int x1,y1;
    public final int x2,y2;
    public final java.awt.Color color;

    /**
     * constructor
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     */
    public Line(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
}