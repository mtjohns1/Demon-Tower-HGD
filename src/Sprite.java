

/**
 * @author Thomas
 */

import java.awt.image.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Sprite {
	private int X,Y,Width,Height,SpriteX,SpriteY,Layer; 
	
	private static Image picture[];

	private Image img;
	
	private ImageObserver observer;
	
	public Sprite(int x,int y,int width,int height,int spriteX, int spriteY, int layer,Image image){
		X = x; Y = y; Width = width; Height = height; SpriteX = spriteX; SpriteY = spriteY; Layer = layer; img = image;
	}
	public Sprite(int x,int y,int width,int height,int spriteX, int spriteY, int layer, int Index){
		X = x; Y = y; Width = width; Height = height; SpriteX = spriteX; SpriteY = spriteY; Layer = layer;img = picture[Index];
	}
	
	
	public static void load() {
		picture = new Image[3];
		
		File Floor = new File("src/tempBackground.png");
		Image floor = null;
		try {
			floor =  ImageIO.read(Floor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		picture[0] = floor;
		
		File Wall = new File("src/tempWall.png");
		Image wall = null;
		try {
			wall =  ImageIO.read(Wall);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		picture[1] = wall;
		
		File Hero = new File("src/hero dude and sword.png");
		Image hero = null;
		try {
			hero =  ImageIO.read(Hero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		picture[2] = hero;
	}
	
	public void draw(Graphics g){
		g.drawImage(img, X, Y, X+Width-1, Y+Height-1, SpriteX, SpriteY, SpriteX+Width-1, SpriteY+Height-1, observer);
		
	}
	
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getWidth() {
		return Width;
	}

	public int getHeight() {
		return Height;
	}

	public int getSpriteX() {
		return SpriteX;
	}

	public int getSpriteY() {
		return SpriteY;
	}

	public int getLayer() {
		return Layer;
	}

	public Image getImg() {
		return img;
	}

	
}
