/**
 * 
 * Base class for mobile objects
 * @author Jacob Charles
 * 
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.util.List;
import java.io.*;
import java.util.*;

public abstract class Mobile {

	private double _x, _y, _z; //position in 3D space
	private double _vx, _vy, _vz; //3D velocities
	private int _w, _h, _d; //dimensions
	private Room _home; //room the object is in
	private boolean _dead; //dead state
	private boolean _collide; //does it hit objects
	private boolean _fly; //does it hit walls
	private int _ticks; //personal timer

	private int _frame; //frame number
	private int _dir; //0-3, used for animations
	private int _animation; //animation number
	private String _spriteSheet; //text index of sprite sheet

	private int _spriteW, _spriteH; //sprite dimensions
	private int _spriteX, _spriteY; //sprite offset (0,0 means centers align)
	private boolean _spriteDir; //if false, sprite does not relate to direction

	/**
	 * @param home: the room the object is inside
	 * 
	 * FOR ALL EXTENSIONS, REMEMBER TO SET H, W, and D!
	 */
	public Mobile(Room home) {
		_dead = false;
		_collide = true;
		_fly = false;
		_home = home;

		_vx = 0;
		_vy = 0;
		_vz = 0;

		_x = 0;
		_y = 0;
		_z = 0;

		_w = 0;
		_h = 0;
		_d = 0;

		_ticks = 0;
		_dir = -1;
		_frame = 0;
		_animation = 0;

		if (_home != null) {
			_home.addMobile(this);
		}
	}

	/**
	 * Get the Tiles the mobile object currently occupies
	 * 
	 * @return a list of the current Tiles
	 */
	public ArrayList<Tile> floor() {
		return _home.getRange(getLeft()/32, getTop()/32, getRight()/32, getBottom()/32);
	}

	/**
	 * Update position, ignoring tile collisions
	 */
	public void flyMove() {
		_x += _vx;
		_y += _vy;
		_z += _vz;
	}

	/**
	 * Update position, calls tileCollision for each new tile touched
	 */
	public void landMove() {
		//moving left
		if (getNextLeft()/32 < getLeft()/32) {
			for (Tile t : getHome().getRange(getNextLeft()/32, getTop()/32, getNextLeft()/32, getBottom()/32)) {
				tileCollision(t, "left");
			}
		}
		//moving right
		else if (getNextRight()/32 > getRight()/32) {
			for (Tile t : getHome().getRange(getNextRight()/32, getTop()/32, getNextRight()/32, getBottom()/32)) {
				tileCollision(t, "right");
			}
		}

		//now update x position
		xMove();

		//moving up
		if (getNextTop()/32 < getTop()/32) {
			for (Tile t : getHome().getRange(getLeft()/32, getNextTop()/32, getRight()/32, getNextTop()/32)) {
				tileCollision(t, "up");
			}
		}
		//moving down
		else if (getNextBottom()/32 > getBottom()/32) {
			for (Tile t : getHome().getRange(getLeft()/32, getNextBottom()/32, getRight()/32, getNextBottom()/32)) {
				tileCollision(t, "down");
			}
		}

		//now update y and z position
		yMove();
		zMove();
	}

	/**
	 * Update position, calls tileCollision for each new tile touched
	 * Ignores tiles if doesFly() returns true
	 */
	public void move() {
		//flying objects skip collisions
		if (doesFly()) {
			flyMove();
			return;
		}
		else landMove();
		_ticks++; //update internal timer on movement
	}

	/**
	 * Apply just the mobile object's x velocity to its position
	 */
	public void xMove() {
		_x += _vx;
	}

	/**
	 * Apply just the mobile object's y velocity to its position
	 */
	public void yMove() {
		_y += _vy;
	}

	/**
	 * Apply just the mobile object's z velocity to its position
	 */
	public void zMove() {
		_z += _vz;
	}

	/**
	 * Multiply vx and vy by a value
	 * 
	 * @param a the acceleration multiplier
	 */
	public void accelerate(double a) {
		setVx(_vx*a);
		setVy(_vy*a);
	}

	/**
	 * Divide vx and vy by a value
	 * 
	 * @param d the deceleration divisor
	 */
	public void decelerate(double d) {
		setVx(_vx/d);
		setVy(_vy/d);
	}

	/**
	 * Tell the object to decide its actions this frame
	 */
	public abstract void update();

	/**
	 * Manage collisions with a tile
	 * 
	 * @param t: the Tile object collided with
	 * @param dir: the direction of the collision
	 */
	public abstract void tileCollision(Tile t, String dir);

	/**
	 * Check if this object is currently overlapping another
	 * 
	 * @param m the other object
	 * 
	 * @return true if this and m intersect
	 */
	private boolean _overlap(Mobile m) {
		if ( (getTop() < m.getBottom()) && (getBottom() > m.getTop()) //y aligned
				&& (getFront() < m.getBack()) && (getBack() > m.getFront()) //z aligned
				&& (getLeft() < m.getRight()) && (getRight() > m.getLeft()) ) { //x aligned 
			return true;
		}
		else return false;
	}

	/**
	 * Check if this object will overlap another after moving
	 * 
	 * @param m the other object
	 * 
	 * @return true if this and m will intersect
	 */
	private boolean _willOverlap(Mobile m) {
		if ( (getNextTop() < m.getNextBottom()) && (getNextBottom() > m.getNextTop()) //y aligned
				&& (getNextFront() < m.getNextBack()) && (getNextBack() > m.getNextFront()) //z aligned
				&& (getNextLeft() < m.getNextRight()) && (getNextRight() > m.getNextLeft()) ) { //x aligned
			return true;
		}
		else return false;
	}

	/**
	 * React to collisions with another object
	 * 
	 * @param m the other object to collide with
	 * @param overlap are they overlapping now?
	 * @param nextOverlap will they overlap after moving?
	 */
	public void collide (Mobile m, boolean overlap, boolean nextOverlap) {
		//TODO: Probably make this abstract!
	}

	/**
	 * Check collisions against another object, call collide() for both
	 * Does nothing if either object's doesCollid() returns false
	 * 
	 * @param m the other object
	 */
	public void checkCollision (Mobile m) {
		//skip collisions if either is intangible
		if (!doesCollide() || !m.doesCollide())
			return;

		if (_overlap(m) || _willOverlap(m)) {
			collide(m, _overlap(m), _willOverlap(m));
			m.collide(this, _overlap(m), _willOverlap(m));
		}
	}

	/**
	 * Make last-minute changes before being removed
	 */
	public void onDeath() {
		//TODO: Probably make this abstract!
	}

	/**
	 * @return the object's layer. Exact values are irrelevant. Higher value = closer to screen.
	 */
	public double calculateLayer() {
		int screenMax = 480*2; //highest visible face- maximum return value is 0
		return  (_y+_h/2)+(_z+_d)-screenMax;
	}
	
	//TODO: Add layer-calculators for arbitrary points and for shadows
	
	/**
	 * Draw the object to the screen
	 * 
	 * @param g: graphics object to use for drawing
	 */
	//public abstract void draw(List<Sprite> l);
	public void draw(List<Sprite> l) {
		//calculate resultant drawing position
		int drawX = getX()+getSpriteX()-getSpriteW()/2;
		int drawY = getY()+getSpriteY()-getSpriteH()/2-getZ();
		//calculate frame position on sprite sheet
		int frameX = getFrame()*getSpriteW();
		int frameY = getAnim()*getSpriteH();
		if (getSpriteDir()) //adjust for directions as well
			frameY = frameY*4+getDirInt()*getSpriteH();
		//generate the resulting sprite
		Sprite s = new Sprite(drawX, drawY, getSpriteW(), getSpriteH(), frameX, frameY, calculateLayer(), getSpriteSheet());
		l.add(s);
		//TODO: Add shadows
	}

	/**
	 * @return true if the object is dead
	 */
	public boolean isDead() {
		return _dead;
	}

	/**
	 * Kills the object, deactivating most functions
	 */
	public void setDead() {
		_dead = true;
	}

	/**
	 * @return true if the object collides
	 */
	public boolean doesCollide() {
		return _collide;
	}

	/**
	 * If true, makes the object collide with others
	 */
	public void setCollide(boolean c) {
		_collide = c;
	}

	/**
	 * @return true if the object collides
	 */
	public boolean doesFly() {
		return _fly;
	}

	/**
	 * If true, makes the object collide with others
	 */
	public void setFly(boolean f) {
		_fly = f;
	}

	/**
	 * @return the room the object lives in
	 */
	public Room getHome() {
		return _home;
	}
	/**
	 * @param home: set the room the object lives in
	 */
	public void setHome(Room home) {
		this._home = home;
	}

	/**
	 * @return the x coordinate
	 */
	public int getX() {
		return (int) _x;
	}
	/**
	 * @param x: the new x coordinate
	 */
	public void setX(int x) {
		this._x = x;
	}
	/**
	 * @return the y coordinate
	 */
	public int getY() {
		return (int) _y;
	}
	/**
	 * @param y: the new y coordinate
	 */
	public void setY(int y) {
		this._y = y;
	}
	/**
	 * @return the z coordinate
	 */
	public int getZ() {
		return (int) _z;
	}
	/**
	 * @param z: the new z coordinate
	 */
	public void setZ(int z) {
		this._z = z;
	}
	/**
	 * @return the x velocity
	 */
	public double getVx() {
		return _vx;
	}
	/**
	 * @param vx: the new x velocity
	 */
	public void setVx(double vx) {
		this._vx = vx;
		if (Math.abs(_vx) < 0.1)
			this._vx = 0;
	}
	/**
	 * @return the y velocity
	 */
	public double getVy() {
		return _vy;
	}
	/**
	 * @param vy: the new y velocity
	 */
	public void setVy(double vy) {
		this._vy = vy;
		if (Math.abs(_vy) < 0.1)
			this._vy = 0;
	}
	/**
	 * @return the z velocity
	 */
	public double getVz() {
		return _vz;
	}
	/**
	 * @param vz: the new z velocity
	 */
	public void setVz(double vz) {
		this._vz = vz;
		if (Math.abs(_vz) < 0.1)
			this._vz = 0;
	}
	/**
	 * @return the width (in x)
	 */
	public int getW() {
		return _w;
	}
	/**
	 * @param w: the new width (in x)
	 */
	public void setW(int w) {
		this._w = w;
	}
	/**
	 * @return the height (in y)
	 */
	public int getH() {
		return _h;
	}
	/**
	 * @param h: the new height (in y)
	 */
	public void setH(int h) {
		this._h = h;
	}
	/**
	 * @return the depth (in z)
	 */
	public int getD() {
		return _d;
	}
	/**
	 * @param d: the new depth (in z)
	 */
	public void setD(int d) {
		this._d = d;
	}

	/**
	 * @return y coordinate of the top edge
	 */
	public int getTop() {
		return (int) (_y-_h/2);
	}
	/**
	 * @return y coordinate of the bottom edge
	 */
	public int getBottom() {
		return (int) (_y+_h/2);
	}
	/**
	 * @return x coordinate of the left edge
	 */
	public int getLeft() {
		return (int) (_x-_w/2);
	}
	/**
	 * @return x coordinate of the right edge
	 */
	public int getRight() {
		return (int) (_x+_w/2);
	}
	/**
	 * @return z coordinate of the front edge (screen-facing)
	 */
	public int getFront() {
		return (int) (_z+_d);
	}
	/**
	 * @return z coordinate of the back edge (ground-facing)
	 */
	public int getBack() {
		return (int) _z;
	}


	/**
	 * @param y: new y value of the top edge
	 */
	public void setTop(int y) {
		_y = y+_h/2; 
	}

	/**
	 * @param y: new y value of the bottom edge
	 */
	public void setBottom(int y) {
		_y = y-_h/2;
	}

	/**
	 * @param y: new x value of the left edge
	 */
	public void setLeft(int x) {
		_x = x+_w/2;
	}

	/**
	 * @param y: new x value of the right edge
	 */
	public void setRight(int x) {
		_x = x-_w/2;
	}

	/**
	 * @return y coordinate of the top edge after movement
	 */
	public int getNextTop() {
		return (int) (_y-_h/2+_vy);
	}
	/**
	 * @return y coordinate of the bottom edge after movement
	 */
	public int getNextBottom() {
		return (int) (_y+_h/2+_vy);
	}
	/**
	 * @return x coordinate of the left edge after movement
	 */
	public int getNextLeft() {
		return (int) (_x-_w/2+_vx);
	}
	/**
	 * @return x coordinate of the right edge after movement
	 */
	public int getNextRight() {
		return (int) (_x+_w/2+_vx);
	}
	/**
	 * @return z coordinate of the front edge (screen-facing) after movement
	 */
	public int getNextFront() {
		return (int) (_z-_d+_vz);
	}
	/**
	 * @return z coordinate of the back edge (ground-facing) after movement
	 */
	public int getNextBack() {
		return (int) (_z+_vz);
	}

	/**
	 * @return the number of frames the object has been updated on
	 */
	public int getTicks() {
		return _ticks;
	}

	/**
	 * @param d new facing direction in string format
	 */
	public void setDir(String d) {
		if (d.equalsIgnoreCase("up")) _dir = 0;
		else if (d.equalsIgnoreCase("right")) _dir = 1;
		else if (d.equalsIgnoreCase("down")) _dir = 2;
		else if (d.equalsIgnoreCase("left")) _dir = 3;
	}
	/**
	 * @param f the new animation frame index
	 */
	public void setFrame(int f) {
		_frame = f;
	}
	/**
	 * @param a new animation index
	 */
	public void setAnim(int a) {
		_animation = a;
	}
	/**
	 * @param s the name of the new sprite sheet
	 */
	public void setSpriteSheet(String s) {
		_spriteSheet = s;
	}

	/**
	 * @return current facing direction
	 */
	public String getDir() {
		switch (_dir)
		{
		case 0: return "up";
		case 1: return "right";
		case 2: return "down";
		case 3: return "left";
		default: return "ERROR";
		}
	}
	/**
	 * @return current facing direction
	 */
	public int getDirInt() {
		return _dir;
	}
	/**
	 * @return current animation frame
	 */
	public int getFrame() {
		return _frame;
	}
	/**
	 * @return current animation index
	 */
	public int getAnim() {
		return _animation;
	}
	/**
	 * @return the name of the current sprite sheet
	 */
	public String getSpriteSheet() {
		return _spriteSheet;
	}

	/**
	 * @param w the width of the sprite to use
	 */
	public void setSpriteW(int w) {
		_spriteW = w;
	}
	/**
	 * @param h the height of the sprite to use
	 */
	public void setSpriteH(int h) {
		_spriteH = h;
	}
	/**
	 * @param x the sprite's X offset from object center
	 */
	public void setSpriteX(int x) {
		_spriteX = x;
	}
	/**
	 * @param y the sprite's Y offset from object center
	 */
	public void setSpriteY(int y) {
		_spriteY = y;
	}
	/**
	 * @param d true if the sprite changes with direction
	 */
	public void setSpriteDir(boolean d) {
		_spriteDir = d;
	}

	/**
	 * @return the width of the sprite used
	 */
	public int getSpriteW() {
		return _spriteW;
	}
	/**
	 * @return the height of the sprite used
	 */
	public int getSpriteH() {
		return _spriteH;
	}
	/**
	 * @return the sprite's X offset from object center
	 */
	public int getSpriteX() {
		return _spriteX;
	}
	/**
	 * @return the sprite's Y offset from object center
	 */
	public int getSpriteY() {
		return _spriteY;
	}
	/**
	 * @return true if the sprite changes with direction
	 */
	public boolean getSpriteDir() {
		return _spriteDir;
	}
}
