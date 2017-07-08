/*
Name: Akshath Jain
Date: 7/7/17
Purpose: Display panel for convex hull
*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayPanel extends JPanel{
	private final int VERTICAL_MARGIN = 30;
	private final int HORIZONTAL_MARGIN = 30;
	private ArrayList<Point> pointList;
	private ArrayList<Line> lineList;
	private Point center;

	public DisplayPanel(){
		super();
		pointList = new ArrayList<>();
		lineList = new ArrayList<>();
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.setColor(new Color(0,0,0));
		for(int i = 0; i < pointList.size(); i++)
			g.fillRect(pointList.get(i).getX() + HORIZONTAL_MARGIN, pointList.get(i).getY() + VERTICAL_MARGIN,5,5);

		for(int i = 0; i < lineList.size(); i++)
			g.drawLine(lineList.get(i).getStart().getX() + HORIZONTAL_MARGIN, lineList.get(i).getStart().getY() + VERTICAL_MARGIN, lineList.get(i).getEnd().getX() + HORIZONTAL_MARGIN, lineList.get(i).getEnd().getY() + VERTICAL_MARGIN);

		g.setColor(Color.BLUE);
		if(center != null)
			g.fillRect(center.getX() + HORIZONTAL_MARGIN, center.getY() + VERTICAL_MARGIN,5,5);

	}

	public void addPoint(int x, int y){
		pointList.add(new Point(x,y));
		repaint();
	}

	public void addCenter(int x, int y){
		this.center = new Point(x, y);
	}

	public void connectPoints(int x1, int y1, int x2, int y2){
		lineList.add(new Line(new Point(x1,y1), new Point(x2, y2)));
		repaint();
	}

	public void clear(){
		pointList = new ArrayList<>();
		lineList = new ArrayList<>();
		repaint();
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