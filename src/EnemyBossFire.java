import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * This enemy is the boss. It stomp while moving, and fire two attacks, fireball and firebreath 
 * @author Matthew_J
 *
 */
public class EnemyBossFire extends Enemy{

	private Player player;
	private Bullet attack;
	private int weaponCoolDown =0;
	private int moveCoolDown =0;
	private int attackType =0;
	private int attackOffset =-160;
	private int attackCountDown =0;
	private int speed =40;
	private int incrementer =20;

	/**
	 * creates a fire boss, it shoots fireballs, firebreath, and explosive steps.
	 * @param home Pass room boss exists in
	 */
	public EnemyBossFire(Room home) {
		super(home);
		this.setMaxHp(20);

		this.setHp(200);

		setW(64);
		setH(64);
		setD(32);


		this.setX(300);
		this.setY(200);
		player = home.getPlayer();
	}

	/**
	 * AI for enemy behavior. 
	 * @param player
	 */
	public void enemyAI(){
		//if fire breath then keep going until it is done
		if (attackType == 2 && attackCountDown != 5){
			attackFireBreath();
			return;
		}
		attackCountDown =0;
		attackType =0;


		//slows down movement. creates explosions on each step.
		moveCoolDown +=1;
		if (moveCoolDown == 100){
			moveCoolDown = 0;
			mover();

			return;
		}
		this.setVx(0);
		this.setVy(0);
		weaponCoolDown +=1;
		if (weaponCoolDown < 50){
			return;
		}
		double random = Math.random()*10;
		if (random > 5.0){
			attackFireBall();
			attackType =1;
		}
		else{
			attackType =2;
			attackFireBreath();
		}
		weaponCoolDown = 0;



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
		if(xDif == 0){ //player up or down from enemy
			yMove = speed * ySign;
		}
		else if (yDif == 0){//player left or right
			xMove = speed * xSign;
		}
		else if (Math.abs(xDif) > Math.abs(yDif)){ //player more up or down then left or right
			xMove = speed/2 * xSign;
			yMove = speed/3 * ySign;
		}
		else if (Math.abs(xDif) < Math.abs(yDif)){//more left/right then up/down
			yMove = speed/2 * ySign;
			xMove = speed/3 * xSign;
		}
		else {//player perfect 45 degree angle
			yMove =speed/2 * ySign;
			xMove =speed/2 * xSign;
		}
		//creates explosion on step
		attack = new BulletExplosion(this,1,1);

		//apply acceleration
		setVx(getVx()+xMove);
		setVy(getVy()+yMove);

		//apply deceleration
		setVx(getVx()/2);
		setVy(getVy()/2);

	}

	/**
	 * logic for how to attack with firebreath
	 */
	public void attackFireBreath(){
		int xOffset =0;
		int yOffset = 0;
		//finds how for enemy is from player
		int xDif = this.getX() - this.getHome().getPlayer().getX();
		int yDif = this.getY() - this.getHome().getPlayer().getY();

		//finds direction of x and y
		xDif = xDif*-1;
		yDif = yDif*-1;

		int xSign =1;
		int ySign =1;
		if (xDif > 0) xSign =-1;
		if (yDif > 0) ySign =-1;

		//logic for how to find offset
		if(xDif == 0){ //player up or down from enemy
			xOffset = attackOffset * ySign;
		}
		else if (yDif == 0){//player left or right
			yOffset = attackOffset * xSign;
		}
		else if (Math.abs(xDif) > Math.abs(yDif)){ //player more up or down then left or right
			yOffset = attackOffset/2 * xSign;
			xOffset = attackOffset/3 * ySign;
		}
		else if (Math.abs(xDif) < Math.abs(yDif)){//more left/right then up/down
			xOffset = attackOffset/2 * ySign;
			yOffset = attackOffset/3 * xSign;
		}
		else {//player perfect 45 degree angle
			xOffset =attackOffset/2 * ySign;
			yOffset =attackOffset/2 * xSign;
		}

		//creates wave for attack and increments counter for attack life span
		attackOffset += incrementer;
		if(attackOffset ==160){
			attackCountDown +=1;
			incrementer =-20;
		}
		else if(attackOffset ==-160){
			attackCountDown +=1;
			incrementer =20;
		}
		//adds a random nature to fire breath
		xOffset += Math.random()*80*xSign;
		yOffset += Math.random()*80*ySign;

		attack = new BulletFireBreath(this,xDif+(xOffset),yDif+(yOffset));
	}
	/**
	 * logic for how to attack with fireball
	 */
	public void attackFireBall(){
		//finds how for enemy is from player
		int xDif = this.getX() - this.getHome().getPlayer().getX();
		int yDif = this.getY() - this.getHome().getPlayer().getY();

		//finds direction of x and y
		xDif = xDif*-1;
		yDif = yDif*-1;

		//logic for how to move
		attack = new BulletFireBall(this,xDif,yDif);
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
			//half heart
			if (getHp() <= i*4+2 && getHp() > i*10) {
				s = new Sprite(610-i*16, y, 32, 32, 32, 0, 0, "hearts");
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
		Sprite s = new Sprite(getLeft()-2, getTop()-2, getW()+4, getH()+4, 0, 0, 0, "enemy");
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