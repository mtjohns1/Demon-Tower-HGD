package bullet;
import utility.Direction;
import mobile.Mobile;

/**
 * basic melee bullet, minor knockback and boosted damage
 * @author Jacob Charles
 *
 */
public class BasicMelee extends Melee{

	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BasicMelee(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 16);
		
		//set dimensions
		setW(28);
		setH(28);
		setD(32);
		
		//get direction again
		Direction dir = new Direction(xAxis, yAxis);
		setDamage(2); //deals slightly boosted damage
		setKnockback(dir.getX()*24, dir.getY()*24); //force back a lot
	}
}
