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

	
	
	private int x0, x1, y0, y1;
	
	//preset for testing
	private int[] keys ={87,83,65,68};
	
	//empty for testing
	public Stick(){
		
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
	 */
	public int getX() {
		return x0 + x1;
	}

	

	/**
	 * 
	 * @return vertical position of the stick
	 */
	public int getY() {
		return y0 + y1;
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
	
}
