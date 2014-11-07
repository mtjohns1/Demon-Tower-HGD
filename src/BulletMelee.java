/**
 * default melee bullet, doesn't move and vanishes after a single frame
 * @author Jacob Charles
 *
 */
public class BulletMelee extends Bullet{

	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BulletMelee(Mobile owner, int xAxis, int yAxis, int offset) {
		super(owner, xAxis, yAxis, offset);
		
		setLife(1); //vanish after one frame
		accelerate(0); //don't move
	}
	
	@Override
	public void tileCollision(Tile t, String dir) {
		//Doesn't react to tile collisions
	}

	@Override
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) || m == getOwner())
			return;
		//cast for convenience
		Actor a = (Actor)m;
		//do damage
		a.takeDamage(new Damage(getDamage(), getKnockbackX(), getKnockbackY(), getStun()));
	}
}
