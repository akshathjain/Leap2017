/*
Name: Akshath Jain
Date: 7/7/17
Purpose: solution and driver for convex hull problem
*/

import javax.swing.*;
import java.util.ArrayList;
import java.util.*;

public class Solution{

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

        //find center
        double maxX = pointList.get(0).getX();
        double minX = pointList.get(0).getX(); 
        double maxY = pointList.get(0).getY(); 
        double minY = pointList.get(0).getY();
        for(int i = 1; i < pointList.size(); i++){
            if(pointList.get(i).getX() > maxX)
                maxX = pointList.get(i).getX();
            else if(pointList.get(i).getX() < minX)
                minX = pointList.get(i).getX();

            if(pointList.get(i).getY() > maxY)
                maxY = pointList.get(i).getY();
            else if(pointList.get(i).getY() < minY)
                minY = pointList.get(i).getY();
        }
        Point center = new Point((maxX - minX) / 2.0 + minX, (maxY - minY) / 2.0 + minY);
        panel.addCenter((int)center.getX(), (int)center.getY());

        //turn the figure and find highest point from center
        ArrayList<Point> perimeter = new ArrayList<>();
        for(int i = 0; i < 360; i++){ //turns the shape one degree at a time
            double maxYDistance = -1; //negative one b/c impossible to have negative distance
            Point maxPoint = new Point(-1,-1);
            for(int j = 0; j < pointList.size(); j++){
                //turn point
                double turnedY = pointList.get(j).turn(i, distance(pointList.get(j), center), center)[1];

                //check to see if this is the max
                if(turnedY - center.getY() > maxYDistance){
                    //System.out.println("found new max");
                    maxYDistance = turnedY - center.getY();
                    maxPoint = pointList.get(j);
                }
            }

            if(!perimeter.contains(maxPoint))
                perimeter.add(maxPoint);

        }

        //paint lines
        perimeter.add(perimeter.get(0)); //in order to connect the first and last points
        for(int i = 0; i < perimeter.size() - 1; i++)
            panel.connectPoints((int)perimeter.get(i).getX(), (int)perimeter.get(i).getY(), (int)perimeter.get(i+1).getX(), (int)perimeter.get(i+1).getY());
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
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