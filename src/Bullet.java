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
	private double _kvx,_kvy;
	private int _stun;
	private Mobile _owner;

	/**
	 * Public constructor
	 * 
	 * @param owner the object that created/owns the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 * @param offset the distance from the owner it spawns
	 */
	public Bullet(Mobile owner, int xAxis, int yAxis, int offset) {
		super(owner.getHome());

		//direction input as speed if unspecified
		Direction dir = new Direction(xAxis, yAxis);
		setVx(dir.getX());
		setVy(dir.getY());

		//spawn offset from the center
		setX(owner.getX()+(int)(dir.getX()*offset));
		setY(owner.getY()+(int)(dir.getY()*offset));
		setZ(owner.getZ());

		_owner = owner;
		_life = Integer.MAX_VALUE; //infinite range if unspecified
		_damage = 0; //no damage if unspecified
		_kvx = _kvy = 0; //no knockback if unspecified
		_stun = 0; //no stun effect if unspecified
	}

	//TODO: Add specific method / parameter for specifying an offset from the player 
	
	/**
	 * @param owner set who owns the bullet
	 */
	public void setOwner(Mobile owner){
		_owner = owner;
	}

	/**
	 * @return the person who shot/owns the bullet
	 */
	public Mobile getOwner(){
		return _owner;
	}

	/**
	 * @param life set bullet life to this value (in frames)
	 */
	public void setLife(int life){
		_life = life;
	}

	/**
	 * @return  remaining life of bullet (in frames)
	 */
	public int getLife(){
		return _life;
	}

	/**
	 * @param damage set bullet damage to this value
	 */
	public void setDamage(int damage){
		_damage = damage;
	}

	/**
	 * @return damage value of the bullet 
	 */
	public int getDamage(){
		return _damage;
	}

	/**
	 * @param kvx new horizontal knockback of the bullet
	 * @param kvy new vertical knockback of the bullet
	 */
	public void setKnockback(double kvx, double kvy) {
		_kvx = kvx;
		_kvy = kvy;
	}
	
	/**
	 * @return the horizontal knockback
	 */
	public double getKnockbackX() {
		return _kvx;
	}
	
	/**
	 * @return the vertical knockback
	 */
	public double getKnockbackY() {
		return _kvy;
	}

	/**
	 * @param stun new stun duration of the bullet
	 */
	public void setStun(int stun) {
		_stun = stun;
	}
	
	/**
	 * @return stun time of the bullet 
	 */
	public int getStun(){
		return _stun;
	}
	
	@Override
	public void move() {		
		//not a super move, just the default move from the superclass
		super.move();
		//update life, then remove if dead
		_life--;
		if (_life <= 0)
			setDead();
	}
	
	@Override
	public void update() {
		//Blank method by default, most bullets won't use it
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
		if (!(m instanceof Actor) || m == getOwner())
			return;

		//cast for convenience
		Actor a = (Actor)m;

		//do damage
		a.takeDamage(new Damage(getDamage(), getKnockbackX(), getKnockbackY(), getStun()));

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
