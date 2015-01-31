package bullet;
import mobile.Mobile;

/**
 * Fireball, explodes.
 * @author Matthew_J
 *
 */
public class BossFireBall extends Bullet{
	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BossFireBall(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 16);
		
		//set default dimensions
		setW(10);
		setH(10);
		setD(32);
		
		//no range limit, for now
		
		setDamage(4); //deals 1 damage
		accelerate(2); //max speed of 3 in each direction
	}

	public void onDeath() {
		BossFireExplosion temp = new BossFireExplosion(this.getOwner(),(int)-this.getVx(),(int)-this.getVy());
		temp.setX(this.getX());
		temp.setY(this.getY());
	}
}
