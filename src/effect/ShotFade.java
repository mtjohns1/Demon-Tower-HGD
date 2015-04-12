package effect;
import java.util.List;

import mobile.Mobile;
import sprite.Sprite;
import utility.Direction;
import world.Tile;
import bullet.Grapple;


public class ShotFade extends Effects {
	
	private Mobile _owner;
	
	public ShotFade(Mobile owner, int x, int y, String dir) {
		super(owner.getHome());
		
		//base dimensions
		setW(10);
		setH(10);
		setD(32);
		
		setFly(true); //ignore tiles
		
		//accept position and velocity
		setX(x);
		setY(y);
		setVx(0);
		setVy(0);
		
		setDir(dir);
		
		//associated sprites
		setSpriteSheet("light_bullet.png");
		setSpriteW(32);
		setSpriteH(32);
		setSpriteDir(true);
		
		//track owner / return target
		_owner = owner;
	}

	@Override
	public void update() {
		setZ(_owner.getZ());
		//set direction
		Direction recall = new Direction(_owner.getX()-getX(), _owner.getY()-getY());
		//apply direction
		setVx(getVx()+recall.getX()*23);
		setVy(getVy()+recall.getY()*23);
		accelerate(0.6); //regulate speed
		//vanish up close, it's done reeling in
		if (recall.getLength() < 32)
		{
			setDead();
		}
	}

	@Override
	public void tileCollision(Tile t, String dir) {
		//won't be called, do nothing
		return;
	}
}
