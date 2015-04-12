package effect;
import java.util.List;

import mobile.Mobile;
import sprite.Sprite;
import utility.Direction;
import world.Tile;
import bullet.Grapple;


public class BlueFlamepuff extends Effects {
	
	public BlueFlamepuff(Mobile owner, int x, int y) {
		super(owner.getHome());
		
		//base dimensions
		setW(10);
		setH(10);
		setD(32);
		
		setFly(true); //ignore tiles
		
		//accept position and velocity
		setX(x);
		setY(y);
		setZ(0);
		setVx(0);
		setVy(0);
		
		//associated sprites
		setSpriteSheet("blue_flame_puff.png");
		setSpriteW(48);
		setSpriteH(48);
		setSpriteDir(false);
	}

	@Override
	public void update() {
		//advance the frames of its animation, fade out
		setZ(getZ()+1);
		setFrame(1+getTicks()/3);
		if (getTicks() >= 4*3) {
			setDead();
		}
	}

	@Override
	public void tileCollision(Tile t, String dir) {
		//won't be called, do nothing
		return;
	}
}
