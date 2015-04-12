package bullet;
import utility.Damage;
import world.Tile;
import mobile.Actor;
import mobile.Mobile;
import mobile.Player;

/**
 * basic bullet, travels straight through walls and does basic damage.
 * @author Matthew_J]
 *
 */
public class TowerShot extends Bullet{
	int counter =0;
	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public TowerShot(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 8);
		
		//set default dimensions
		setW(10);
		setH(10);
		setD(32);
		
		setSpriteSheet("firebreath.png");
		setSpriteW(64);
		setSpriteH(64);
		
		//no range limit, for now
		
		setDamage(1); //deals 1 damage
		accelerate(6); //max speed
	}

	
	/**
	 * allows to go through wall
	 */
	public void tileCollision(Tile t, String dir) {
		
		
	}

	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) || m == getOwner())
			return;

		//cast for convenience
		Actor a = (Actor)m;

		//do damage

		if (a instanceof Player){
			a.takeDamage(new Damage(getDamage(), getKnockbackX(), getKnockbackY(), getStun()));

			//vanish
			setLife(0);

		}
	}
}
