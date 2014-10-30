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

	private int _life;
	private int _damage;
	private Mobile _owner;
	
	/**
	 * Public constructor
	 * 
	 * @param owner the object that created the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public Bullet(Mobile owner, int xAxis, int yAxis) {
		super(owner.getHome());
		
		setVx(xAxis);
		setVy(yAxis); //direct input as speed if unspecified
		
		_owner = owner;
		_life = Integer.MAX_VALUE; //infinite range if unspecified
		_damage = 0; //no damage if unspecified
		//TODO: set default velocity by owner's aim?
	}
	
	/**
	 * 
	 * @param owner set who owns the bullet
	 */
	public void setOwner(Mobile owner){
		_owner = owner;
	}

	/**
	 * 
	 * @return the person who shot/owns the bullet
	 */
	public Mobile getOwner(){
		return _owner;
	}
	
	/**
	 * 
	 * @param life set bullet life to this value (in frames)
	 */
	public void setLife(int life){
		_life = life;
	}
	
	/**
	 *
	 * @return  remaining life of bullet (in frames)
	 */
	public int getLife(){
		return _life;
	}
	
	/**
	 * 
	 * @param damage set bullet damage to this value
	 */
	public void setDamage(int damage){
		_damage = damage;
	}
	
	/**
	 *
	 * @return  damage value of the bullet 
	 */
	public int getDamage(){
		return _damage;
	}
	
	@Override
	public void update() {
		//remove if dead
		if (_life <= 0 || getDead()) {
			setDead();
			return;
		}
		
		//update position and check collisions
		move();
		
		//update life time
		_life--;
	}
	
	@Override
	public void tileCollision(Tile t, String dir) {
		//break on walls
		if (t.getType().contains("w")) {
			setLife(0);
		}
	}
	
	@Override
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		if (!(m instanceof Actor)) return; //ignore non-actors
		
		Actor a = (Actor)m; //cast for convenience
		//do damage
		a.takeDamage(_damage);
		setLife(0); //vanish
	}
}
