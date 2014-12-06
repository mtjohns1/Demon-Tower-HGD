/**
 * Fire Breath, splits on wall, low damage.
 * @author Matthew_J
 *
 */
public class BulletFireBreath extends Bullet{


	Mobile _owner;
	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BulletFireBreath(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 16);
		_owner =owner;
		//set default dimensions
		setW(10);
		setH(10);
		setD(32);

		//no range limit, for now
		super.setLife(125);
		setDamage(1); //deals 1 damage
		accelerate(4); //max speed of 3 in each direction
	}


	@Override
	public void update() {
		super.setLife(super.getLife()-1);
	}
	@Override
	public void tileCollision(Tile t, String dir) {
		//break on walls
		if (t.getType().contains("w")) {
			if (dir.equals("left")){
				super.setVx(0);
			}
			else if(dir.equals("right")){
				super.setVx(0);
			}
			else if(dir.equals("up")){
				super.setVy(0);
			}
			else if(dir.equals("down")){
				super.setVy(0);
			}
		}
	}


}
