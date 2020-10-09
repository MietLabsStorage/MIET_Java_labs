package com.company.Model;

import com.company.Controller;
import com.company.Model.clothes.LinenColor;
import com.company.View.Tag;

import java.util.ArrayList;

/**
 * Model class
 */
public class Actions {
    private  static final WashingMachine wm = new WashingMachine();
    private  static final ArrayList<LinenColor> dirtyHeap = new ArrayList<>();

    /**
     * do any action against tag's int code
     * @param actNum tag's int code
     * @throws Exception
     */
    public static void runAction(int actNum) throws Exception {

        if(actNum == Tag.AddWMPowder.getCode()){
            com.company.View.Console.Out.ShowAddWMSmth("powder");
            wm.setPowder(Controller.getString());
            return;
        }

        if(actNum == Tag.AddWMConditioner.getCode()){
            com.company.View.Console.Out.ShowAddWMSmth("conditioner");
            wm.setConditioner(Controller.getString());
            return;
        }

        if(actNum == Tag.AddWMColor.getCode()){
            com.company.View.Console.Out.ShowAddWMSmth("color");
            com.company.View.Console.Out.ShowColors();
            wm.setColor(Controller.getColor());
            return;
        }

        if(actNum == Tag.AddWMTemperature.getCode()){
            try {
                com.company.View.Console.Out.ShowAddWMSmth("temperature");
                wm.setTemperature(Controller.getInt());
            }
            catch (Exception e){
                com.company.View.Console.Err.PrintErr(e.toString());
            }
            return;
        }

        if(actNum == Tag.LoadWMLinen.getCode()){
            try {
                dirtyHeap.addAll(wm.Load(dirtyHeap));
            }
            catch (Exception e){
                com.company.View.Console.Err.PrintErr(e.toString());
            }
            return;
        }

        if(actNum == Tag.UnloadWMLinenAndShow.getCode()){
            com.company.View.Console.Out.ShowLinens(wm.Unload());
            return;
        }

        if(actNum == Tag.RunWM.getCode()){
            com.company.View.Console.Out.ShowRunWM(wm.run());
            return;
        }

        if(actNum == Tag.StatusWM.getCode()){
            com.company.View.Console.Out.ShowStatusWM(wm);
            return;
        }

        if(actNum == Tag.AddDirtyLinen.getCode()){
            com.company.View.Console.Out.ShowLinenColorStructure();
            dirtyHeap.add(new LinenColor(Controller.getInt(),Controller.getInt(),Controller.getColor()));
            return;
        }

        if(actNum == Tag.ShowDirtyLinen.getCode()){
            com.company.View.Console.Out.ShowLinens(dirtyHeap);
            return;
        }

        com.company.View.Console.Err.PrintErr("no so action");

    }
}
