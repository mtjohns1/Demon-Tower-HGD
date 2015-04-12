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
import bullet.*;

/**
 * This enemy stand stills and shoots the player
 * @author Matthew_J
 *
 */
public class Tower extends Enemy{

	private Player player;
	private BasicBullet attack;
	private int weaponCoolDown =0;
	
	public Tower(Room home, int x, int y) {
		super(home,2);
		this.setMaxHp(20);

		this.setHp(5);
		this.setX(x);
		this.setY(y);
		
		player = home.getPlayer();
		setSpriteSheet("fire_tower.png");
		setSpriteW(64);
		setSpriteH(64);
		setSpriteDir(true);
		setDir("right");
		setAnim(0);
		setFrame(0);
	}

	/**
	 * AI for enemy behavior. Chases player using shortest path, regardless of obsticals
	 * @param player
	 */
	public void enemyAI(){
		setVx(0);
		setVy(0);
		weaponCoolDown +=1;

		//finds how for enemy is from player
		int xDif = this.getX() - this.getHome().getPlayer().getX();
		int yDif = this.getY() - this.getHome().getPlayer().getY();
		setFrame((getTicks()/10)%4);
		double dx = xDif;
		double dy = yDif;
		if (Math.abs(dy) > Math.abs(dx)) {
			if (dy > 0) setDir("up");
			else if (dy < 0) setDir("down");
		}
		else if (Math.abs(dx) > Math.abs(dy)) {
			
			if (dx > 0) setDir("left");
			else if (dx < 0) setDir("right");
		}
		if (weaponCoolDown < 100){
			return;
		}

		weaponCoolDown = 0;
		
		//finds direction of x and y
		xDif = xDif*-1;
		yDif = yDif*-1;
		
		//logic for how to move
		new BossFireBall(this,xDif,yDif);
	
		
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
		if(a instanceof Player && !(overlap))
		if((a instanceof Enemy)){
			
		}
	}
	
}
