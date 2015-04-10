package bullet;
import mobile.Mobile;
import mobile.Player;
import world.Tile;

/**
 * Fire Breath, splits on wall, low damage.
 * @author Matthew_J
 *
 */
public class Tracker extends Bullet{


	Mobile _owner;
	/**
	 * Standard public constructor
	 *  
	 * @param owner the object creating the bullet
	 * @param xAxis the initial x velocity of the bullet
	 * @param yAxis the initial y velocity of the bullet
	 */
	public Tracker(Mobile owner, int xAxis, int yAxis) {
		super(owner, xAxis, yAxis, 16);
		_owner =owner;
		//set default dimensions
		setW(10);
		setH(10);
		setD(32);

		//no range limit, for now
		super.setLife(500);
		setDamage(1); //deals 1 damage
		accelerate(4); //max speed of 3 in each direction
	}

	/**
	 * Logic for movement, called to set vx and vy and create explosive step
	 */
	public void mover(){
		Player player = this.getHome().getPlayer();
		if(player == null){
			player = this.getHome().getPlayer();
			if(player == null){
				return;
			}

		}
		double speed =2.75;
		//finds how far enemy is from player
		double xDif = this.getX() - player.getX();
		double yDif = this.getY() - player.getY();
		double total = Math.sqrt((double)(yDif*yDif+xDif*xDif));

		//finds direction of x and y
		double xMove = xDif*-speed;
		double yMove = yDif*-speed;
		if(total ==0){
			setVx(getVx()+1);
			setVy(getVy()+1);

		}

		else{
			xMove = xMove/total;
			yMove = yMove/total;
			setVx(getVx()+xMove);
			setVy(getVy()+yMove);
		}

		//apply deceleration
		setVx(getVx()/2);
		setVy(getVy()/2);
		

	}
	@Override
	public void update() {
		this.mover();
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
