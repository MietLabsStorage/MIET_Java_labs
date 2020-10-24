package com.company.View;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static final String name = "log.txt";
    public static boolean isRun = false;

    public static boolean tryWrite(String msg){
        if(isRun){
            return alwaysWrite(msg);
        }
        return false;
    }

    public static boolean alwaysWrite(String msg){
        try(FileWriter writer = new FileWriter(name, true))
        {
            writer.write(LocalDate.now() + " " +
                    LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss")) + "|\t" + msg + "\n");
            writer.flush();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
