/**
 * basic bullet, travels straight stops at walls and does basic damage.
 * @author Matthew_J
 *
 */
public class BulletBasic extends Bullet{

	public BulletBasic(Room home,Mobile shooter) {
		super(home,shooter);

		setW(10);
		setH(10);
		setD(32);
	}

	

	/**
	 * Manage collisions with a tile
	 * 
	 * @param t: the Tile object collided with
	 * @param dir: the direction of the collision
	 */
	public void tileCollision(Tile t, String dir) {
		if (t.getType().contains("w")) {
			setHealth(0);
		}
	}
	
	public void actorCollision(Actor a){
	
		//if (a.getClass().isInstance(player)){ player.setHealth(player.getHealth() -2)}
	
	}
	
	/**
	 * Function to determine how long the bullet lasts.
	 * call on each frame
	 */
	public void lifeSpan(){
		setHealth(getHealth() -1); 
	}
	
}
