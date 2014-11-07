/**
 * basic bullet melee bullet, doesn't move and vanishes after a single frame
 * @author Jacob Charles
 *
 */
public class BulletBasicMelee extends BulletMelee{

	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BulletBasicMelee(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 16);
		
		//set dimensions
		setW(28);
		setH(28);
		setD(32);
		
		//get direction again
		Direction dir = new Direction(xAxis, yAxis);
		setDamage(2); //deals slightly boosted damage
		setKnockback(dir.getX()*5, dir.getY()*5); //force back a lot
	}
}
