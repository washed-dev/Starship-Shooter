package main;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Painter extends JPanel {
	public Color mycolor = Color.black;
	public Random random = new Random();
	
	
	
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		Shooter shooter = Shooter.shooter;
		
		String Lives = String.format("Lives: %d", shooter.playerLives);
		String Enemies = String.format("Enemies: %d", shooter.enemyCount);
		String Score = String.format("Score: %d", shooter.score);
		g.setColor(mycolor);
		g.fillRect(0, 0, 570, 700);
		
		
		ArrayList<Point> stars = new ArrayList<Point>();
		for(int i =0; i<75;i++) {
			stars.add(new Point(random.nextInt(575),random.nextInt(500)));
		}
		
		
		for(Iterator<Point> iterator = Shooter.stars.iterator(); iterator.hasNext();) {
			Point p = iterator.next();
			if(p.getY()+shooter.starDisp>=500) {
				p.setLocation(p.getX(), 0-shooter.starDisp);
			}
			g.setColor(Color.white);
			g.fillOval((int) p.getX(),(int) p.getY()+shooter.starDisp, random.nextInt(5), random.nextInt(5));
		}
		
		g.drawLine(0, 500, 575, 500);
		
		
		
		for(Enemy e: shooter.enemies) {
			e.drawEnemy(g);
		}
		for(Bullet b:shooter.bullets) {
		
			b.drawBullet(g);
			
		}
		for(Bullet b:shooter.eBullets) {
			
			b.drawBullet(g);
			
		}
		
		shooter.player.drawPlayer(g);
		g.setColor(Color.red);
		g.drawString(Lives, 0, 511);
		g.drawString(Enemies, 0, 531);
		g.drawString(Score, 0, 551);
		
	}
	
}