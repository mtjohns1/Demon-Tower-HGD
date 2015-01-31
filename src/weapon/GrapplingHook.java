package weapon;
import mobile.Actor;
import bullet.Grapple;

/**
 * Experimental grappling hook weapon
 * 
 * @author Jacob Charles
 */

public class GrapplingHook extends Weapon {

	/**
	 * Public constructor
	 */
	public GrapplingHook() {
		super(50, "Grappling Hook", 1);
	}
	
	@Override
	public void fire(Actor a, int xAxis, int yAxis) {
		_spend(a);
		new Grapple(a, xAxis, yAxis);
	}
}
