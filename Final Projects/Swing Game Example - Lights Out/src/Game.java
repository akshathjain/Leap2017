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
	
	private double windowWidth;
	private double windowHeight;
	private boolean board[][];
	private int mouseX = -1;
	private int mouseY = -1;

	public Game(){
		super();

		//initialize the board
		board = new boolean[NUM_ROWS][NUM_COLUMNS];

		for(int i = 0; i < (int)(Math.random() * NUM_ROWS * NUM_COLUMNS / 2) + 2; i++)
			changeState((int)(Math.random() * NUM_ROWS), (int)(Math.random() * NUM_COLUMNS));

		//add the mouse listeners
		this.addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		//get window width and height
		windowWidth = g.getClipBounds().getWidth();
		windowHeight = g.getClipBounds().getHeight();

		for(int i = 0; i < NUM_ROWS; i++){
			for(int j = 0; j < NUM_COLUMNS; j++){
				
				//color the screen in a grid like pattern
				if((i + j) % 2 == 0)
					g.setColor(new Color(255, 255, 255));
				else
					g.setColor(new Color(240,240,240));
			
				//color the square black if board dictates 
				if(board[i][j])
					g.setColor(new Color(0,0,0));
				
				//draw the rectangle with the specified color
				g.fillRect((int)Math.round(j * windowWidth / NUM_COLUMNS), (int)Math.round(i * windowHeight / NUM_ROWS), (int)Math.round(windowWidth / NUM_COLUMNS) + 1, (int)Math.round(windowHeight / NUM_ROWS) + 1);

			}
		}
	}

	@Override //called when user clicks the mouse
	public void mouseClicked(MouseEvent e){
		this.mouseX = e.getX();
		this.mouseY = e.getY();

		//change the board state
		changeState((int)(mouseY / (windowHeight / (double) NUM_ROWS)), (int)(mouseX / (windowWidth / (double) NUM_COLUMNS)));

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

	//check if the user has won
	private boolean checkIfGameFinished(){
		for(int i = 0; i < NUM_ROWS; i++){
			for(int j = 0; j < NUM_COLUMNS; j++){
				if(!board[i][j])
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
}