package com.company.Model.clothes;

/**
 * linen for washing in Washing Machine
 * consist of washing and ironing temperatures
 */
abstract class Linen {
    protected int temperatureWashing;
    protected int temperatureIroning;

    /**
     * constructor
     * !if param less than 0 than it's field sets as 0!
     * @param _temperatureWashing washing temperature
     * @param _temperatureIroning ironing temperature
     */
    protected Linen(int _temperatureWashing, int _temperatureIroning) {
        this.temperatureWashing = Math.max(_temperatureWashing, 0);
        this.temperatureIroning = Math.max(_temperatureIroning, 0);
    }

    /**
     * null-object constructor
     */
    protected  Linen() {
        this.temperatureWashing = 0;
        this.temperatureIroning = 0;
    }

    /**
     * get washing temperature
     * @return washing temperature
     */
    public int getTemperatureWashing() {
        return temperatureWashing;
    }

    /**
     * ironing temperature
     * @return ironing temperature
     */
    public int getTemperatureIroning() {
        return temperatureIroning;
    }

    /**
     *
     * @return "washing T: " + temperatureWashing + "\niron T: " + temperatureIroning
     */
    @Override
    public String toString() {
        return "washing T: " + temperatureWashing + "\niron T: " + temperatureIroning;
    }
}
