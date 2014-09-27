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
		
		JFrame frame = new JFrame();
		frame.add(game);
		frame.setSize(600,600);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.repaint();
		
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startup();
	}




}
