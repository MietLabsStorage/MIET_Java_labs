package com.company.model;

import com.company.model.clothes.*;

import java.util.ArrayList;

/**
 * Washing machine
 */
public class WashingMachine {
    /**
     * get powder
     * @return powder
     */
    public String getPowder() {
        return powder;
    }

    /**
     * set powder
     * @param powder will set in this.powder
     */
    public void setPowder(String powder) {
        this.powder = powder;
    }

    /**
     * get conditioner
     * @return conditioner
     */
    public String getConditioner() {
        return conditioner;
    }

    /**
     * set conditioned
     * @param conditioner will set in this.conditioner
     */
    public void setConditioner(String conditioner) {
        this.conditioner = conditioner;
    }

    /**
     * get color
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * set color
     * @param color will set in this.color
     * @throws Exception throw if color == null
     */
    public void setColor(Color color) throws Exception {
        if(color != null)
            this.color = color;
        else throw new Exception("color is null");
    }

    /**
     * get temperature
     * @return temperature
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * set temperature
     * @param temperature will set in this.temperature
     * @throws Exception throw if temperature <= 0
     */
    public void setTemperature(int temperature) throws Exception {
        if(temperature > 0 && temperature < 100)
            this.temperature = temperature;
        else throw new Exception("temperature must be in (0;100)");
    }

    /**
     * is there something in WM
     * @return true if one or more linens in WM
     */
    public boolean isLoaded(){
        return !(inputLinens.isEmpty());
    }

    public ArrayList<LinenColor> getInputLinens() {
        return inputLinens;
    }

    private String powder;
    private String conditioner;
    private Color color;
    private int temperature;
    private final ArrayList<LinenColor> inputLinens;

    /**
     * null-object constructor
     */
    public WashingMachine(){
        powder = " ";
        conditioner = " ";
        color = Color.nonColor;
        temperature = 0;
        inputLinens = new ArrayList<LinenColor>();
    }

    /**
     * Load linens in WM
     * @param linens linens that will try be loaded
     * @return linens that was not load
     * @throws Exception throw if already loaded or there is not colors or temperature
     */
    public ArrayList<LinenColor> load(ArrayList<LinenColor> linens) throws Exception {
        ArrayList<LinenColor> outputLinen = new ArrayList<LinenColor>();
        if(color != (new WashingMachine()).color && temperature != (new WashingMachine()).temperature) {
            for (LinenColor linen : linens) {
                if (linen.getTemperatureWashing() == temperature && linen.getColor() == color) {
                    inputLinens.add(linen);
                }
                else{
                    outputLinen.add(linen);
                }
            }
        }
        else{
            throw new Exception("there is not colors or temperature");
        }
        return outputLinen;
    }

    /**
     * unload WM (do empty)
     * @return linens that were in WM
     */
    public ArrayList<LinenColor> unload(){
        ArrayList<LinenColor> outputLinen = new ArrayList<LinenColor>(inputLinens);
        inputLinens.clear();
        return outputLinen;
    }

    /**
     * wash linens in WM
     * @return is washing was success
     */
    public boolean run(){
        WashingMachine nullObject = new WashingMachine();
        if(!powder.equals(nullObject.powder) && !conditioner.equals(nullObject.conditioner) && !inputLinens.isEmpty()){
            powder = nullObject.powder;
            conditioner = nullObject.conditioner;
            color = nullObject.color;
            temperature = nullObject.temperature;
            return true;
        }
        return false;
    }

}
