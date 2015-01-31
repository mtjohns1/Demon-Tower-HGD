package mobile;
/**
 * 
 * A class for the player character
 *
 */

import game.Game;

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import control.Control;
import powerup.Powerup;
import sprite.Sprite;
import utility.Damage;
import utility.Direction;
import weapon.*;
import world.Room;
import world.Tile;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

	private Control _c;
	private int _equip; //which weapon you currently have equipped
	private ArrayList<Weapon> _wep = new ArrayList<Weapon>(); //array of weapons you can use
	private boolean _debounce = false; //prevent repeated input from held keys
	private int _lastX, _lastY; //position to reset to in case of pit-falling

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
		setMaxHp(8);
		setHp(getMaxHp());

		//add default weapon
		_wep.add(new HeroSword());
		_wep.add(new GrapplingHook()); //grappling hook test TODO: remove, add via level
		_wep.add(new BlastSword()); //grappling hook test TODO: remove, add via level
		//_wep.add(new WeaponElectric()); //lightning weapon test TODO: remove, add via level
		_equip = 0;

		//initialize local values
		setFireDelay(0);
		//_stamina = _staminaMax;
	}

	@Override
	public void update() {
		//read input
		int dx = _c.getMove().getX();
		int dy = _c.getMove().getY();

		//feet on the ground here!
		if (getBack() <= 0)
		{
			Tile t = getHome().getTile(getX()/32, getY()/32);
			/*pit logic here!*/
			if (t.getType().contains("p")) {
				takeDamage(2);
				setX(_lastX*32+16);
				setY(_lastY*32+16);
				setVz(-16);
				setBack(getBottom()+480);
				stun(32+getBottom()/16);
				mercy(35+getBottom()/16);
			}
			//update last position
			else {
				_lastX = getX()/32; //was there a tile size constant?
				_lastY = getY()/32; //was there a tile size constant?
			}
			//TODO: Implement other standing-ground tiles too!
		}

		if (isStunned()) {dx = 0; dy = 0;}
		//apply acceleration
		if (Math.abs(dx) > 10 || Math.abs(dy) > 10) {
			Direction dir = new Direction(dx, dy);
			setVx(getVx()+dir.getX()*3);
			setVy(getVy()+dir.getY()*3);
		}
		//apply friction
		accelerate(0.475);

		//apply gravity (z direction)
		setVz(getVz()-0.15);

		//change weapons
		if (isStunned()) {_debounce = true;}
		if (!_debounce)
		{
			if (_c.getShoot().cycleRight()) cycleWeaponRight();
			if (_c.getShoot().cycleLeft()) cycleWeaponLeft();
		}
		_debounce = (_c.getShoot().cycleRight() || _c.getShoot().cycleLeft());

		//get bullet input
		dx = _c.getShoot().getX();
		dy = _c.getShoot().getY();

		if (isStunned()) {dx = 0; dy = 0;} //nullify input while stunned

		//fire, but only if a variety of conditions are met (including having a weapon!
		if (getWeapons().size() > 0 && getFireDelay() <= 0 && /*_stamina > 0 &&*/ (Math.abs(dx) > 10 || Math.abs(dy) > 10)) {
			_wep.get(_equip).fire(this, dx, dy);
		}

		//count down to next shot
		setFireDelay(getFireDelay()-1);
	}

	@Override
	public void move() {
		super.move();
		//z-control and collision
		if (getBack() < 0.001) {
			setBack(0);
			//don't keep falling when on the ground
			if (getVz() < 0)
				setVz(0);
		}
	}

	@Override
	public int takeDamage(Damage dmg) {
		int result = super.takeDamage(dmg);
		if (result >= 1) mercy(35); //apply mercy invincibility
		return result;
	}

	/**
	 * @return the controller attached to the player
	 */
	public Control getControls() {
		return _c;
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
		if (_equip < 0) _equip = 0; //protection case for not having any weapons
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
		//solid wall collisions / grounded pit collisions
		if (t.getType().contains("w") /*|| (t.getType().contains("p") && this.getBack() < 0.001)*/) {
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
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		super.collide(m, overlap, nextOverlap);

		//generic powerup 
		if (m instanceof Powerup)
		{
			((Powerup)m).getCollected(this);
		}
	}

	@Override
	public void setHome(Room home) {
		//getHome().setPlayer(null);
		super.setHome(home);
		getHome().setPlayer(this);
	}

	/**
	 * Draw HUD information about the player
	 * 
	 * @param list the list to add sprites to
	 * @param y the top edge of the HUD area, assumed to be 32 pixels high
	 */
	public void drawHUD(List<Sprite> list, int y) {
		for (int i = 0; i < getMaxHp()/2; i++) {
			//full heart
			Sprite s = new Sprite(44+i*16, y, 32, 32, 0, 0, 0, "hearts");
			//half heart
			if (getHp() == i*2+1) {
				s = new Sprite(44+i*16, y, 32, 32, 32, 0, 0, "hearts");
			}
			//empty heart
			if (getHp() <= i*2) {
				s = new Sprite(44+i*16, y, 32, 32, 64, 0, 0, "hearts");
			}
			list.add(s);
		}
		if (getWeapons().size() > 0)
		{
			int swordIcon = getWeapons().get(getEquip()).getIcon();
			Sprite s = new Sprite(4, y, 32, 32, swordIcon*32, 0, 0, "swords");
			list.add(s);
		}
	}

	@Override
	public void draw(List<Sprite> list)
	{
		drawHUD(list, 448);
		if ((getMercy()/5) % 2 > 0) return;
		Sprite s = new Sprite(getLeft()-2, getTop()-2-getBack(), getW()+4, getH()+4, 0, 0, calculateLayer(), "hero");
		list.add(s);
	}
}
