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

	public void createBlankRoom() //creates a blank room
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


	public void addMobile(Mobile e)//add a mobile actor to a room
	{
		this.list.add(e);
	}

	public void addTileChange(String s, int x, int y) //add a specific 'thing' to a tile
	{
		floor[y][x].setType(s);
	}

	public ArrayList<Mobile> getList() //For getting a list of mobile actors in a room
	{
		return list;
	}

	public void clearList() //removes all actors from the list
	{
		this.list.clear();
	}

	public Tile getTile(int x, int y)
	{
		return floor[y][x];
	}

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
