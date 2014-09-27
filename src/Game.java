/**
 * 
 * The main class for executing the game
 * 
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class Game extends JPanel {

	
	
	private int circlex;
	private int circley;

	//private JButton exit = new JButton("Exit Button");
	
	
	public Game(){
		//box.add(exit);
		final int WIDTH = 640;
		final int HEIGHT = 480; 
		
		this.circlex = 10;
		this.circley = 10;

		this.setSize(WIDTH, HEIGHT);
		
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		g.setColor(Color.red);
		g.drawOval( this.circlex, this.circley ,10,10);
		repaint();
	}

	public void setCX(int x){
		this.circlex = x;
		
	}
	
	public void setCY(int y){
		this.circley = y;
		
	}
	
	public int getCY(){
		return this.circley;
	}
	
	public int getCX(){
		return this.circlex;
	}
	
	
}
