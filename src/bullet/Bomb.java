package bullet;
import java.util.List;

import mobile.Actor;
import mobile.Mobile;
import sprite.Sprite;
import utility.Damage;
import world.Tile;

/**
 * Bomb, explotion.
 * @author Matthew_J
 *
 */
public class Bomb extends Bullet{
	private int summon=0;
	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public Bomb(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 16);

		//set default dimensions
		setW(32);
		setH(32);
		setD(32);
		this.setX(xAxis);
		this.setY(yAxis);
		//no range limit, for now
		//Sprite temp = new Sprite()
		setLife(90);
		setVx(0);
		setVy(0);
		setDamage(2); //deals 1 damage
		accelerate(4); //max speed of 3 in each direction
	}
	public Bomb(Mobile owner, int xAxis, int yAxis,int summon) {
		super(owner, xAxis, yAxis, 16);
		//set default dimensions
		setW(32);
		setH(32);
		setD(32);
		this.setX(xAxis);
		this.setY(yAxis);
		//no range limit, for now
		//Sprite temp = new Sprite()
		setLife(100);
		setVx(0);
		setVy(0);
		setDamage(2); //deals 1 damage
		accelerate(4); //max speed of 3 in each direction
	}
	/**
	 * Creats bomb, has no velocity.
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 * @param xOff x Offset from owner
	 * @param yOff y Offset from owner
	 */
	public Bomb(Mobile owner, int xAxis, int yAxis,int xOff, int yOff) {
		super(owner, xAxis, yAxis, 16);

		//set default dimensions
		setW(30);
		setH(32);
		setD(32);
		this.setX(owner.getX()+xOff);
		this.setY(owner.getY()+yOff);
		//no range limit, for now
		//Sprite temp = new Sprite()
		setLife(90);
		setVx(0);
		setVy(0);
		setDamage(2); //deals 1 damage
		accelerate(4); //max speed of 3 in each direction
	}
	public void onDeath() {
		BossFireExplosion temp = new BossFireExplosion(this.getOwner(),(int)-this.getVx(),(int)-this.getVy());
		temp.setX(this.getX());
		temp.setY(this.getY());
	}
	public void update(){
		setLife(this.getLife()-1);
	}
	public void tileCollision(Tile t, String dir) {
		//setLife(0);
	}
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) || m == getOwner())
			return;


		//vanish
		//setLife(0);
	}
	public void draw(List<Sprite> list)
	{
		Sprite s = new Sprite(getLeft(), getTop(), getW(), getH(), 0, 0, calculateLayer(), "bomb");
		list.add(s);
	}
}
