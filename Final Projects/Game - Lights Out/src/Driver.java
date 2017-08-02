/*
Name: Akshath Jain
Date: 8/2/17
Purpose: Light's out game
*/

import java.util.*;
import javax.swing.*;

public class Driver{
	public static void main(String[] args) {
		//make Java Swing actually look good
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
		}

		//create Jframe
		JFrame frame = new JFrame("Lights Out");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //so the jframe closes when i press the red x on the top right
		frame.setSize(500,500); //size of the frame (this is adjustable)
		Game gamePanel = new Game();
		frame.add(gamePanel);
		frame.setVisible(true);
	}
}