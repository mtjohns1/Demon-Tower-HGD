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
	
	private Stick wsad;
	private Stick arrow;
	private Stick mouse;
	//wsad, arrows, joysticks, etc as well
	
	private Mobile player = null;
	
	//used for keynumber of wsad and arrows
	private int[] wsadList = {87,83,65,68};
	private int[] arrowList = {38,40,37,39};
	
	/**
	 * initilizes keyboard using wasd and arrow keys
	 */
	public Control(){
		wsad = new Stick(wsadList);
		arrow = new Stick(arrowList);
		mouse = new Stick(player);
		
		_move = wsad;
		_shoot = arrow;
	}
	
	/**
	 * Initialize the control listeners
	 * 
	 * @param frame the frame to listen through
	 */
	public void init(JFrame frame) {
		frame.addKeyListener(wsad);
		frame.addKeyListener(arrow);
	}
	/**
	 * starter for keyboard
	 * 
	 * @param Stick keyboard
	 */
	public Control(Stick arg0, Stick arg1){
		_move = arg0;
		_shoot = arg1;
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
	 * @param playerObject player object to set mouse listener to
	 * 						use player as a reference point.
	 */
	public void setMouse (Mobile playerObject){
		player = playerObject;
	}
}