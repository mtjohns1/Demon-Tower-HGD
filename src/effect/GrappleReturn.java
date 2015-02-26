package effect;
import java.util.List;

import mobile.Mobile;
import sprite.Sprite;
import utility.Direction;
import world.Tile;
import bullet.Grapple;


public class GrappleReturn extends Effects {
	
	private Mobile _owner;
	
	public GrappleReturn(Mobile owner, int x, int y, double vx, double vy) {
		super(owner.getHome());
		
		//base dimensions
		setW(10);
		setH(10);
		setD(32);
		
		setFly(true); //ignore tiles
		
		//accept position and velocity
		setX(x);
		setY(y);
		setVx(vx);
		setVy(vy);
		
		//track owner / return target
		_owner = owner;
	}

	@Override
	public void update() {
		setZ(_owner.getZ());
		//set direction
		Direction recall = new Direction(_owner.getX()-getX(), _owner.getY()-getY());
		//apply direction
		setVx(getVx()+recall.getX()*22);
		setVy(getVy()+recall.getY()*22);
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
	
	@Override
	public void draw(List<Sprite> list)
	{
		//bullet body
		Sprite s = new Sprite(getLeft(), getTop(), getW(), getH(), 0, 0, calculateLayer(), "tempWall");
		list.add(s);
		
		//chain bits
		for (int i = 1; i < Grapple.LINKS; i++) {			
			//calculate the chain
			int x = ( getX()*i + _owner.getX()*(Grapple.LINKS-i) )/Grapple.LINKS;
			int y = ( getY()*i + _owner.getY()*(Grapple.LINKS-i) )/Grapple.LINKS;
			int z = ( getZ()*i + _owner.getZ()*(Grapple.LINKS-i) )/Grapple.LINKS;
			
			//drawing position and layer
			int top = y-(getH()/2)+2;
			int left = x-(getW()/2)+2;
			double layer = Mobile.calculateLayer(y, z, getH()-4, getD());
			
			s = new Sprite(left, top-z, getW()-4, getH()-4, 0, 0, layer, "tempWall");
			list.add(s);
		}
	}
}
