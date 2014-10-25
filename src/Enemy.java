/**
 * 
 * A class for the enemies
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class Enemy extends Actor {
	
	private int health = 100; 
	public Enemy(Room home) {
		super(home);

		setW(28);
		setH(28);
		setD(32);
	}
	/**
	 * 
	 * @param newHealth sets health to new int
	 */
	public void setHealth(int newHealth){
		health = newHealth;
	}
	/**
	 * 
	 * @return Health of character
	 */
	public int getHealth(){
		return health;
	}
	/**
	 * 
	 * @param increment adds new int to health and returns new health total. 
	 * 					use neg ints to decrement health
	 * @return health, returns new health value
	 */
	public int incrementHealth(int increment){
		health = health + increment;
		return health;
	}
	
	/**
	 * 
	 * @return boolean true if dead false if alive
	 */
	public boolean isDead(){
		if (health <= 0) return true;
		return false;
	}
	
	/**
	 * Manage collisions with a tile
	 * 
	 * @param t: the Tile object collided with
	 * @param dir: the direction of the collision
	 */
	public void tileCollision(Tile t, String dir) {
		
	}
	
	/**
	 * call on each frame to update.
	 */
	public void update(){
		
	}
	
	
}
