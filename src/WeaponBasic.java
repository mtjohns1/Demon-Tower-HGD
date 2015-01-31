/**
 * A generic implementation of a weapon
 * 
 * @author Jacob Charles
 */

public class WeaponBasic extends Weapon {

	/**
	 * Public constructor
	 */
	public WeaponBasic() {
		super(0, 16, "Test Weapon", 0);
	}
	
	@Override
	public void fire(Actor a, int xAxis, int yAxis) {
		_spend(a);
		new BulletBasicMelee(a, xAxis, yAxis);
		new BulletBasic(a, xAxis, yAxis);
	}
}
