/*
Name: Akshath Jain
Date: 7/27/17
Purpose: custom panel class for demo, pointer follows ball around and random button that does nothing
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MyCustomPanel extends JPanel implements MouseMotionListener{
	private int mouseX;
	private int mouseY;
	private String userName;

	public MyCustomPanel(){
		super();

		//set color to white
		super.setBackground(new Color(240,240,240));

		//add mouse listener
		super.addMouseMotionListener(this);

		//add a random button
		JButton randomButton = new JButton("Random");
		randomButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "Null Colored Pen");
				setColor();
			}
		});
		this.add(randomButton);

		JButton getUserName = new JButton("Get Name");
		getUserName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				userName = JOptionPane.showInputDialog(null, "Enter your name");
			}
		});
		this.add(getUserName);
	}

	private void setColor(){
		super.setBackground(new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		//draws the circle whereever the mouse is
		g.setColor(new Color((mouseX) % 256, (mouseY) % 256, ((mouseX * mouseY) / 10) % 256));
		g.fillOval(mouseX - 35, mouseY - 35, 70, 70);
		

		if(userName != null && (int)(Math.random() * 5) == 0){
			g.setColor(new Color(0,0,0));
			g.drawString(userName, (int)(Math.random() * 500), (int)(Math.random() * 500));
		}
		g.setColor(new Color(255,255,255));
	}

	public void mouseMoved(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();
		repaint();
	}

	public void mouseDragged(MouseEvent e){
		
	}
}