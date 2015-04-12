package menu;

import java.util.ArrayList;

import sprite.Sprite;
import sprite.TransparencySprite;
import game.Game;
import control.Control;

public class HowToPlayMenu extends Menu{

	private boolean move = false;
	private boolean select = false;
	private boolean pause = true;
	private boolean isDead = false;
	private boolean enabled = false;
	private Game _game;
	private int Y = 0;
	

	Control c;


	public boolean isDead() {
		return isDead;
	}
	
	
	public HowToPlayMenu(Control c, Game game){
		this.c = c;
		//this.menuInit();
		_game = game;
	}
	
	public void drawMenu(ArrayList<Sprite> sprites) {
		//Sprite Menu = new Sprite(50, Y, 500, 300, 0, 0, 1000, "menu");
		//sprites.add(Menu);
		TransparencySprite H2P = new TransparencySprite(0,Y,640, 631,0,0, 7000,"HowToPlayMenu.png", 1f);
		sprites.add(H2P);
		
	}
	
	public void update(){
		if(c.getMenu().escape() && pause == false){
			pause = true;
			isDead = !isDead;
		}
		else if(!c.getMenu().escape() && pause == true){
			pause = false;
		}
		if (c.getMenu().select() && select == false) {
			select = true;
		} else if (!c.getMenu().select() && select == true) {
			select = false;
			isDead = !isDead;

		}
		if(c.getShoot().getY() < 0){
			
			if(Y > 480-631)
				Y--;
			
			
		}
		if(c.getShoot().getY() > 0){
			if(Y != 0)
				Y++;
		}
	}
	
}
