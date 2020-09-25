package com.company.Model;

import com.company.Controller;
import com.company.Model.clothes.LinenColor;
import com.company.View.Tag;

import java.util.ArrayList;
import java.util.List;

public class Actions {
    private  static WashingMachine wm;
    private  static List<LinenColor> dirtyHeap = new ArrayList<>();

    public static void runAction(int actNum){
        if(actNum == Tag.AddWMPowder.getCode()){
            wm.setPowder(Controller.getString());
            return;
        }
        if(actNum == Tag.AddWMConditioner.getCode()){
            wm.setPowder(Controller.getString());
            return;
        }
        if(actNum == Tag.AddWMColor.getCode()){
            return;
        }
        if(actNum == Tag.AddWMTemperature.getCode()){
            return;
        }
        if(actNum == Tag.LoadWMLinen.getCode()){
            return;
        }
        if(actNum == Tag.UnloadWMLinen.getCode()){
            return;
        }
        if(actNum == Tag.AddDirtyLinen.getCode()){
            return;
        }
        if(actNum == Tag.RunWM.getCode()){
            com.company.View.Console.Out.Show(Tag.RunWM, Boolean.toString(wm.run()));
            return;
        }

    }
}
