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
import java.util.List;

public class Player extends Actor {

	private Control _c;
	private int _stamina; //effectively ammo
	private int _staminaMax = 100; //maximum "ammo
	private int _fireRate; //delay before firing another bullet
	private Weapon wep = new WeaponBasic(); //TODO: Make into an array, add swapping!

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
		
		//initialize local values
		_fireRate = 0;
		_stamina = _staminaMax;
	}

	@Override
	public void update() {
		//read input
		int dx = _c.getMove().getX();
		int dy = _c.getMove().getY();

		//apply acceleration
		if (dx > 10)
			setVx(getVx()+5);
		else if (dx < -10)
			setVx(getVx()-5);
		if (dy > 10)
			setVy(getVy()+5);
		else if (dy < -10)
			setVy(getVy()-5);

		//apply deceleration
		decelerate(2);
		
		//fire bullets
		dx = _c.getShoot().getX();
		dy = _c.getShoot().getY();
		if (_fireRate <= 0 && _stamina > 0 && (Math.abs(dx) > 10 || Math.abs(dy) > 10)) {
			wep.fire(getHome(), this, dx, dy);
		}
		else if (_stamina < _staminaMax)
			_stamina++;

		_fireRate--;
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
		Sprite s = new Sprite(getLeft()-2, getTop()-2, getW()+4, getH()+4, 0, 0, 0, "hero");
		list.add(s);
	}
}
