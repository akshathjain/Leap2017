/*
Name: Akshath Jain
Date: 8/2/17
Purpose: Light's out game
*/

import java.util.*;
import javax.swing.*;

public class Driver{
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
		}

		JFrame frame = new JFrame("Lights Out");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		Game gamePanel = new Game();
		frame.add(gamePanel);
		frame.setVisible(true);
	}
}