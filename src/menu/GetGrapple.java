package menu;
import java.util.ArrayList;

import sprite.Sprite;
import sprite.TransparencySprite;
import game.Game;
import control.Control;


public class GetGrapple extends Menu{
	

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
		
		public GetGrapple(Control c, Game game){
			this.c = c;
			//this.menuInit();
			_game = game;
		}
		
		public void drawMenu(ArrayList<Sprite> sprites) {
			//Sprite Menu = new Sprite(50, Y, 500, 300, 0, 0, 1000, "menu");
			//sprites.add(Menu);
			TransparencySprite H2P = new TransparencySprite(640/2-413/2,480/2-119/2,413, 119,0,0, 7000,"GetGrapple.png", 1f);
			sprites.add(H2P);
		}
			
}
