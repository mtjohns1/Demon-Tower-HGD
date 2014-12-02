/**
 * Fireball, explotion.
 * @author Matthew_J
 *
 */
public class BulletExplosion extends Bullet{
	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BulletExplosion(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 16);
		
		//set default dimensions
		setW(42);
		setH(42);
		setD(32);
		this.setX(this.getX()-this.getW()/2);
		this.setY(this.getY()-this.getH()/2);
		//no range limit, for now
		//Sprite temp = new Sprite()
		
		setDamage(1); //deals 1 damage
		accelerate(4); //max speed of 3 in each direction
	}
	public void update(){
		//setLife(this.getLife()-1);
	}
	public void tileCollision(Tile t, String dir) {
		setLife(0);
	}
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) || m == getOwner())
			return;

		//cast for convenience
		Actor a = (Actor)m;
		
		
		//do damage
		a.takeDamage(new Damage(getDamage(), getKnockbackX(), getKnockbackY(), getStun()));

		//vanish
		setLife(0);
	}
}
