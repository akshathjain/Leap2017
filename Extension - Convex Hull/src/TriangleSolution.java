/*
Name: Akshath Jain
Date: 7/7/17
Purpose: solution and driver for convex hull problem
*/

import javax.swing.*;
import java.util.ArrayList;
import java.util.*;

public class TriangleSolution{

    private static ArrayList<Point> pointList;

    public static void main(String[] args){
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

        //create random points
        pointList = new ArrayList<>(); 
        for(int i = 0; i < 10; i++){
            pointList.add(new Point((int)(Math.random() * 400), (int)(Math.random() * 400)));
            panel.addPoint((int)pointList.get(i).getX(), (int)pointList.get(i).getY());
        }

        //find 4 edges
        ArrayList<Point> perimeter = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            perimeter.add(pointList.get(0));

        for(int i = 0; i < pointList.size(); i++){
            if(pointList.get(i).getY() > perimeter.get(0).getY())
                perimeter.set(0, pointList.get(i));
            else if(pointList.get(i).getY() < perimeter.get(2).getY())
                perimeter.set(2, pointList.get(i));

            if(pointList.get(i).getX() > perimeter.get(3).getX())
                perimeter.set(3, pointList.get(i));
            else if(pointList.get(i).getX() < perimeter.get(1).getX())
                perimeter.set(1, pointList.get(i));

        }


        //paint lines
        perimeter.add(perimeter.get(0)); //in order to connect the first and last points
        for(int i = 0; i < perimeter.size() - 1; i++)
            panel.connectPoints((int)perimeter.get(i).getX(), (int)perimeter.get(i).getY(), (int)perimeter.get(i+1).getX(), (int)perimeter.get(i+1).getY());
    }
}

class Point{
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double[] turn(double degrees, double radius, Point center){
        double radians = degrees * Math.PI / 180.0;
        double thetaNot = Math.atan((y - center.getY()) / (x - center.getX()));

            //do a quadrant check and convert
        if(x - center.getX() < 0)
            thetaNot += Math.PI;

        double thetaOne = thetaNot + radians;
        return new double[]{radius * Math.cos(thetaOne) + center.getX(), radius * Math.sin(thetaOne) + center.getY()};
    }

    @Override
    public boolean equals(Object o){
        Point other = (Point) o;
        return other.x == this.x && other.y == this.y;
    }
}