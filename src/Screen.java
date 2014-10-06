/**
 * 
 * A class for handling screen drawing
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;

public class Screen {


	public static void startup(){
		final Game game = new Game();
		final Stick controls = new Stick();
		JFrame frame = new JFrame();
		frame.addKeyListener(controls);
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
