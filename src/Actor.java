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

public class Actor extends Mobile {	
	
	private int _hp, _hpMax;
	
	/**
	 * @param home: the room the object is inside
	 */
	public Actor(Room home) {
		super(home);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMaxHP() {
		return _hpMax;
	}
	
	/**
	 * 
	 * @return
	 */
	protected void setMaxHP( int maxHp) {
		_hpMax = maxHp;
	}
	
	/**
	 * @return current HP value
	 */
	public int getHp() {
		return _hp;
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
		
		if (_hp < 0) _hp = 0; //avoid overkill
		
		return damage; //not all versions will take full damage
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
		
		if (_hp > _hpMax) _hp = _hpMax; //avoid overheal
		
		return healing; //not all versions will heal fully
	}
}
