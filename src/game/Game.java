package game;
/**
 * 
 * The main class for executing the game
 * 	@author Thomas
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import sprite.Sprite;
import sprite.SpriteComparitor;
import world.Floor;
import world.Room;
import control.Control;
import menu.*;
import menu.Menu;
import mobile.Player;

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
	private boolean reset = false;
	private boolean win = false;
	private ArrayList<Menu> menu = new ArrayList<Menu>();


	public ArrayList<Menu> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Menu> menu) {
		this.menu = menu;
	}

	public boolean getWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	int mapX;
	int mapY;
	int mapZ;
	Room currentRoom = new Room();
	Floor f[] = new Floor[4];
	int whatFloor = 0;
	
	
	public Game(Control c){
		//box.add(exit);
		final int WIDTH = 640;
		final int HEIGHT = 480; 

		this.setSize(WIDTH, HEIGHT);
		this.controls = c;
	}

	public void run() {
		
		for(int i = 0; i < 4; i++){
			f[i] = new Floor();
		}
		//build important objects
		Room r = new Room(1);
		Player p = new Player(r, controls);
		//create a timer
		Timer t = new Timer(18);
		
		//generic position
		p.setTop(100);
		p.setLeft(100);
		
		//main loop (it never ends!)
		boolean paused = true;
		boolean pressed = false;
		
		PauseMenu.menuInit();
		
		menu.add(new StartMenu(this.controls));
		
		while(true) {
			if(this.controls.getMenu().escape()){
				if (pressed == false){
					pressed = true;
					if(menu.isEmpty()){	
						menu.add(new PauseMenu(this.controls, this));
						paused = true;
					}
					else{
						//to add check to see if menu is start menu
						//menu.remove(menu.size()-1);
					}					
				}
			}
			else{
				pressed = false;
			}
			if(win && menu.isEmpty()){
				menu.add(new WinMenu(this.controls, this));
				paused = true;
			}
			if(p.isDead() && menu.isEmpty()){
				menu.add(new GameOverMenu(this.controls, this));
				paused = true;
			}
			if(win && menu.isEmpty()){
				menu.add(new WinMenu(this.controls, this));
				paused = true;
			}
			for(int i = 0; i < menu.size(); i++){//for(Menu m : menu.){
				Menu m = menu.get(i);
				if (m.isDead()){
					menu.remove(m);
				}
			}
			if(menu.isEmpty()){
				paused = false;
			}
			
			
			if(paused == false){
				t.startLoop();
				r.update();
				r = RoomDesign(r, p);
				r.draw(getSprites());
				redraw();
				t.endLoop();
			}else{
				
				t.startLoop();
				
				r.draw(getSprites());
				menu.get(menu.size()-1).update();
				menu.get(menu.size()-1).drawMenu(getSprites());
				
				redraw();
				t.endLoop();
			}
			
			if(reset)
				break;
			
		//	t.startLoop();
			//r.update();
			//RoomDesign();
			
			
			//r.draw(getSprites());	
			//redraw();
			//t.endLoop();
		}
	}
	
	public void checkWin(){
		if(this.whatFloor == 1){
			//call win fuction
		}
	}
	
	
	public Room RoomDesign(Room n, Player c){
		Room r = n;
		Player p = c;
		
		
		if(r.getDirection().equals("east") )
		{
			if(mapX == 3)
				mapX = 0;
			else
				mapX += 1;
			Room newR = f[whatFloor].get(mapX, mapY); 
			//this.whatFloor = 1;
			//this.setWin(true);
			newR.addMobile(p);
			p.setTop(205);
			p.setLeft(5);
			//TODO: Migrate player to newR
			r = newR;
		}
		
		else if(r.getDirection().equals("west") )
		{
			if(mapX == 0)
				mapX = 3;
			else
				mapX -= 1;
			Room newR = f[whatFloor].get(mapX, mapY); 
			newR.addMobile(p);
			p.setTop(205);
			p.setRight(635);
			//TODO: Migrate player to newR
			r = newR;
		}
		
		else if(r.getDirection().equals("north") )
		{
			if(mapY == 0)
				mapY = 3;
			else
				mapY -= 1;
			Room newR = f[whatFloor].get(mapX, mapY); 
			newR.addMobile(p);
			p.setBottom(440);
			p.setLeft(300);
			//TODO: Migrate player to newR
			r = newR;
		}
		//bug in collision.
		else if(r.getDirection().equals("south") )
		{
			if(mapY == 3)
				mapY = 0;
			else
				mapY += 1;
			Room newR = f[whatFloor].get(mapX, mapY); 
			newR.addMobile(p);
			p.setTop(35);
			p.setLeft(300);
			//TODO: Migrate player to newR
			r = newR;
		}
		
		
		return r;
		
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
	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}
}
