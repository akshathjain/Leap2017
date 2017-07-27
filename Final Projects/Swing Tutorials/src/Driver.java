/*
Name: Akshath Jain
Date: 7/27/17
Purpose: driver class for swing tutorials
*/

import javax.swing.*;

public class Driver{
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
		
		JFrame frame = new JFrame("Java Swing Demo");
		MyCustomPanel panel = new MyCustomPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.add(panel);
		frame.setVisible(true);
	}
}