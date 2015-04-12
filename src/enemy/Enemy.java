package enemy;
/**
 * 
 * A class for the enemies
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import effect.*;
import mobile.Actor;
import powerup.Heart;
import world.Room;

import java.awt.*;
import java.io.*;

public abstract class Enemy extends Actor {
	private int type;
	
	public Enemy(Room home, int x) {
		super(home);
		type = x;
		//will probably be changed
		setW(28);
		setH(28);
		setD(32);
	}
	public int getType(){
		return type;
	}
	
	@Override
	public int takeDamage(int damage) {
		new Explosion(this, getX(), getY(), 32, 32, 8, 1, 1);
		return super.takeDamage(damage);
	}
	
	@Override
	public void onDeath()
	{
		//base 1/3 chance of spawning hearts
		if (Math.random() < 0.34) {
			new Heart(getHome(), getX(), getY());
		}
		new Explosion(this, getX(), getY(), 32, 32, 18, 1, 2);
		new Freezeframe(this, 18);
	}
}
