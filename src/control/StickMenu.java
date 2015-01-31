package control;
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

	
	
	private boolean escape,select =false;
	
	//empty for testing
	public StickMenu (){
	
	}
	
	/**
	 * 
	 * @return true if escape hit
	 */
	public boolean escape() {
		//gets x if stick is mouse
		return escape;
	}

	

	/**
	 * 
	 * @return true if enter hit
	 */
	public boolean select() {
		//gets y if stick is mouse
		return select;
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
		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
