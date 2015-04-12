package powerup;
/**
 * A weapon drop powerup from bosses
 * 
 * @author Jacob Charles
 */

import java.util.List;

import mobile.Player;
import sprite.Sprite;
import weapon.Weapon;
import world.Room;

public class BossWeapon extends Powerup {

	private Weapon _wep;
	
	public BossWeapon(Room home, int x, int y, Weapon weapon) {
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
	public void getCollected(Player p) {
		p.addWeapon(_wep); //new weapon
		
		p.setMaxHp(p.getMaxHp()+2); //heart container
		p.heal(Integer.MAX_VALUE);
		
		setDead(); //vanish
	}
	
	@Override
	public void draw(List<Sprite> l) {
		//TODO: Show boss soul icon, rather than sword icon
		int swordIcon = _wep.getIcon();
		Sprite s = new Sprite(getLeft(), getTop(), getW(), getH(), swordIcon*32, 0, calculateLayer(), "hero sword.png");
		l.add(s);
	}
}
