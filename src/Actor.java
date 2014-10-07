/**
 * 
 * A class for characters
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;

public class Actor extends Mobile {

	/**
	 * Public no-arguments constructor
	 */
	public Actor() {
		super();
	}
	
	/**
	 * @param home: the room the object is inside
	 */
	public Actor(Room home) {
		super(home);
	}
}
