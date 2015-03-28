package menu;
import game.Game;

import java.util.ArrayList;

import sprite.Sprite;
import sprite.TransparencySprite;
import control.Control;


public class GameOverMenu extends Menu{
private int highlighted;
	
	private boolean move = false;
	private boolean select = false;
	private boolean pause = true;
	private boolean isDead = false;
	private boolean enabled = false;
	private Game _game;
	
	private static int offset = 0;
	private int Y = -480;
	private float fade = 0;
	
	public boolean isDead() {
		return isDead;
	}
	
	Control c;
	public GameOverMenu(Control c, Game game){
		this.highlighted = 0;
		this.c = c;
		//this.menuInit();
		_game = game;
	}
	
	/*public static void menuInit(){
		Sprite.addGraphic("tempGameOverMenu.png", "gameOver");
		Sprite.addGraphic("temExitButton.png","exit");
		Sprite.addGraphic("temExitButtonHighlight.png","exitH");
		Sprite.addGraphic("temExitButtonPressed.png","exitP");
		Sprite.addGraphic("tempReturnButton.png", "returnButton");
		Sprite.addGraphic("tempReturnButtonHighlighted.png", "returnButtonH");
		Sprite.addGraphic("tempReturnButtonPressed.png","returnButtonP");
	}*/
	
	public void drawMenu(ArrayList<Sprite> sprites) {
		// TODO Auto-generated method stub
		
		TransparencySprite Menu = new TransparencySprite(0, 0, 640, 480, 0, 0, 4000, "tempGameOverMenu.png", fade-1);
		sprites.add(Menu);
		
		TransparencySprite button1 = new TransparencySprite(220, 320,200, 40, 0, 0, 4001, "tempReturnButton.png", fade-1);
		if(highlighted ==0){
			if(select){
				button1 = new TransparencySprite(220, 320,200, 40, 0, 0, 4001, "tempReturnButtonPressed.png", fade-1);
			}else{
				button1 = new TransparencySprite(220, 320,200, 40, 0, 0, 4001, "tempReturnButtonHighlighted.png", fade-1);
			}
		}		
		sprites.add(button1);
		TransparencySprite button2 = new TransparencySprite(220, 370, 200, 40, 0, 0, 4001, "temExitButton.png", fade-1);
		if(highlighted ==1){
			if(select){
				button2 = new TransparencySprite(220, 370, 200, 40, 0, 0, 4001, "temExitButtonPressed.png", fade-1);
			}else{
				button2 = new TransparencySprite(220, 370, 200, 40, 0, 0, 4001, "temExitButtonHighlight.png", fade-1);
			}
		}		
		sprites.add(button2);
		TransparencySprite blackscreen = new TransparencySprite(0,0,640,480,0,0, 300,"BlackScreen.png",fade);
		sprites.add(blackscreen);
	}
	public void update(){
		if(fade < 2){
			fade += .05f;
		}else{
			enabled = true;
		}
		
		
		if (enabled) {
			if ((c.getShoot().getY() >= 50 || c.getShoot().getY() <= -50)
					&& move == false) {
				int updown = 0;
				if (c.getShoot().getY() >= 50)
					updown = 1;
				if (c.getShoot().getY() <= -50)
					updown = -1;
				move = true;

				highlighted = (highlighted + updown) % 2;
				if (highlighted < 0)
					highlighted = 2 + highlighted;
			} else if (c.getShoot().getY() == 0 && move == true) {
				move = false;
			}
			if (c.getMenu().select() && select == false) {
				select = true;
			} else if (!c.getMenu().select() && select == true) {
				select = false;
				if (highlighted == 0)
					_game.setReset(true);
				if (highlighted == 1)
					System.exit(0);

			}
		}
		
	
	}

}
