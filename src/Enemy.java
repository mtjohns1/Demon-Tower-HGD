/**
 * 
 * A class for the enemies
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public abstract class Enemy extends Actor {
	
	public Enemy(Room home) {
		super(home);

		//will probably be changed
		setW(28);
		setH(28);
		setD(32);
	}
	
}
