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

public class Stick implements KeyListener, MouseInputListener {

	
	
	private int x0, x1, y0, y1;
	private int[] mouseAxis = {-1,1};
	//preset for testing
	private int[] keys ={87,83,65,68};
	private Mobile player =null;
	
	//empty for testing
	public Stick(){
		
	}
	
	/**
	 * nulls default keys to use as a Mouse Listener
	 * and asks for player so mouse can give click relative
	 * to player.
	 * @param player used for relative place
	 */
	public Stick(Mobile player){
		keys =null;
		this.player = player;
	}

	/**
	 * 
	 * @param argList Sets what keys are being listened for.
	 */
	public Stick(int[] argList){
		keys = argList;
	}
	/**
	 * 
	 * @return horizontal position of the stick
	 * also returns relative mouse position
	 */
	public int getX() {
		//gets x if stick is mouse
		if(player != null){
			return mouseAxis[0] - player.getX();
		}
		return x0 + x1;
	}

	

	/**
	 * 
	 * @return vertical position of the stick
	 * also returns relative mouse position
	 */
	public int getY() {
		//gets y if stick is mouse
		if(player != null){
			return mouseAxis[1] - player.getY();
		}
		return y0 + y1;
	}
	
	/**
	 * gives mouse location relative to screen
	 * @return mouseAxis list with 0 = x position 1 = y position
	 */
	public int[] getMouseLocation(){
		return mouseAxis;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		//if w or up pressed
		System.out.println(arg0.getExtendedKeyCode());
		if (arg0.getExtendedKeyCode() == keys[0]){
			y1 = -100;
		}
		//if s or down pressed
		else if (arg0.getExtendedKeyCode() == keys[1]){
			y0 = 100;
		}
		
		//if a or left pressed
		if (arg0.getExtendedKeyCode() == keys[2]){
			x1 = -100;
		}
		//if d or right pressed
		else if (arg0.getExtendedKeyCode() == keys[3]){
			x0 = 100;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		//if w or up pressed
		if (arg0.getExtendedKeyCode() == keys[0]){
			y1 =0;
		}
		//if s or down pressed
		else if (arg0.getExtendedKeyCode() ==keys[1]){
			y0=0;
		}
		//if a or left pressed
		if (arg0.getExtendedKeyCode() ==keys[2]){
			x1=0;
		}
		//if d or right pressed
		else if (arg0.getExtendedKeyCode() == keys[3]){
			x0 = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseAxis[0] = e.getX();
		mouseAxis[1] = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//sets x and y (0, 1) to off screen.
		mouseAxis[0]= -1;
		mouseAxis[1]= 1;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		mouseAxis[0] = e.getX();
		mouseAxis[1] = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
}
