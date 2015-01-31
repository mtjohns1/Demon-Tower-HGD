package bullet;
import java.util.List;

import mobile.Actor;
import mobile.Mobile;
import sprite.Sprite;
import utility.Damage;
import world.Tile;

/**
 * Fireball, explotion.
 * @author Matthew_J
 *
 */
public class BossFireExplosion extends Bullet{
	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BossFireExplosion(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 16);
		
		//set default dimensions
		setW(0);
		setH(0);
		setD(32);
		this.setX(owner.getX());
		this.setY(owner.getY());
		//no range limit, for now
		//Sprite temp = new Sprite()
		setLife(32);
		setVx(0);
		setVy(0);
		setDamage(2); //deals 1 damage
		accelerate(4); //max speed of 3 in each direction
	}
	public void update(){
		setW(getW()+8);
		setH(getH()+8);
		setLife(this.getLife()-1);
	}
	public void tileCollision(Tile t, String dir) {
		//setLife(0);
	}
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) || m == getOwner())
			return;

		//cast for convenience
		Actor a = (Actor)m;
		
		
		//do damage
		a.takeDamage(new Damage(getDamage(), getKnockbackX(), getKnockbackY(), getStun()));

		//vanish
		//setLife(0);
	}
	public void draw(List<Sprite> list)
	{
		Sprite s = new Sprite(getLeft(), getTop(), getW(), getH(), 0, 0, calculateLayer(), "explosion");
		list.add(s);
	}
}
