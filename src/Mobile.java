/**
 * 
 * Interface for mobile objects
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class Mobile {

	private int _x, _y, _z; //position in 3D space
	private int _vx, _vy, _vz; //3D velocities
	private int _w, _h, _d; //dimensions
	private Room _home; //room the object is in
	
	//TODO: Add mobile to tile collision
	
	/**
	 * Apply the mobile object's velocity to its position
	 */
	public void move() {
		_x += _vx;
		_y += _vy;
		_z += _vz;
	}
	
	//TODO: Add mobile-mobile collisions.
	
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
	public int get_x() {
		return _x;
	}
	/**
	 * @param _x: the new x coordinate
	 */
	public void set_x(int _x) {
		this._x = _x;
	}
	/**
	 * @return the y coordinate
	 */
	public int get_y() {
		return _y;
	}
	/**
	 * @param _y: the new y coordinate
	 */
	public void set_y(int _y) {
		this._y = _y;
	}
	/**
	 * @return the z coordinate
	 */
	public int get_z() {
		return _z;
	}
	/**
	 * @param _z: the new z coordinate
	 */
	public void set_z(int _z) {
		this._z = _z;
	}
	/**
	 * @return the x velocity
	 */
	public int get_vx() {
		return _vx;
	}
	/**
	 * @param _vx: the new x velocity
	 */
	public void set_vx(int _vx) {
		this._vx = _vx;
	}
	/**
	 * @return the y velocity
	 */
	public int get_vy() {
		return _vy;
	}
	/**
	 * @param _vy: the new y velocity
	 */
	public void set_vy(int _vy) {
		this._vy = _vy;
	}
	/**
	 * @return the z velocity
	 */
	public int get_vz() {
		return _vz;
	}
	/**
	 * @param _vz: the new z velocity
	 */
	public void set_vz(int _vz) {
		this._vz = _vz;
	}
	/**
	 * @return the width (in x)
	 */
	public int get_w() {
		return _w;
	}
	/**
	 * @param _w: the new width (in x)
	 */
	public void set_w(int _w) {
		this._w = _w;
	}
	/**
	 * @return the height (in y)
	 */
	public int get_h() {
		return _h;
	}
	/**
	 * @param _h: the new height (in y)
	 */
	public void set_h(int _h) {
		this._h = _h;
	}
	/**
	 * @return the depth (in z)
	 */
	public int get_d() {
		return _d;
	}
	/**
	 * @param _d: the new depth (in z)
	 */
	public void set_d(int _d) {
		this._d = _d;
	}
}
