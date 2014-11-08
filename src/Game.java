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
import java.util.Collections;
import java.util.LinkedList;

public class Game extends JPanel {

	//private ImageObserver observer;
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private Control controls;
	int mapX;
	int mapY;
	int mapZ;
	Room currentRoom = new Room();
	Floor f = new Floor();
	
	public Game(Control c){
		//box.add(exit);
		final int WIDTH = 640;
		final int HEIGHT = 480; 

		this.setSize(WIDTH, HEIGHT);
		this.controls = c;
	}

	public void run() {
		//build important objects
		Room r = new Room();
		Player p = new Player(r, controls);
		//create a timer
		Timer t = new Timer(18);
		
		//generic position
		p.setTop(100);
		p.setLeft(100);
		
		//main loop (it never ends!)
		while(true) {
			t.startLoop();
			r.update();
			if(r.getEast().equals("east"))
			{
				mapX += 1;
				//f.getEast();
				Room newR = f.get(mapX, mapY); 
				//p.setHome(newR);
				newR.addMobile(p);
				p.setTop(100);
				p.setLeft(100);
				//TODO: Migrate player to newR
				r = newR;
			}
			r.draw(getSprites());	
			redraw();
			t.endLoop();
		}
	}
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}



	public void setSprites(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}



	public void redraw(){
		Image backBuffer = createImage(640,480);

		Graphics g = backBuffer.getGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

		Collections.sort(sprites, new SpriteComparitor());
		if(!sprites.isEmpty()){
			for(int c = 0; c < sprites.size(); c++){
				sprites.get(c).draw(g);

			}
		}
		sprites.clear();
		getGraphics().drawImage(backBuffer, 0, 0, null);

	}
}
