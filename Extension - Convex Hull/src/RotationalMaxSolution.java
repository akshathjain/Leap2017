/*
Name: Akshath Jain
Date: 7/7/17
Purpose: solution and driver for convex hull problem
*/

import javax.swing.*;
import java.util.ArrayList;
import java.util.*;

public class RotationalMaxSolution{

    private static ArrayList<Point> pointList;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (UnsupportedLookAndFeelException e) {
        }
        JFrame frame = new JFrame("Convex Hull");
        DisplayPanel panel = new DisplayPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(panel);
        frame.setVisible(true);
/*
        Point p4 = new Point(200, 100);
        Point p2 = new Point(100, 200);
        Point p1 = new Point(200, 200);
        Point p3 = new Point(100, 100);
        Point p5 = new Point(150, 100);
        Point center = new Point (150, 150);
        panel.addPoint(p1);
        panel.addPoint(p2);
        panel.addPoint(p3);
        panel.addPoint(p4);
        panel.addPoint(p5);
        panel.addSignificantPoint(center);

        pointList = new ArrayList<>();
        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);
        pointList.add(p4);
        pointList.add(p5);

        ArrayList<Point> perimeter = new ArrayList<>();
        perimeter.add(p1);

        double totalRotation = p1.getAngle(center);

        */
        //create random points
        pointList = new ArrayList<>(); 
        /*for(int i = 0; i < 10; i++){
            pointList.add(new Point((int)(Math.random() * 400), (int)(Math.random() * 400)));
            panel.addPoint((int)pointList.get(i).getX(), (int)pointList.get(i).getY());
        }*/
        pointList.add(new Point(250,30));
        pointList.add(new Point(145,132));
        pointList.add(new Point(100,100));
        pointList.add(new Point(151,30));
        pointList.add(new Point(270,323));
        pointList.add(new Point(39,180));
        pointList.add(new Point(240,78));
        for(int i = 0; i < pointList.size(); i++)
            panel.addPoint(pointList.get(i));

        //find 4 edges
        ArrayList<Point> perimeter = new ArrayList<>();
        perimeter.add(null);

        double maxX = pointList.get(0).getX();
        double minX = pointList.get(0).getX(); 
        double maxY = pointList.get(0).getY(); 
        double minY = pointList.get(0).getY();

        for(int i = 1; i < pointList.size(); i++){
            if(pointList.get(i).getY() > maxY){
                maxY = pointList.get(i).getY();
            }else if(pointList.get(i).getY() < minY){ //min y
                minY = pointList.get(i).getY();
            }

            if(pointList.get(i).getX() > maxX){ //max x
                perimeter.set(0, pointList.get(i));
                maxX = pointList.get(i).getX();
            }else if(pointList.get(i).getX() < minX){ //min x
                minX = pointList.get(i).getX();
            }
        }

        Point center = new Point((maxX - minX) / 2.0 + minX, (maxY - minY) / 2.0 + minY);
        double totalRotation = perimeter.get(perimeter.size() - 1).getAngle(center);
        

        panel.addSignificantPoint((int)center.getX(), (int)center.getY());

        while(totalRotation < 360){
            System.out.println();
            Point newMax = null;
            double maxAngle = -1;
            
            if(perimeter.get(perimeter.size() - 1) == null)
                System.out.println('n');

            double[] old = perimeter.get(perimeter.size() - 1).turn(-1 * totalRotation, distance(perimeter.get(perimeter.size() - 1), center), center);
            System.out.println("old: " + Arrays.toString(old));
            //panel.clear();
            for(int i = 0; i < pointList.size(); i++){
                Point currentPoint = pointList.get(i);
                double[] turned = currentPoint.turn(-1 * totalRotation, distance(center, currentPoint), center);
                //panel.addPoint(new Point(turned));
                System.out.println(Arrays.toString(turned) + " " + (turned[1] > old[1]) + " " + Point.getAngleBetween(old, turned));
                if(turned[1] >= old[1]){
                    if(Point.getAngleBetween(old, turned) > maxAngle){
                        maxAngle = Point.getAngleBetween(old, turned);
                        newMax = currentPoint;
                        System.out.println("getting here");
                    }
                }
            }
            try{
                Thread.sleep(200);
            }catch(InterruptedException e){

            }
            System.out.println("total rotation: " + totalRotation);
            System.out.println("current max: " + Arrays.toString(old));
            System.out.println("max angle: " + maxAngle);

            totalRotation += maxAngle;        
            perimeter.add(newMax);

        }

        for(int i = 0; i < perimeter.size() - 1; i++)
            panel.connectPoints((int)perimeter.get(i).getX(), (int)perimeter.get(i).getY(), (int)perimeter.get(i+1).getX(), (int)perimeter.get(i+1).getY());
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

}

