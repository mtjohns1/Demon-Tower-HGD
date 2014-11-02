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
public class EnemyChaser extends Enemy{

	private Player player;

	private Image enemyImage;
	
	public EnemyChaser(Room home, Player playerPassed) {
		super(home);
		
		this.setX(100);
		this.setY(100);
		
		player= playerPassed;
		
		File temp = new File("src/generic_Evil_creature_small.jpg");

		try {
			enemyImage =  ImageIO.read(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated constructor stub
	}

	/**
	 * AI for enemy behavior. Chases player using shortest path, regardless of obsticals
	 * @param player
	 */
	public void enemyAI(){
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
			yMove = 6 * ySign;
		}
		else if (yDif == 0){
			xMove =6 * xSign;
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
			if (dir.equals("right")) {
				setRight(t.getLeft()-1);
				setVx(0);
			}
			if (dir.equals("left")) {
				setLeft(t.getRight()+1);
				setVx(0);
			}
			if (dir.equals("down")) {
				setBottom(t.getTop()-1);
				setVy(0);
			}
			if (dir.equals("up")) {
				setTop(t.getBottom()+1);
				setVy(0);
			}
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
	
}
