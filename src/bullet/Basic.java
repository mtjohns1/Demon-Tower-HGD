package bullet;
import mobile.Mobile;

/**
 * basic bullet, travels straight stops at walls and does basic damage.
 * @author Matthew_J
 * @author Jacob Charles
 *
 */
public class Basic extends Bullet{

	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public Basic(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 8);
		
		//set default dimensions
		setW(10);
		setH(10);
		setD(32);
		
		//no range limit, for now
		
		setDamage(1); //deals 1 damage
		accelerate(6); //max speed
	}
}
