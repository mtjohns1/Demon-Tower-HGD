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

	
	Room rooms[][] = new Room[2][2];
	
	public Floor(){
		
		for(int y = 0; y < 2; y++){
			for(int x = 0; x < 2; x++){
				rooms[y][x] = new Room();
			}
		}
		
		
	}
	
	public Room get(int x, int y){
		return rooms[y][x];
		
	}
	
	
	
	

}
