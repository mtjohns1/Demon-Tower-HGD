package menu;
import game.Game;

import java.util.ArrayList;

import sprite.Sprite;
import control.Control;

public class WinMenu extends Menu {
private int highlighted;
	
	private boolean move = false;
	private boolean select = false;
	private boolean pause = true;
	private boolean isDead = false;
	private boolean enabled = false;
	private Game _game;
	
	private static int offset = 0;
	private int Y = -480;
	
	public boolean isDead() {
		return isDead;
	}
	
	Control c;
	public WinMenu(Control c, Game game){
		this.highlighted = 0;
		this.c = c;
		this.menuInit();
		_game = game;
	}
	
	public static void menuInit(){
		Sprite.addGraphic("tempWinMenu.jpg", "win");
		Sprite.addGraphic("temExitButton.png","exit");
		Sprite.addGraphic("temExitButtonHighlight.png","exitH");
		Sprite.addGraphic("temExitButtonPressed.png","exitP");
		Sprite.addGraphic("tempReturnButton.png", "returnButton");
		Sprite.addGraphic("tempReturnButtonHighlighted.png", "returnButtonH");
		Sprite.addGraphic("tempReturnButtonPressed.png","returnButtonP");
	}
	
	public void drawMenu(ArrayList<Sprite> sprites) {
		// TODO Auto-generated method stub
		
		Sprite Menu = new Sprite(0, Y+0, 640, 480, 0, 0, 4000, "win");
		sprites.add(Menu);
		
		Sprite button1 = new Sprite(220, Y+320,200, 40, 0, 0, 4001, "returnButton");;
		if(highlighted ==0){
			if(select){
				button1 = new Sprite(220, Y+320,200, 40, 0, 0, 4001, "returnButtonP");
			}else{
				button1 = new Sprite(220, Y+320,200, 40, 0, 0, 4001, "returnButtonH");
			}
		}		
		sprites.add(button1);
		Sprite button2 = new Sprite(220, Y+370, 200, 40, 0, 0, 4001, "exit");
		if(highlighted ==1){
			if(select){
				button2 = new Sprite(220, Y+370, 200, 40, 0, 0, 4001, "exitP");
			}else{
				button2 = new Sprite(220, Y+370, 200, 40, 0, 0, 4001, "exitH");
			}
		}		
		sprites.add(button2);
		

	}
	public void update(){
		if(Y!=offset){
			Y+=10;
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

