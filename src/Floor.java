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
					rooms[y][x] = new Room(1);
				else if(x == 1 && y == 2){
					rooms[y][x] = new Room(2);
				}
				else if(x == 1 && y == 1){
					rooms[y][x] = new Room(3);
				}
				else
					rooms[y][x] = new Room();
			}
		}
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
		
		rooms[1][2].openEast();
		rooms[3][2].openEast();
		
		rooms[2][1].openNorth();
		rooms[2][1].openSouth();
		
		rooms[3][1].openNorth();
		
		
		
	}
	
	public Room get(int x, int y){
		Tile tile[][] = rooms[y][x].getTiles();
		rooms[y][x] = new Room(tile);
		return rooms[y][x];
		
	}
	
	
	
	
	
	
	
	
	

}
