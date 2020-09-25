package com.company;

import com.company.View.*;

import java.util.Scanner;

public class Controller {
    private static Scanner scanner = new Scanner(System.in);
    public static int getInt(){
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
}
