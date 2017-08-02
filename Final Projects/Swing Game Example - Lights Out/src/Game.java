/*
Name: Akshath Jain
Date: 8/2/17
Purpose: Game JPanel for Lights Out
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;

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
		board[(int)(Math.random() * NUM_ROWS)][(int)(Math.random() * NUM_COLUMNS)] = true;

		//add the mouse listeners
		this.addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		windowWidth = g.getClipBounds().getWidth();
		windowHeight = g.getClipBounds().getHeight();

		for(int i = 0; i < NUM_ROWS; i++){
			for(int j = 0; j < NUM_COLUMNS; j++){
				if((i + j) % 2 == 0)
					g.setColor(new Color(255, 255, 255));
				else
					g.setColor(new Color(240,240,240));
			

				if(board[i][j])
					g.setColor(new Color(0,0,0));
				
				g.fillRect((int)Math.round(i * windowWidth / NUM_ROWS), (int)Math.round(j * windowHeight / NUM_COLUMNS), (int)Math.round(windowWidth / NUM_ROWS) + 1, (int)Math.round(windowHeight / NUM_COLUMNS) + 1);

			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e){
		this.mouseX = e.getX();
		this.mouseY = e.getY();

		//change the board state
		changeState((int)(mouseX / (windowWidth / NUM_ROWS)), (int)(mouseY / (windowWidth / NUM_COLUMNS)));

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

	private boolean checkIfGameFinished(){
		for(int i = 0; i < NUM_ROWS; i++){
			for(int j = 0; j < NUM_COLUMNS; j++){
				if(!board[i][j])
					return false;
			}
		}

		return true;
	}

	private int round(double x){
		return (int) (x + 0.5);
	}

	private void changeState(int x, int y){	
		board[x][y] = !board[x][y];

		if(x + 1 < NUM_ROWS){
			board[x + 1][y] = !board[x + 1][y];
		}

		if(x - 1 >= 0)
			board[x - 1][y] = !board[x - 1][y];

		if(y + 1 < NUM_COLUMNS)
			board[x][y + 1] = !board[x][y + 1];

		if(y - 1 >= 0)
			board[x][y - 1] = !board[x][y - 1];
	}
}