package effect;

import mobile.Mobile;
import world.Tile;


public class BlueExplosion extends Effects {
	
	private int _life, _per, _amp;
	
	public BlueExplosion(Mobile owner, int x, int y, int w, int h, int life, int per, int amp) {
		super(owner.getHome());
		
		_life = life;
		_per = per;
		_amp = amp;
		
		//base dimensions
		setW(w);
		setH(h);
		setD(32);
		
		setFly(true); //ignore tiles
		
		//accept position and velocity
		setX(x);
		setY(y);
		setZ(0);
	}

	@Override
	public void update() {
		//generate random flamepuffs
		if (getTicks() > _life) {
			setDead();
		}
		//only produce particles every freq frames
		if (getTicks() % _per != 0) return;
		
		for (int i = 0; i < _amp; i++) {
			int rx = (int) (Math.random()*getW());
			int ry = (int) (Math.random()*getW());
			new BlueFlamepuff(this, getLeft()+rx, getTop()+ry);
		}
	}

	@Override
	public void tileCollision(Tile t, String dir) {
		//won't be called, do nothing
		return;
	}
}
