/**
 * experimental grappling hook bullet
 * @author Jacob Charles
 *
 */
public class BulletGrapple extends Bullet{

	private boolean _reelIn; 
	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public BulletGrapple(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 8);

		//set default dimensions
		setW(10);
		setH(10);
		setD(32);

		//last just under two seconds
		setLife(30);

		setDamage(1); //deals 1 damage
		accelerate(20); //max speed
		setKnockback(-getVx()/5, -getVy()/5); //reverse knockback
		setStun(15); //stuns for 15 frames
		//not reeling yet
		_reelIn = false;
	}

	@Override
	public void update() {
		//time to pull the player in!
		if (_reelIn) {
			Mobile owner = getOwner();
			Direction dir = new Direction(getX()-owner.getX(), getY()-owner.getY());
			double reelSpeed = 6;
			owner.setVx(owner.getVx()+dir.getX()*reelSpeed);
			owner.setVy(owner.getVy()+dir.getY()*reelSpeed);
		}
	}

	@Override
	public void tileCollision(Tile t, String dir) {
		//break on walls
		if (t.getType().contains("w")) {
			setVx(0);
			setVy(0); //stop moving
			_reelIn = true;
			setLife(30); //set to reel in for just under 1 second
			//stick to the wall
			if (dir.equals("right")) setRight(t.getLeft()-1);
			if (dir.equals("left")) setLeft(t.getRight()+1);
			if (dir.equals("down")) setBottom(t.getTop()-1);
			if (dir.equals("up")) setTop(t.getBottom()+1);
		}
	}

	@Override
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor))
			return;

		//cast for convenience
		Actor a = (Actor)m;

		//do damage
		if (m != getOwner() && !_reelIn)
		{
			a.takeDamage(new Damage(getDamage(), getKnockbackX(), getKnockbackY(), getStun()));
		}
		//vanish
		if (m != getOwner() || _reelIn)
		{
			setLife(0);
		}
	}
}