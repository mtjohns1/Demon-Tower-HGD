/**
 * A base class for managing different weapons via object
 * 
 * @author Jacob Charles
 */

public abstract class Weapon {
	private int _cost;
	private int _rate;
	
	/**
	 * Basic constructor others feed from
	 * 
	 * @param c the MP cost of firing a shot
	 * @param r the number of frames between shots
	 */
	public Weapon(int c, int r) {
		_cost = c;
		_rate = r;
	}
	
	/**
	 * Deduct the weapon's firing cost from the weapon user's stats
	 * 
	 * @param p Player using the weapon
	 */
	protected void _spend(Player p) {
		p.setFireDelay(_rate);
		p.useStamina(_cost);
	}
	
	public abstract void fire(Room r, Player p, int xAxis, int yAxis);
}
