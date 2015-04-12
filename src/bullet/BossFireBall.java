package bullet;
import utility.Damage;
import mobile.Actor;
import mobile.Mobile;
import mobile.Player;

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

		setW(30);
		setH(30);
		setD(32);
		
		setSpriteSheet("superbreath.png");
		setSpriteW(64);
		setSpriteH(64);
		setSpriteY(-6);
		setFrame(0);
		setAnim(0);
		
		this.setZ(0);
		
		//no range limit, for now

		setDamage(4); //deals 1 damage
		accelerate(4); //max speed of 3 in each direction
	}
	public void update(){
		setFrame((getTicks()/10)%3);
	}
	public void onDeath() {
		BossFireExplosion temp = new BossFireExplosion(this.getOwner(),(int)-this.getVx(),(int)-this.getVy());
		temp.setX(this.getX());
		temp.setY(this.getY());
	}

	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) || m == getOwner())
			return;

		//cast for convenience
		Actor a = (Actor)m;

		//do damage
		if (a instanceof Player){
			a.takeDamage(new Damage(getDamage(), getKnockbackX(), getKnockbackY(), getStun()));

			//vanish
			setLife(0);
		}
	}

}
