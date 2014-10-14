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

	/**
	 * @param start: the room the player starts in
	 */
	public Player(Room start) {
		super(start);
		_c = new Control();
		setW(36);
		setH(36);
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
		setVx(getVx()/2);
		setVy(getVy()/2);

		//TODO: fire bullets
		//TODO: change weapons

		//get the list of tile collisions
		RoomCollision r = new RoomCollision(this);
		for (TileCollision t : r.getCollisions()) {
			tileCollision(t);
		}

		//TODO: collide with other objects?

		//TODO: check for death
		//TODO: check bounding / room change

		move(); //move to new position
	}

	/**
	 * Manage collisions with a tile
	 * 
	 * @param t: the TileCollision object generated by RoomCollisions
	 */
	public void tileCollision(TileCollision t) {
		//solid wall collisions
		if (t.getTile().getType().contains("w")) {
			if (t.getXAxis() > 0) {
				setRight(t.getTile().getLeft());
				setVx(0);
			}
			if (t.getXAxis() < 0) {
				setLeft(t.getTile().getRight());
				setVx(0);
			}
			if (t.getYAxis() > 0) {
				setBottom(t.getTile().getTop());
				setVy(0);
			}
			if (t.getYAxis() < 0) {
				setTop(t.getTile().getBottom());
				setVy(0);
			}
		}
	}

	/**
	 * Generate the player's sprite for this frame
	 * 
	 * @param l: the list of sprites to add to
	 */
	public void  draw(List<Sprite> l)
	{
		Sprite s = new Sprite(getLeft(), getTop(), getW(), getH(), 0, 0, 0, null);
		l.add(s);
	}
}
