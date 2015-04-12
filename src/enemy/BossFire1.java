package enemy;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import powerup.Heart;
import effect.Explosion;
import effect.Freezeframe;
import effect.ScreenFlash;
import mobile.Actor;
import mobile.Mobile;
import mobile.Player;
import sprite.Sprite;
import sprite.TransparencySprite;
import world.Room;
import world.Tile;
import bullet.BouncerExplosion;
import bullet.Bullet;
import bullet.BossFireExplosion;
import bullet.BossFireBall;
import bullet.BossFireBreath;

/**
 * This enemy is the boss. It stomp while moving, and fire two attacks, fireball and firebreath 
 * @author Matthew_J
 *
 */
public class BossFire1 extends Enemy{

	private Player player;
	private Bullet attack;
	private int weaponCoolDown =0;
	private int moveCoolDown =0;
	private int attackType =0;
	private int attackOffset =-160;
	private int attackCountDown =0;
	private int speed =5;
	private int incrementer =20;
	private int count =0;
	private boolean isMoving =false;
	/**
	 * creates a fire boss, it shoots fireballs, firebreath, and explosive steps.
	 * @param home Pass room boss exists in
	 */
	public BossFire1(Room home) {
		super(home,10);
		this.setMaxHp(30);

		this.setHp(200);

		setW(64);
		setH(64);
		setD(32);


		this.setX(300);
		this.setY(200);
		
		setSpriteSheet("gojira.png");
		setSpriteW(80);
		setSpriteH(80);
		setSpriteY(-8);
		setSpriteDir(true);
		setDir("right");
		setAnim(0);
		setFrame(0);
		
		
		player = home.getPlayer();
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
		//animation
		int xDif = this.getX() - this.getHome().getPlayer().getX();
		int yDif = this.getY() - this.getHome().getPlayer().getY();
		
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
		
		setFrame(3);
		//if fire breath then keep going until it is done
		if (attackType == 2 && attackCountDown != 5){
			count +=1;
			setAnim(0);
			if(count <75){
			setFrame(count/25);
			}
			else{
			setFrame(3);
			attackFireBreath();
			}
			return;
		}
		if (attackType == 1 ){
			count +=1;
			setAnim(1);
			if(count <45){
			setFrame(count/15);
			}
			else{
			setFrame(3);
			attackFireBall();
			attackType=0;
			}
			return;
		}
	
		attackCountDown =0;
		attackType =0;


		//slows down movement. creates explosions on each step.
		moveCoolDown +=1;
		if (moveCoolDown == 100 || isMoving){
			isMoving =true;
			count +=1;
			if(count == 30){
				isMoving = false;
			}
			moveCoolDown = -10;
			setAnim(2);
			setFrame(getTicks()/10%4);
			mover();

			return;
		}
		count =0;
		this.setVx(0);
		this.setVy(0);
		weaponCoolDown +=1;
		if (weaponCoolDown < 50){
			return;
		}
		double random = Math.random()*10;
		if (random > 5.0){

			attackType =1;
			
			
		}
		else{
			count +=1;
			attackType =2;
			if(count <15){
			setFrame(count/5%4);
			}
			else{
			
			attackFireBreath();
			}
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
		if(count ==0 || count ==5 || count ==10 || count ==15 || count ==20 ||count ==25)
		attack = new BouncerExplosion(this,0,0);

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

		attack = new BossFireBreath(this,xDif+(xOffset),yDif+(yOffset));
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
		attack = new BossFireBall(this,xDif,yDif);
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
			Sprite s = new Sprite(610-i*16, y, 32, 32, 0, 0, 0, "hearts.png");
			//half heart (decreases left to right)
			if (getHp() <= i*4+2 && getHp() > i*10) {
				s = new Sprite(610-i*16, y, 32, 32, 96, 0, 0, "hearts.png");
			}
			//empty heart
			if (getHp() <= i*4) {
				s = new Sprite(610-i*16, y, 32, 32, 64, 0, 0, "hearts.png");
			}
			list.add(s);
		}
	}

	@Override
	public int takeDamage(int damage) {
		new Explosion(this, getX(), getY()+4, 72, 72, 8, 1, 5);
		return super.takeDamage(damage);
	}
	
	@Override
	public void onDeath()
	{
		new Explosion(this, getX(), getY(), 72, 72, 131, 1, 7);
		new Freezeframe(this, 121);
		new ScreenFlash(this, 120, 10, 20);
	}
	
	/**
	 * Draw the player's shadow
	 * @param list the list to add sprites to 
	 */
	public void drawShadow(List<Sprite> list)
	{
		if (getSpriteSheet() == null) return; //do nothing if no sprite
		//calculate resultant drawing position (aligned centers)
		int drawX = getX()-32;
		int drawY = getY()-40;
		//generate the resulting sprite
		Sprite s = new TransparencySprite(drawX, drawY, 64, 64, 0, 0, calculateShadowLayer()+30, "gojira_shadow.png", 0.5);
		list.add(s);
	}

	@Override
	public void draw(List<Sprite> list)
	{
		super.draw(list);
		drawShadow(list);
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