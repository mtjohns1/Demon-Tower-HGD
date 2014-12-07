/**
 * 
 * A class for floors of the tower
 *
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;



public class Floor {


	Room rooms[][] = new Room[4][4];

	public Floor(){

		for(int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				if(x == 0 && y == 0)
					rooms[y][x] = new Room(1); //This is for the starting room
				else if(x == 1 && y == 2){
					rooms[y][x] = new Room(2); //This is for the room prior to the boss room
				}
				else if(x == 1 && y == 1){
					rooms[y][x] = new Room(3); //This is for the boss room
				}
				else if(x == 3 && y == 2)
					rooms[y][x] = new Room(4); //This is for the grappling hook room
				else
					rooms[y][x] = new Room(); //This is for random rooms
			}
		}
		
		//The code below opens sections of the map as needed. Some areas have multiple entry
		//points while others have only one way. The boss room, for example, only has 1 entry point.
		for(int i = 0; i < 3; i++){
			rooms[i][0].openSouth();
			rooms[i + 1][0].openNorth();
			rooms[i][2].openSouth();
			rooms[i + 1][2].openNorth();
			rooms[i][3].openSouth();
		}

		for(int i = 0; i < 3; i++){
			rooms[0][i].openEast();
			rooms[3][i].openEast();
			rooms[0][i + 1].openWest();
			rooms[3][i + 1].openWest();
		}
		rooms[1][1].openSouth();
		rooms[3][3].openNorth();
		rooms[1][2].openEast();
		rooms[3][2].openEast();

		rooms[2][1].openNorth();
		rooms[2][1].openSouth();

		rooms[3][1].openNorth();
		rooms[1][3].openNorth();
		rooms[2][3].openNorth();


	}

	public Room get(int x, int y){
		Tile tile[][] = rooms[y][x].getTiles();
		int[] a = rooms[y][x].enemyholder;
		int type = rooms[y][x].type;
		rooms[y][x] = new Room(tile, a, type);
		return rooms[y][x];

	}










}
