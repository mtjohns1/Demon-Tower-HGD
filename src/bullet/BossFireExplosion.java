package bullet;
import java.util.List;

import mobile.Actor;
import mobile.Mobile;
import mobile.Player;
import sprite.Sprite;
import utility.Damage;
import utility.Direction;
import world.Tile;

/**
 * Fireball, explotion.
 * @author Matthew_J
 *
 */
public class BossFireExplosion extends Bullet{
	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BossFireExplosion(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 16);
		
		//set default dimensions

		setW(8);
		setH(8);
		setD(32);
		
		setSpriteSheet("superbreath.png");
		setSpriteW(64);
		setSpriteH(64);
		setSpriteY(8);
		
		this.setX(owner.getX()+120);
		this.setY(owner.getY()+32);
		
		//no range limit, for now
		//Sprite temp = new Sprite()
		setLife(96/2);
		setVx(0);
		setVy(0);
		setDamage(2); //deals 1 damage
		accelerate(4); //max speed of 3 in each direction
		this.setOwner(owner);
	}
	public void update(){
		setFrame((getTicks()/5)%4);
		setW(getW()+(getTicks()/5)%4+1);
		setH(getH()+(getTicks()/5)%4+1);
		setLife(this.getLife()-1);
	}
	public void tileCollision(Tile t, String dir) {
		//setLife(0);
	}
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) || m == getOwner())
			return;

		//cast for convenience
		Actor a = (Actor)m;
		
		
		//do damage
		if (a instanceof Player){
			Direction push = new Direction(a.getX()-getX(), a.getY()-getY());
			a.takeDamage(new Damage(getDamage(), push.getX()*28,push.getY()*28, getStun()));
		}
		//vanish
		//setLife(0);
	}
}
