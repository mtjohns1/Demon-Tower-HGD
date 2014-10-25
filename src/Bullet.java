/**
 * 
 * A class for projectiles
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class Bullet extends Mobile {

	private int health = 100;
	private Mobile owner;
	
	/**
	 * 
	 * @param home		Room bullet exists in
	 * @param shooter	Actor who shot the bullet.
	 */
	public Bullet(Room home, Mobile shooter) {
		super(home);
		owner = shooter;
	}
	
	/**
	 * 
	 * @param shooter set who shot/owns the bullet
	 */
	public void setShooter(Mobile shooter){
		owner = shooter;
	}
	
	/**
	 * 
	 * @return the person who shot/owns the bullet
	 */
	public Mobile getShooter(){
		return owner;
	}
	
	/**
	 * 
	 * @param life set bullet health to this int
	 */
	public void setHealth(int life){
		health = life;
	}
	
	/**
	 *
	 * @return  get remaining health of bullet 
	 */
	public int getHealth(){
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
	

}
