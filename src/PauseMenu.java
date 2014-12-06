import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class PauseMenu extends Menu {
	private int highlighted;
	
	private boolean move = false;
	private boolean select = false;
	private boolean pause = true;
	private boolean isDead = false;
	private Game _game;
	public boolean isDead() {
		return isDead;
	}

	

	Control c;
	public PauseMenu(Control c, Game game){
		this.highlighted = 0;
		this.c = c;
		this.menuInit();
		_game = game;
	}

	public static void menuInit(){
		Sprite.addGraphic("src/tempMenuWindow.png", "menu");
		Sprite.addGraphic("src/tempResumeButton.png","resume");
		Sprite.addGraphic("src/tempResumeButtonHighlight.png","resumeH");
		Sprite.addGraphic("src/tempResumeButtonPressed.png","resumeP");
		Sprite.addGraphic("src/temExitButton.png","exit");
		Sprite.addGraphic("src/temExitButtonHighlight.png","exitH");
		Sprite.addGraphic("src/temExitButtonPressed.png","exitP");
	}

	public void drawMenu(ArrayList<Sprite> sprites) {
		// TODO Auto-generated method stub
		
		Sprite Menu = new Sprite(50, 100, 500, 300, 0, 0, 1000, "menu");
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
		Sprite button2 = new Sprite(75, 175,200, 40, 0, 0, 1001, "exit");;
		if(highlighted ==1){
			if(select){
				button2 = new Sprite(75, 175,200, 40, 0, 0, 1001, "exitP");
			}else{
				button2 = new Sprite(75, 175,200, 40, 0, 0, 1001, "exitH");
			}
		}		
		sprites.add(button2);
		

	}
	
	public void update(){
		if((c.getShoot().getY() >=50 || c.getShoot().getY() <= -50) && move == false){
			int updown = 0;
			if(c.getShoot().getY()>=50)
				updown = 1;
			if(c.getShoot().getY()<=-50)
				updown= -1;
			move = true;
			
			highlighted = (highlighted + updown)%2;
			if (highlighted < 0)
				highlighted = -highlighted;
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
			if(highlighted == 1)
				_game.setReset(true);
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
