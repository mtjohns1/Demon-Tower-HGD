package menu;

import java.util.ArrayList;

import sprite.Sprite;
import control.Control;
import game.Game;

public class OptionsMenu extends Menu{
	private boolean move = false;
	private boolean select = false;
	private boolean pause = true;
	private boolean isDead = false;
	
	private Game _game;
	
	private int highlighted;
	
	
	Control c;
	
	public boolean isDead() {
		return isDead;
	}
	
	public OptionsMenu(Control c, Game game){
		this.highlighted = 0;
		this.c = c;
		this.menuInit();
		_game = game;
	}
	
	public static void menuInit(){
		Sprite.addGraphic("tempOptionsWindow.png", "optionsMenu");
		Sprite.addGraphic("tempResumeButton.png","resume");
		Sprite.addGraphic("tempResumeButtonHighlight.png","resumeH");
		Sprite.addGraphic("tempResumeButtonPressed.png","resumeP");
	}
	
	public void drawMenu(ArrayList<Sprite> sprites) {
		// TODO Auto-generated method stub
		
		Sprite Menu = new Sprite(50, 100, 500, 300, 0, 0, 1000, "optionsMenu");
		sprites.add(Menu);
		Sprite button1 = new Sprite(75, 125,200, 40, 0, 0, 1001, "resume");;
		if(highlighted ==0){
			if(select){
				button1 = new Sprite(75, 125,200, 40, 0, 0, 1001, "resumeP");
			}else{
				button1 = new Sprite(75, 125,200, 40, 0, 0, 1001, "resumeH");
			}
		}		
		sprites.add(button1);
	}
	
	public void update(){
		if((c.getShoot().getY() >=50 || c.getShoot().getY() <= -50) && move == false){
			int updown = 0;
			if(c.getShoot().getY()>=50)
				updown = 1;
			if(c.getShoot().getY()<=-50)
				updown= -1;
			move = true;
			
			highlighted = (highlighted + updown)%1;
			if (highlighted < 0)
				highlighted = 1 + highlighted;
		}
		else if(c.getShoot().getY() == 0 && move == true){
			move = false;
		}
		if(c.getMenu().select() && select == false){
			select = true;
		}
		else if(!c.getMenu().select() && select == true){
			select = false;
			if(highlighted == 0)
				isDead = true;
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
