

/**
 * @author Thomas
 */

import java.awt.image.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class Sprite {
	private int X,Y,Width,Height,SpriteX,SpriteY,Layer; 
	
	private static Image picture[];
	private static HashMap<String, Image> pics = new HashMap<String, Image>();

	
	
	private Image img;
	
	private ImageObserver observer;
	//private ImageEnum id;
	
	public Sprite(int x,int y,int width,int height,int spriteX, int spriteY, int layer,Image image){
		X = x; Y = y; Width = width; Height = height; SpriteX = spriteX; SpriteY = spriteY; Layer = layer; img = image;
	}
	public Sprite(int x,int y,int width,int height,int spriteX, int spriteY, int layer, int Index){
		X = x; Y = y; Width = width; Height = height; SpriteX = spriteX; SpriteY = spriteY; Layer = layer;img = picture[Index];
	}
	public Sprite(int x,int y,int width,int height,int spriteX, int spriteY, int layer, String ImgName){
		X = x; Y = y; Width = width; Height = height; SpriteX = spriteX; SpriteY = spriteY; Layer = layer;img = pics.get(ImgName);
	}
	
	public static void load() {
		picture = new Image[4];
		//System.out.println(ImageEnum.valueOf("tempWall").getValue());
		
		File Floor = new File("src/tempBackground.png");
		Image floor = null;
		try {
			floor =  ImageIO.read(Floor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pics.put("tempFloor", floor);
		
		
		picture[0] = floor;
		
		File Wall = new File("src/tempWall.png");
		Image wall = null;
		try {
			wall =  ImageIO.read(Wall);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pics.put("tempWall", wall);
		
		picture[1] = wall;

		File Hero = new File("src/hero dude and sword.png");
		Image hero = null;
		try {
			hero =  ImageIO.read(Hero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pics.put("hero", hero);
		
		picture[2] = hero;
		
		File Enemy = new File("src/generic_Evil_creature_small.jpg");
		Image enemy = null;
		try {
			enemy =  ImageIO.read(Enemy);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pics.put("enemy", enemy);
		
		picture[3] = enemy;
	}
	
	public void addGraphic(String fileName, String spriteName){
		File toAdd = new File(fileName);
		Image Adding = null;
		
		try {
			Adding =  ImageIO.read(toAdd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pics.put(spriteName, Adding);
		
	}

	
	
	public void draw(Graphics g){
		g.drawImage(img, X, Y, X+Width, Y+Height, SpriteX, SpriteY, SpriteX+Width, SpriteY+Height, observer);
		
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
