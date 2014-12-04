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
				rooms[y][x] = new Room();
			}
		}
		
		
	}
	
	public Room get(int x, int y){
		Tile tile[][] = rooms[y][x].getTiles();
		rooms[y][x] = new Room(tile);
		return rooms[y][x];
		
	}
	
	
	
	

}
