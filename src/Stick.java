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

	
	
	private int _x, _y;

	/**
	 * 
	 * @return horizontal position of the stick
	 */
	public int getX() {
		return _x;
	}

	/**
	 * 
	 * @param x new horizontal position of stick
	 */
	public void setX(int x) {
		this._x = x;
	}

	/**
	 * 
	 * @return vertical position of the stick
	 */
	public int getY() {
		return _y;
	}

	/**
	 * 
	 * @param y new vertical position of the stick
	 */
	public void setY(int y) {
		this._y = y;
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
			System.out.println("left");
			this.setX(-100);
		}
		//if d pressed
		else if (arg0.getExtendedKeyCode() == 68){
			System.out.println("right");
			this.setX(100);
			
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
			System.out.println("left");
			this.setX(0);
		}
		//if d pressed
		else if (arg0.getExtendedKeyCode() == 68){
			System.out.println("right");
			this.setX(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
