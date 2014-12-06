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
	public void fire(Player p, int xAxis, int yAxis) {
		_spend(p);
		new BulletBasicMelee(p, xAxis, yAxis);
		new BulletBasic(p, xAxis, yAxis);
	}
}
