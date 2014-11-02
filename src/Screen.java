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
		final Control controls = new Control();
		final Game game = new Game(controls);
		JFrame frame = new JFrame();

		//adds key listeners for movement and shooting
		controls.init(frame);
		
		//game.setPreferredSize(new Dimension(640, 480));
		
		frame.add(game);
		frame.getContentPane().setPreferredSize(new Dimension(640,480));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Sprite.load();
		
		game.run();
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startup();
	}




}
