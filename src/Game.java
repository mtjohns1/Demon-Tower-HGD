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

public class Game extends JFrame implements ActionListener, KeyListener {
	
	
	public JPanel box = new JPanel(new FlowLayout());
	
	//private JButton exit = new JButton("Exit Button");
	
	
	public Game(){
		super("This is a game placeholder");
		//box.add(exit);
		final int WIDTH = 640;
		final int HEIGHT = 480; 
		this.add(box);
		
		setLocationByPlatform(true);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
