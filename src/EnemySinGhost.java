import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * This enemy chases player using shortest path, regardless of obsticals
 * @author Matthew_J
 *
 */
public class EnemySinGhost extends Enemy{

	private Player player;
	private int attack;
	private int xCounter, yCounter =0;
	public EnemySinGhost(Room home) {
		super(home);
		
		this.setX(300);
		this.setY(300);
		
		attack = 5;

		// TODO Auto-generated constructor stub
	}

	/**
	 * AI for enemy behavior. Chases player using shortest path, regardless of obsticals
	 * @param player
	 */
	public void enemyAI(){

		if(player == null){
			Mobile temp = this.getHome().getList().get(0);
			if(temp != null){
				player = (Player) this.getHome().getList().get(0);
			}
			else return;
		}
		
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
			yMove = 5 * ySign;
		}
		else if (yDif == 0){
			xMove =5 * xSign;
		}
		else if (Math.abs(xDif) > Math.abs(yDif)){
			xMove = 4 * xSign;
			yMove = 2 * ySign;
		}
		else if (Math.abs(xDif) < Math.abs(yDif)){
			yMove = 4 * ySign;
			xMove = 2 * xSign;
		}
		else {
			yMove =3 * ySign;
			xMove =3 * xSign;
		}
		
		//apply acceleration
		setVx(getVx()+xMove);
		setVy(getVy()+yMove);
		
		//apply deceleration
		setVx(getVx()/2);
		setVy(getVy()/2);

	}
	

	/**
	 * Manage collisions with a tile
	 * 
	 * @param t: the Tile object collided with
	 * @param dir: the direction of the collision
	 */	
	public void tileCollision(Tile t, String dir) {
		if (t.getType().contains("w")) {
			
		}
		
	}
	
	/**
	 * call on each frame to update.
	 */
	public void update(){
		enemyAI();
		move();
	}
	
	/**
	 * Generate the player's sprite for this frame
	 * 
	 * @param list: the list of sprites to add to
	 */
	public void  draw(List<Sprite> list)
	{
		Sprite s = new Sprite(getLeft()-2, getTop()-2, getW()+4, getH()+4, 0, 0, 0, "enemy");
		list.add(s);
	}
	
	
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) )
			return;
		
		//cast for convenience
		Actor a = (Actor)m;
		//do damage
		if(a instanceof Player && !(overlap))
			a.takeDamage(attack);
		if((a instanceof Enemy)){
			
		}
	}
}