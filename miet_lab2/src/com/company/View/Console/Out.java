package com.company.View.Console;

import com.company.Controller;
import com.company.Model.Actions;
import com.company.Model.WashingMachine;
import com.company.Model.clothes.Color;
import com.company.Model.clothes.LinenColor;
import com.company.View.Tag;

import java.util.ArrayList;

/**
 * print out message in console
 */
public class Out {

    /**
     * menu of all acts and their codes
     * @throws Exception
     */
    public static void ShowActionsMenu() throws Exception {
        System.out.println("\n--------------------------------");
        System.out.print(
                showByTag(Tag.AddWMPowder) +
                        showByTag(Tag.AddWMConditioner) +
                        showByTag(Tag.AddWMColor) +
                        showByTag(Tag.AddWMTemperature) +
                        showByTag(Tag.LoadWMLinen) +
                        showByTag(Tag.UnloadWMLinenAndShow) +
                        showByTag(Tag.RunWM) +
                        showByTag(Tag.StatusWM) +
                        showByTag(Tag.AddDirtyLinen) +
                        showByTag(Tag.ShowDirtyLinen) +
                        "\nWrite a number for choice act:\n"
        );
        Actions.runAction(Controller.getInt());
    }

    public static void showLoginMenu(){

    }

    /**
     * message of addin something in WM
     * @param addedSmth 'something'
     */
    public static void ShowAddWMSmth(String addedSmth){
        System.out.println("Add " + addedSmth + ": ");
    }

    /**
     * message of succes of running
     * @param isSucces
     */
    public static void ShowRunWM(boolean isSucces) {
        System.out.println("Washing's succes is: " + Boolean.toString(isSucces));
    }

    /**
     * show all linens in linens
     * @param linens linens that will be showed
     */
    public static void ShowLinens(ArrayList<LinenColor> linens) {
        if(linens.isEmpty()){
            System.out.println("no linens");
            return;
        }
        else {
            for (LinenColor linen : linens) {
                System.out.println(linen);
                System.out.println();
            }
        }
    }

    /**
     * show status about washing mashine
     * @param wm washing mashine
     */
    public static void ShowStatusWM(WashingMachine wm) {
        System.out.println(
                "Powder: " + wm.getPowder() + "\n" +
                        "Conditioner: " + wm.getConditioner() + "\n" +
                        "Color: " + wm.getColor() + "\n" +
                        "Temperature: " + wm.getTemperature() + "\n" +
                        "Is loaded: " + wm.isLoaded()
        );
    }

    /**
     * show "temperatureWashing\n temperatureIroning\n color: "
     */
    public static void ShowLinenColorStructure(){
        System.out.print("temperatureWashing\n temperatureIroning\n color: ");
        ShowColors();
    }

    /**
     * show colors names
     */
    public static void ShowColors() {
        System.out.println(
                "(" + Color.light.toString() + ", "
                        + Color.dark.toString() + ", "
                        + Color.multycolor.toString() + ")"
        );
    }

    //for create shown tag line
    private static String showByTag(Tag tag){
        try {
            return tag.getCode() + ":  " + tag.toString() + "\n";
        }
        catch (Exception e){
            com.company.View.Console.Err.PrintErr(e.toString());
        }
        return "";
    }
}
