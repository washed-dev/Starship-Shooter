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
	
	
	
	//poop

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		Shooter shooter = Shooter.shooter;
		
		String Lives = String.format("Lives: %d", shooter.playerLives);
		String Enemies = String.format("Enemies: %d", shooter.enemyCount);
		String Score = String.format("Score: %d", shooter.score);
		String Health =  String.format("Health: %d", shooter.health);
		String WaveNum = String.format("Wave: %d", shooter.waveNum);
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
			g.setColor(Color.green);
			g.fillRect(e.bulletX()+5-20*e.health/e.initHealth, e.bulletY+5, 40/e.initHealth*(e.health), 5);
			g.setColor(Color.white);
			g.drawRect(e.bulletX()-15, e.bulletY+5, 40, 5);
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
		g.drawString(Health, 100, 511);
		
	}
	
}