package powerup;
/**
 * A heart drop powerup from enemies
 * 
 * @author Jacob Charles
 */

import java.util.List;

import mobile.Player;
import sprite.Sprite;
import world.Room;

public class Heart extends Powerup {
	
	public Heart(Room home, int x, int y) {
		super(home, x, y);
		setW(16);
		setH(16);
		setD(32);
	}
	
	/**
	 * Apply collection effect
	 * 
	 * @param p the player doing the collecting
	 */
	public void getCollected(Player p) {
		p.heal(2); //TODO: make variable amount?
		setDead(); //vanish
	}
	
	@Override
	public void draw(List<Sprite> l) {
		Sprite s = new Sprite(getLeft()-8, getTop()-8, getW()+16, getH()+16, 0, 0, calculateLayer(), "hearts.png");
		l.add(s);
	}
}
