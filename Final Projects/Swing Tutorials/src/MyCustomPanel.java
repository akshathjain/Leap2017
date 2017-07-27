/*
Name: Akshath Jain
Date: 7/27/17
Purpose: custom panel class for demo, pointer follows ball around and random button that does nothing
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;


public class MyCustomPanel extends JPanel implements MouseMotionListener{
	private int mouseX;
	private int mouseY;
	private String userName;
	private BufferedImage image;

	public MyCustomPanel(){
		super();

		JButton randomButton = new JButton("Random");
		randomButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "Null colored pen");
				changeBackground();
			}
		});
		super.add(randomButton); //this adds the button to the panel

		//add the mouselistener methods to the panel
		super.addMouseMotionListener(this);

		//button to ask user for name
		JButton nameGetter = new JButton("User Name");
		nameGetter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				userName = JOptionPane.showInputDialog(null, "Enter a name");
			}
		});
		super.add(nameGetter);

		try{
			image = ImageIO.read(new File("image.jpg"));
		}catch(Exception e){
			e.printStackTrace();
		}	
	}

	//changes the background color randomly
	private void changeBackground(){
		super.setBackground(new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.setColor(new Color(mouseX % 256, mouseY % 256, (mouseX*mouseY) % 256));
		g.fillOval(mouseX - 35, mouseY - 35, 70, 70);

		//randomly draw the name on screen
		if(userName != null && (int)(Math.random() * 5) == 0){
			g.setFont(new Font("ARIAL", Font.PLAIN, 200)); //set font to arial, 20px
			g.drawString(userName, (int)(Math.random() * g.getClipBounds().getWidth()), (int)(Math.random() * g.getClipBounds().getHeight()));
		}

		g.drawImage(image, 0, 0, null);
	}

	//this is called whenever we click and drag
	@Override
	public void mouseDragged(MouseEvent e){

	}

	//this one is called everytime we drag the mouse
	@Override
	public void mouseMoved(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();
		super.repaint();
	}

}
































/*public class MyCustomPanel extends JPanel implements MouseMotionListener{
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
}*/