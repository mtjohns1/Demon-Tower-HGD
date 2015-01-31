package world;
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

import mobile.Mobile;
import mobile.Player;
import sprite.Sprite;
import enemy.Enemy;
import enemy.BossFire;
import enemy.Chaser;
import enemy.Tower;

import java.util.Random;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Room {
	int[] enemyholder = {1};
	Random r = new Random();
	Tile tiles[][] = new Tile[15][20];
	Enemy enemies[] = null;
	ArrayList<Mobile> list = new ArrayList<Mobile>();
	Player player = null;
	int width = 640, height = 448;
	int type = 0;

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

				if(x == 0 || y == 0 || y == 13 || x == 19 /*|| Math.random()*10 < 1*/)
				{
					tiles[y][x].setType("w");
				}
				else
				{
					tiles[y][x].setType("");
				}
			}
		}		
		
		int a = r.nextInt(101);
	//	if(a <= 33)
	//		tiles = this.interestingRoom(tiles);
		/*else */if(a <= 66)
			tiles = this.fourSquareRoom(tiles);
		else
			tiles = this.threeSquareRoom(tiles);
			
		generateBadguys(enemyholder);
	}

	public Room(int start) //creates the starting room
	{
		for(int x = 0; x < 20; x++)
		{
			for(int y = 0; y < 14; y++)
			{
				tiles[y][x] = new Tile(this, x, y);

				if(x == 0 || y == 0 || y == 13 || x == 19 /*|| Math.random()*10 < 1*/)
				{
					tiles[y][x].setType("w");
				}
				else
				{
					tiles[y][x].setType("");
				}
			}
		}
		if(start == 1){
			tiles[13][9].setType("");
			tiles[13][10].setType("");

			tiles[6][19].setType("");
			tiles[7][19].setType("");
		}
		
		else if(start == 2){
			tiles[1][7].setType("p");
			tiles[2][7].setType("p");
			for(int i = 8; i < 13; i++)
				tiles[2][i].setType("p");
			
			tiles[1][12].setType("p");
			
		}
		else if(start == 3){
			enemyholder[0] = 3;
			this.bossRoom();
		}
		
		else if(start == 4){
			for(int i = 4; i < 8; i++){
				tiles[5][i].setType("w");
				tiles[8][i].setType("w");
				tiles[5][i + 8].setType("w");
				tiles[8][i + 8].setType("w");
			}
			
			for(int i = 7; i < 13; i++){
				tiles[4][i].setType("w");
				tiles[9][i].setType("w");
			}
			
			for(int i = 3; i < 6; i++){
				tiles[i][4].setType("w");
				tiles[i][15].setType("w");
				tiles[i + 5][4].setType("w");
				tiles[i + 5][15].setType("w");
			}
		}
		
		generateBadguys(enemyholder);
	}
	//This will be used for re-creating an old room
	public Room(Tile[][] t, int[] a, int roomType){
		tiles = t;
		type = roomType;
		enemyholder = a;
		generateBadguys(enemyholder);
	}
	
	public void enemySpawner(int type){
		
	}
	
	public Tile[][] fourSquareRoom(Tile[][] t){
		Tile tiles[][] = t;
		type = 1;
		for(int i = 3; i < 7; i++){
			tiles[3][i + 10].setType("w");
			tiles[4][i + 10].setType("w");
			tiles[5][i + 10].setType("w");
			
			tiles[3][i].setType("w");
			tiles[4][i].setType("w");
			tiles[5][i].setType("w");
			
			tiles[8][i + 10].setType("w");
			tiles[9][i + 10].setType("w");
			tiles[10][i + 10].setType("w");
			
			tiles[8][i].setType("w");
			tiles[9][i].setType("w");
			tiles[10][i].setType("w");
			int[] a = {1,1,1};
			
			
			
		}
		
		return tiles;
	}
	
	public Tile[][] threeSquareRoom(Tile[][] t){
		Tile tiles[][] = t;
		type = 2;
		for(int i = 3; i < 6; i++){
			tiles[i][3].setType("w");
			tiles[i][4].setType("w");
			
			tiles[i][8].setType("w");
			tiles[i][9].setType("w");
			tiles[i][10].setType("w");
			tiles[i][11].setType("w");
			
			tiles[i][15].setType("w");
			tiles[i][16].setType("w");
		}
		
		for(int i = 5; i < 15; i++){
			tiles[8][i].setType("w");int[] a = {1,1,1};
			tiles[9][i].setType("w");
			tiles[10][i].setType("w");
		}
		
		return tiles;
	}
	
	public Tile[][] interestingRoom(Tile[][] t){
		type = 3;
		for(int i = 3; i < 5; i++){
			tiles[2][i].setType("w");
			tiles[3][i].setType("w");
			
			tiles[6][i].setType("w");
			tiles[7][i].setType("w");
			
			tiles[10][i].setType("w");
			tiles[11][i].setType("w");
			//
			tiles[2][i + 12].setType("w");
			tiles[3][i + 12].setType("w");
			
			tiles[6][i + 12].setType("w");
			tiles[7][i + 12].setType("w");
			
			tiles[10][i + 12].setType("w");
			tiles[11][i + 12].setType("w");	
		}
		
		for(int i = 7; i < 13; i+=5){
			tiles[3][i].setType("w");
			tiles[4][i].setType("w");
			tiles[5][i].setType("w");
			tiles[8][i].setType("w");
			tiles[9][i].setType("w");
			tiles[10][i].setType("w");
		}
		
		tiles[3][10].setType("w");
		tiles[3][11].setType("w");
		tiles[5][8].setType("w");
		tiles[5][9].setType("w");
		tiles[8][10].setType("w");
		tiles[8][11].setType("w");
		tiles[10][8].setType("w");
		tiles[10][9].setType("w");
		
		return tiles;
	}
	
	public Tile[][] bossRoom(){
		type = 4;
		for(int i = 1; i < 4; i++)
		{
			for(int j = 1; j < 6; j++)
			{
				tiles[i][j].setType("w");
			}
			
			for(int j = 14; j < 20; j++)
			{
				tiles[i][j].setType("w");
			}
		}
		
		tiles[2][6].setType("w");
		tiles[2][7].setType("w");
		tiles[2][13].setType("w");
		tiles[2][14].setType("w");
		
		tiles[10][2].setType("w");
		tiles[11][3].setType("w");
		tiles[10][17].setType("w");
		tiles[11][16].setType("w");
		
		tiles[6][2].setType("w");
		tiles[5][3].setType("w");
		tiles[6][17].setType("w");
		tiles[5][16].setType("w");
		
		for(int i = 8; i < 12; i++)
			tiles[10][i].setType("w");
		
		tiles[11][6].setType("w");
		tiles[11][13].setType("w");
		tiles[12][6].setType("w");
		tiles[12][13].setType("w");
		
		return tiles;
	}
	
	

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
	
	/**
	 * 
	 * @param a array of ints
	 * 	1 = enemy chaser
	 * 	2 = enemy tower
	 * 	3 = Fire boss
	 */
	public void generateBadguys(int[] a){
		enemies = new Enemy[a.length];
		for(int i = 0; i < a.length; i++){
			if(a[i] == 1){
				enemies[i] = new Chaser(this);
			}
			if(a[i] == 2){
				enemies[i] = new Tower(this);
			}
			if(a[i] == 3){
				enemies[i] = new BossFire(this);
			}
		}
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
		if(player != null && this.player.getRight() > getRight())
		{
			return "east";
		}

		else if(player != null && this.player.getLeft() < getLeft())
		{
			return "west";
		}

		else if(player != null && this.player.getTop() < getTop())
		{
			return "north";
		}

		else if(player != null && this.player.getBottom() > getBottom())
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