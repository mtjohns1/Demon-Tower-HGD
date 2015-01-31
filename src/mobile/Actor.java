package mobile;
/**
 * 
 * A class for characters
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import utility.Damage;
import world.Room;

import java.awt.*;
import java.io.*;

public abstract class Actor extends Mobile {	
	
	private int _hp, _hpMax; //health and max health
	private int _stun = 0, _mercy = 0; //stunned and invulnerable (mercy) states
	private int _fireRate; //delay before firing another bullet
	
	/**
	 * @param home: the room the object is inside
	 */
	public Actor(Room home) {
		super(home);
		setDir("down");
		setFireDelay(0);
	}
	
	@Override
	public void move() {
		super.move();
		if (isStunned()) stun(-1);
		if (hasMercy()) mercy(-1);
	}
	
	/**
	 * @return the actor's maximum HP
	 */
	public int getMaxHp() {
		return _hpMax;
	}
	
	/**
	 * @param maxHp the new maximum HP
	 */
	public void setMaxHp(int maxHp) {
		_hpMax = maxHp;
		if (_hpMax <= 0) {_hpMax = 0; setDead();} //special death
		if (_hp > _hpMax) _hp = _hpMax;
	}
	
	/**
	 * @return current HP value
	 */
	public int getHp() {
		return _hp;
	}
	
	/**
	 * @param hp the new HP value
	 */
	protected void setHp(int hp) {
		_hp = hp;
		if (_hp > _hpMax) _hp = _hpMax;
		if (_hp <= 0) {
			_hp = 0; //avoid overkill
			setDead();
		}
		
	}
	
	/**
	 * @param t Time (in frames) before another shot can be fired
	 */
	public void setFireDelay(int t) {
		_fireRate = t;
		if (_fireRate < 0) _fireRate = 0;
	}
	
	/**
	 * @return Time (in frames) until another shot can be fired
	 */
	public int getFireDelay() {
		return _fireRate;
	}
	
	/**
	 * Inflict damage to the actor
	 * 
	 * @param damage amount to hurt
	 * 
	 * @return damage actually dealt
	 */
	public int takeDamage(int damage) {
		_hp -= damage;
		
		if (_hp <= 0) {
			_hp = 0; //avoid overkill
			setDead();
		}
		return damage; //not all versions will take full damage
	}
	
	/**
	 * Inflict damage to the actor (with additional effects)
	 * 
	 * @param dmg the details of the damage
	 * 
	 * @return <1 if damage is resisted, >1 if damage is boosted 
	 */
	public int takeDamage(Damage dmg) {
		
		if (hasMercy()) return 0; //no damage under mercy invincibility
		
		takeDamage(dmg.getDamage());
		setVx(getVx()+dmg.getVx());
		setVy(getVy()+dmg.getVy());
		stun(dmg.getStun());
		return 1;
	}
	
	/**
	 * Heal the actor
	 * 
	 * @param healing amount to restore
	 * 
	 * @return health actually restored
	 */
	public int heal(int healing) {
		_hp += healing;
		
		if (_hp > _hpMax) _hp = _hpMax; //avoid over-heal
		
		return healing; //not all versions will heal fully
	}
	
	/**
	 * Stun the actor for a number of frames
	 * 
	 * @param time the duration of the stun
	 */
	public void stun(int time) {
		_stun += time;
	}
	
	/**
	 * remove stun conditions from the actor
	 */
	public void endStun() {
		_stun = 0;
	}
	
	/**
	 * @return true if the actor is stunned
	 */
	public boolean isStunned() {
		return (_stun > 0);
	}
	
	/**
	 * Provide mercy invincibility for a number of frames
	 * 
	 * @param time the duration of the stun
	 */
	public void mercy(int time) {
		_mercy += time;
	}
	
	/**
	 * remove mercy conditions from the actor
	 */
	public void endMercy() {
		_mercy = 0;
	}
	
	/**
	 * @return true if the actor is in mercy state
	 */
	public boolean hasMercy() {
		return (_mercy > 0);
	}
}
