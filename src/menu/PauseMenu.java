package menu;
import game.Game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import sprite.Sprite;
import sprite.TransparencySprite;
import control.Control;


public class PauseMenu extends Menu {
	private int highlighted;

	private boolean move = false;
	private boolean select = false;
	private boolean pause = true;
	private boolean isDead = false;
	private boolean enabled = false;
	private Game _game;
	public boolean isDead() {
		return isDead;
	}

	private static int offset = 100;
	private int Y = 480;


	Control c;
	public PauseMenu(Control c, Game game){
		this.highlighted = 0;
		this.c = c;
		//this.menuInit();
		_game = game;
	}

	/*public static void menuInit(){
		Sprite.addGraphic("tempMenuWindow.png", "menu");
		Sprite.addGraphic("tempResumeButton.png","resume");
		Sprite.addGraphic("tempResumeButtonHighlight.png","resumeH");
		Sprite.addGraphic("tempResumeButtonPressed.png","resumeP");
		Sprite.addGraphic("temExitButton.png","exit");
		Sprite.addGraphic("temExitButtonHighlight.png","exitH");
		Sprite.addGraphic("temExitButtonPressed.png","exitP");
		Sprite.addGraphic("tempReturnButton.png", "returnButton");
		Sprite.addGraphic("tempReturnButtonHighlighted.png", "returnButtonH");
		Sprite.addGraphic("tempReturnButtonPressed.png","returnButtonP");
		Sprite.addGraphic("tempOptionsButton.png", "options");
		Sprite.addGraphic("tempOptionsButtonHighlight.png", "optionsH");
		Sprite.addGraphic("tempOptionsButtonPressed.png", "optionsP");

	}*/

	public void drawMenu(ArrayList<Sprite> sprites) {
		// TODO Auto-generated method stub

		TransparencySprite Menu = new TransparencySprite(50, Y, 500, 300, 0, 0, 1000, "tempMenuWindow.png",.75f);
		sprites.add(Menu);
		Sprite button1 = new Sprite(75, Y+75,200, 40, 0, 0, 1001, "tempResumeButton.png");
		if(highlighted ==0){
			if(select){
				button1 = new Sprite(75, Y+75,200, 40, 0, 0, 1001, "tempResumeButtonPressed.png");
			}else{
				button1 = new Sprite(75, Y+75,200, 40, 0, 0, 1001, "tempResumeButtonHighlight.png");
			}
		}		
		sprites.add(button1);
		Sprite button2 = new Sprite(75, Y+175,200, 40, 0, 0, 1001, "tempReturnButton.png");
		if(highlighted ==2){
			if(select){
				button2 = new Sprite(75, Y+175,200, 40, 0, 0, 1001, "tempReturnButtonPressed.png");
			}else{
				button2 = new Sprite(75, Y+175,200, 40, 0, 0, 1001, "tempReturnButtonHighlighted.png");
			}
		}		
		sprites.add(button2);
		Sprite button3 = new Sprite(75, Y+225, 200, 40, 0, 0, 1001, "temExitButton.png");
		if(highlighted ==3){
			if(select){
				button3 = new Sprite(75, Y+225, 200, 40, 0, 0, 1001, "temExitButtonPressed.png");
			}else{
				button3 = new Sprite(75, Y+225, 200, 40, 0, 0, 1001, "temExitButtonHighlight.png");
			}
		}		
		sprites.add(button3);
		Sprite button4 = new Sprite(75, Y+125, 200, 40, 0, 0, 1001, "tempOptionsButton.png");
		if(highlighted ==1){
			if(select){
				button4 = new Sprite(75, Y+125, 200, 40, 0, 0, 1001, "tempOptionsButtonPressed.png");
			}else{
				button4 = new Sprite(75, Y+125, 200, 40, 0, 0, 1001, "tempOptionsButtonHighlight.png");
			}
		}		
		sprites.add(button4);
	
	}

	public void update(){
		if(Y!=offset){
			Y-=20;
		}else{
			enabled = true;
		}

		//scroll(Y, offset);

		if (enabled) {
			if ((c.getShoot().getY() >= 50 || c.getShoot().getY() <= -50)
					&& move == false) {
				int updown = 0;
				if (c.getShoot().getY() >= 50)
					updown = 1;
				if (c.getShoot().getY() <= -50)
					updown = -1;
				move = true;

				highlighted = (highlighted + updown) % 4;
				if (highlighted < 0)
					highlighted = 4 + highlighted;
			} else if (c.getShoot().getY() == 0 && move == true) {
				move = false;
			}
			if (c.getMenu().select() && select == false) {
				select = true;
			} else if (!c.getMenu().select() && select == true) {
				select = false;
				if (highlighted == 0)
					isDead = true;
				if (highlighted == 2)
					_game.setReset(true);
				if (highlighted == 3){
					System.exit(0);
									}
				if (highlighted == 1) {
					ArrayList<Menu> temp = _game.getMenu();
					temp.add(new OptionsMenu(c, _game));
					_game.setMenu(temp);
				}

			}
		}
		if(c.getMenu().escape() && pause == false){
			pause = true;
			isDead = !isDead;
		}
		else if(!c.getMenu().escape() && pause == true){
			pause = false;
		}

	}

}
