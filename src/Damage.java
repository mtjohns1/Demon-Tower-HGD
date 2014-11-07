/**
 * An object to pass detailed damage information
 * 
 * @author Jacob Charles
 */
public class Damage {
	private int _damage; //HP loss
	private double _fx, _fy; //knockback / force
	private int _stun; //hitstun
	//TODO: Figure out what all properties are needed, and how to organize?
	
	public Damage(int dmg) {
		_damage = dmg;
		_fx = 0;
		_fy = 0;
		_stun = 0;
	}
	
	public Damage(int dmg, double vx, double vy) {
		_damage = dmg;
		_fx = vx;
		_fy = vy;
		_stun = 0;
	}
	
	public Damage(int dmg, int stun) {
		_damage = dmg;
		_fx = 0;
		_fy = 0;
		_stun = stun;
	}
	
	public Damage(int dmg, double vx, double vy, int stun) {
		_damage = dmg;
		_fx = vx;
		_fy = vy;
		_stun = stun;
	}
	
	/**
	 * @return HP damage
	 */
	public int getDamage() {
		return _damage;
	}
	
	/**
	 * @return horizontal velocity (knockback)
	 */
	public double getVx() {
		return _fx;
	}
	
	/**
	 * @return vertical velocity (knockback)
	 */
	public double getVy() {
		return _fy;
	}
	
	/**
	 * @return hitstun (in frames)
	 */
	public int getStun() {
		return _stun;
	}
}
