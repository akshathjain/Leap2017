/*
Name: Akshath Jain
Date: 8/2/17
Purpose: Game JPanel for Lights Out
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial") //ignore this (this is just for me)
public class Game extends JPanel implements MouseListener{
	private final int NUM_ROWS = 8;
	private final int NUM_COLUMNS = 8;
	private final int INFO_BAR_HEIGHT = 75;	
	private final int FONT_SIZE = 20;

	private double windowWidth;
	private double windowHeight;
	private boolean board[][];
	private int mouseX = -1;
	private int mouseY = -1;
	private double time;
	private int numClicks;

	public Game(){
		super();

		//initialize the board
		board = new boolean[NUM_ROWS][NUM_COLUMNS];

		for(int i = 0; i < (int)(Math.random() * NUM_ROWS * NUM_COLUMNS / 2) + 2; i++)
			changeState((int)(Math.random() * NUM_ROWS), (int)(Math.random() * NUM_COLUMNS));

		//add the mouse listeners
		this.addMouseListener(this);

		//start the timer
		startTimer();
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		//get window width and height
		windowWidth = g.getClipBounds().getWidth();
		windowHeight = g.getClipBounds().getHeight();

		//draw the info bar
		g.setColor(new Color(9,80,193)); //blue
		g.fillRect(0, 0, (int) windowWidth, INFO_BAR_HEIGHT);

		//draw the timer
		g.setColor(new Color(255,255,255)); //white (so we can see the text)
		g.setFont(new Font("COURIER", Font.BOLD, FONT_SIZE));
		g.drawString("Time: " + (int) (time + 0.5) + "s", 20, (int) (INFO_BAR_HEIGHT / 2 + FONT_SIZE * .40));

		//draw the number of clicks
		int clickWidth = g.getFontMetrics().stringWidth("Clicks: " + numClicks); //get the width of the string so i can place it a set distance from the right
		g.drawString("Clicks: " + numClicks, (int)(windowWidth - 20 - clickWidth), (int) (INFO_BAR_HEIGHT / 2 + FONT_SIZE * .40));

		//draw the game board (the grid)
		for(int i = 0; i < NUM_ROWS; i++){
			for(int j = 0; j < NUM_COLUMNS; j++){
				
				//color the screen in a grid like pattern
				if((i + j) % 2 == 0)
					g.setColor(new Color(255, 255, 255)); //white
				else
					g.setColor(new Color(240,240,240)); //grey

				//color the square black if board dictates 
				if(board[i][j])
					g.setColor(new Color(0,0,0)); //black
				
				//draw the rectangle with the specified color
				g.fillRect((int)(j * windowWidth / NUM_COLUMNS), (int)((i * (windowHeight - INFO_BAR_HEIGHT)) / NUM_ROWS) + INFO_BAR_HEIGHT, (int)(windowWidth / NUM_COLUMNS) + 1, (int)(((windowHeight - INFO_BAR_HEIGHT) / NUM_ROWS) + 1));

			}
		}
	}

	//the timer
	private void startTimer(){
		new Thread(new Runnable(){
			@Override
			public void run(){
				while(!checkIfGameFinished()){
					try{
						Thread.sleep(500); //wait 1 second
					}catch(InterruptedException e){

					}
					time += 0.5;
					repaint();
				}
			}
		}).start();
	}

	//check if the user has won
	private boolean checkIfGameFinished(){
		for(int i = 0; i < NUM_ROWS; i++){
			for(int j = 0; j < NUM_COLUMNS; j++){
				if(board[i][j])
					return false;
			}
		}

		return true;
	}

	//switches the state of the block clicked and all the blocks around it
	private void changeState(int row, int col){	
		board[row][col] = !board[row][col];

		if(row + 1 < NUM_ROWS)
			board[row + 1][col] = !board[row + 1][col];

		if(row - 1 >= 0)
			board[row - 1][col] = !board[row - 1][col];

		if(col + 1 < NUM_COLUMNS)
			board[row][col + 1] = !board[row][col + 1];

		if(col - 1 >= 0)
			board[row][col - 1] = !board[row][col - 1];
	}

	@Override //called when user clicks the mouse
	public void mouseClicked(MouseEvent e){
		this.mouseX = e.getX();
		this.mouseY = e.getY();

		//increment the number of clicks
		numClicks++;

		//change the board state
		if(mouseY > INFO_BAR_HEIGHT)
			changeState((int)((mouseY - INFO_BAR_HEIGHT) / ((windowHeight - INFO_BAR_HEIGHT) / (double) NUM_ROWS)), (int)(mouseX / (windowWidth / (double) NUM_COLUMNS)));

		//update the screen
		repaint();

		//check if game is over
		if(checkIfGameFinished())
			JOptionPane.showMessageDialog(this, "You Win!");
	}

	@Override //useless
	public void mouseEntered(MouseEvent e){

	}

	@Override //useless
	public void mouseExited(MouseEvent e){

	}

	@Override //useless
	public void mousePressed(MouseEvent e){

	}

	@Override //useless
	public void mouseReleased(MouseEvent e){

	}
}