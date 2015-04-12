package effect;

import mobile.Mobile;
import world.Tile;


public class Freezeframe extends Effects {
	
	private int _life;
	
	public Freezeframe(Mobile owner, int life) {
		super(owner.getHome());
		
		_life = life;
		
		//base dimensions
		setW(owner.getW());
		setH(owner.getH());
		setD(owner.getD());
		
		setFly(true); //ignore tiles
		
		//accept position and velocity
		setX(owner.getX());
		setY(owner.getY());
		setZ(owner.getZ());
		setVx(0);
		setVy(0);
		
		//associated sprites
		setSpriteSheet(owner.getSpriteSheet());
		setSpriteW(owner.getSpriteW());
		setSpriteH(owner.getSpriteH());
		setSpriteX(owner.getSpriteX());
		setSpriteY(owner.getSpriteY());
		setSpriteDir(owner.getSpriteDir());
		
		setFrame(owner.getFrame());
		setAnim(owner.getAnim());
		setDir(owner.getDir());
	}

	@Override
	public void update() {
		if (getTicks() > _life) {
			setDead();
		}
	}

	@Override
	public void tileCollision(Tile t, String dir) {
		//won't be called, do nothing
		return;
	}
}
