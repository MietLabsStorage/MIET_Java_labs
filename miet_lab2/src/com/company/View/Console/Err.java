package com.company.View.Console;

public class Err {
    /**
     * print err message in console
     * @param err
     */
    public static void PrintErr(String err){
        com.company.View.Log.tryWrite(err);
        System.err.println(err);
    }
}
