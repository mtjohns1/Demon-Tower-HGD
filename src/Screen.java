/**
 * 
 * A class for handling screen drawing
 *	@author Thomas
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Screen {


	public static void startup(){
		final Game game = new Game();
		final Control controls = new Control();
		JFrame frame = new JFrame();

		//adds key listeners for movement and shooting
		controls.init(frame);
		
		
		//game.setPreferredSize(new Dimension(640, 480));
		
		frame.add(game);
		frame.getContentPane().setPreferredSize(new Dimension(640,480));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		//Insets i = frame.getInsets();
		//System.out.println(i.left+" "+i.right);
		//System.out.println(i.top+" "+i.bottom);
		//
		//frame.setSize(new Dimension(i.left + i.right + 640,i.bottom + i.top +  480));
		//frame.setSize(640+640-(int)frame.getContentPane().getSize().getWidth(),480+480-(int)frame.getContentPane().getSize().getHeight());
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

		Room r = new Room();
		Player p = new Player(r, controls);
		p.setTop(100);
		p.setLeft(100);
		Timer t = new Timer(18);
		Sprite.load();

		while(true) {
			t.startLoop();
			r.update();
			r.draw(game.getSprites());
			game.redraw();
			t.endLoop();

			/*while(game.getCX() < 590){
				game.setCX(game.getCX()+1);

				ArrayList<Sprite> adder = game.getSprites();
				adder.add(new Sprite(game.getCX(),game.getCY(),60, 75,0,0,2,game.getTestImage()));
				adder.add(new Sprite(100+(game.getCX()/2),100+(game.getCY()/2),36, 36,0,0,1,game.getHero()));
				game.setSprites(adder);



				//frame.repaint();
				try {
					Thread.sleep((long)1000/60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//game.sprites.clear();
				game.redraw();
			}
			while(game.getCY() < 470){
				game.setCY(game.getCY()+1);
				//game.sprites.add(new Sprite(game.getCX(),game.getCY(),60, 75,0,0,2,game.getTestImage()));
				//game.sprites.add(new Sprite(100+(game.getCX()/2),100+(game.getCY()/2),36, 36,0,0,1,game.getHero()));

				ArrayList<Sprite> adder = game.getSprites();
				adder.add(new Sprite(game.getCX(),game.getCY(),60, 75,0,0,2,game.getTestImage()));
				adder.add(new Sprite(100+(game.getCX()/2),100+(game.getCY()/2),36, 36,0,0,1,game.getHero()));
				game.setSprites(adder);

				//frame.repaint();
				try {
					Thread.sleep((long)1000/60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//game.sprites.clear();
				game.redraw();
			}
			while(game.getCX() > 10){
				game.setCX(game.getCX()-1);

				ArrayList<Sprite> adder = game.getSprites();
				adder.add(new Sprite(game.getCX(),game.getCY(),60, 75,0,0,2,game.getTestImage()));
				adder.add(new Sprite(100+(game.getCX()/2),100+(game.getCY()/2),36, 36,0,0,1,game.getHero()));
				game.setSprites(adder);
				//frame.repaint();
				try {
					Thread.sleep((long)1000/60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//game.sprites.clear();
				game.redraw();
			}
			while(game.getCY() > 10){
				game.setCY(game.getCY()-1);

				ArrayList<Sprite> adder = game.getSprites();
				adder.add(new Sprite(game.getCX(),game.getCY(),60, 75,0,0,2,game.getTestImage()));
				adder.add(new Sprite(100+(game.getCX()/2),100+(game.getCY()/2),36, 36,0,0,1,game.getHero()));
				game.setSprites(adder);
				//frame.repaint();
				try {
					Thread.sleep((long)1000/60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//game.sprites.clear();
				game.redraw();
			}*/
		}


	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startup();
	}




}
