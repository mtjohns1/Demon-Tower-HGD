/**
 * Experimental grappling hook weapon
 * 
 * @author Jacob Charles
 */

public class WeaponGrapple extends Weapon {

	/**
	 * Public constructor
	 */
	public WeaponGrapple() {
		super(0, 50, "Grappling Hook", 1);
	}
	
	@Override
	public void fire(Actor a, int xAxis, int yAxis) {
		_spend(a);
		new BulletGrapple(a, xAxis, yAxis);
	}
}
