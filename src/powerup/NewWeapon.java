package powerup;
/**
 * A weapon drop powerup from bosses
 * 
 * @author Jacob Charles
 */

import game.Game;

import java.util.List;

import menu.GetGrapple;
import mobile.Player;
import sprite.Sprite;
import weapon.*;
import world.Room;

public class NewWeapon extends Powerup {

	private Weapon _wep;
	
	public NewWeapon(Room home, int x, int y, Weapon weapon) {
		super(home, x, y);
		setW(32);
		setH(32);
		setD(32);
		_wep = weapon;
	}
	
	public NewWeapon(Room home, int x, int y) {
		super(home, x, y);
		setW(32);
		setH(32);
		setD(32);
		_wep = new GrapplingHook();
	}
	
	/**
	 * Apply collection effect
	 * 
	 * @param p the player doing the collecting
	 */
	public void getCollected(Player p) {
		p.addWeapon(_wep);
		setDead(); //vanish
		//TODO: Acknowledge collection
		Game g = getHome().getGame();
		g.addMenu(new GetGrapple(g.getControls(), g));
	}
	
	@Override
	public void draw(List<Sprite> l) {
		int swordIcon = _wep.getIcon();
		Sprite s = new Sprite(getLeft(), getTop(), getW(), getH(), swordIcon*32, 0, calculateLayer()+8, "hero sword.png");
		l.add(s);
	}
}
