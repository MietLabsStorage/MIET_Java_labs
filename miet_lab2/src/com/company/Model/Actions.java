package com.company.Model;

import com.company.Controller;
import com.company.Model.clothes.LinenColor;
import com.company.View.Tag;

import java.util.ArrayList;
import java.util.List;

public class Actions {
    private  static WashingMachine wm = new WashingMachine();
    private  static List<LinenColor> dirtyHeap = new ArrayList<>();

    public static void runAction(int actNum) throws Exception {
        if(actNum == Tag.AddWMPowder.getCode()){
            wm.setPowder(Controller.getString());
            return;
        }
        if(actNum == Tag.AddWMConditioner.getCode()){
            wm.setPowder(Controller.getString());
            return;
        }
        if(actNum == Tag.AddWMColor.getCode()){
            wm.setColor(Controller.getColor());
            return;
        }
        if(actNum == Tag.AddWMTemperature.getCode()){
            wm.setTemperature(Controller.getInt());
            return;
        }
        if(actNum == Tag.LoadWMLinen.getCode()){
            dirtyHeap.addAll(wm.Load(dirtyHeap));
            return;
        }
        if(actNum == Tag.UnloadWMLinen.getCode()){
            wm.Unload();
            return;
        }
        if(actNum == Tag.AddDirtyLinen.getCode()){
            dirtyHeap.add(new LinenColor(Controller.getInt(),Controller.getInt(),Controller.getColor()));
            return;
        }
        if(actNum == Tag.RunWM.getCode()){
            com.company.View.Console.Out.Show(Tag.RunWM, Boolean.toString(wm.run()));
            return;
        }

    }
}
