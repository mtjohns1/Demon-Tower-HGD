/**
 * @author Thomas
 */

import java.awt.image.*;
import java.awt.*;


public class Sprite {
	private int X,Y,Width,Height,SpriteX,SpriteY,Layer;
	
	private Image img;
	
	private ImageObserver observer;
	
	public Sprite(int x,int y,int width,int height,int spriteX, int spriteY, int layer,Image image){
		X = x; Y = y; Width = width; Height = height; SpriteX = spriteX; SpriteY = spriteY; Layer = layer; img = image;
	}
	
	public void draw(Graphics g){
		g.drawImage(img, X, Y, null);
		
	}
}
