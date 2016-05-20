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

public class Enemy {
	
	private int enemyX[] = new int[standardEnemy.npoints];
	private int enemyY[] =new int[standardEnemy.npoints];
	public static final Polygon standardEnemy = ShapeList.standardEnemy;
	public int bulletX;
	public int bulletY;
	public int direction;
	
	public int transX;
	public int transY;
	public int health;
	public int initHealth;
	public Color color;
	
	public Polygon transEnemy;
	
	
	
	public Enemy(Point p, Polygon eShape) {
		for(int i=0;i<=standardEnemy.npoints-1;i++) {
			enemyX[i]= standardEnemy.xpoints[i] + (int) p.getX();
		}
		for(int i=0;i<=standardEnemy.npoints-1;i++) {
			enemyY[i]= standardEnemy.ypoints[i] + (int) p.getY();
		}
		transEnemy= new Polygon(enemyX,enemyY,standardEnemy.npoints);
		
	}
	
public Enemy(int x, int y,int health, int direction) {
	for(int i=0;i<=standardEnemy.npoints-1;i++) {
		enemyX[i]= standardEnemy.xpoints[i] + x;
	}
	for(int i=0;i<=standardEnemy.npoints-1;i++) {
		enemyY[i]= standardEnemy.ypoints[i] + y;
	}
	transEnemy= new Polygon(enemyX,enemyY,standardEnemy.npoints);
	
	bulletX=x+10;
	
	bulletY=y+25;
	
	this.direction=direction;
	this.health=health;
	initHealth=health;
	
	}

public void transEnemy(int dx) {
	for(int i=0;i<=standardEnemy.npoints-1;i++) {
		enemyX[i]= enemyX[i] + dx;
	}
	transEnemy= new Polygon(enemyX,enemyY,standardEnemy.npoints);
	
	bulletX+=dx;
	
}

	
	public void drawEnemy(Graphics g) {
		switch(health) {
		case 1: color=new Color(0xFF0000);
		break;
		case 2: color=new Color(0xFF6600);
		break;
		case 3: color=new Color(0xFFBF00);
		break;
		default: color=new Color(0xFFBF00);
		break;
	}
		g.setColor(color);
		g.fillPolygon(transEnemy);
		
		
	}
	
	public int bulletX() {
		return bulletX;
	}
	
	public int bulletY() {
		return bulletY;
	}
	
	public int getLeftX() {
		return transEnemy.xpoints[0];
	}
	
	public int getRightX() {
		return transEnemy.xpoints[3];
	}
	
	public Point getLeftPoint() {
		return new Point (transEnemy.xpoints[0],transEnemy.ypoints[0]);
	}
	
	public Point getRightPoint() {
		return new Point (transEnemy.xpoints[3],transEnemy.ypoints[3]);
	}
	
	public void setColor(Color c) {
		color=c;
	}
	
	public void switchDirection() {
		direction*=-1;
	}	
}
