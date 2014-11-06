/**
 * basic bullet, travels straight stops at walls and does basic damage.
 * @author Matthew_J
 * @author Jacob Charles
 *
 */
public class BulletBasicMelee extends Bullet{

	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BulletBasicMelee(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 24);
		
		//set dimensions
		setW(28);
		setH(28);
		setD(32);
		
		//Linger for just a few frames
		setLife(1);
		
		setDamage(5); //deals 5 damage
		accelerate(0); //doesn't move
	}
	
	@Override
	public void tileCollision(Tile t, String dir) {
		//Do nothing, ignore walls/etc
	}

	@Override
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) || m == getOwner())
			return;
		//cast for convenience
		Actor a = (Actor)m;
		//do damage
		a.takeDamage(getDamage());
		//TODO: Generate damage object instead
		
		//doesn't vanish!
	}
}
