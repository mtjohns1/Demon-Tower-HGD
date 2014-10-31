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
	private int _fireRate; //delay before firing another bullet

	/**
	 * @param start: the room the player starts in
	 */
	public Player(Room start, Control c) {
		super(start);
		_c = c;
		setW(28);
		setH(28);
		setD(32);
	}

	/**
	 * Tell the player to take its actions this frame
	 */
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

		//TODO: collide with other objects?

		//TODO: change weapons
		
		//fire bullets
		dx = _c.getShoot().getX();
		dy = _c.getShoot().getY();
		if (_fireRate <= 0 && (Math.abs(dx) > 10 || Math.abs(dy) > 10)) {
			getHome().addMobile(new BulletBasic(this, dx, dy));
			//TODO: Also add "melee bullet"
			_fireRate = 12; //six frames spacing for now, will be weapon-dependent later
			//TODO: Implement stamina
		}

		//update position, checking collisions along the way
		move();
		
		_fireRate--;
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

	/**
	 * Generate the player's sprite for this frame
	 * 
	 * @param list: the list of sprites to add to
	 */
	public void  draw(List<Sprite> list)
	{
		Sprite s = new Sprite(getLeft()-2, getTop()-2, getW()+4, getH()+4, 0, 0, 0, "hero");
		list.add(s);
	}
}
