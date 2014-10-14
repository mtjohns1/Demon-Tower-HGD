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
				game.sprites.add(new Sprite(game.getCX(),game.getCY(),60, 75,0,0,2,game.getTestImage()));
				game.sprites.add(new Sprite(100+(game.getCX()/2),100+(game.getCY()/2),36, 36,0,0,1,game.getHero()));
				//frame.repaint();
				try {
					Thread.sleep((long)1000/60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//game.sprites.clear();
				game.redraw();
			}
			while(game.getCY() < 470){
				game.setCY(game.getCY()+1);
				game.sprites.add(new Sprite(game.getCX(),game.getCY(),60, 75,0,0,2,game.getTestImage()));
				game.sprites.add(new Sprite(100+(game.getCX()/2),100+(game.getCY()/2),36, 36,0,0,1,game.getHero()));
				//frame.repaint();
				try {
					Thread.sleep((long)1000/60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//game.sprites.clear();
				game.redraw();
			}
			while(game.getCX() > 10){
				game.setCX(game.getCX()-1);
				game.sprites.add(new Sprite(game.getCX(),game.getCY(),60, 75,0,0,2,game.getTestImage()));
				game.sprites.add(new Sprite(100+(game.getCX()/2),100+(game.getCY()/2),36, 36,0,0,1,game.getHero()));
				//frame.repaint();
				try {
					Thread.sleep((long)1000/60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//game.sprites.clear();
				game.redraw();
			}
			while(game.getCY() > 10){
				game.setCY(game.getCY()-1);
				game.sprites.add(new Sprite(game.getCX(),game.getCY(),60, 75,0,0,2,game.getTestImage()));
				game.sprites.add(new Sprite(100+(game.getCX()/2),100+(game.getCY()/2),36, 36,0,0,1,game.getHero()));
				//frame.repaint();
				try {
					Thread.sleep((long)1000/60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//game.sprites.clear();
				game.redraw();
			}
		}


	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startup();
	}




}
