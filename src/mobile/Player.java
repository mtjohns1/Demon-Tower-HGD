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
	private boolean _stairDebounce = false;
	private double _traction = 1;
	
	private boolean _walk; //boolean for walking/firing animation switches
	private boolean _repeat; //boolean for repeat step/slashes
	private int _actionTime; //integer for how long since you last switched actions
	private int _subAnim; //integer frame offset, based on _repeat

	/**
	 * @param start: the room the player starts in
	 */
	public Player(Room start, Control c) {
		super(start);
		_c = c; //map the controller!
		setW(28);
		setH(28);
		setD(32);
		this.getHome().setPlayer(this);
		
		setSpriteSheet("hero.png");
		setSpriteW(64);
		setSpriteH(64);
		setSpriteDir(true);
		setDir("right");
		setAnim(0);
		setFrame(0);
		
		
		//hitpoints! (initial)
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
	}

	@Override
	public void update() {
		//read input
		int dx = _c.getMove().getX();
		int dy = _c.getMove().getY();
		//get bullet input
		int cx = _c.getShoot().getX();
		int cy = _c.getShoot().getY();

		//reset traction for now
		_traction = 1.0;
		
		//feet on the ground here!
		if (getBack() <= 0)
		{
			Tile t = getHome().getTile(getX()/32, getY()/32);
			/*pit logic here!*/
			if (t.getType().contains(Tile.PIT)) {
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
			
			/*stair logic here!*/ //TODO: Move into collision logic?
			//ascend stairs
			if (t.getType().contains(Tile.UP)&&!_stairDebounce) {
				getHome().setFloorDirection("up");
				_stairDebounce = true;
			}
			//descend stairs
			else if (t.getType().contains(Tile.DOWN)&&!_stairDebounce) {
				getHome().setFloorDirection("down");
				_stairDebounce = true;
			}
			else {
				_stairDebounce = false;
			}
			
			/*water/ice friction logic here!*/
			//ice slips around
			if (t.getType().contains(Tile.ICE)) {
				_traction = 0.75;
			}
			//water slows you
			else if (t.getType().contains(Tile.WATER)) {
				_traction = 2.0;
			}
			
			//TODO: Implement other standing-ground tiles too?
		}

		//if stunned, nullify inputs
		if (isStunned()) {dx = 0; dy = 0;}
		
		//apply acceleration
		if (Math.abs(dx) > 10 || Math.abs(dy) > 10) {
			Direction dir = new Direction(dx, dy);
			setVx(getVx()+dir.getX()*3);
			setVy(getVy()+dir.getY()*3);
		}
		//apply friction
		accelerate(0.475/_traction);
		
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

		if (isStunned()) {dx = 0; dy = 0;} //nullify input while stunned

		//fire, but only if a variety of conditions are met (including having a weapon!
		if (getWeapons().size() > 0 && getFireDelay() <= 0 && /*_stamina > 0 &&*/ (Math.abs(cx) > 10 || Math.abs(cy) > 10)) {
			_wep.get(_equip).fire(this, cx, cy);
			//switch from walking to firing, toggle repeats
			_actionTime = 0;
			_walk = false;
			_repeat = !_repeat;
		}

		//change facing direction for movement
		if (_walk)
		{
			if (Math.abs(dy) > Math.abs(dx)) {
				if (dy < 0) setDir("up");
				else if (dy > 0) setDir("down");
			}
			else if (Math.abs(dx) > Math.abs(dy)) {
				
				if (dx < 0) setDir("left");
				else if (dx > 0) setDir("right");
			}
		}
		//change facing direction for attacks
		else
		{
			if (Math.abs(cy) > Math.abs(cx)) {
				if (cy < 0) setDir("up");
				else if (cy > 0) setDir("down");
			}
			else if (Math.abs(cx) > Math.abs(cy)) {
				
				if (cx < 0) setDir("left");
				else if (cx > 0) setDir("right");
			}
		}
		
		//set animation
		if (_walk) setAnim(0);
		else setAnim(1);
		
		//everyone loves the left/right sweep
		if (_repeat) _subAnim = 2;
		else _subAnim = 0;
		
		//set frame (shooting)
		if (!_walk) {
			
			//return to just walking
			if (_actionTime < 5) {
				setFrame(0+_subAnim);
			}
			else {
				setFrame(1+_subAnim);
			}
			if (_actionTime > 25) {
				_actionTime = 0;
				_walk = true;
			}
			_actionTime++;
		}
		//set frame (walking)
		else {
			if (Math.abs(dx) < 11 && Math.abs(dy) < 11) _actionTime = 0;
			if (_actionTime % 20 == 1) _repeat = !_repeat;
			setFrame( ((_actionTime+1)/10)%2+_subAnim );
			_actionTime++;
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
			Sprite s = new Sprite(44+i*16, y, 32, 32, 0, 0, 0, "hearts.png");
			//half heart
			if (getHp() == i*2+1) {
				s = new Sprite(44+i*16, y, 32, 32, 32, 0, 0, "hearts.png");
			}
			//empty heart
			if (getHp() <= i*2) {
				s = new Sprite(44+i*16, y, 32, 32, 64, 0, 0, "hearts.png");
			}
			list.add(s);
		}
		if (getWeapons().size() > 0)
		{
			int swordIcon = getWeapons().get(getEquip()).getIcon();
			Sprite s = new Sprite(4, y, 32, 32, swordIcon*32, 0, 0, "hero sword.png");
			list.add(s);
		}
	}

	@Override
	public void draw(List<Sprite> list)
	{
		if ((getMercy()/5) % 2 == 0) super.draw(list);
		drawHUD(list, 448);
		/* drawHUD(list, 448);
		if ((getMercy()/5) % 2 > 0) return;
		Sprite s = new Sprite(getLeft()-2, getTop()-6-getBack(), getW()+4, getH()+8, 0, 0, calculateLayer(), "hero");
		list.add(s); */
	}
}
