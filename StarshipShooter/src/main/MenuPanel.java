package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel {

	@Override
protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	
	
	g.setColor(Color.red);
	g.fillRect(0, 500, 575, 200);
	
	}
}
