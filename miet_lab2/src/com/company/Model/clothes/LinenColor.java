package com.company.Model.clothes;

public class LinenColor extends Linen {

    private Color color;

    public LinenColor(int _temperatureWashing, int _temperatureIroning, Color _color) {
        super(_temperatureWashing, _temperatureIroning);
        color = _color;
    }

    public  LinenColor(){
        super();
        color = null;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return super.toString() + "\ncolor: " + color;
    }
}
