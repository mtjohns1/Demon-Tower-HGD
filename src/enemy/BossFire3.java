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
import bullet.BlastMelee;
import bullet.Bomb;
import bullet.Bullet;
import bullet.BossFireExplosion;
import bullet.BossFireBall;
import bullet.BossFireBreath;
import weapon.BlastSword;
import weapon.Weapon;

/**
 * This enemy is the boss. It spits fire and splits up on death.
 * @author Matthew_J
 *
 */
public class BossFire3 extends Enemy{

	private Player player;
	private Bullet attack;
	private Weapon blaster;
	private int weaponCoolDown =0;
	private int moveCoolDown =0;
	private int bombCoolDown =0;
	private int attackType =0;
	private int attackOffset =-160;
	private int attackCountDown =0;
	private int speed =3;
	private int moveCount = 0;
	private int oldX;
	private int oldY;
	private int split;

	private int directionX =1, directionY =1;

	/**
	 * creates a fire boss, it shoots Bombs.
	 * @param home Pass room boss exists in
	 */
	public BossFire3(Room home) {
		super(home);
		this.setMaxHp(20);

		this.setHp(10);
		split=0;
		setW(32);
		setH(32);
		setD(32);
		blaster = new BlastSword();
		speed=1;
		this.setX(300);
		this.setY(200);
		player = home.getPlayer();
		oldX = getX();
		oldY = getY();
	}

	/**
	 * creates a fire boss, it splicts.
	 * @param home Pass room boss exists in
	 */
	public BossFire3(Room home,int splitCount, int x,int y) {
		super(home);
		this.setMaxHp(20);
		split= splitCount;
		this.setHp(10/split);
		
		setW(32);
		setH(32);
		setD(32);
		blaster = new BlastSword();
		speed=splitCount;
		this.setX(x);
		this.setY(y);
		player = home.getPlayer();
		oldX = getX();
		oldY = getY();
	}
	
	/*
	 * 
	 * @see enemy.Enemy#onDeath()
	 */
	public void onDeath(){
		if(split<3){
			new BossFire3(this.getHome(),split+1,this.getX()+32,this.getY());
			new BossFire3(this.getHome(),split+1,this.getX()-32,this.getY());
		}
		
	}

	/**
	 * AI for enemy behavior. 
	 * @param player
	 */
	public void enemyAI(){
		if(player == null){
			player = this.getHome().getPlayer();
			if(player == null){
				return;
			}

		}
		//finds how far enemy is from player
		double xDif = this.getX() - player.getX();
		double yDif = this.getY() - player.getY();
		double total = Math.sqrt((double)(yDif*yDif+xDif*xDif));
		attackCountDown +=1;
		mover();

		if (attackCountDown == 100){
			attackCountDown = 0;
			shooter();
		}
	}

	/**
	 * Logic for movement, called to set vx and vy and create explosive step
	 */
	public void mover(){

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
				else if(getBottom() > getHome().getBottom()){
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
	 * logic for how to create a bomb on top of enemy
	 */
	public void attackBombSummon(){
		//logic for how to move
		attack = new Bomb(this, this.getHome().getPlayer().getX(), this.getHome().getPlayer().getY(),0);
	}


	/**
	 * logic for how to create a bomb on top of enemy
	 */
	public void shooter(){

		//finds how for enemy is from player
		int xDif = this.getX() - this.getHome().getPlayer().getX();
		int yDif = this.getY() - this.getHome().getPlayer().getY();
		
		//finds direction of x and y
		xDif = xDif*-1;
		yDif = yDif*-1;
		
		//logic for how to shoot
		new BossFireBall(this,xDif,yDif);
	}
	/**
	 * Manage collisions with a tile 
	 * when colliding bounce off wall.
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
		if(a instanceof Player && !(overlap))
			if((a instanceof Enemy)){

			}
	}
}