/**
 * 
 * A class for individual rooms
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Room {

	Tile floor[][] = new Tile[15][20];
	ArrayList<Mobile> list = new ArrayList<Mobile>();

	
	/**
	 * Creates a new blank room with walls surrounding it.
	 */
	public Room() //creates a blank room
	{
		for(int i = 0; i < 15; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				floor[i][j] = new Tile(this, i, j);

				if(i == 0 || j == 0 || i == 14 || j == 19)
				{
					floor[i][j].setType("w");
				}
			}
		}

	}

/**
 * 
 * @param e: A mobile object to be added to an ArrayList
 */
	public void addMobile(Mobile e)//add a mobile actor to a room
	{
		this.list.add(e);
	}

	/**
	 * 
	 * @param s: A string reference to change the tile, example being "rocks" or "ice" or "fire"
	 * @param x: The x coordinate for the tile to be changed.
	 * @param y: the y coordinate for the tile to be changed.
	 */
	public void addTileChange(String s, int x, int y) //add a specific 'thing' to a tile
	{
		floor[y][x].setType(s);
	}

	/**
	 * 
	 * @return returns an ArrayList of mobile objects upon being called.
	 */
	public ArrayList<Mobile> getList() //For getting a list of mobile actors in a room
	{
		return list;
	}

	/**
	 * Clears the current mobile ArrayList. Will be used for repopulating the list or when you leave the room.
	 */
	public void clearList() //removes all actors from the list
	{
		this.list.clear();
	}

	/**
	 * 
	 * @param x The x coordinate for the tile you are asking for.
	 * @param y The y coordinate for the tile you are asking for.
	 * @return The tile located at the x and y coordinates.
	 */
	public Tile getTile(int x, int y)
	{
		return floor[y][x];
	}
	
/**
 * 
 * @param x_1 = the x coordinate for the first tile.
 * @param y_1 = the y coordinate for the first tile.
 * @param x_2 = the x coordinate for the second tile.
 * @param y_2 = the y coordinate for the second tile.
 * @return an ArrayList of Tile(s) starting at the coordinates of the first tile and ending, and including, 
 *  the coordinates for the second tile.
 */
	public ArrayList<Tile> getRange(int x_1, int y_1, int x_2, int y_2) //Function to return a range of tiles
	{
		ArrayList<Tile> range = new ArrayList<Tile>(); //ArrayList that stores all tiles in the range

		if(x_1 > x_2 || y_1 > y_2)
		{
			return range; //returns an empty set. Means the inputs are messed up
		}

		for(int i = x_1; i <= x_2; i += 1)
		{
			for(int j = y_1; j <= y_2; j += 1)
			{
				range.add(this.floor[j][i]);
			}
		}

		return range;
	}


}
