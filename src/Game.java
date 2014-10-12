/**
 * 
 * The main class for executing the game
 * 	@author Thomas
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game extends JPanel {



	private int circlex;
	private int circley;

	private Image testImage;
	private Image hero;
	private ImageObserver observer;

	private Sprite testSprite;

	public ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	//private JButton exit = new JButton("Exit Button");




	public Game(){
		//box.add(exit);
		final int WIDTH = 600;
		final int HEIGHT = 480; 

		this.circlex = 10;
		this.circley = 10;

		this.setSize(WIDTH, HEIGHT);

		//I'm getting this from stackoverflow, so if its not what
		//we're using for image loading please tell me -Thomas
		//ClassLoader load = Thread.currentThread().getContextClassLoader();

		File input = new File("src/generic_Evil_creature_small.jpg");

		try {
			testImage =  ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File Hero = new File("src/hero dude and sword.png");

		try {
			hero =  ImageIO.read(Hero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		testSprite = new Sprite(100, 100, 36, 36, 0, 0, 1, hero);
	}


	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());



		//g.setColor(Color.red);
		//g.drawOval( this.circlex, this.circley ,10,10);

		//g.drawImage(testImage, this.circlex, this.circley, observer);

		if(!sprites.isEmpty()){
			for(int c = 0; c < sprites.size(); c++){
				sprites.get(c).draw(g);

			}
		}
		//sprites.clear();


		//g.setColor(Color.red);
		//g.fillRect(0, 0, getWidth(), getHeight());
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


	public Image getTestImage() {
		return testImage;
	}


	public Image getHero() {
		return hero;
	}

}
