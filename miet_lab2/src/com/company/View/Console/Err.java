package com.company.View.Console;

/**
 * print out message in console
 * and try wtite in log
 */
public class Err {

    public static int getCount() {
        return count;
    }

    private static int count = 0;
    /**
     * print err message in console
     * @param err
     */
    public static void PrintErr(String err){
        com.company.View.Log.tryWrite("Err: " + err);
        System.err.println(err);
        count++;
    }


}
