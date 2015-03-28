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
import bullet.BasicBullet;
import bullet.BossFireBall;

/**
 * This enemy stand stills and shoots the player
 * @author Matthew_J
 *
 */
public class Tower extends Enemy{

	private Player player;
	private BasicBullet attack;
	private int weaponCoolDown =0;
	
	public Tower(Room home) {
		super(home);
		this.setMaxHp(20);

		this.setHp(5);
		this.setX(300);
		this.setY(300);
		player = home.getPlayer();
	}

	/**
	 * AI for enemy behavior. Chases player using shortest path, regardless of obsticals
	 * @param player
	 */
	public void enemyAI(){

		weaponCoolDown +=1;
		if (weaponCoolDown < 50){
			return;
		}

		weaponCoolDown = 0;
		//finds how for enemy is from player
		int xDif = this.getX() - this.getHome().getPlayer().getX();
		int yDif = this.getY() - this.getHome().getPlayer().getY();
		
		//finds direction of x and y
		xDif = xDif*-1;
		yDif = yDif*-1;
		
		//logic for how to move
		attack = new BasicBullet(this,xDif,yDif);
		
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
	
	/**
	 * Generate the player's sprite for this frame
	 * 
	 * @param list: the list of sprites to add to
	 */
	public void  draw(List<Sprite> list)
	{
		Sprite s = new Sprite(getLeft()-2, getTop()-2, getW()+4, getH()+4, 0, 0, 0, "enemyTower");
		list.add(s);
	}
	
	
	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) )
			return;
		System.out.println("inside");
		//cast for convenience
		Actor a = (Actor)m;
		//do damage
		if(a instanceof Player && !(overlap))
		if((a instanceof Enemy)){
			
		}
	}
	
}
