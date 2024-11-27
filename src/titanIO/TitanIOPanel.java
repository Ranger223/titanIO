package titanIO;
/*Author: Steven Bates
 * Date: 2/23/16
 * Comments: agario
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class TitanIOPanel extends JPanel{
	
	private ImageIcon image;
	private Circle c1;
	private javax.swing.Timer timer;
	private int mouseX = 0, mouseY = 0;
	private Circle pellet;
	private Circle enemy, enem;
	private ArrayList<Circle> pellets = new ArrayList <Circle>();
	private ArrayList<Circle> enemies = new ArrayList <Circle>();
	private int score = 0;
	private Image pic;
	private int hi;
	private long startTime, totalTime;
	
	
	public TitanIOPanel(ImageIcon im, Image p) {
		
		image = im;
		pic = p;
		//pic = new ImageIcon("images/Doge.jpeg").getImage();
		Random generator = new Random();
		Color color2;
		color2 = new Color(generator.nextInt(255), generator.nextInt(255), generator.nextInt(255));
		c1 = new Circle(840, 525, 25, color2);
		addMouseListener(new PanelListener());
		addMouseMotionListener(new PanelMotionListener());
		timer = new javax.swing.Timer(50, new MoveListener());
		timer.start();
		startTime = System.currentTimeMillis();

	
		for (int i = 0; i < 70; i++) {
			makePellet();
			
		}
		
		for (int en = 0; en < 3; en++) {
			makeEnemy();
			
			
			
		}
	
		//enemy = new Circle(505, 505, 50, Color.blue);
		
		
		
		
	}
	public void paintComponent(Graphics g){
		Font f1 = new Font("Narkisim", Font.BOLD, 35);
		g.setFont(f1);
		g.setColor(Color.cyan);
		
		super.paintComponent(g);
		image.paintIcon(this, g, 0, 0);
		c1.fill(g);
		int ratio = (int) (c1.getRadius() * .7);
		//pic.paintIcon(this, g, c1.getX() - ratio, c1.getY() - ratio);
		g.drawImage(pic, c1.getX() - ratio, c1.getY() - ratio, ratio * 2, ratio * 2, null);
		//enemy.fill(g);
		
		int sec =  (int) (totalTime / -1000);
		g.drawString("Score: " + score, 10, 30);
		g.drawString("Time: " + (sec + 1), (getWidth() - 200), 30);
		
		if (sec == -1) {
			Font f2 = new Font("Impact", Font.BOLD, 115);
			g.setFont(f2);
			g.setColor(Color.red);
			g.drawString("Game Over!", 640, 400);
			
			
			Font f3 = new Font("Narkisim", Font.BOLD, 60);
			g.setFont(f3);
			g.setColor(Color.blue);
			g.drawString("Your score: " + score, 740, 500);
			
			
			
			
			
			PrintWriter writer;
			
			try {
				Scanner reader = new Scanner(new  File("score.txt"));
				while(reader.hasNext()){
				hi = reader.nextInt();
				
				}
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			
			Font f4 = new Font("Narkisim", Font.BOLD, 60);
			g.setFont(f4);
			g.setColor(Color.blue);
			g.drawString("High Score: " + hi, 740, 600);
			
			timer.stop();//************stops the game*******************
			
			if (score > hi) {
	
				try {
					writer = new PrintWriter (new File("score.txt"));
					writer.println(score);
					writer.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		}
		
		
		
		for (int i = 0; i < pellets.size(); i++) {
			Circle pel = pellets.get(i);
			pel.fill(g);
			
		}
		
		for (int en = 0; en < enemies.size(); en++) {
			Circle enem = enemies.get(en);
			enem.fill(g);
			
			
		}
		
		
	}
	
	
	
		
	
	
	
	private void makePellet(){
		Random generator = new Random();
		int x, y;
		Color color1;
		
		
		x = generator.nextInt(1800) + 20;
		y = generator.nextInt(1000) + 20;		
		color1 = new Color(generator.nextInt(255), generator.nextInt(255), generator.nextInt(255));
		pellet = new Circle(x, y, 10, color1);
		
		pellets.add(pellet);
		
	}
	
	private void makeEnemy(){
		Random gener = new Random();
		int x, y, z;
		Color color3;
		
		
		x = gener.nextInt(1910 - 20) + 5;
		y = gener.nextInt(1000 - 20) + 5;		
		z = gener.nextInt(50) + 15;
		color3 = new Color(gener.nextInt(255), gener.nextInt(255), gener.nextInt(255));
		enemy = new Circle(x, y, z, color3);
		enemy.setDirection(gener.nextInt(360));
		enemy.setVelocity(gener.nextInt(15) + 5);
		
		enemies.add(enemy);
	
		
		
		repaint();
	}
	
	
	
	
	private class MoveListener implements ActionListener{ //Timer
		
		public void actionPerformed(ActionEvent e){
			
			
			totalTime = System.currentTimeMillis() - startTime;
			totalTime = totalTime - 60000;//time______________________________________________________________________________________________
		
			if (mouseY < c1.getY() - 5) {  //Mouse above circle.
				c1.move(0, -5);
			}
			else if (mouseY > c1.getY() + 5) {  //Mouse below circle.
				c1.move(0, 5);
			}
			if (mouseX < c1.getX() - 5) {  //Mouse left circle.
				c1.move(-5, 0);
			}
			else if (mouseX > c1.getX() + 5) {  //Mouse right circle.
				c1.move(5, 0);
			}
			
			for (int i = 0; i < pellets.size(); i++) {
				Circle pel = pellets.get(i);
				
				if (c1.hasCollision(pel)) {
					pellets.remove(i);
					c1.setRadius(c1.getRadius() + pel.getRadius() / 4);
					score += 10;
					try {
						Audio.main("sounds/pop.wav");//pop.wav
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
				
				for (int en = 0; en < enemies.size(); en++) {
					enem = enemies.get(en);
					
					Random gen = new Random();//generator for direction
					// moves the enemy ----------------------------------------
					

						if (enem.getX() - enem.getRadius() <= (15) + 5 || enem.getX() + enem.getRadius() >= (1910) - 5){  //left to right
						enem.turn(gen.nextInt(180) + 90);
							
							
						}
						
						if (enem.getY() - enem.getRadius() <= (10) + 5|| enem.getY() + enem.getRadius() >= (1060) - 5){  //up and down
						enem.turn(gen.nextInt(180) + 90);
							
						}
						
						
						enem.move();
						
						repaint();
					
					
					
					if (c1.hasCollision(enem)) {
						enemies.remove(en);
						c1.setRadius(c1.getRadius() / 2 );
						score = score / 2;
						try {
							Audio.main("sounds/Pain.wav") ;//pop.wav
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();								
						}	
								
						}
					} 
					
					
					
					
								
								
								// check if enemy eats pellet
							/*	for (int i = 0; i < pellets.size(); i++) {
									Circle pel = pellets.get(i);
									if(enemy.hasCollision(pel)) {    //Collision with enemy
										pellets.remove(i);
										enemy.setRadius(enemy.getRadius() + pel.getRadius() / 4); //how much the enemy grow with each pellet they eat (i.e. 4)
										try {
										
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}*/
								
								repaint();
								
								} 
								
								
						
				} //closes MoveListener
				
				

	
		
	private class PanelMotionListener extends MouseMotionAdapter{
		
		
		
		public void mouseMoved(MouseEvent e){
			
			mouseX = e.getX();
			mouseY = e.getY();
			
			
			
			
			System.out.println("x: " + mouseX + " y: " + mouseY);
		}
		
	}
	
	
	
	public class PanelListener implements MouseListener{

		
		public void mouseClicked(MouseEvent arg0) {

		}
		
		public void mouseEntered(MouseEvent arg0) {

		}
		
		public void mouseExited(MouseEvent arg0) {
	
		}
		
		public void mousePressed(MouseEvent arg0) {
			
		}
		
		public void mouseReleased(MouseEvent arg0) {
			
		}

		
	}
	
	
}//closes