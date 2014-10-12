/**
 * 
 * A class for individual tiles
 * @author Jacob Charles
 * @author Bret Miller
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;

public class Tile {

	private Room _home;
	private int _x, _y;
	private String _tile_type;
	
	/**
	 * Initialize a tile within a room
	 * 
	 * @param r the room it's part of
	 * @param x the horizontal location in the map
	 * @param y the vertical location in the map
	 */
	public Tile(Room r, int x, int y) {
		_home = r;
		_x = x;
		_y = y;
	}

	/**
	 * @return y coordinate of the top edge
	 */
	public int getTop() {
		return _y*32;
	}
	/**
	 * @return y coordinate of the bottom edge
	 */
	public int getBottom() {
		return _y*32+32;
	}
	/**
	 * @return x coordinate of the left edge
	 */
	public int getLeft() {
		return _x*32;
	}
	/**
	 * @return x coordinate of the right edge
	 */
	public int getRight() {
		return _x*32+32;
	}
	
	/**
	 * @return the room the tile is part of
	 */
	public Room getHome() {
		return _home;
	}
	
	/**
	 * @return the tile's type (string format)
	 */
	public String getType()
	{
		return _tile_type;
	}
	
	/**
	 * @param s the new tile type
	 */
	public void setType(String s)
	{
		this._tile_type = s;
	}
	
}
