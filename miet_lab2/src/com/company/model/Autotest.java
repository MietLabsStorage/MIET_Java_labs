package com.company.model;

import com.company.View.Log;
import com.company.model.clothes.Color;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * autotest class
 */
public class Autotest {

    public static ArrayList<Double> ArrayList_ADD_TOTAL = new ArrayList<>();
    public static ArrayList<Double> ArrayList_ADD_TOTAL_MEDIAN = new ArrayList<>();
    public static ArrayList<Double> ArrayList_ADD_MEDIAN = new ArrayList<>();
    public static ArrayList<Double> LinkedList_ADD_TOTAL = new ArrayList<>();
    public static ArrayList<Double> LinkedList_ADD_TOTAL_MEDIAN = new ArrayList<>();
    public static ArrayList<Double> LinkedList_ADD_MEDIAN = new ArrayList<>();

    public static ArrayList<Double> ArrayList_DELETE_TOTAL = new ArrayList<>();
    public static ArrayList<Double> ArrayList_DELETE_TOTAL_MEDIAN = new ArrayList<>();
    public static ArrayList<Double> ArrayList_DELETE_MEDIAN = new ArrayList<>();
    public static ArrayList<Double> LinkedList_DELETE_TOTAL = new ArrayList<>();
    public static ArrayList<Double> LinkedList_DELETE_TOTAL_MEDIAN = new ArrayList<>();
    public static ArrayList<Double> LinkedList_DELETE_MEDIAN = new ArrayList<>();

    private static ArrayList<WashingMachine> wm1;
    private static LinkedList<WashingMachine> wm2;
    public static final int[] sizes = {10,100, 500, 1000, 5000, 10000};

    /**
     * initialisation
     * @return
     * @throws IOException
     */
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

