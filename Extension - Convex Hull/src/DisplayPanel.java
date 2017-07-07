/*
Name: Akshath Jain
Date: 7/7/17
Purpose: Display panel for convex hull
*/

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayPanel extends JPanel{
	ArrayList<Point> pointList;
	ArrayList<Line> lineList;

	public DisplayPanel(){
		super();
		pointList = new ArrayList<>();
		lineList = new ArrayList<>();
	}

	@Override
	public void paintComponent(Graphics g){
		for(int i = 0; i < pointList.size(); i++)
			g.fillRect(pointList.get(i).getX(), pointList.get(i).getY(), 5,5);

		for(int i = 0; i < lineList.size(); i++)
			g.drawLine(lineList.get(i).getStart().getX(), lineList.get(i).getStart().getY(), lineList.get(i).getEnd().getX(), lineList.get(i).getEnd().getY());
	}

	public void addPoint(int x, int y){
		pointList.add(new Point(x,y));
		repaint();
	}

	public void connectPoints(int x1, int y1, int x2, int y2){
		lineList.add(new Line(new Point(x1,y1), new Point(x2, y2)));
		repaint();
	}

	public void clear(){
		pointList.clear();
		lineList.clear();
	}

	private class Point{
		private int x;
		private int y;

		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		public int getX(){
			return x;
		}

		public int getY(){
			return y;
		}
	}

	private class Line{
		private Point p1, p2;

		public Line(Point one, Point two){
			this.p1 = one;
			this.p2 = two;
		}

		public Point getStart(){
			return p1;
		}

		public Point getEnd(){
			return p2;
		}
	}
}