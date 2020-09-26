package com.company;

import java.util.Properties; //Подключаем пакет Properties
import java.util.Scanner;

/**
 * @ author mJaJksJ
 * @ version 26.09.2020
 */
public class Main {

    static Properties properties;

    //model
    static void model() {
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

    //controller
    static void controller() {
        Scanner scanner = new Scanner(System.in);
        view("invitation");
        String value = scanner.next();
        try {
            Integer.parseInt(value);
            properties.setProperty("num", value);
        }
        catch (Exception e){
            controller();
        }
        finally {
            model();
        }
    }

    //view
    static void view(String mesChooser) {
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

        }
    }

    /**
     *
     * @param args do nothing
     */
    public static void main(String[] args) {
        properties = new Properties();
        view("task");
        controller();
    }
}
