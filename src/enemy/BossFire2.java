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
import bullet.BlastMelee;
import bullet.Bomb;
import bullet.Bullet;
import bullet.BossFireExplosion;
import bullet.BossFireBall;
import bullet.BossFireBreath;
import weapon.BlastSword;
import weapon.Weapon;

/**
 * This enemy is the boss. It uses bombs
 * @author Matthew_J
 *
 */
public class BossFire2 extends Enemy{

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

	/**
	 * creates a fire boss, it shoots Bombs.
	 * @param home Pass room boss exists in
	 */
	public BossFire2(Room home) {
		super(home,11);
		this.setMaxHp(40);

		this.setHp(200);

		setW(32);
		setH(32);
		setD(32);
		blaster = new BlastSword();

		this.setX(300);
		this.setY(200);
		player = home.getPlayer();
		oldX = getX();
		oldY = getY();
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
		if (total >200 &&attackCountDown < 30){

			attackCountDown +=1;

			if(attackCountDown == 30)
				attackBombSummon();
			setVx(getVx()/2);
			setVy(getVy()/2);

			if (attackCountDown ==30)
				attackCountDown =0;
			return;
		}

		attackType =0;
		if (total <50&& attackCountDown <30){

			attackCountDown +=1;
			if(attackCountDown ==30){
				new BossFireExplosion(this, 0 ,0);

			
				}
		}
		if(attackCountDown >=30){
			attackCountDown +=1;

			setVx(getVx()/2);
			setVy(getVy()/2);
			if (attackCountDown ==80)
				attackCountDown =0;
			return;
		}
		mover();

		//slows down movement. creates explosions on each step.
		bombCoolDown +=1;
		if (bombCoolDown == 100){
			bombCoolDown = 0;
			new Bomb(this,getX(),getY());
			attackCountDown =0;
			return;
		}

	}

	/**
	 * Logic for movement, called to set vx and vy and create explosive step
	 */
	public void mover(){

		if(player == null){
			player = this.getHome().getPlayer();
			if(player == null){
				return;
			}

		}
		
		moveCount +=1;

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
		
		if (moveCount ==5){
			if (oldX == getX() || oldY == getY()){
				blaster.fire(this, 0, 0);
			}
			oldX=getX();
			oldY = getY();
		}

	}

	/**
	 * logic for how to attack with fireball
	 */
	public void attackBombSummon(){
		//logic for how to move
		attack = new Bomb(this, this.getHome().getPlayer().getX(), this.getHome().getPlayer().getY(),0);
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