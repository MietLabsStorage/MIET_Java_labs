package com.company.Model;

import com.company.Model.clothes.*;

import java.util.List;

public class WashingMachine {
    public String getPowder() {
        return powder;
    }

    public void setPowder(String powder) {
        this.powder = powder;
    }

    public String getConditioner() {
        return conditioner;
    }

    public void setConditioner(String conditioner) {
        this.conditioner = conditioner;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) throws Exception {
        if(color != null)
            this.color = color;
        else throw new Exception("color is null");
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) throws Exception {
        if(temperature > 0)
            this.temperature = temperature;
        else throw new Exception("temperature must be positive");
    }

    private String powder;
    private String conditioner;
    private Color color;
    private int temperature;

    private List<LinenColor> inputLinens;

    public WashingMachine(){
        powder = "";
        conditioner = "";
        color = null;
        temperature = 0;
        inputLinens = null;
    }

    public List<LinenColor> Load(List<LinenColor> linens) throws Exception {
        List<LinenColor> outputLinen = null;
        if(inputLinens.isEmpty() && color != (new WashingMachine()).color && temperature != (new WashingMachine()).temperature) {
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
            throw new Exception("already loaded or there is not colors or temperature");
        }
        return outputLinen;
    }

    public List<LinenColor> Unload(){
        List<LinenColor> outputLinen = null;
        outputLinen.addAll(inputLinens);
        inputLinens.clear();
        return  outputLinen;
    }

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
