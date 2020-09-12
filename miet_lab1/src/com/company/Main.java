package com.company;

import java.util.Properties; //Подключаем пакет Properties
import java.util.Scanner;

/**
 * @ author mJaJksJ
 * @ version 12.09.2020 20:16
 */
public class Main {

    static Properties properties;
    static Scanner scanner;

    static void model() throws InterruptedException {
        String num = properties.getProperty("num");
        boolean isArithmeticProgression = true;
        for(int i = 1; i < num.length() - 1; i++){
            if(num.charAt(i) - num.charAt(i - 1) != num.charAt(i + 1) - num.charAt(i)){
                isArithmeticProgression = false;
                break;
            }
        }
        properties.setProperty("isProgression", Boolean.toString(isArithmeticProgression));
        view("ans");
    }

    static void controller() throws InterruptedException {
        view("invitation");
        String value = scanner.next();
        try {
            Integer.parseInt(value);
            properties.setProperty("num", value);
        }
        catch (Exception e){
            System.err.println(e.toString());
            Thread.sleep(100);
            controller();
        }
        finally {
            model();
        }
    }

    static void view(String mesChooser) throws InterruptedException {
        switch (mesChooser){
            case "task":
                System.out.println(
                        "\nДля заданного натурального числа определить, " +
                                "образуют ли его цифры арифметическую прогрессию. " +
                                "Предполагается, что в числе не менее трёх цифр. \n"
                );
                break;
            case "ans":
                System.out.println(
                        "Is " + properties.getProperty("num") +
                                " arithmetic progression:: " +
                                properties.getProperty("isProgression")
                );
                controller();
                break;
            case "invitation":
                System.out.print("Write num for checking:: ");
                break;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        properties = new Properties();
        scanner = new Scanner(System.in);
        view("task");
        controller();
    }
}
