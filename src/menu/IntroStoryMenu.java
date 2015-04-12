package menu;

import java.io.IOException;
import java.util.ArrayList;

import game.Game;

import javax.imageio.ImageIO;

import sprite.Sprite;
import sprite.TransparencySprite;
import control.Control;

import java.util.ArrayList;

import sprite.Sprite;
import game.Game;
import control.Control;

public class IntroStoryMenu extends Menu{
	private int highlighted;


	private boolean move = false;
	private boolean select = false;
	private boolean pause = true;
	private boolean isDead = false;
	private boolean enabled = false;
	private Game _game;
	private static int offset = 0;
	private int Y = 0;
	private int Yy = 0;
	private double X = 0;
	private int finalcountdown = 0;
	private float fade;

	Control c;


	public boolean isDead() {
		return isDead;
	}

	public IntroStoryMenu(Control c, Game game){
		this.highlighted = 0;
		this.c = c;
		//this.menuInit();
		_game = game;
	}

	/*public static void menuInit(){
		Sprite.addGraphic("OpeningPic1.png", "IntroPic2");
		Sprite.addGraphic("OpeningPic2.png", "IntroPic");
		Sprite.addGraphic("OpeningPic3.png", "IntroPic3");
		Sprite.addGraphic("OpeningPicText.png", "IntroText");
	}*/

	public void drawMenu(ArrayList<Sprite> sprites) {
		//Sprite Menu = new Sprite(50, Y, 500, 300, 0, 0, 1000, "menu");
		//sprites.add(Menu);
		TransparencySprite introP = new TransparencySprite(0,0,640, 240,0,0, 7000,"OpeningPic2.png", 1f-fade);
		sprites.add(introP);
		TransparencySprite introT = new TransparencySprite(0,240,640,240,0,0+(int)X,9001,"OpeningPicText.png", 1f-fade);
		sprites.add(introT);
		TransparencySprite introPP = new TransparencySprite(640-Y,0,640,240,0,0,8000,"OpeningPic1.png",1f-fade);
		sprites.add(introPP);
		TransparencySprite introPPP = new TransparencySprite(640-Yy,0,640,240,0,0,8000,"OpeningPic3.png",1f-fade);
		sprites.add(introPPP);
		TransparencySprite blackscreen = new TransparencySprite(0,0,640,480,0,0,3000,"BlackScreen.png", 2f-fade);
		sprites.add(blackscreen);
	}

	public void update(){
		if(X<480){
			X+=.5;
		}
		if(X>=120){
			if(Y < 640){
				Y+=5;
			}
		}
		if(X>=360){
			if(Yy < 640){
				Yy+=5;
			}
		}
		if(X == 480){
			finalcountdown++;
		}
		if(finalcountdown >= 200){
			fade += 1f/30f;
			if(fade >= 2f)
				_game.getMenu().add(new HowToPlayMenu(c, _game));
				isDead = !isDead;
		}

		if(c.getMenu().escape() && pause == false){
			pause = true;
			_game.getMenu().add(new HowToPlayMenu(c, _game));
			isDead = !isDead;
		}
		else if(!c.getMenu().escape() && pause == true){
			pause = false;
		}
		if (c.getMenu().select() && select == false) {
			select = true;
		} else if (!c.getMenu().select() && select == true) {
			select = false;
			_game.getMenu().add(new HowToPlayMenu(c, _game));
			isDead = !isDead;

		}
		
	}

}
