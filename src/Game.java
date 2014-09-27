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

	
	
	//private JButton exit = new JButton("Exit Button");
	
	
	public Game(){
		//box.add(exit);
		final int WIDTH = 640;
		final int HEIGHT = 480; 

		this.setSize(WIDTH, HEIGHT);
		
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		g.setColor(Color.red);
		g.drawOval( 10,10,10,10);
		repaint();
	}

	
}
