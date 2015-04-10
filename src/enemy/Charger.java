package enemy;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import mobile.Actor;
import mobile.Mobile;
import mobile.Player;
import sprite.Sprite;
import world.Room;
import world.Tile;
import bullet.Bullet;
import bullet.BasicMelee;

/**
 * This enemy chases player using shortest path, regardless of obsticals
 * @author Matthew_J
 *
 */
public class Charger extends Enemy{

	private Player player;
	private Bullet attack;
	private int speed;
	private int randomMove=0;
	private double xDir, yDir;
	public Charger(Room home, int x, int y) {
		super(home,4);
		this.setMaxHp(20);

		this.setHp(2);
		this.setX(x);
		this.setY(y);

		speed=3;
	}

	/**
	 * AI
	 * @param player
	 */
	public void enemyAI(){

		if(player == null){
			player = this.getHome().getPlayer();
			if(player == null){
				return;
			}
		}
		double xDif = this.getX() - player.getX();
		double yDif = this.getY() - player.getY();
		double total = Math.sqrt((double)(yDif*yDif+xDif*xDif));
		
		if (total < 150)
			charge();
		else{
			randomMove+=1;
			if(randomMove ==0){
				double xisNeg =Math.random();
				double yisNeg =Math.random();
				int xNeg =1;
				int yNeg =1;
				if( xisNeg < .4){
					xNeg =-1;
				}
				if( yisNeg < .4){
					yNeg =-1;
				}
				xDir = (Math.random()*speed-2*xNeg);
				yDir = (Math.random()*speed-2*yNeg);
			}
			if(randomMove==50){
				randomMove=-1;
			}
			setVx(getVx()+xDir);
			setVy(getVy()+yDir);


			//apply deceleration
			setVx(getVx()/2);
			setVy(getVy()/2);
		}
		if(getRight() > getHome().getRight()){
			setVx(-1);
		}
		else if(getLeft() < getHome().getLeft()){
			setVx(1);
		}
		else if(getTop() < getHome().getTop()){
			setVy(1);
		}
		else if(getBottom() > this.getHome().getBottom()){
			setVy(-1);
		}
	}

	/**
	 * AI for charging player;
	 */
	public void charge(){

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
	/**
	 * Manage collisions with a tile
	 * 
	 * @param t: the Tile object collided with
	 * @param dir: the direction of the collision
	 */	
	public void tileCollision(Tile t, String dir) {
		if (t.getType().contains("w") || t.getType().contains("p")) {
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
		if(this.getHp() <= 0){
			this.setDead();
		}

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
			attack = new BasicMelee(this, (int)getVx(),(int)getVy());
		setVx(-getVx());
		setVy(-getVy());
		if((a instanceof Enemy)){

		}
	}

}
