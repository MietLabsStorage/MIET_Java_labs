package com.company.Model.clothes;

public abstract class Linen {
    protected int temperatureWashing;
    protected int temperatureIroning;

    public Linen(int _temperatureWashing, int _temperatureIroning) {
        this.temperatureWashing = Math.max(_temperatureWashing, 0);
        this.temperatureIroning = Math.max(_temperatureWashing, 0);
    }

    public  Linen() {
        this.temperatureWashing = 0;
        this.temperatureIroning = 0;
    }

    public int getTemperatureWashing() {
        return temperatureWashing;
    }

    public int getTemperatureIroning() {
        return temperatureIroning;
    }

    @Override
    public String toString() {
        return "washing T: " + temperatureWashing + "\niron T: " + temperatureIroning;
    }
}
