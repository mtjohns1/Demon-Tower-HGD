package menu;
import java.util.ArrayList;

import sprite.Sprite;
import control.Control;


public class StartMenu extends Menu{
	private int highlighted;

	private boolean move = false;
	private boolean select = false;
	private boolean pause = true;
	private boolean isDead = false;

	public boolean isDead() {
		return isDead;
	}


	Control c;
	public StartMenu(Control c){
		this.highlighted = 0;
		this.c = c;
		//this.menuInit();
	}
/*
	public static void menuInit(){
		Sprite.addGraphic("tempStartMenu.png", "startMenu");
		Sprite.addGraphic("tempStartButton.png", "startButton");
		Sprite.addGraphic("tempStartButtonHighlighted.png", "startButtonH");
		Sprite.addGraphic("tempStartButtonPressed.png", "startButtonP");
		Sprite.addGraphic("tempQuitButton.png", "quitButton");
		Sprite.addGraphic("tempQuitButtonHighlighted.png", "quitButtonH");
		Sprite.addGraphic("tempQuitButtonPressed.png", "quitButtonP");
	}
*/
	public void drawMenu(ArrayList<Sprite> sprites) {
		// TODO Auto-generated method stub

		Sprite Menu = new Sprite(0, 0, 640, 480, 0, 0, 3000, "tempStartMenu.png");
		sprites.add(Menu);
		Sprite button1 = new Sprite(80, 280, 160, 80, 0, 0, 3001, "tempStartButton.png");
		if(highlighted ==0){
			if(select){
				button1 = new Sprite(80, 280, 160, 80, 0, 0, 3001, "tempStartButtonPressed.png");
			}else{
				button1 = new Sprite(80, 280, 160, 80, 0, 0, 3001, "tempStartButtonHighlighted.png");
			}
		}		
		sprites.add(button1);
		Sprite button2 = new Sprite(400, 280, 160, 80, 0, 0, 3001, "tempQuitButton.png");
		if(highlighted ==1){
			if(select){
				button2 = new Sprite(400, 280, 160, 80, 0, 0, 3001, "tempQuitButtonPressed.png");
			}else{
				button2 = new Sprite(400, 280, 160, 80, 0, 0, 3001, "tempQuitButtonHighlighted.png");
			}
		}		
		sprites.add(button2);



	}

	public void update(){
		if((c.getShoot().getX() >=50 || c.getShoot().getX() <= -50) && move == false){
			int updown = 0;
			if(c.getShoot().getX()>=50)
				updown = 1;
			if(c.getShoot().getX()<=-50)
				updown= -1;
			move = true;

			highlighted = (highlighted + updown)%2;
			if (highlighted < 0)
				highlighted = -highlighted;
		}
		else if(c.getShoot().getX() == 0 && move == true){
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
				System.exit(0);
		}


	}


}
