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

	public Bouncer(Room home,int x, int y) {
		super(home,3);
		this.setMaxHp(5);

		this.setHp(2);
		this.setX(x);
		this.setY(y);
		setW(30);
		setH(30);
		this.setD(40);
		
		setSpriteSheet("fire_bomber.png");
		setSpriteW(48);
		setSpriteH(48);
		setSpriteY(-8);
		setAnim(0);
		setFrame(0);

		speed=3;
	
	}

	/**
	 * AI for enemy behavior. bounces around the room at a good speed.
	 * Blows up when it hits the player.
	 * @param player
	 */
	public void enemyAI(){
		setFrame((getTicks()/5)%4);
		//handles end of screen
		if(getRight() > getHome().getRight()){
			directionX=-1;
		}
		else if(getLeft() < getHome().getLeft()){
			directionX=1;
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
		new BossFireExplosion(this,(int)-this.getVx(),(int)-this.getVy());
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
