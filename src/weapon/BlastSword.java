package weapon;
import mobile.Actor;
import bullet.*;

/**
 * A heavy, pure-melee sword
 * 
 * @author Jacob Charles
 */

public class BlastSword extends Weapon {

	/**
	 * Public constructor
	 */
	public BlastSword() {
		super(70, "Blast Sword", 2);
	}
	
	@Override
	public void fire(Actor a, int xAxis, int yAxis) {
		_spend(a);
		new BlastMelee(a, xAxis, yAxis);
		a.stun(10);
	}
}
