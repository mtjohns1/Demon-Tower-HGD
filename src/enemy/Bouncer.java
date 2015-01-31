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
import bullet.BossFireExplosion;

/**
 * This enemy chases player using shortest path, regardless of obsticals
 * @author Matthew_J
 *
 */
public class Bouncer extends Enemy{

	private Player player;
	private int directionX =1, directionY =1;
	private int speed;

	public Bouncer(Room home) {
		super(home);
		this.setMaxHp(5);

		this.setHp(2);
		this.setX(500);
		this.setY(300);

		speed=3;
	
	}

	/**
	 * AI for enemy behavior. bounces around the room at a good speed.
	 * Blows up when it hits the player.
	 * @param player
	 */
	public void enemyAI(){
		
		//handles end of screen
		if(getRight() > getHome().getRight()){
			directionX=1;
		}
		else if(getLeft() < getHome().getLeft()){
			directionX=-1;
		}
		else if(getTop() < getHome().getTop()){
			directionY=1;
		}
		else if(getBottom() > this.getHome().getBottom()){
			directionY=-1;
		}
		
		//apply acceleration
		if (!isStunned()) {
			setVx(getVx()+speed*directionX);
			setVy(getVy()+speed*directionY);
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
		if (t.getType().contains("w")) {
			if (dir.equals("right")) {
				setRight(t.getLeft()-1);
				directionX=-1;
				setVx(0);
			}
			else if (dir.equals("left")) {
				setLeft(t.getRight()+1);
				directionX=1;
				setVx(0);
			}
			else if (dir.equals("down")) {
				setBottom(t.getTop()-1);
				directionY=-1;
				setVy(0);
				}
			else if (dir.equals("up")) {
				setTop(t.getBottom()+1);
				directionY=1;
				setVy(0);
			}
		}


	}
	
	public void onDeath(){
		BossFireExplosion temp = new BossFireExplosion(this,0,0);
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
		if(a instanceof Player && !(overlap)){
				this.setHp(0);
				BossFireExplosion temp = new BossFireExplosion(this,0,0);
				}
		if((a instanceof Enemy)){

		}
	}

}
