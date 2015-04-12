package bullet;
import effect.ShotFade;
import mobile.Mobile;

/**
 * basic bullet, travels straight stops at walls and does basic damage.
 * @author Matthew_J
 * @author Jacob Charles
 *
 */
public class BasicBullet extends Bullet{

	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BasicBullet(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 8);
		
		//set default dimensions
		setW(10);
		setH(10);
		setD(60);
		
		setSpriteSheet("light_bullet.png");
		setSpriteW(32);
		setSpriteH(32);
		setSpriteDir(true);
		
		//no range limit, for now
		
		setDamage(1); //deals 1 damage
		accelerate(6); //max speed
	}
	
	public void onDeath() {
		new ShotFade(this);
	}
}
