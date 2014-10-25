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
		setVx(getVx()/2);
		setVy(getVy()/2);

		//TODO: collide with other objects?

		//TODO: fire bullets
		//TODO: change weapons

		//update position, checking collisions along the way
		move();

		//TODO: check for death
		//TODO: check bounding / room change
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
		Sprite s = new Sprite(getLeft()-2, getTop()-2, getW()+4, getH()+4, 0, 0, 0, 2);
		list.add(s);
	}
}
