package menu;
/**
 * 
 * A class for menu objects that appear
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import sprite.Sprite;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Menu {
private int highlighted;
	
	private boolean move = false;
	private boolean select = false;
	private boolean pause = false;
	private boolean isDead;
	private boolean active = false;
	
	private int offset = 0;
	private int Y = 480;
	
	public boolean isDead() {
		return isDead;
	}
	
	//overide to load graphics, any other initialization
	public static void menuInit(){
		
	}

	public void update() {
		// TODO Auto-generated method stub
		
			
		
	}

	public void drawMenu(ArrayList<Sprite> sprites) {
		// TODO Auto-generated method stub
		
	}
	public void scroll(int Y, int offset){
		if(Y!=offset){
			Y-=10;
		}
	}
	
}
	