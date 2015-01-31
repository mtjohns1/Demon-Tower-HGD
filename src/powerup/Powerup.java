package powerup;
import mobile.Mobile;
import mobile.Player;
import world.Room;
import world.Tile;

/**
 * A generic powerup implementation
 * 
 * @author Jacob Charles
 */

public abstract class Powerup extends Mobile {
	
	public Powerup(Room home, int x, int y) {
		super(home);
		setX(x);
		setY(y);
	}
	
	/**
	 * Apply collection effect
	 * 
	 * @param p the player doing the collecting
	 */
	public abstract void getCollected(Player p);

	@Override
	public void update() {
		// Blank
	}

	@Override
	public void tileCollision(Tile t, String dir) {
		// Blank
	}
}
