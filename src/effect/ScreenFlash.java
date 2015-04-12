package effect;
import java.util.List;

import mobile.Mobile;
import sprite.Sprite;
import sprite.TransparencySprite;
import utility.Direction;
import world.Tile;
import bullet.Grapple;


public class ScreenFlash extends Effects {
	
	private int in, hold, out;
	public ScreenFlash(Mobile owner, int in, int hold, int out) {
		super(owner.getHome());
		
		//remember fades
		this.in = in;
		this.hold = hold;
		this.out = out;
		
		//base dimensions
		setW(10);
		setH(10);
		setD(32);
		
		setFly(true); //ignore tiles
		
		//associated sprites
		setSpriteSheet("WhiteScreen.png");
	}

	@Override
	public void update() {
		//vanish when time's up
		if (getTicks() > in+hold+out) {
			setDead();
		}
	}

	@Override
	public void tileCollision(Tile t, String dir) {
		//won't be called, do nothing
		return;
	}
	
	@Override
	public void draw(List<Sprite> l) {
		double fade = 0.0;
		if (getTicks() < in) {
			fade = ((double)getTicks())/((double)in);
		}
		else if (getTicks() < in+hold) {
			fade = 1.0;
		}
		else {
			fade = 1.0-((double)getTicks()-(in+hold))/((double)out);
		}
		l.add(new TransparencySprite(0, 0, 640, 480, 0, 0, -0.05, getSpriteSheet(), fade));
	}
}
