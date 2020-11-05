package com.company.model;

import com.company.View.Log;
import com.company.model.clothes.Color;
import com.company.model.clothes.LinenColor;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Watchable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Autotest {

    private static ArrayList<WashingMachine> wm1;
    private static LinkedList<WashingMachine> wm2;
    private static final int[] sizes = {10, 100};

    private static boolean init() throws IOException {
        wm1 = new ArrayList<>();
        wm2 = new LinkedList<>();
        for(int i = 0; i < sizes.length; i++){
            try(FileWriter writer = new FileWriter(Log.autotestName+"ArrayList"+sizes[i]+".txt", false)) {

            }
            catch (Exception e){
                return false;
            }
            try(FileWriter writer = new FileWriter(Log.autotestName+"LinkedList"+sizes[i]+".txt", false)) {

            }
            catch (Exception e){
                return false;
            }
        }

        try(FileWriter writer = new FileWriter(Log.autotestName+"final.txt", false)) {

        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean run() throws Exception {
        init();
        wm1.add(new WashingMachine()); wm1.clear();
        wm2.add(new WashingMachine()); wm1.clear();

        //ArrayList
        long currentTime1 = 0;
        long currentTime2 = 0;
        long allTime1 = 0;
        long allTime2 = 0;
        long totalTime;
        for(int i = 0; i < sizes.length; i++){
            //com.company.View.Log.tryWrite("-------------------------------------");
            totalTime = 0;
            //com.company.View.Log.tryWrite("ArrayList " + sizes[i] + " elements. Start program (timer as microsecond)");
            //com.company.View.Log.WriteOtherLog("------------ArrayList------------", "final.txt");
            wm1.clear();
            allTime1 = System.nanoTime();
            for(int j=0; j < sizes[i]; j++){
                currentTime1 = System.nanoTime();
                wm1.add(randomWM());
                currentTime2 = System.nanoTime();
                com.company.View.Log.WriteOtherLog("ADD: ID, TIME = " + j + ", "+ (currentTime2 - currentTime1)/1000,Log.autotestName+"ArrayList"+sizes[i]+".txt");
                totalTime += (currentTime2 - currentTime1)/1000;
            }
            allTime2 = System.nanoTime();
            com.company.View.Log.WriteOtherLog("ADD TOTAL TIME: " + (allTime2-allTime1)/1000, Log.autotestName+"ArrayList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("ADD TOTAL_MEDIAN TIME: " + totalTime, Log.autotestName+"ArrayList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("ADD MEDIAN TIME: " + totalTime/sizes[i], Log.autotestName+"ArrayList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("----------------------------------\nArrayList::", Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, ADD TOTAL TIME: " + sizes[i] + ", " + (allTime2-allTime1)/1000, Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, TOTAL_MEDIAN TIME: "+ sizes[i] + ", " + totalTime,Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, ADD MEDIAN TIME: " + sizes[i] + ", " + totalTime/sizes[i],Log.autotestName+"final.txt");

            totalTime = 0;
            allTime1 = System.nanoTime();
            for(int j=0; j < sizes[i]*0.1; j++){
                int k = (new Random()).nextInt((int)(wm1.size()));
                currentTime1 = System.nanoTime();
                wm1.remove(k);
                currentTime2 = System.nanoTime();
                com.company.View.Log.WriteOtherLog("DELETE: ID, TIME = " + k + ", "+ (currentTime2 - currentTime1)/1000,Log.autotestName+"ArrayList"+sizes[i]+".txt");
                totalTime += (currentTime2 - currentTime1)/1000;
            }
            allTime2 = System.nanoTime();
            com.company.View.Log.WriteOtherLog("DELETE TOTAL TIME: " + (allTime2-allTime1)/1000, Log.autotestName+"ArrayList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("DELETE TOTAL_MEDIAN TIME: " + totalTime, Log.autotestName+"ArrayList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("DELETE MEDIAN TIME: " + totalTime/(sizes[i]*0.1), Log.autotestName+"ArrayList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("----------------------------------\nArrayList::", Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, DELETE TOTAL TIME: " + sizes[i] + ", "+ (allTime2-allTime1)/1000, Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, DELETE TOTAL_MEDIAN TIME: "+ sizes[i] + ", " + totalTime,Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, DELETE MEDIAN TIME: " + sizes[i] + ", "+ totalTime/(sizes[i]*0.1),Log.autotestName+"final.txt");
            //com.company.View.Log.tryWrite("-------------------------------------");
        }

        //LinkedList
        currentTime1 = 0;
        currentTime2 = 0;
        allTime1 = 0;
        allTime2 = 0;
        for(int i = 0; i < sizes.length; i++){
            //com.company.View.Log.tryWrite("-------------------------------------");
            totalTime = 0;
            //com.company.View.Log.tryWrite("LinkedList " + sizes[i] + " elements. Start program (timer as microsecond)");
            //com.company.View.Log.WriteOtherLog("------------LinkedList------------", "final.txt");
            wm2.clear();
            allTime1 = System.nanoTime();
            for(int j=0; j < sizes[i]; j++){
                currentTime1 = System.nanoTime();
                wm2.add(randomWM());
                currentTime2 = System.nanoTime();
                com.company.View.Log.WriteOtherLog("ADD: ID, TIME = " + j + ", "+ (currentTime2 - currentTime1)/1000,Log.autotestName+"LinkedList"+sizes[i]+".txt");
                totalTime += (currentTime2 - currentTime1)/1000;
            }
            allTime2 = System.nanoTime();
            com.company.View.Log.WriteOtherLog("ADD TOTAL TIME: " + (allTime2-allTime1)/1000, Log.autotestName+"LinkedList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("ADD MEDIAN TIME: " + totalTime, Log.autotestName+"LinkedList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("ADD MEDIAN TIME: " + totalTime/sizes[i], Log.autotestName+"LinkedList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("----------------------------------\nLinkedList::", Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, ADD TOTAL TIME: "+ sizes[i] + ", " + (allTime2-allTime1)/1000, Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, ADD TOTAL_MEDIAN TIME: "+ sizes[i] + ", " + totalTime,Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, ADD MEDIAN TIME: " + sizes[i] + ", "+ totalTime/sizes[i]*0.1,Log.autotestName+"final.txt");

            totalTime = 0;
            allTime1 = System.nanoTime();
            for(int j=0; j < sizes[i]*0.1; j++){
                int k = (new Random()).nextInt((int)(wm2.size()));
                currentTime1 = System.nanoTime();
                wm2.remove(k);
                currentTime2 = System.nanoTime();
                com.company.View.Log.WriteOtherLog("DELETE: ID, TIME = " + k + ", "+ (currentTime2 - currentTime1)/1000,Log.autotestName+"LinkedList"+sizes[i]+".txt");
                totalTime += (currentTime2 - currentTime1)/1000;
            }
            allTime2 = System.nanoTime();
            com.company.View.Log.WriteOtherLog("DELETE TOTAL TIME: " + (allTime2-allTime1)/1000, Log.autotestName+"LinkedList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("DELETE TOTAL_MEDIAN TIME: " + totalTime, Log.autotestName+"LinkedList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("DELETE MEDIAN TIME: " + totalTime/(sizes[i]*0.1), Log.autotestName+"LinkedList"+sizes[i]+".txt");
            com.company.View.Log.WriteOtherLog("----------------------------------\nLinkedList::", Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, DELETE TOTAL TIME: "+ sizes[i] + ", " + (allTime2-allTime1)/1000, Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, DELETE TOTAL_MEDIAN TIME: " + sizes[i] + ", "+ totalTime,Log.autotestName+"final.txt");
            com.company.View.Log.WriteOtherLog("NUM OF ELEMENTS, DELETE MEDIAN TIME: " + sizes[i] + ", "+ totalTime/(sizes[i]*0.1),Log.autotestName+"final.txt");

            //com.company.View.Log.tryWrite("-------------------------------------");
        }

        return true;
    }

    private static WashingMachine randomWM() throws Exception {
        WashingMachine wm = new WashingMachine();
        wm.setPowder("random powder");
        wm.setConditioner("random conditioner");
        wm.setColor(Color.tryConvert((new Random()).nextInt(3)+1));
        wm.setTemperature((new Random()).nextInt(99)  + 1);
        return wm;
    }
}
