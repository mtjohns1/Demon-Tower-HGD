/**
 * 
 * A class for menu objects that appear
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;

public class Menu {
private int highlighted;
	
	private boolean move = false;
	private boolean select = false;
	private boolean pause = false;
	private boolean isDead;
	
	public boolean isDead() {
		return isDead;
	}
	
	//overide to load graphics, any other initialization
	public static void menuInit(){
		
	}
}
