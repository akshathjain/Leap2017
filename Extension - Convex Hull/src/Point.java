/*
Name: Akshath Jain
Date: 7/17/17
Purpose: Point class for convex hull usage
*/

public class Point{
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point(double[] xy){
        this.x = xy[0];
        this.y = xy[1];
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getXInt(){
        return (int) x;
    }

    public int getYInt(){
        return (int) y;
    }

    public double getAngle(Point center){
        double deg = 180.0 / Math.PI * Math.atan((y - center.getY()) / (x - center.getX()));
        if(x - center.getX() < 0)
            deg += 180;

        return deg;
    }

    public static double getAngle(double[] c1, double[] c2){
        Point p = new Point(c1[0], c1[1]);
        return p.getAngle(new Point(c2[0], c2[1]));
    }

    public double getAngleBetween(Point other){
        return Math.atan((other.y - this.y) / (this.x - other.x)) * 180 / Math.PI;
    }

    public static double getAngleBetween(double[] c1, double[] c2){
        Point p = new Point(c1[0], c1[1]);
        return p.getAngleBetween(new Point(c2[0], c2[1]));
    }

    public double[] turn(double degrees, double radius, Point center){
        double radians = degrees * Math.PI / 180.0;
        double thetaNot = getAngle(center) * Math.PI / 180.0;

        double thetaOne = thetaNot + radians;
        return new double[]{radius * Math.cos(thetaOne) + center.getX(), radius * Math.sin(thetaOne) + center.getY()};
    }

    @Override
    public boolean equals(Object o){
        Point other = (Point) o;
        return other.x == this.x && other.y == this.y;
    }
}