package effect;
/**
 * 
 * A class for special effects
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import mobile.Mobile;
import world.Room;

import java.awt.*;
import java.io.*;

public abstract class Effects extends Mobile {

	public Effects(Room home) {
		super(home);
	}

}
