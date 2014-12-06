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
	public void fire(Player p, int xAxis, int yAxis) {
		_spend(p);
		new BulletGrapple(p, xAxis, yAxis);
	}
}
