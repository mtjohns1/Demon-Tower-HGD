package bullet;
import java.util.List;

import mobile.Actor;
import mobile.Mobile;
import sprite.Sprite;
import utility.Damage;
import utility.Direction;
import world.Tile;
import effect.EffectGrappleReturn;

/**
 * experimental grappling hook bullet
 * @author Jacob Charles
 *
 */
public class Grapple extends Bullet{

	//TODO: Make shot appear to return if it misses!
	public static final int LINKS = 8;
	private boolean _reelIn; 
	
	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public Grapple(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 8);

		//set default dimensions
		setW(10);
		setH(10);
		setD(32);

		//last just under one second
		setLife(20);

		setDamage(1);
		accelerate(22); //max speed
		setKnockback(getVx()/3, getVy()/3); //normal knockback
		setStun(25); //stuns for 15 frames
		//not reeling yet
		_reelIn = false;
	}

	@Override
	public void update() {
		//time to pull the player in!
		if (_reelIn) {
			Mobile owner = getOwner();
			Direction dir = new Direction(getX()-owner.getX(), getY()-owner.getY());
			double reelSpeed = 8.5;
			owner.setBack(1);
			owner.setVz(0.2);
			owner.setVx(owner.getVx()+dir.getX()*reelSpeed);
			owner.setVy(owner.getVy()+dir.getY()*reelSpeed);
		}
	}

	@Override
	public void tileCollision(Tile t, String dir) {
		//break on walls
		if (t.getType().contains("w")) {
			setVx(0);
			setVy(0); //stop moving
			_reelIn = true;
			setLife(30); //set to reel in for just under 1 second
			//stick to the wall
			if (dir.equals("right")) setRight(t.getLeft()-1);
			if (dir.equals("left")) setLeft(t.getRight()+1);
			if (dir.equals("down")) setBottom(t.getTop()-1);
			if (dir.equals("up")) setTop(t.getBottom()+1);
		}
	}

	@Override
	public void onDeath() {
		//spawn a grapple return effect
		new EffectGrappleReturn(getOwner(), getX(), getY(), getVx(), getVy());
	}
	
	@Override
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor))
			return;

		//cast for convenience
		Actor a = (Actor)m;

		//do damage
		if (m != getOwner() && !_reelIn)
		{
			a.takeDamage(new Damage(getDamage(), getKnockbackX(), getKnockbackY(), getStun()));
			accelerate(0.25);
		}
		//vanish
		if (m != getOwner() || _reelIn)
		{
			setLife(0);
		}
	}
	
	@Override
	public void draw(List<Sprite> list)
	{
		//bullet body
		super.draw(list);
		
		//chain bits
		for (int i = 1; i < LINKS; i++) {			
			//calculate the chain
			int x = ( getX()*i + getOwner().getX()*(LINKS-i) )/LINKS;
			int y = ( getY()*i + getOwner().getY()*(LINKS-i) )/LINKS;
			
			//drawing position and layer
			int top = y-(getH()/2)+2;
			int left = x-(getW()/2)+2;
			double layer = Mobile.calculateLayer(y, getZ(), getH()-4, getD());
			
			Sprite s = new Sprite(left, top, getW()-4, getH()-4, 0, 0, layer, "tempWall");
			list.add(s);
		}
	}
}
