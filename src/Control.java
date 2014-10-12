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
	private Stick _mouse;
	
	private int[] movementList = {87,83,65,68};
	private int[] shootList = {38,40,37,39};
	
	/**
	 * initilizes keyboard using wasd and arrow keys
	 */
	public Control(){
		_move = new Stick(movementList);
		_shoot = new Stick(shootList);
		_mouse = new Stick(1);
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
	public Stick getMove() {
		return _move;
	}
	/**
	 * @param move
	 * 			the new movement stick
	 */
	public void setMove(Stick move) {
		this._move = move;
	}
	/**
	 * @return the stick that controls firing
	 */
	public Stick getShoot() {
		return _shoot;
	}
	
	/**
	 * @param shoot
	 * 			the new firing stick
	 */
	public void setShoot(Stick shoot) {
		this._shoot = shoot;
	}
	/**
	 * 
	 * @return Mouse 
	 * 			used if mouse controls wanted
	 */
	public Stick getMouse(){
		return _mouse;
	}
}
