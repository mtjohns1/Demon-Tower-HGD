/**
 * 
 * A class for handling screen drawing
 *	@author Thomas
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;

public class Screen {


	public static void startup(){
		final Game game = new Game();
		final Control controls = new Control();
		JFrame frame = new JFrame();
		
		//adds key listeners for movement and shooting
		frame.addKeyListener(controls.getMove());
		frame.addKeyListener(controls.getShoot());
		
		frame.add(game);
		frame.setSize(600,480);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		while(true){
			while(game.getCX() < 590){
				game.setCX(game.getCX()+1);
				frame.repaint();
				
			}
			while(game.getCY() < 470){
				game.setCY(game.getCY()+1);
				frame.repaint();
				
			}
			while(game.getCX() > 10){
				game.setCX(game.getCX()-1);
				frame.repaint();
				
			}
			while(game.getCY() > 10){
				game.setCY(game.getCY()-1);
				frame.repaint();
				
			}
			
		}
		
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startup();
	}




}
