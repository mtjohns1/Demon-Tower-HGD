/**
 * 
 * A class for individual tiles
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class Tile {

	private String tile_type;
	
	public String getType()
	{
		return tile_type;
	}
	
	public void setType(String x)
	{
		this.tile_type = x;
	}
	
}
