/**
 * A weapon drop powerup from bosses
 * 
 * @author Jacob Charles
 */

import java.util.List;

public class PowerupWeapon extends Powerup {

	private Weapon _wep;
	
	public PowerupWeapon(Room home, int x, int y, Weapon weapon) {
		super(home, x, y);
		setW(32);
		setH(32);
		setD(32);
		_wep = weapon;
	}
	
	/**
	 * Apply collection effect
	 * 
	 * @param p the player doing the collecting
	 */
	void getCollected(Player p) {
		p.addWeapon(_wep);
		setDead(); //vanish
	}
	
	@Override
	public void draw(List<Sprite> l) {
		int swordIcon = _wep.getIcon();
		Sprite s = new Sprite(getLeft(), getTop(), getW(), getH(), swordIcon*32, 0, calculateLayer(), "swords");
		l.add(s);
	}
}
