package sprite;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class TransparencySprite extends Sprite{
	private float Trans = 1;
	public TransparencySprite(int x, int y, int width, int height, int spriteX,
			int spriteY, double layer, String ImgName, float trans) {
		super(x, y, width, height, spriteX, spriteY, layer, ImgName);
		Trans = trans;
		if(Trans > 1){
			Trans = 1;
		}
		if(Trans < 0){
			Trans = 0;
		}
		// TODO Auto-generated constructor stub
	}


	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,Trans));
		g2d.drawImage(img, X, Y, X+Width, Y+Height, SpriteX, SpriteY, SpriteX+Width, SpriteY+Height, observer);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
	}
		

}
