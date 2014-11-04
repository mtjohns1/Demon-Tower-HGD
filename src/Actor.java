/**
 * 
 * A class for characters
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;

public abstract class Actor extends Mobile {	
	
	private int _hp, _hpMax;
	
	/**
	 * @param home: the room the object is inside
	 */
	public Actor(Room home) {
		super(home);
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
	protected void setMaxHp(int maxHp) {
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
		takeDamage(dmg.getDamage());
		setVx(getVx()+dmg.getVx());
		setVy(getVy()+dmg.getVy());
		//TODO: Implement hitstun?
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
}
