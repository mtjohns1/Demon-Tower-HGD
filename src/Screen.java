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
import java.util.ArrayList;

public class Screen {

	public static void startup(){
		//load images on the front end
		Sprite.load();
		
		//loop until System.Exit()
		while (true)
		{
			final Control controls = new Control(); //create controls
			final Game game = new Game(controls); //initialize a game
			JFrame frame = new JFrame(); //create the jframe
			controls.init(frame); //add listeners
			
			frame.add(game);
			frame.getContentPane().setPreferredSize(new Dimension(640,480));
			frame.setResizable(false);
			frame.setVisible(true);
			frame.pack();

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			//run the game
			game.run();
		}
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startup();
	}




}
