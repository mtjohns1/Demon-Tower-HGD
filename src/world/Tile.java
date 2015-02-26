package world;
/**
 * 
 * A class for individual tiles
 * @author Jacob Charles
 * @author Bret Miller
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import sprite.Sprite;

import java.awt.*;
import java.io.*;
import java.util.List;

public class Tile {

	//key of type flags
	public static String WALL = "w";
	public static String PIT = "p";
	public static String BREAK = "b";
	public static String WATER = "a";
	public static String ICE = "i";
	public static String SPIKE = "s";
	
	public static String SWITCH = "t";
	public static String GATE = "g";
	
	public static String UP = "u";
	public static String DOWN = "d";
	
	//actual variables
	private Room _home;
	private int _x, _y;
	private String _tileType;
	private String _tileSet;
	
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
		return _y*32+31;
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
		return _x*32+31;
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
		return _tileType;
	}
	
	/**
	 * @param s the new tile type
	 */
	public void setType(String s)
	{
		this._tileType = s;
	}
	
	/**
	 * @param s the name of the new tile set
	 */
	public void setTileSet(String s) {
		_tileSet = s;
	}
	
	/**
	 * @return the name of the current tile set
	 */
	public String getTileSet() {
		return _tileSet;
	}
	
	/**
	 * if the tile has the breakable trait, remove certain other traits
	 */
	public void breakTile() {
		if (!_tileType.contains("b")) return; //can't break the unbreakable
		_tileType = _tileType.replace("w", " "); //remove wall
		_tileType = _tileType.replace("s", " "); //remove damaging
		_tileType = _tileType.replace("t", " "); //remove switch
		//break ice with water under it
		if (_tileType.contains("a"))
			_tileType = _tileType.replace("i", " ");
		_tileType = _tileType.replace("b", " "); //already broken, no more breaking
	}
	
	/**
	 * @param height the height of the tile's top face
	 * @return the tile's layer. Exact values are irrelevant. Higher value = closer to screen.
	 */
	public double calculateLayer(int height) {
		int screenMax = 480*2; //highest visible face- maximum return value is 0
		return (getBottom()+height-screenMax)-0.01; //reduce layer slightly to ensure it draws below
	}

	/**
	 * Generate the tile's sprite for this frame
	 * 
	 * @param l: the list of sprites to add to
	 */
	public void  draw(List<Sprite> l)
	{
		//TODO: Make this read from a single tileset, using the types to determine frame within 
		Sprite s = new Sprite(getLeft(), getTop(), 32, 32, 0, 0, calculateLayer(0), "tempFloor");
		if (getType().contains("w")) s = new Sprite(getLeft(), getTop()-32, 32, 64, 0, 0, calculateLayer(32), "newWall");
		if (getType().contains("p")) return; //pits don't have a sprite, skip for now!
		l.add(s);
	}
	
}
