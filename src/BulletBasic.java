/**
 * basic bullet, travels straight stops at walls and does basic damage.
 * @author Matthew_J
 * @author Jacob Charles
 *
 */
public class BulletBasic extends Bullet{

	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BulletBasic(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis);
		
		//set default dimensions
		setW(10);
		setH(10);
		setD(32);
		
		//no range limit, for now
		
		setDamage(1); //deals 1 damage
		accelerate(3.75); //max speed of 3 in each direction
	}
}
