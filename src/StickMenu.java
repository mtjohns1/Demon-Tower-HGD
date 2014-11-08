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
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.Scanner;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class StickMenu implements KeyListener {

	
	
	private boolean escape,_q,_e,select =false;
	
	//empty for testing
	public StickMenu (){
	
	}
	
	/**
	 * 
	 * @return horizontal position of the stick
	 * also returns relative mouse position
	 */
	public boolean escape() {
		//gets x if stick is mouse
		return escape;
	}

	

	/**
	 * 
	 * @return vertical position of the stick
	 * also returns relative mouse position
	 */
	public boolean select() {
		//gets y if stick is mouse
		return select;
	}
	
	/**
	 * gives mouse location relative to screen
	 * @return mouseAxis list with 0 = x position 1 = y position
	 */
	public boolean cycleLeft(){
		return _q;
	}
	
	public boolean cycleRight(){
		return _e;
	}
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//if w or up pressed
		if (arg0.getExtendedKeyCode() == 27){
			escape =true;
		}
		//if s or down pressed
		else if (arg0.getExtendedKeyCode() == 10){
			select = true;
		}
		
		//if a or left pressed
		if (arg0.getExtendedKeyCode() == 81){
			_q =true;
		}
		//if d or right pressed
		else if (arg0.getExtendedKeyCode() == 69){
			_e = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		//if w or up pressed
		if (arg0.getExtendedKeyCode() == 27){
			escape =false;
		}
		//if s or down pressed
		else if (arg0.getExtendedKeyCode() == 10){
			select = false;
		}
		
		//if a or left pressed
		if (arg0.getExtendedKeyCode() == 81){
			_q =false;
		}
		//if d or right pressed
		else if (arg0.getExtendedKeyCode() == 69){
			_e = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
