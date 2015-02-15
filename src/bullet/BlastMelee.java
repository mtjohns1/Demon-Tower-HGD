package bullet;
import java.util.List;

import sprite.Sprite;
import utility.Damage;
import utility.Direction;
import world.Tile;
import mobile.Actor;
import mobile.Mobile;

/**
 * basic melee bullet, minor knockback and boosted damage
 * @author Jacob Charles
 *
 */
public class BlastMelee extends Melee{

	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BlastMelee(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 8);
		
		//set dimensions
		setW(64);
		setH(64);
		setD(32);
		
		setLife(3);
		
		//get direction again
		Direction dir = new Direction(xAxis, yAxis);
		setDamage(4); //heavy damage
		setStun(50); //decent stun
		setBreaking(10); //annihilate breakable tiles
	}
	
	@Override
	public void update() {
		//damage all overlapping breakable tiles?
	}
	
	@Override
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) || m == getOwner())
			return;
		//cast for convenience
		Actor a = (Actor)m;
		//calculate knockback
		Direction push = new Direction(a.getX()-getX(), a.getY()-getY());
		//do damage
		a.takeDamage(new Damage(getDamage(), push.getX()*28, push.getY()*28, getStun()));
	}
	
	@Override
	public void draw(List<Sprite> list)
	{
		Sprite s = new Sprite(getLeft(), getTop(), getW(), getH(), 0, 0, calculateLayer(), "explosion");
		list.add(s);
	}
}
