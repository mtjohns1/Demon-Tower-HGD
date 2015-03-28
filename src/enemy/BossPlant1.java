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
import utility.Damage;
import world.Room;
import world.Tile;
import bullet.*;

/**
 * This enemy is the boss. 
 * @author Matthew_J
 *
 */
public class BossPlant1 extends Enemy{

	private Player player;
	private Bullet attack;
	private int weaponCoolDown =0;
	private int moveCoolDown =0;
	private int spinActive =0;
	private int attackOffset =-160;
	private int attackCountDown =0;
	private int speed =5;
	private int spinCoolDown = 0;
	private int wall=0;
	private int directionX =1, directionY =1;

	/**
	 * creates a plant boss
	 * @param home Pass room boss exists in
	 */
	public BossPlant1(Room home) {
		super(home);
		this.setMaxHp(20);

		this.setHp(5);

		setW(64);
		setH(64);
		setD(32);


		this.setX(500);
		this.setY(80);
		player = home.getPlayer();
	}

	/**
	 * AI for enemy behavior. 
	 * @param player
	 */
	public void enemyAI(){
		
		if (weaponCoolDown==100){
			swing();
			weaponCoolDown +=1;
			return;
		}
		if (weaponCoolDown>100&&weaponCoolDown <105){

			weaponCoolDown +=1;
			return;
		}
		attackCountDown+=1;
		if (attackCountDown >100){
			spin();
			return;
		}
		mover();

		if(weaponCoolDown <100){

			weaponCoolDown +=1;
			return;
		}
		weaponCoolDown +=1;
		weaponCoolDown =0;

		setVz(0);

	}

	/**
	 * boss spin attack
	 * 
	 */
	public void spin(){
		//handles end of screen
		spinCoolDown +=1;
		spinActive =1;
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
		
		if (spinCoolDown >250){
			spinCoolDown =0;
			attackCountDown =0;
			spinActive =0;
		}
		setVx(getVx()+speed*directionX);
		setVy(getVy()+speed*directionY);


		//apply deceleration
		setVx(getVx()/2);
		setVy(getVy()/2);
	}
	/**
	 * Logic for "swinging" from one wall to another wall;
	 */
	public void swing(){

		if(player == null){
			player = this.getHome().getPlayer();
			if(player == null){
				return;
			}

		}

		//finds how for enemy is from player
		int xDif = this.getX() - this.getHome().getPlayer().getX();
		int yDif = this.getY() - this.getHome().getPlayer().getY();

		//finds direction of x and y
		xDif = xDif*-1;
		yDif = yDif*-1;

		//logic for how to move
		attack = new BossGrapple(this,xDif,yDif);


	}
	/**
	 * Logic for movement, called to set vx and vy and create explosive step
	 */
	public void mover(){

		int direction =-1;
		moveCoolDown +=1;
		if (moveCoolDown >= 200){
			direction = 1;
			if (moveCoolDown>=400){
				moveCoolDown =0;
			}
		}

		if (wall ==0 || wall == 2){

			//apply acceleration
			setVx(getVx()+2*direction);
			setVy(getVy());

		}
		if (wall ==1 || wall == 3){

			//apply acceleration
			setVx(getVx());
			setVy(getVy()+2*direction);

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
		if(spinActive ==1){
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
		
		if (t.getType().contains("w") || t.getType().contains("p")) {
			if (dir.equals("right")) {
				setRight(t.getLeft()-1);
				setVx(0);
				wall=3;
			}
			if (dir.equals("left")) {
				setLeft(t.getRight()+1);
				setVx(0);
				wall=1;
			}
			if (dir.equals("down")) {
				setBottom(t.getTop()-1);
				setVy(0);
				wall=2;
			}
			if (dir.equals("up")) {
				setTop(t.getBottom()+1);
				setVy(0);
				wall=0;
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

	public void drawHUD(List<Sprite> list, int y) {
		//draw health
		for (int i = 0; i < getMaxHp()/4; i++) {
			//full heart
			Sprite s = new Sprite(610-i*16, y, 32, 32, 0, 0, 0, "hearts");
			//half heart (decreases left to right)
			if (getHp() <= i*4+2 && getHp() > i*10) {
				s = new Sprite(610-i*16, y, 32, 32, 96, 0, 0, "hearts");
			}
			//empty heart
			if (getHp() <= i*4) {
				s = new Sprite(610-i*16, y, 32, 32, 64, 0, 0, "hearts");
			}
			list.add(s);
		}
	}

	/**
	 * Generate the player's sprite for this frame
	 * 
	 * @param list: the list of sprites to add to
	 */
	public void  draw(List<Sprite> list)
	{
		Sprite s = new Sprite(getLeft()-2, getTop()-2, getW()+4, getH()+4, 0, 0, 0, "boss1");
		list.add(s);
		drawHUD(list, 448);
	}


	public void collide(Mobile m, boolean overlap, boolean nextOverlap) {
		//non-actors and your owner are ignored
		if (!(m instanceof Actor) )
			return;

		//cast for convenience
		Actor a = (Actor)m;
		//do damage
		if(a instanceof Player && !(overlap)){

			a.takeDamage(new Damage(1, 0, 0, getStun()));
		}
			if((a instanceof Enemy)){

			}
	}
}