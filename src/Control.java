/**
 * 
 * A class for the control input
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;


public class Control {

	private Stick _move;
	private Stick _shoot;
	
	public Control(){
		_move = new Stick();
		_shoot = new Stick();
	}
	
	/**
	 * starter for keyboard
	 * 
	 * @param Stick keyboard
	 */
	public Control(Stick arg0){
		_move = arg0;
		_move = _shoot;
	}
	/**
	 * @return the stick that controls movement
	 */
	public Stick get_move() {
		return _move;
	}
	/**
	 * @param _move
	 * 			the new movement stick
	 */
	public void set_move(Stick _move) {
		this._move = _move;
	}
	/**
	 * @return the stick that controls firing
	 */
	public Stick get_shoot() {
		return _shoot;
	}
	/**
	 * @param _shoot
	 * 			the new firing stick
	 */
	public void set_shoot(Stick _shoot) {
		this._shoot = _shoot;
	}
}
