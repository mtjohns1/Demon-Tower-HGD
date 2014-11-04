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
		super(owner, xAxis, yAxis);
		
		//set default dimensions
		setW(15);
		setH(15);
		setD(32);
		
		for (int i = 0; i < 10; i++)
		{xMove(); yMove();}
		
		//Linger for just a few frames
		setLife(4);
		
		setDamage(5); //deals 5 damage
		accelerate(0); //doesn't move
	}
}
