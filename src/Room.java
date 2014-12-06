/**
 * 
 * A class for individual rooms
 * 
 * @author Brett Miller
 * @author Jacob Charles
 * 
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Room {

	Tile tiles[][] = new Tile[15][20];
	ArrayList<Mobile> list = new ArrayList<Mobile>();
	Player player = null;
	int width = 640, height = 448;
	
	/**
	 * Creates a new blank room with walls surrounding it.
	 */
	public Room() //creates a blank room
	{
		for(int x = 0; x < 20; x++)
		{
			for(int y = 0; y < 14; y++)
			{
				tiles[y][x] = new Tile(this, x, y);

				if(x == 0 || y == 0 || y == 13 || x == 19 || Math.random()*10 < 1)
				{
					tiles[y][x].setType("w");
				}
				else
				{
					tiles[y][x].setType("");
				}
			}
		}
		
		generateBadguys();
	}
	
	public Room(boolean start) //creates the starting room
	{
		for(int x = 0; x < 20; x++)
		{
			for(int y = 0; y < 14; y++)
			{
				tiles[y][x] = new Tile(this, x, y);

				if(x == 0 || y == 0 || y == 13 || x == 19 || Math.random()*10 < 1)
				{
					tiles[y][x].setType("w");
				}
				else
				{
					tiles[y][x].setType("");
				}
			}
		}
		
		tiles[13][9].setType("");
		tiles[13][10].setType("");
		
		tiles[6][19].setType("");
		tiles[7][19].setType("");
		
		generateBadguys();
	}
	//This will be used for re-creating an old room
	public Room(Tile[][] t){
		tiles = t;
		generateBadguys();
	}
	//This constructor is for generating rooms on an edge
	
	public Tile[][] getTiles(){
		return tiles;
	}
	public void openSouth(){
		tiles[13][9].setType("");
		tiles[13][10].setType("");
	}
	
	public void openNorth(){
		tiles[0][9].setType("");
		tiles[0][10].setType("");
	}
	
	public void openWest(){
		tiles[6][0].setType("");
		tiles[7][0].setType("");
	}
	
	public void openEast(){
		tiles[6][19].setType("");
		tiles[7][19].setType("");
	}
	public void generateBadguys(){
		EnemyChaser enemy = new EnemyChaser(this);
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public void setPlayer(Player p){
		this.player = p;
	}

	/**
	 * @return y coordinate of the top edge
	 */
	public int getTop() {
		return 0;
	}
	/**
	 * @return y coordinate of the bottom edge
	 */
	public int getBottom() {
		return height-1;
	}
	/**
	 * @return x coordinate of the left edge
	 */
	public int getLeft() {
		return 0;
	}
	/**
	 * @return x coordinate of the right edge
	 */
	public int getRight() {
		return width-1;
	}
	
	public String getDirection()
	{
		if(player != null && this.player.getRight() > 640)
		{
			return "east";
		}
		
		else if(player != null && this.player.getLeft() < 0)
		{
			return "west";
		}
		
		else if(player != null && this.player.getTop() < 0)
		{
			return "north";
		}
		
		else if(player != null && this.player.getBottom() > 470)
		{
			return "south";
		}
		else {
			return "no change";
		}
	}
	
	

	/**
	 * 
	 * @param e: A mobile object to be added to an ArrayList
	 */
	public void addMobile(Mobile e)//add a mobile actor to a room
	{
		e.setHome(this);
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
		tiles[y][x].setType(s);
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
		return tiles[y][x];
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

		if (x_1 < 0) x_1 = 0;
		if (x_2 > 19) x_2 = 19;
		if (y_1 < 0) y_1 = 0;
		if (y_2 > 13) y_2 = 13;

		if(x_1 > x_2 || y_1 > y_2)
		{
			//TODO: Remove!
			return range; //returns an empty set. Means the inputs are messed up
		}

		for(int x = x_1; x <= x_2; x++)
		{
			for(int y = y_1; y <= y_2; y++)
			{
				range.add(tiles[y][x]);
			}
		}
		return range;
	}

	/**
	 * Update all room contents
	 */
	public void update() {
		//loop through the mobiles, get their updates
		for (int i = 0; i < list.size(); i++) {
			list.get(i).update();
		}
		//check collisions between all mobiles
		for (int x = 0; x < list.size(); x++) {
			for (int y = x+1; y < list.size(); y++) {
				list.get(x).checkCollision(list.get(y));
			}
		}
		//loop through the mobiles, move them and remove dead ones
		for (int i = 0; i < list.size(); i++) {
			list.get(i).move();
			//check if they died
			if (list.get(i).isDead()) {
				list.get(i).onDeath(); //let it make it's last modifications
				list.remove(i);
				i--;
			}
		}
	}

	/**
	 * Generate the room's content sprites for this frame
	 * 
	 * @param l: the list of sprites to add to
	 */
	public void draw(List<Sprite> l)
	{
		//loop through the ground tiles
		for (int x = 0; x < 20; x++)
		{
			for (int y = 0; y < 14; y++)
			{
				getTile(x, y).draw(l);
			}
		}

		//loop through the mobiles
		for (int i = 0; i < list.size(); i++)
		{
			list.get(i).draw(l);
		}
	}


}
