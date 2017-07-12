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
        perimeter.add(pointList.get(0));


        double maxX = pointList.get(0).getX();
        double minX = pointList.get(0).getX(); 
        double maxY = pointList.get(0).getY(); 
        double minY = pointList.get(0).getY();

        for(int i = 1; i < pointList.size(); i++){
            if(pointList.get(i).getY() > maxY){
                maxY = pointList.get(i).getY();
            } //max y
            else if(pointList.get(i).getY() < minY){ //min y
                minY = pointList.get(i).getY();
            }

            if(pointList.get(i).getX() > maxX){ //max x
                perimeter.set(0, pointList.get(i));
                maxX = pointList.get(i).getX();
            }
            else if(pointList.get(i).getX() < minX){ //min x
                minX = pointList.get(i).getX();
            }

        }

        Point center = new Point((maxX - minX) / 2.0 + minX, (maxY - minY) / 2.0 + minY);
        panel.addCenter((int)center.getX(), (int)center.getY());

        //now finish the hull
        double rotationalCounter = perimeter.get(0).getAngle(center);
        while(rotationalCounter < 360){
            //find second rightMost point compared to the maxX
            double mX = minX;
            Point pX = null;
            for(int i = 0; i < pointList.size(); i++){
                Point current = pointList.get(i);
                double[] turned = current.turn(-1 * rotationalCounter, distance(center, current), center);
                if(turned[1] > perimeter.get(perimeter.size() - 1).turn(-1 * rotationalCounter, distance(center, perimeter.get(perimeter.size() - 1)), center)[1]){
                    if(turned[0] > mX && turned[0] < maxX){
                        mX = pointList.get(i).getX();
                        pX = current;
                    }
                }
            }

            perimeter.add(pX);
            if(pX != null)
               rotationalCounter = pX.getAngle(center);
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

    public double getAngle(Point center){
        return Math.atan((y - center.getY()) / (x - center.getX()));
    }

    public double[] turn(double degrees, double radius, Point center){
        double radians = degrees * Math.PI / 180.0;
        double thetaNot = getAngle(center);

        //do a quadrant check and convert
        if(x - center.getX() < 0)
            thetaNot += Math.PI;
        else if(y - center.getY() < 0)
            thetaNot += 2 * Math.PI;

        double thetaOne = thetaNot + radians;
        return new double[]{radius * Math.cos(thetaOne) + center.getX(), radius * Math.sin(thetaOne) + center.getY()};
    }

    @Override
    public boolean equals(Object o){
        Point other = (Point) o;
        return other.x == this.x && other.y == this.y;
    }
}