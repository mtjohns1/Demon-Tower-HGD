/**
 * 
 * A class for the player character
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

	private Control _c;
	private int _stamina; //effectively ammo
	private int _staminaMax = 100; //maximum "ammo
	private int _fireRate; //delay before firing another bullet
	private int _equip; //which weapon you currently have equipped
	private ArrayList<Weapon> _wep = new ArrayList<Weapon>(); //array of weapons you can use
	private boolean _debounce = false; //prevent repeated input from held keys

	/**
	 * @param start: the room the player starts in
	 */
	public Player(Room start, Control c) {
		super(start);
		_c = c;
		setW(28);
		setH(28);
		setD(32);
		this.getHome().setPlayer(this);

		//hitpoints!
		setMaxHp(10);
		setHp(10);

		//add default weapon
		_wep.add(new WeaponBasic());
		_wep.add(new WeaponGrapple()); //grappling hook test TODO: remove, add via level
		_equip = 0;

		//initialize local values
		_fireRate = 0;
		_stamina = _staminaMax;
	}

	@Override
	public void update() {
		//read input
		int dx = _c.getMove().getX();
		int dy = _c.getMove().getY();
		if (isStunned()) {dx = 0; dy = 0;}
		//apply acceleration
		if (Math.abs(dx) > 10 || Math.abs(dy) > 10) {
			Direction dir = new Direction(dx, dy);
			setVx(getVx()+dir.getX()*5);
			setVy(getVy()+dir.getY()*5);
		}		
		//apply friction
		accelerate(0.5);

		//change weapons
		if (isStunned()) {_debounce = true;}
		if (!_debounce)
		{
			if (_c.getShoot().cycleRight()) cycleWeaponRight();
			if (_c.getShoot().cycleLeft()) cycleWeaponLeft();
		}
		_debounce = (_c.getShoot().cycleRight() || _c.getShoot().cycleLeft());

		//fire bullets
		dx = _c.getShoot().getX();
		dy = _c.getShoot().getY();
		if (isStunned()) {dx = 0; dy = 0;}
		if (_fireRate < 0 && _stamina > 0 && (Math.abs(dx) > 10 || Math.abs(dy) > 10)) {
			_wep.get(_equip).fire(this, dx, dy);
		}
		//recover stamina while not firing
		else if (_fireRate < 0)
			_stamina++;

		//cap maximum stamina
		if (_stamina > _staminaMax) {
			_stamina = _staminaMax;
		}

		//TODO: Add z-coordinate physics
		
		//count down to next shot
		_fireRate--;
	}
	
	@Override
	public int takeDamage(Damage dmg) {
		int result = super.takeDamage(dmg);
		if (result >= 1) mercy(35); //apply mercy invincibility
		return result;
	}

	/**
	 * @param t Time before another shot can be fired
	 */
	public void setFireDelay(int t) {
		_fireRate = t;
	}

	/**
	 * @param s the amount of stamina consumed
	 */
	public void useStamina(int s) {
		_stamina -= s;
	}

	/**
	 * @return the index of the currently equipped weapon
	 */
	public int getEquip() {
		return _equip;
	}

	/**
	 * @return the list of weapons the player has
	 */
	public ArrayList<Weapon> getWeapons() {
		return _wep;
	}

	/**
	 * @param w the weapon to add to the player's collection
	 */
	public void addWeapon(Weapon w) {
		_wep.add(w);
	}

	/**
	 * Cycle equipped weapons to the left
	 * 
	 * @return the new equipped weapon
	 */
	public int cycleWeaponLeft() {
		_equip--;
		if (_equip < 0) _equip = _wep.size()-1;
		return getEquip();
	}

	/**
	 * Cycle equipped weapons to the right
	 * 
	 * @return the new equipped weapon
	 */
	public int cycleWeaponRight() {
		_equip++;
		if (_equip >= _wep.size()) _equip = 0;
		return getEquip();
	}

	@Override
	public void tileCollision(Tile t, String dir) {
		//solid wall collisions
		if (t.getType().contains("w")) {
			if (dir.equals("right")) {
				setRight(t.getLeft()-1);
				setVx(0);
			}
			if (dir.equals("left")) {
				setLeft(t.getRight()+1);
				setVx(0);
			}
			if (dir.equals("down")) {
				setBottom(t.getTop()-1);
				setVy(0);
			}
			if (dir.equals("up")) {
				setTop(t.getBottom()+1);
				setVy(0);
			}
		}
	}

	@Override
	public void setHome(Room home) {
		getHome().setPlayer(null);
		super.setHome(home);
		getHome().setPlayer(this);
	}

	@Override
	public void draw(List<Sprite> list)
	{
		Sprite s = new Sprite(getLeft()-2, getTop()-2, getW()+4, getH()+4, 0, 0, calculateLayer(), "hero");
		list.add(s);
	}
}
