package com.company;

import com.company.Model.clothes.Color;
import com.company.View.*;

import java.util.Scanner;

public class Controller {

    private static Scanner scanner = new Scanner(System.in);
    public static int getInt() throws Exception {
        String value = scanner.next();
        try {
            return Integer.parseInt(value);
        }
        catch (Exception e){
            com.company.View.Console.Err.PrintErr(e.toString());
            com.company.View.Console.Out.Show(Tag.Action, "");
        }
        return -1;
    }

    public static String getString() {
        return scanner.next();
    }

    public static Color getColor() {
        String value = scanner.next();
        if(value == Color.light.toString()){
            return  Color.light;
        }
        if(value == Color.dark.toString()){
            return  Color.dark;
        }
        if(value == Color.multycolor.toString()){
            return  Color.multycolor;
        }
        com.company.View.Console.Err.PrintErr("No this color");
        return getColor();
    }

}
