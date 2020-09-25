package com.company.View.Console;

import com.company.Controller;
import com.company.Model.Actions;
import com.company.View.Tag;

import java.security.spec.ECField;

public class Out {
    public static void Show(Tag tag, String message){
        switch (tag){
            case LoadWMLinen:
                break;
            case UnloadWMLinen:
                break;
            case Action:
                System.out.print(
                        "Write a number for choise act:\n" +
                                showByTag(Tag.AddWMPowder) +
                                showByTag(Tag.AddWMConditioner) +
                                showByTag(Tag.AddWMColor) +
                                showByTag(Tag.AddWMTemperature) +
                                showByTag(Tag.LoadWMLinen) +
                                showByTag(Tag.UnloadWMLinen) +
                                showByTag(Tag.AddDirtyLinen) +
                                showByTag(Tag.RunWM)

                );
                Actions.runAction(Controller.getInt());
                break;
            case RunWM:
                System.out.println("Washing\'s succes is: " + message);
                break;

        }
    }

    private static String showByTag(Tag tag){
        try {
            return tag.getCode() + ": add " + tag.toString() + "\n";
        }
        catch (Exception e){
            com.company.View.Console.Err.PrintErr(e.toString());
        }
        return "";
    }
}
