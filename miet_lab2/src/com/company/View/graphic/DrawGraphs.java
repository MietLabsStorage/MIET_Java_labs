package com.company.View.graphic;

import com.company.model.Autotest;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * draw graph as component
 */
public class DrawGraphs extends JComponent {
    private static final Line oX = new Line(50,550,750,550, Color.black);
    private static final Line oY = new Line(50,50,50,550,Color.black);

    public int I;

    /**
     * overrided paintComponent
     * @param g
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        //coordinates
        anyGraphics.draw_coordinates(g2, oX, oY);
        //graphics
        switch (I){
            case 0:
                anyGraphics.draw_ADD_TOTAL(g2);
                break;
            case 1:
                anyGraphics.draw_ADD_TOTAL_MEDIAN(g2);
                break;
            case 2:
                anyGraphics.draw_ADD_MEDIAN(g2);
                break;
            case 3:
                anyGraphics.draw_DELETE_TOTAL(g2);
                break;
            case 4:
                anyGraphics.draw_DELETE_TOTAL_MEDIAN(g2);
                break;
            case 5:
                anyGraphics.draw_DELETE_MEDIAN(g2);
                break;
        }

    }
}

/**
 * graphics that will be drawed
 */
class anyGraphics{

    public static void draw_coordinates(Graphics2D g2, Line oX, Line oY){
        //oX
        g2.setColor(oX.color);
        Line2D lineOX = new Line2D.Double(oX.x1, oX.y1, oX.x2, oX.y2);
        g2.draw(lineOX);
        g2.fill(lineOX);
        Line2D lineOX1 = new Line2D.Double(oX.x2-10, oX.y1-10, oX.x2, oX.y2);
        g2.draw(lineOX1);
        g2.fill(lineOX1);
        Line2D lineOX2 = new Line2D.Double(oX.x2-10, oX.y1+10, oX.x2, oX.y2);
        g2.draw(lineOX2);
        g2.fill(lineOX2);
        Font FTitleX = new Font("Arial", Font.PLAIN, 15);
        g2.setFont(FTitleX);
        g2.drawString("Num Elems", oX.x2-50, oX.y2-15);

        //oY
        g2.setColor(oX.color);
        Line2D lineOY = new Line2D.Double(oY.x1, oY.y1, oY.x2, oY.y2);
        g2.draw(lineOY);
        g2.fill(lineOY);
        Line2D lineOY1 = new Line2D.Double(oY.x1-10, oY.y1+10, oY.x2, oY.y1);
        g2.draw(lineOY1);
        g2.fill(lineOY1);
        Line2D lineOY2 = new Line2D.Double(oY.x1+10, oY.y1+10, oY.x2, oY.y1);
        g2.draw(lineOY2);
        g2.fill(lineOY2);
        Font FTitleY = new Font("Arial", Font.PLAIN, 15);
        g2.setFont(FTitleX);
        g2.drawString("Time, nsec", oY.x1+15, oY.y1);

        //legend
        g2.setColor(Color.BLUE);
        Line2D lineLegendArray = new Line2D.Double(650,450,670,450);
        g2.draw(lineLegendArray);
        g2.fill(lineLegendArray);
        g2.setColor(Color.RED);
        Line2D lineLegendLinked = new Line2D.Double(650,420,670,420);
        g2.draw(lineLegendLinked);
        g2.fill(lineLegendLinked);
        g2.setColor(Color.BLACK);
        Font FTitleLegendArray = new Font("Arial", Font.PLAIN, 12);
        g2.setFont(FTitleLegendArray);
        g2.drawString("ArrayList", 680, 450);
        Font FTitleLegendLinked = new Font("Arial", Font.PLAIN, 12);
        g2.setFont(FTitleLegendLinked);
        g2.drawString("LinkedList", 680, 420);
        Font FTitleLegend = new Font("Arial", Font.PLAIN, 12);
        g2.setFont(FTitleLegend);
        g2.drawString("Legend:", 650, 400);
    }

