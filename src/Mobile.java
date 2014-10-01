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
import java.io.*;

public abstract class Mobile {

	private int _x, _y, _z; //position in 3D space
	private int _vx, _vy, _vz; //3D velocities
	private int _w, _h, _d; //dimensions
	private Room _home; //room the object is in
	
	//TODO: Add mobile-to-tile collision
	
	/**
	 * Apply the mobile object's velocity to its position
	 */
	public void move() {
		_x += _vx;
		_y += _vy;
		_z += _vz;
	}
	
	/**
	 * 
	 * @param g
	 */
	public void draw(Graphics g)
	{
		//TODO: Make a generic drawing function here
	}
	
	/**
	 * @return the room the object lives in
	 */
	public Room getHome() {
		return _home;
	}
	/**
	 * @param _home: set the room the object lives in
	 */
	public void setHome(Room _home) {
		this._home = _home;
	}
	
	/**
	 * @return the x coordinate
	 */
	public int getX() {
		return _x;
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
		return _y;
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
		return _z;
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
	public int getVx() {
		return _vx;
	}
	/**
	 * @param vx: the new x velocity
	 */
	public void setVx(int vx) {
		this._vx = vx;
	}
	/**
	 * @return the y velocity
	 */
	public int getVy() {
		return _vy;
	}
	/**
	 * @param vy: the new y velocity
	 */
	public void setVy(int vy) {
		this._vy = vy;
	}
	/**
	 * @return the z velocity
	 */
	public int getVz() {
		return _vz;
	}
	/**
	 * @param vz: the new z velocity
	 */
	public void setVz(int vz) {
		this._vz = vz;
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
		return _y-_h/2;
	}
	/**
	 * @return y coordinate of the bottom edge
	 */
	public int getBottom() {
		return _y+_h/2;
	}
	/**
	 * @return x coordinate of the left edge
	 */
	public int getLeft() {
		return _x-_w/2;
	}
	/**
	 * @return x coordinate of the right edge
	 */
	public int getRight() {
		return _x+_w/2;
	}
	/**
	 * @return z coordinate of the front edge (screen-facing)
	 */
	public int getFront() {
		return _z-_d;
	}
	/**
	 * @return z coordinate of the back edge (ground-facing)
	 */
	public int getBack() {
		return _z;
	}
	
	/**
	 * @return y coordinate of the top edge after movement
	 */
	public int getNextTop() {
		return _y-_h/2+_vy;
	}
	/**
	 * @return y coordinate of the bottom edge after movement
	 */
	public int getNextBottom() {
		return _y+_h/2+_vy;
	}
	/**
	 * @return x coordinate of the left edge after movement
	 */
	public int getNextLeft() {
		return _x-_w/2+_vx;
	}
	/**
	 * @return x coordinate of the right edge after movement
	 */
	public int getNextRight() {
		return _x+_w/2+_vx;
	}
	/**
	 * @return z coordinate of the front edge (screen-facing) after movement
	 */
	public int getNextFront() {
		return _z-_d+_vz;
	}
	/**
	 * @return z coordinate of the back edge (ground-facing) after movement
	 */
	public int getNextBack() {
		return _z+_vz;
	}
}
