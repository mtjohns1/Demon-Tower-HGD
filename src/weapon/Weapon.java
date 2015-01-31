package weapon;
import mobile.Actor;

/**
 * A base class for managing the player's different weapons via object
 * 
 * @author Jacob Charles
 */

public abstract class Weapon {
	private int _rate;
	private int _icon;
	private String _name;
	
	/**
	 * Basic constructor others feed from
	 * 
	 * @param c the MP cost of firing a shot
	 * @param r the number of frames between shots
	 */
	public Weapon(int r, String name, int i) {
		_rate = r;
		_name = name;
		_icon = i;
	}
	
	/**
	 * Deduct the weapon's firing cost from the weapon user's stats
	 * 
	 * @param p Player using the weapon
	 */
	protected void _spend(Actor a) {
		a.setFireDelay(_rate);
	}
	
	/**
	 * Fire in a direction
	 * 
	 * @param p the player wielding the weapon
	 * @param xAxis the x direction of fire
	 * @param yAxis the y direction of fire
	 */
	public abstract void fire(Actor a, int xAxis, int yAxis);
	
	/**
	 * @return the weapon's name
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * @return the sword icon for the weapon
	 */
	public int getIcon() {
		return _icon;
	}
}
