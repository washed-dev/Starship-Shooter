
package main;

import java.awt.Polygon;

public class ShapeList {
	public static final Polygon standardEnemy = new Polygon(new int[] {
			0,10,15,20,30,20,10}, new int[] {10,0,10,0,10,30,30}, 7);
	public static final Polygon playerPoly = new Polygon(new int[] {
			0,-10,-10,-20,-5,-5,5,5,20,10,10}, new int[] {0,20,0,5,-30,-40,-40,-30,5,0,20}, 11);
	public ShapeList() {	
	}
}
