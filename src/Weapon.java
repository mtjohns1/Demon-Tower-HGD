/**
 * A base class for managing different weapons via object
 * 
 * @author Jacob Charles
 */

public abstract class Weapon {
	private int _cost;
	private int _rate;
	private String _name;
	
	/**
	 * Basic constructor others feed from
	 * 
	 * @param c the MP cost of firing a shot
	 * @param r the number of frames between shots
	 */
	public Weapon(int c, int r, String name) {
		_cost = c;
		_rate = r;
		_name = name;
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
	
	/**
	 * Fire in a direction
	 * 
	 * @param p the player wielding the weapon
	 * @param xAxis the x direction of fire
	 * @param yAxis the y direction of fire
	 */
	public abstract void fire(Player p, int xAxis, int yAxis);
	
	/**
	 * @return the weapon's name
	 */
	public String getName() {
		return _name;
	}
}
