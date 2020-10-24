package com.company.View.Console;

import com.company.Controller;
import com.company.model.Actions;
import com.company.model.WashingMachine;
import com.company.model.clothes.Color;
import com.company.model.clothes.LinenColor;
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
        while(true){
            System.out.println("\nWhat_would_you_do?");
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
                            showByTag(Tag.SaveChanges) +
                            showByTag(Tag.SignOut) +
                            "Write a number for choice act:\n"
            );
            if(Actions.runAction(Controller.getInt()) == -1){
                break;
            }
        }
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
                        + Color.multicolor.toString() + ")"
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

    public static void SignInMenu() throws Exception {
        while(true){
            System.out.print(
                    showByTag(Tag.SignIn) +
                            showByTag(Tag.SignUp) +
                            showByTag(Tag.ShowUsers) +
                            showByTag(Tag.DeleteUser) +
                            showByTag(Tag.Exit) +
                            "Write a number for choice act:\n"
            );
            if(Actions.runSign(Controller.getInt()) == -1){
                return;
            }
        }
    }

    public static void ShowMessage(String msg) {
        System.out.println(msg);
    }
}
