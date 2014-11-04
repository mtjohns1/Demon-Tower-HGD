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
import java.util.List;

public abstract class Bullet extends Mobile {

	private int _life;
	private int _damage;
	private Mobile _owner;

	/**
	 * Public constructor
	 * 
	 * @param owner the object that created/owns the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public Bullet(Mobile owner, int xAxis, int yAxis) {
		super(owner.getHome());

		//direction input as speed if unspecified
		setVx(xAxis);
		setVy(yAxis);
		decelerate(100); //set range between -1 and 1

		//spawn just out from the center
		setX(owner.getX()+xAxis/20);
		setY(owner.getY()+yAxis/20);
		setZ(owner.getZ());

		_owner = owner;
		_life = Integer.MAX_VALUE; //infinite range if unspecified
		_damage = 0; //no damage if unspecified
	}

	//TODO: Add specific method / parameter for specifying an offset from the player 
	
	/**
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

		//update life time
		_life--;
	}

	@Override
	public void move() {		
		//not a super move, just the default move
		super.move();
		//update life, then remove if dead
		_life--;
		if (_life <= 0)
			setDead();
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
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) || m == _owner)
			return;

		//cast for convenience
		Actor a = (Actor)m;

		//do damage
		a.takeDamage(_damage);

		//vanish
		setLife(0);
	}

	@Override
	public void draw(List<Sprite> list)
	{
		Sprite s = new Sprite(getLeft(), getTop(), getW(), getH(), 0, 0, 0, "tempWall");
		list.add(s);
	}
}
