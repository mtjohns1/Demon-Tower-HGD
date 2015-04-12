package effect;
import java.util.List;

import mobile.Mobile;
import sprite.Sprite;
import utility.Direction;
import world.Tile;
import bullet.Grapple;


public class ShotFade extends Effects {
	
	public ShotFade(Mobile owner) {
		super(owner.getHome());
		
		//base dimensions
		setW(10);
		setH(10);
		setD(32);
		
		setFly(true); //ignore tiles
		
		//accept position and velocity
		setX(owner.getX());
		setY(owner.getY());
		setZ(owner.getZ());
		setVx(0);
		setVy(0);
		
		setDir(owner.getDir());
		
		//associated sprites
		setSpriteSheet(owner.getSpriteSheet());
		setSpriteW(owner.getSpriteW());
		setSpriteH(owner.getSpriteH());
		setSpriteDir(owner.getSpriteDir());
	}

	@Override
	public void update() {
		
		//advance the frames of its animation, fade out
		setFrame(1+getTicks()/10);
		if (getTicks() >= 40) {
			setDead();
		}
	}

	@Override
	public void tileCollision(Tile t, String dir) {
		//won't be called, do nothing
		return;
	}
}
