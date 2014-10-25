import java.awt.Image;
import java.util.List;

/**
 * This enemy chases player using shortest path, regardless of obsticals
 * @author Matthew_J
 *
 */
public class EnemyChaser extends Enemy{

	private Player player;
	
	public EnemyChaser(Room home) {
		super(home);
		// TODO Auto-generated constructor stub
	}

	/**
	 * AI for enemy behavior. Chases player using shortest path, regardless of obsticals
	 * @param player
	 */
	public void enemyAI(Player player){
		//finds how for enemy is from player
		int xDif = this.getX() - player.getX();
		int yDif = this.getY() - player.getY();
		
		int yMove = 0;
		int xMove = 0;
		
		//finds direction of x and y
		int xSign =1;
		int ySign =1;
		if (xDif > 0) xSign =-1;
		if (yDif > 0) ySign =-1;
		
		//logic for how to move
		if(xDif == 0){
			yMove = 5;
		}
		else if (yDif == 0){
			xMove =5;
		}
		else if (Math.abs(xDif) > Math.abs(yDif)){
			xMove = 4 * xSign;
			yMove = 1 * ySign;
		}
		else if (Math.abs(xDif) < Math.abs(yDif)){
			yMove = 4 * xSign;
			xMove = 1 * ySign;
		}
		else {
			yMove =3;
			xMove =2;
		}
		
		//apply acceleration
		setVx(getVx()+xMove);
		setVy(getVy()+yMove);
		
		//apply deceleration
		setVx(getVx()/2);
		setVy(getVy()/2);

	}
	
	/**
	 * call on each frame to update.
	 */
	public void update(){
		//enemyAI(getHome().getPlayer());
		move();
	}
	
	/**
	 * Generate the player's sprite for this frame
	 * 
	 * @param list: the list of sprites to add to
	 */
	public void  draw(List<Sprite> list)
	{
		Sprite s = new Sprite(getLeft()-2, getTop()-2, getW()+4, getH()+4, 0, 0, 0, 2);
		list.add(s);
	}
	
}
