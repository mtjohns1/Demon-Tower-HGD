/**
 * 
 * Class for control "sticks" (directional input)
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Scanner;

public class Stick implements KeyListener {

	
	
	private int _x, y;

	/**
	 * 
	 * @return horizontal position of the stick
	 */
	public int get_x() {
		return _x;
	}

	/**
	 * 
	 * @param _x new horizontal position of stick
	 */
	public void set_x(int _x) {
		this._x = _x;
	}

	/**
	 * 
	 * @return vertical position of the stick
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @param y new vertical position of the stick
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		//if w pressed
		if (arg0.getExtendedKeyCode() == 87){
			System.out.println("up");
			this.setY(-100);
		}
		//if s pressed
		else if (arg0.getExtendedKeyCode() ==83){
			System.out.println("down");
			this.setY(100);
		}
		//if a pressed
		if (arg0.getExtendedKeyCode() ==65){
			System.out.println("Left");
			this.set_x(-100);
		}
		//if d pressed
		else if (arg0.getExtendedKeyCode() == 68){
			System.out.println("Right");
			this.set_x(100);
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//if w pressed
		if (arg0.getExtendedKeyCode() == 87){
			System.out.println("up");
			this.setY(0);
		}
		//if s pressed
		else if (arg0.getExtendedKeyCode() ==83){
			System.out.println("down");
			this.setY(0);
		}
		//if a pressed
		if (arg0.getExtendedKeyCode() ==65){
			System.out.println("Left");
			this.set_x(0);
		}
		//if d pressed
		else if (arg0.getExtendedKeyCode() == 68){
			System.out.println("Right");
			this.set_x(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
