package weapon;
import mobile.Actor;
import bullet.BasicBullet;
import bullet.BasicMelee;

/**
 * A generic implementation of a weapon
 * 
 * @author Jacob Charles
 */

public class HeroSword extends Weapon {

	/**
	 * Public constructor
	 */
	public HeroSword() {
		super(16, "Test Weapon", 0);
	}
	
	@Override
	public void fire(Actor a, int xAxis, int yAxis) {
		_spend(a);
		new BasicMelee(a, xAxis, yAxis);
		new BasicBullet(a, xAxis, yAxis);
	}
}
