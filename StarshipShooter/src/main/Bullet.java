package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;

public class Bullet {
private  int x;
private int y;
private int width=6;
private int height=20;
private Color color=new Color(0xFF0000);

	public Bullet(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public Bullet(int x, int y, Color color){
		this.x=x;
		this.y=y;
		this.color=color;
	}
	
	public Bullet(Point p) {
		x=(int) p.getX();
		y=(int) p.getY();
	}
	
		public void drawBullet(Graphics g) {
			g.setColor(color);
			g.fillRect(x, y, width, height);
		}
		
		public void transBulletX() {
			
		}
		
		public void transBulletY(int dy) {
			this.y-=dy;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
}