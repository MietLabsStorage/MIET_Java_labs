package com.company;

import com.company.model.clothes.Color;

import java.util.Scanner;

/**
 * Controller-class
 */
public class Controller {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * get int from keyboard
     * @return int number
     * @throws Exception throw if not int
     */
    public static int getInt() throws Exception {
        String value = scanner.nextLine();
        try {
            return Integer.parseInt(value);
        }
        catch (Exception e){
            com.company.View.Console.Err.PrintErr(e.toString());
        }
        return -1;
    }

    /**
     * get string from keyboard
     * @return string line
     */
    public static String getString() {
        String s = scanner.nextLine();
        return s;
    }

    /**
     * get clothes.color from keyboard
     * @return clothes.color
     */
    public static Color getColor() {
        String value = scanner.nextLine();
        if(value.equals(Color.light.toString())){
            return  Color.light;
        }
        if(value.equals(Color.dark.toString())){
            return  Color.dark;
        }
        if(value.equals(Color.multicolor.toString())){
            return  Color.multicolor;
        }
        com.company.View.Console.Err.PrintErr("No this color");
        return getColor();
    }

}
