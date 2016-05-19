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


public class Player {
	public static final Polygon playerPoly = ShapeList.playerPoly;
	
	
	private static int playerX[] = new int[playerPoly.npoints];
	private static int playerY[] =new int[playerPoly.npoints];
	private static Point playerPoint= new Point(playerX[0],playerY[0]);
	public static int bulletPoint;
	public static int hitBoxX[]= new int[5];
	public static int hitBoxY[]= new int[5];
	public static Color playerColor=Color.WHITE;
	private static Boolean destroyed = false;
	
	public Polygon transPlayer;
	
	public Player(Point p, Polygon eShape) {
		for(int i=0;i<=playerPoly.npoints-1;i++) {
			playerX[i]= playerPoly.xpoints[i] + (int) p.getX();
		}
		for(int i=0;i<=playerPoly.npoints-1;i++) {
			playerY[i]= playerPoly.ypoints[i] + (int) p.getY();
		}
		
		
		
		transPlayer= new Polygon(playerX,playerY,playerPoly.npoints);
		hitBoxX[0]=transPlayer.xpoints[3]+5;
		
		setBulletPoint(transPlayer.xpoints[4]);
	}
	
public Player(int x, int y, Polygon eShape) {
	for(int i=0;i<=playerPoly.npoints-1;i++) {
		playerX[i]= playerPoly.xpoints[i] + x;
	}
	for(int i=0;i<=playerPoly.npoints-1;i++) {
		playerY[i]= playerPoly.ypoints[i] + y;
	}
	
	
	
	transPlayer= new Polygon(playerX,playerY,playerPoly.npoints);
	}
	
	public void drawPlayer(Graphics g) {
		if(!destroyed) {
		g.setColor(playerColor);
		g.fillPolygon(transPlayer);
		}
		
	}
	
	public static int getCenterX() {
		return playerX[0];
	}
	
	public static int getCenterY() {
		return playerY[0];
	}
	
	public static Point getPlayerPoint() {
		playerPoint=new Point(playerX[0]-5,playerY[0]+120);
		return playerPoint;
	}
	
	public void transPlayer(int x, int y) {
		transPlayer.translate(x, y);
		playerPoint.translate(x, y);
		setBulletPoint(transPlayer.xpoints[4]);
	}

	public int getBulletPoint() {
		return bulletPoint;
	}

	public static void setBulletPoint(int bulletPoint) {
		Player.bulletPoint = bulletPoint;
	}
	public void setX(int x) {
		for(int i=0;i<=playerPoly.npoints-1;i++) {
			playerX[i] = ShapeList.playerPoly.xpoints[i] + x;
		}

}
}

