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
					rooms[y][x] = new Room(true);
				else
					rooms[y][x] = new Room();
			}
		}
		
		for(int i = 0; i < 3; i++){
			rooms[i][0].openSouth();
			rooms[i][2].openSouth();
			rooms[i][3].openSouth();
		}
		
		
		
	}
	
	public Room get(int x, int y){
		Tile tile[][] = rooms[y][x].getTiles();
		rooms[y][x] = new Room(tile);
		return rooms[y][x];
		
	}
	
	
	
	

}
