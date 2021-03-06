package sprite;


/**
 * @author Thomas
 */

import game.Game;

import java.awt.image.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class Sprite {
	protected int X,Y,Width,Height,SpriteX,SpriteY;
	private double Layer;
	
	private static Image picture[];
	private static HashMap<String, Image> pics = new HashMap<String, Image>();

	
	
	protected Image img;
	
	protected ImageObserver observer;
	//private ImageEnum id;
	
	public Sprite(int x,int y,int width,int height,int spriteX, int spriteY, double layer,Image image){
		X = x; Y = y; Width = width; Height = height; SpriteX = spriteX; SpriteY = spriteY; Layer = layer; img = image;
	}
	public Sprite(int x,int y,int width,int height,int spriteX, int spriteY, double layer, int Index){
		X = x; Y = y; Width = width; Height = height; SpriteX = spriteX; SpriteY = spriteY; Layer = layer;img = picture[Index];
	}
	public Sprite(int x,int y,int width,int height,int spriteX, int spriteY, double layer, String ImgName){
		if (!pics.containsKey(ImgName)) addGraphic(ImgName, ImgName);
		X = x; Y = y; Width = width; Height = height; SpriteX = spriteX; SpriteY = spriteY; Layer = layer;img = pics.get(ImgName);
	}
	
	/**
	 * Load the standard images into the sprite list
	 */
	public static void load() {
		picture = new Image[4];
		//System.out.println(ImageEnum.valueOf("tempWall").getValue());
		
		File Floor = new File("tempBackground.png");
		Image floor = null;
		try {
			//floor =  ImageIO.read(Floor);
			floor = ImageIO.read(Game.class.getResource("tempBackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pics.put("tempFloor", floor);
		
		picture[0] = floor;
		
		File Wall = new File("tempWall.png");
		Image wall = null;
		try {
			//wall =  ImageIO.read(Wall);
			wall = ImageIO.read(Game.class.getResource("tempWall.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pics.put("tempWall", wall);
		
		picture[1] = wall;

		File Hero = new File("hero dude and sword.png");
		Image hero = null;
		try {
			//hero =  ImageIO.read(Hero);
			hero = ImageIO.read(Game.class.getResource("hero dude and word.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pics.put("hero", hero);
		
		picture[2] = hero;
		
		/*File Enemy = new File("generic_Evil_creature_small.jpg");
		Image enemy = null;
		try {
			enemy =  ImageIO.read(Enemy);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pics.put("enemy", enemy);
		
		picture[3] = enemy;*/
		
		
		addGraphic("hero sword.png", "swords");
		addGraphic("hearts.png", "hearts");
		addGraphic("TempExplosion.png", "explosion");
		addGraphic("bomb.png", "bomb");
		addGraphic("temp_flame_tower.png", "enemyTower"); //new placeholder tower
		addGraphic("temp_flame_spirit.png", "enemy"); //new placeholder enemy
		addGraphic("small_fireboss_trans.png", "boss1"); //new boss placeholder
		addGraphic("beta_wall.png", "newWall"); //new wall placeholder
		addGraphic("upstairs.png", "upstair"); //stair placeholders
		addGraphic("downstairs.png", "downstair");
	}
	
	/**
	 * Load a new image into the sprite list
	 * @param fileName the name of the image file
	 * @param spriteName the name of the storage image
	 */
	public static void addGraphic(String fileName, String spriteName){
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

	
	/**
	 * Draw the sprite 
	 * 
	 * @param g the graphics object to draw with
	 */
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

	public double getLayer() {
		return Layer;
	}

	public Image getImg() {
		return img;
	}

	
}
