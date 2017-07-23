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
	private ArrayList<Point> significantPoint;;

	public DisplayPanel(){
		super();
		pointList = new ArrayList<>();
		lineList = new ArrayList<>();
		significantPoint = new ArrayList<>();
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int y = (int) g.getClipBounds().getHeight();

		g.setColor(new Color(0,0,0));
		for(int i = 0; i < pointList.size(); i++)
			g.fillRect(pointList.get(i).getXInt() + HORIZONTAL_MARGIN, y - pointList.get(i).getYInt() - VERTICAL_MARGIN,5,5);

		for(int i = 0; i < lineList.size(); i++)
			g.drawLine(lineList.get(i).getStart().getXInt() + HORIZONTAL_MARGIN, y - lineList.get(i).getStart().getYInt() - VERTICAL_MARGIN, lineList.get(i).getEnd().getXInt() + HORIZONTAL_MARGIN, y - lineList.get(i).getEnd().getYInt() - VERTICAL_MARGIN);

		g.setColor(Color.BLUE);
		for(int i = 0; i < significantPoint.size(); i++)
			g.fillRect(significantPoint.get(i).getXInt() + HORIZONTAL_MARGIN, y - significantPoint.get(i).getYInt() - VERTICAL_MARGIN,5,5);

	}

	public void addPoint(Point p){
		pointList.add(p);
		repaint();
	}

	public void addPoint(int x, int y){
		pointList.add(new Point(x,y));
		repaint();
	}

	public void addSignificantPoint(int x, int y){
		significantPoint.add(new Point(x, y));
		repaint();
	}

	public void addSignificantPoint(Point p){
		significantPoint.add(p);
		repaint();
	}

	public void connectPoints(int x1, int y1, int x2, int y2){
		lineList.add(new Line(new Point(x1,y1), new Point(x2, y2)));
		repaint();
	}

	public void connectPoints(Point p1, Point p2){
		lineList.add(new Line(p1, p2));
		repaint();
	}

	public void clear(){
		pointList = new ArrayList<>();
		lineList = new ArrayList<>();
		repaint();
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