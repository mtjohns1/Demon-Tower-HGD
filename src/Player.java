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

public class Player extends Actor {

	private Control _c;
	
	/**
	 * Public no-argument constructor
	 */
	public Player() {
		super();
		_c = new Control();
	}
	
	/**
	 * @param start: the room the player starts in
	 */
	public Player(Room start) {
		super(start);
		_c = new Control();
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
		//TODO: check collisions
		//TODO: check for death
		//TODO: check bounding / room change
		
		move(); //move to new position
	}
}
