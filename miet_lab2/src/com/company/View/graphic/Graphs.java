package com.company.View.graphic;

import com.company.View.Log;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * class drawing graphics as frames
 */
public class Graphs {

    private static JFrame[] graphs = new JFrame[6];
    public static DrawGraphs[] dg = new DrawGraphs[graphs.length];

    /**
     *     for calling as event
     */
    public static void initAndRun(){

        for(int i = 0; i < graphs.length; i++){
            graphs[i] = new JFrame();
            graphs[i].setSize(new Dimension(800,600));
            switch (i){
                case 0:
                    graphs[i].setTitle("ADD_TOTAL TIME");
                    break;
                case 1:
                    graphs[i].setTitle("ADD_TOTAL_MEDIAN TIME");
                    break;
                case 2:
                    graphs[i].setTitle("ADD_MEDIAN TIME");
                    break;
                case 3:
                    graphs[i].setTitle("DELETE_TOTAL TIME");
                    break;
                case 4:
                    graphs[i].setTitle("DELETE_TOTAL_MEDIAN TIME");
                    break;
                case 5:
                    graphs[i].setTitle("DELETE_MEDIAN TIME");
                    break;
            }
            //graphs[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dg[i] = new DrawGraphs();
            dg[i].I = i;

            graphs[i].add(dg[i]);
            if(Log.isRun){
                graphs[i].setVisible(true);
            }

        }
    }
}