    /**
     * run autotests
     * @return
     * @throws Exception
     */
    public static boolean run() throws Exception {
        init();
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
                //WashingMachine temp = randomWM();
                WashingMachine temp = new WashingMachine();
                currentTime1 = System.nanoTime();
                wm1.add(temp);
                currentTime2 = System.nanoTime();
                Log.WriteOtherLog("ADD: ID, TIME = " + j + ", "+ (currentTime2 - currentTime1),Log.autotestName+"ArrayList"+sizes[i]+".txt");
                totalTime += (currentTime2 - currentTime1);
            }
            allTime2 = System.nanoTime();
            Log.WriteOtherLog("ADD TOTAL TIME: " + (allTime2-allTime1), Log.autotestName+"ArrayList"+sizes[i]+".txt");
            Log.WriteOtherLog("ADD TOTAL_MEDIAN TIME: " + totalTime, Log.autotestName+"ArrayList"+sizes[i]+".txt");
            Log.WriteOtherLog("ADD MEDIAN TIME: " + totalTime/sizes[i], Log.autotestName+"ArrayList"+sizes[i]+".txt");
            Log.WriteOtherLog("----------------------------------\nArrayList::", Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, ADD TOTAL TIME: " + sizes[i] + ", " + (allTime2-allTime1), Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, ADD TOTAL_MEDIAN TIME: "+ sizes[i] + ", " + totalTime,Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, ADD MEDIAN TIME: " + sizes[i] + ", " + totalTime/sizes[i],Log.autotestName+"final.txt");

            ArrayList_ADD_TOTAL.add((double)((allTime2-allTime1)));
            ArrayList_ADD_TOTAL_MEDIAN.add((double)(totalTime));
            ArrayList_ADD_MEDIAN.add((double)(totalTime/(sizes[i])));

            totalTime = 0;
            allTime1 = System.nanoTime();
            for(int j=0; j < sizes[i]*0.1; j++){
                int k = (new Random()).nextInt((int)(wm1.size()));
                currentTime1 = System.nanoTime();
                wm1.remove(k);
                currentTime2 = System.nanoTime();
                Log.WriteOtherLog("DELETE: ID, TIME = " + k + ", "+ (currentTime2 - currentTime1),Log.autotestName+"ArrayList"+sizes[i]+".txt");
                totalTime += (currentTime2 - currentTime1);
            }
            allTime2 = System.nanoTime();
            Log.WriteOtherLog("DELETE TOTAL TIME: " + (allTime2-allTime1), Log.autotestName+"ArrayList"+sizes[i]+".txt");
            Log.WriteOtherLog("DELETE TOTAL_MEDIAN TIME: " + totalTime, Log.autotestName+"ArrayList"+sizes[i]+".txt");
            Log.WriteOtherLog("DELETE MEDIAN TIME: " + totalTime/(sizes[i]*0.1), Log.autotestName+"ArrayList"+sizes[i]+".txt");
            Log.WriteOtherLog("----------------------------------\nArrayList::", Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, DELETE TOTAL TIME: " + sizes[i] + ", "+ (allTime2-allTime1), Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, DELETE TOTAL_MEDIAN TIME: "+ sizes[i] + ", " + totalTime,Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, DELETE MEDIAN TIME: " + sizes[i] + ", "+ totalTime/(sizes[i]*0.1),Log.autotestName+"final.txt");

            ArrayList_DELETE_TOTAL.add((double)((allTime2-allTime1)));
            ArrayList_DELETE_TOTAL_MEDIAN.add((double)(totalTime));
            ArrayList_DELETE_MEDIAN.add((double)(totalTime/(sizes[i]*0.1)));
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
                //WashingMachine temp = randomWM();
                WashingMachine temp = new WashingMachine();
                currentTime1 = System.nanoTime();
                wm2.add(temp);
                currentTime2 = System.nanoTime();
                Log.WriteOtherLog("ADD: ID, TIME = " + j + ", "+ (currentTime2 - currentTime1),Log.autotestName+"LinkedList"+sizes[i]+".txt");
                totalTime += (currentTime2 - currentTime1);
            }
            allTime2 = System.nanoTime();
            Log.WriteOtherLog("ADD TOTAL TIME: " + (allTime2-allTime1), Log.autotestName+"LinkedList"+sizes[i]+".txt");
            Log.WriteOtherLog("ADD MEDIAN TIME: " + totalTime, Log.autotestName+"LinkedList"+sizes[i]+".txt");
            Log.WriteOtherLog("ADD MEDIAN TIME: " + totalTime/sizes[i], Log.autotestName+"LinkedList"+sizes[i]+".txt");
            Log.WriteOtherLog("----------------------------------\nLinkedList::", Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, ADD TOTAL TIME: "+ sizes[i] + ", " + (allTime2-allTime1), Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, ADD TOTAL_MEDIAN TIME: "+ sizes[i] + ", " + totalTime,Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, ADD MEDIAN TIME: " + sizes[i] + ", "+ totalTime/sizes[i],Log.autotestName+"final.txt");

            LinkedList_ADD_TOTAL.add((double)((allTime2-allTime1)));
            LinkedList_ADD_TOTAL_MEDIAN.add((double)(totalTime));
            LinkedList_ADD_MEDIAN.add((double)(totalTime/(sizes[i])));

            totalTime = 0;
            allTime1 = System.nanoTime();
            for(int j=0; j < sizes[i]*0.1; j++){
                int k = (new Random()).nextInt((int)(wm2.size()));
                currentTime1 = System.nanoTime();
                wm2.remove(k);
                currentTime2 = System.nanoTime();
                Log.WriteOtherLog("DELETE: ID, TIME = " + k + ", "+ (currentTime2 - currentTime1),Log.autotestName+"LinkedList"+sizes[i]+".txt");
                totalTime += (currentTime2 - currentTime1);
            }
            allTime2 = System.nanoTime();
            Log.WriteOtherLog("DELETE TOTAL TIME: " + (allTime2-allTime1), Log.autotestName+"LinkedList"+sizes[i]+".txt");
            Log.WriteOtherLog("DELETE TOTAL_MEDIAN TIME: " + totalTime, Log.autotestName+"LinkedList"+sizes[i]+".txt");
            Log.WriteOtherLog("DELETE MEDIAN TIME: " + totalTime/(sizes[i]*0.1), Log.autotestName+"LinkedList"+sizes[i]+".txt");
            Log.WriteOtherLog("----------------------------------\nLinkedList::", Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, DELETE TOTAL TIME: "+ sizes[i] + ", " + (allTime2-allTime1), Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, DELETE TOTAL_MEDIAN TIME: " + sizes[i] + ", "+ totalTime,Log.autotestName+"final.txt");
            Log.WriteOtherLog("NUM OF ELEMENTS, DELETE MEDIAN TIME: " + sizes[i] + ", "+ totalTime/(sizes[i]*0.1),Log.autotestName+"final.txt");

            LinkedList_DELETE_TOTAL.add((double)((allTime2-allTime1)));
            LinkedList_DELETE_TOTAL_MEDIAN.add((double)(totalTime));
            LinkedList_DELETE_MEDIAN.add((double)(totalTime/(sizes[i]*0.1)));
            //com.company.View.Log.tryWrite("-------------------------------------");
        }

        BonusTest();

        Log.tryWrite("autotests done");

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

    private static void BonusTest() throws Exception {

        try(FileWriter writer = new FileWriter("BonusAutotestLog.txt", false)) {
        }
        catch (Exception e){        }
        long currentTime1, currentTime2;
        int capacity = 1;
        for(int i = 0; i < 10; i++){
            long time = 0;
            ArrayList<WashingMachine> wm = new ArrayList<>();
            Log.WriteOtherLog("Capasity: "+capacity+"::", "BonusAutotestLog.txt");
            for(int j = 0; j < capacity; j++){
                WashingMachine temp = new WashingMachine();
                currentTime1 = System.nanoTime();
                wm.add(temp);
                currentTime2 = System.nanoTime();
                Log.WriteOtherLog("Index, time: "+j+", "+(currentTime2-currentTime1)/100, "BonusAutotestLog.txt");
                time += (currentTime2-currentTime1)/100;
            }
            Log.WriteOtherLog("MEDIUM:"+time/capacity, "BonusAutotestLog.txt");
            Log.WriteOtherLog("-----------------------", "BonusAutotestLog.txt");
            capacity = (capacity*3)/2 + 1;
        }
    }
}
