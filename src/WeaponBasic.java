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
		super(23, 8, "Test Weapon");
	}
	
	@Override
	public void fire(Player p, int xAxis, int yAxis) {
		_spend(p);
		Room r = p.getHome();
		r.addMobile(new BulletBasicMelee(p, xAxis, yAxis));
		r.addMobile(new BulletBasic(p, xAxis, yAxis));
	}
}