    public static void draw_ADD_TOTAL(Graphics2D g2){
        //oX nums
        for(int i = 0; i < Autotest.sizes.length; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-5,
                    50+Autotest.sizes[i]/20,
                    550+5);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(Autotest.sizes[i]+"", 50+Autotest.sizes[i]/20, 550+10);
        }
        //oY nums
        for(int i = 0; i < 5; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50-5,
                    550-100*i,
                    50+5,
                    550-100*i);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(i*Autotest.ArrayList_ADD_TOTAL.get(Autotest.ArrayList_ADD_TOTAL.size()-3)*5+"", 50-30, 550-100*i);
        }
        //ArrayList
        for(int i = 0; i < Autotest.ArrayList_ADD_TOTAL.size()-1; i++){
            g2.setColor(Color.BLUE);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.ArrayList_ADD_TOTAL.get(i)/Autotest.ArrayList_ADD_TOTAL.get(Autotest.ArrayList_ADD_TOTAL.size()-3)*50,
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.ArrayList_ADD_TOTAL.get(i+1)/Autotest.ArrayList_ADD_TOTAL.get(Autotest.ArrayList_ADD_TOTAL.size()-3)*50);
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
        //LinkedList
        for(int i = 0; i < Autotest.LinkedList_ADD_TOTAL.size()-1; i++){
            g2.setColor(Color.RED);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.LinkedList_ADD_TOTAL.get(i)/Autotest.ArrayList_ADD_TOTAL.get(Autotest.ArrayList_ADD_TOTAL.size()-3)*50,
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.LinkedList_ADD_TOTAL.get(i+1)/Autotest.ArrayList_ADD_TOTAL.get(Autotest.ArrayList_ADD_TOTAL.size()-3)*50);
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
    }

    public static void draw_ADD_TOTAL_MEDIAN(Graphics2D g2){
        //oX nums
        for(int i = 0; i < Autotest.sizes.length; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-5,
                    50+Autotest.sizes[i]/20,
                    550+5);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(Autotest.sizes[i]+"", 50+Autotest.sizes[i]/20, 550+10);
        }

        //oY nums
        for(int i = 0; i < 5; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50-5,
                    550-100*i,
                    50+5,
                    550-100*i);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(i*Autotest.ArrayList_ADD_TOTAL_MEDIAN.get(Autotest.ArrayList_ADD_TOTAL_MEDIAN.size()-3)*50+"", 50-30, 550-100*i);
        }
        //ArrayList
        for(int i = 0; i < Autotest.ArrayList_ADD_TOTAL.size()-1; i++){
            g2.setColor(Color.BLUE);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.ArrayList_ADD_TOTAL_MEDIAN.get(i)*50/Autotest.ArrayList_ADD_TOTAL_MEDIAN.get(Autotest.ArrayList_ADD_TOTAL_MEDIAN.size()-3)*50,
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.ArrayList_ADD_TOTAL_MEDIAN.get(i+1)*50/Autotest.ArrayList_ADD_TOTAL_MEDIAN.get(Autotest.ArrayList_ADD_TOTAL_MEDIAN.size()-3)*50);
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
        //LinkedList
        for(int i = 0; i < Autotest.LinkedList_ADD_TOTAL.size()-1; i++){
            g2.setColor(Color.RED);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.LinkedList_ADD_TOTAL_MEDIAN.get(i)/Autotest.ArrayList_ADD_TOTAL_MEDIAN.get(Autotest.ArrayList_ADD_TOTAL_MEDIAN.size()-3)*50,
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.LinkedList_ADD_TOTAL_MEDIAN.get(i+1)/Autotest.ArrayList_ADD_TOTAL_MEDIAN.get(Autotest.ArrayList_ADD_TOTAL_MEDIAN.size()-3)*50);
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
    }

    public static void draw_ADD_MEDIAN(Graphics2D g2){
        //oX nums
        for(int i = 0; i < Autotest.sizes.length; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-5,
                    50+Autotest.sizes[i]/20,
                    550+5);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(Autotest.sizes[i]+"", 50+Autotest.sizes[i]/20, 550+10);
        }

        //oY nums
        for(int i = 0; i < 5; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50-5,
                    550-100*i,
                    50+5,
                    550-100*i);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(i*Autotest.ArrayList_ADD_MEDIAN.get(2)*5+"", 50-30, 550-100*i);
        }
        //ArrayList
        for(int i = 0; i < Autotest.ArrayList_ADD_TOTAL.size()-1; i++){
            g2.setColor(Color.BLUE);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.ArrayList_ADD_MEDIAN.get(i)*50/Autotest.ArrayList_ADD_MEDIAN.get(2),
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.ArrayList_ADD_MEDIAN.get(i+1)*50/Autotest.ArrayList_ADD_MEDIAN.get(2));
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
        //LinkedList
        for(int i = 0; i < Autotest.LinkedList_ADD_TOTAL.size()-1; i++){
            g2.setColor(Color.RED);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.LinkedList_ADD_MEDIAN.get(i)*50/Autotest.ArrayList_ADD_MEDIAN.get(2),
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.LinkedList_ADD_MEDIAN.get(i+1)*50/Autotest.ArrayList_ADD_MEDIAN.get(2));
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
    }

    public static void draw_DELETE_TOTAL(Graphics2D g2){
        //oX nums
        for(int i = 0; i < Autotest.sizes.length; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-5,
                    50+Autotest.sizes[i]/20,
                    550+5);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(Autotest.sizes[i]+"", 50+Autotest.sizes[i]/20, 550+10);
        }
        //oY nums
        for(int i = 0; i < 5; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50-5,
                    550-100*i,
                    50+5,
                    550-100*i);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(i*Autotest.ArrayList_DELETE_TOTAL.get(Autotest.ArrayList_DELETE_TOTAL.size()-3)*5+"", 50-30, 550-100*i);
        }
        //ArrayList
        for(int i = 0; i < Autotest.ArrayList_DELETE_TOTAL.size()-1; i++){
            g2.setColor(Color.BLUE);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.ArrayList_DELETE_TOTAL.get(i)/Autotest.ArrayList_DELETE_TOTAL.get(Autotest.ArrayList_DELETE_TOTAL.size()-3)*50,
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.ArrayList_DELETE_TOTAL.get(i+1)/Autotest.ArrayList_DELETE_TOTAL.get(Autotest.ArrayList_DELETE_TOTAL.size()-3)*50);
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
        //LinkedList
        for(int i = 0; i < Autotest.LinkedList_DELETE_TOTAL.size()-1; i++){
            g2.setColor(Color.RED);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.LinkedList_DELETE_TOTAL.get(i)/Autotest.ArrayList_DELETE_TOTAL.get(Autotest.ArrayList_DELETE_TOTAL.size()-3)*50,
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.LinkedList_DELETE_TOTAL.get(i+1)/Autotest.ArrayList_DELETE_TOTAL.get(Autotest.ArrayList_DELETE_TOTAL.size()-3)*50);
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
    }

    public static void draw_DELETE_TOTAL_MEDIAN(Graphics2D g2){
        //oX nums
        for(int i = 0; i < Autotest.sizes.length; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-5,
                    50+Autotest.sizes[i]/20,
                    550+5);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(Autotest.sizes[i]+"", 50+Autotest.sizes[i]/20, 550+10);
        }

        //oY nums
        for(int i = 0; i < 5; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50-5,
                    550-100*i,
                    50+5,
                    550-100*i);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(i*Autotest.ArrayList_DELETE_TOTAL_MEDIAN.get(Autotest.ArrayList_DELETE_TOTAL_MEDIAN.size()-3)*5+"", 50-30, 550-100*i);
        }
        //ArrayList
        for(int i = 0; i < Autotest.ArrayList_DELETE_TOTAL.size()-1; i++){
            g2.setColor(Color.BLUE);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.ArrayList_DELETE_TOTAL_MEDIAN.get(i)/Autotest.ArrayList_DELETE_TOTAL_MEDIAN.get(Autotest.ArrayList_DELETE_TOTAL_MEDIAN.size()-3)*50,
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.ArrayList_DELETE_TOTAL_MEDIAN.get(i+1)/Autotest.ArrayList_DELETE_TOTAL_MEDIAN.get(Autotest.ArrayList_DELETE_TOTAL_MEDIAN.size()-3)*50);
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
        //LinkedList
        for(int i = 0; i < Autotest.LinkedList_DELETE_TOTAL.size()-1; i++){
            g2.setColor(Color.RED);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.LinkedList_DELETE_TOTAL_MEDIAN.get(i)/Autotest.ArrayList_DELETE_TOTAL_MEDIAN.get(Autotest.ArrayList_DELETE_TOTAL_MEDIAN.size()-3)*50,
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.LinkedList_DELETE_TOTAL_MEDIAN.get(i+1)/Autotest.ArrayList_DELETE_TOTAL_MEDIAN.get(Autotest.ArrayList_DELETE_TOTAL_MEDIAN.size()-3)*50);
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
    }

    public static void draw_DELETE_MEDIAN(Graphics2D g2){
        //oX nums
        for(int i = 0; i < Autotest.sizes.length; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-5,
                    50+Autotest.sizes[i]/20,
                    550+5);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(Autotest.sizes[i]+"", 50+Autotest.sizes[i]/20, 550+10);
        }

        //oY nums
        for(int i = 0; i < 5; i++){
            g2.setColor(Color.BLACK);
            Line2D tempLine = new Line2D.Double(50-5,
                    550-100*i,
                    50+5,
                    550-100*i);
            g2.draw(tempLine);
            g2.fill(tempLine);
            Font FTitle = new Font("Arial", Font.PLAIN, 10);
            g2.setFont(FTitle);
            g2.drawString(i*5/Autotest.ArrayList_DELETE_MEDIAN.get(Autotest.ArrayList_DELETE_MEDIAN.size()-3)+"", 50-30, 550-100*i);
        }
        //ArrayList
        for(int i = 0; i < Autotest.ArrayList_DELETE_TOTAL.size()-1; i++){
            g2.setColor(Color.BLUE);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.ArrayList_DELETE_MEDIAN.get(i)*50/Autotest.ArrayList_DELETE_MEDIAN.get(Autotest.ArrayList_DELETE_MEDIAN.size()-3),
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.ArrayList_DELETE_MEDIAN.get(i+1)*50/Autotest.ArrayList_DELETE_MEDIAN.get(Autotest.ArrayList_DELETE_MEDIAN.size()-3));
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
        //LinkedList
        for(int i = 0; i < Autotest.LinkedList_DELETE_TOTAL.size()-1; i++){
            g2.setColor(Color.RED);
            Line2D tempLine = new Line2D.Double(50+ Autotest.sizes[i]/20,
                    550-Autotest.LinkedList_DELETE_MEDIAN.get(i)*50/Autotest.ArrayList_DELETE_MEDIAN.get(Autotest.ArrayList_DELETE_MEDIAN.size()-3),
                    50+Autotest.sizes[i+1]/20,
                    550-Autotest.LinkedList_DELETE_MEDIAN.get(i+1)*50/Autotest.ArrayList_DELETE_MEDIAN.get(Autotest.ArrayList_DELETE_MEDIAN.size()-3));
            g2.draw(tempLine);
            g2.fill(tempLine);
        }
    }

}

