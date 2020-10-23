package com.company.model.clothes;

/**
 * linen for washing in Washing Machine
 * extends class Linen
 * add field Color
 */
public class LinenColor extends Linen {

    private final Color color;

    /**
     * constructor
     * using Linen constructor
     * @param  _temperatureWashing washing temperature
     * @param _temperatureIroning ironing temperature
     * @param _color color of linen
     */
    public LinenColor(int _temperatureWashing, int _temperatureIroning, Color _color) {
        super(_temperatureWashing, _temperatureIroning);
        color = _color;
    }

    /**
     * null-object constructor
     */
    public  LinenColor(){
        super();
        color = null;
    }

    /**
     * get linen color
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @return Linen.toString() + "\ncolor: " + color
     */
    @Override
    public String toString() {
        return super.toString() + "\ncolor: " + color;
    }
}
