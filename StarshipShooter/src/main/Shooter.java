package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Shooter implements ActionListener, KeyListener {
	public  Dimension dim;
	public static int jWidth=570,jHeight=600;
	public static int scale=5;
	
	public static Shooter shooter;
	public Enemy enemy;
	public Bullet bullet;
	public Painter painter;
	public JFrame jFrame;
	public Timer timer = new Timer(1,this);
	public ShapeList shapeList;
	public Player player;
	public MenuPanel menuPanel;
	
	
	
	
	
	
	
	
	public static int ticks=0;
	public static int starDisp=0;
	public Random Picker=new Random();
	public Enemy moveEnemy;
	public Enemy shootEnemy;
	public int move;
	public int enemyCount;
	public static int playerLives=3;
	
	
	public int bulletsInTick=0;
	public Boolean bulletsCapped=false;
	public Boolean gunKeyHeld=false;
	public Boolean leftKeyHeld=false;
	public Boolean rightKeyHeld=false;
	public int lastKey=0;
	public int direction=0;
	
	
	
	public static ArrayList <Bullet> bullets = new ArrayList<Bullet>();
	public static ArrayList<Bullet> eBullets = new ArrayList<Bullet>();
	public static ArrayList<Point> stars = new ArrayList<Point>();
	
	
	public boolean paused =false;
	private int dx=0;
	public static int score=0;
	public static int shotNum=0;
	
	
	public static ArrayList <Enemy>enemies = new ArrayList<Enemy>();
	public static ArrayList <Enemy> liveEnemies = enemies;
	
	
	
	
	public Shooter() {
		dim=Toolkit.getDefaultToolkit().getScreenSize();
		jFrame = new JFrame("Starship Shooter");
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(jWidth,jHeight);
		jFrame.setLocation((dim.width/2-jFrame.getWidth()/2),(dim.height/2-jFrame.getHeight()/2));
		jFrame.setBackground(Color.BLACK);
		painter = new Painter();
		menuPanel = new MenuPanel();
		jFrame.add(painter);
		
		
		jFrame.setResizable(false);
		jFrame.addKeyListener(this);
		
		startGame();
		
		
	}
	
	public void startGame() {
		starDisp=0;
		for(int i =0; i<75;i++) {
			stars.add(new Point(Picker.nextInt(575),Picker.nextInt(500)));
		}
		 playerLives=3;
		 dx=0;
		 score=0;
		 
		
		
		
		player = new Player(new Point(285,460),ShapeList.playerPoly);
			for(int i =0 ;i<=8;i+=2) {
			enemies.add(new Enemy(60*i,10,1,1/*i==0||i==4||i==8?1:-1*/));
			
			}
			for(int i =0 ;i<=9;i+=2) {
				enemies.add(new Enemy(60*i,70,3,i==0||i==4||i==8?-1:1));
			}
			enemyCount=enemies.size();
			
			
			
			
			timer.start();
	}
	
	public      static void main(String[]args) {
		shooter = new Shooter();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//updates the JPanel and adds a tick to the time
		painter.repaint();
		ticks++;
		if(ticks%3==0)
		starDisp++;
		
		if(!paused) {
		//chooses what enemy will shoot and what enemy will shoot
		if(enemies.size()>0) {
		moveEnemy=enemies.get(Picker.nextInt(enemies.size()));
		shootEnemy=enemies.get(Picker.nextInt(enemies.size()));
		}
		
		if(enemyCount<=0||playerLives<=0) {
			direction=0;
			stars.removeAll(stars);
			enemies.removeAll(enemies);
			bullets.removeAll(bullets);
			eBullets.removeAll(eBullets);
			startGame();
			
		}
		
		/*picks da stars
		if(ticks%1000==0) {
			stars.removeAll(stars);
			for(int i =0; i<75;i++) {
				stars.add(new Point(Picker.nextInt(575),Picker.nextInt(500)));
			}
		}
		*/
		
		//chooses what the next random move will be
		switch(Picker.nextInt(3)) {
		case 0: move=0;
		break;
		case 1: move=1;
		break;
		case 2: move=-1;
		break;
		}
		
		if(bullets.size()>10) {
				bullets.remove(0);
			}
		while(eBullets.size()>10000) {
			eBullets.remove(0);
		}
		
		//Tests collision between two enemies
			for(int i=0; i<=enemies.size()-1;i++){  
				Enemy e = enemies.get(i);
				for(int j=0; j<=enemies.size()-1;j++) {
					if(i!=j) {
						Enemy t =enemies.get(j);
						/*This is a prime example of redundant code
						 * if(e.getY()==t.getY()&&(e.getLeftX()==t.getRightX()||e.getLeftX()==t.getRightX()+1||e.getRightX()==t.getLeftX()+1)) {
							e.direction=1;
							t.direction=-1;
						}*/
						if(e.getY()==t.getY()&&(e.getRightX()-t.getLeftX()<=5&&e.getRightX()-t.getLeftX()>=-5)) {
							e.direction=-1;
							t.direction=1;
						}
					}
				
				}
			
		}
		
		
		
		//Tests collision between bullets and enemies
		for(Iterator<Enemy> iterator=enemies.iterator(); iterator.hasNext();) {
				Enemy e = iterator.next();
				for(Iterator<Bullet> bIterator = bullets.iterator(); bIterator.hasNext();) {
					Bullet b = bIterator.next();
					if(e.transEnemy.intersects(new Rectangle(b.getX(),b.getY(),10,20))) {
						e.health-=1;
						bIterator.remove();
						if(e.health==0) {
						iterator.remove();
						enemyCount--;
						score+=10*e.initHealth;
						}
					
						
						
					}
				}
			}
		
		
		
		//Tests collision between bullets and player
		for(Iterator<Bullet> bIterator = eBullets.iterator(); bIterator.hasNext();) {
			Bullet b = bIterator.next();
			if(b.getY()>=480) {
				bIterator.remove();
			}
			if(player.transPlayer.intersects(new Rectangle(b.getX(),b.getY(),10,20))) {
				bIterator.remove();
				playerLives--;
			}
		}
		
		//Tells the bullets to move
		if(ticks%2==0) {
			for(Bullet b: bullets) {
				b.transBulletY(1);
			}
			for(Bullet b: eBullets) {
				b.transBulletY(-1);
			}
			
			
		}
		
		//Tells the enemies to move
		if(ticks%20==0) {for(Iterator<Enemy> iterator=enemies.iterator(); iterator.hasNext();) {
			Enemy e =iterator.next();
			e.transEnemy(1*e.direction*60/(3*(e.health+2)));
			if(e.getX()+20>560) {
				e.switchDirection();
			}
			if(e.getX()<10) {
				e.switchDirection();}
			}
		}
		
		
		//Tells the enemies to shoot
		if(ticks%350==0) {
			for(Enemy e: enemies){
			if(Picker.nextInt(3)==0)
			eBullets.add(new Bullet(e.getX()+2,e.bulletY));
			
			if(e.getX()+20>=560||e.getX()<=10) {
				eBullets.add(new Bullet(e.getX()+2,e.bulletY));
			}
		}
		}
		//Tells the player to move if holding key
		if(ticks%5==0&&(player.getCenterX()>20&&player.getCenterX()<480)) {
			player.transPlayer(direction,0);
			dx+=direction;
			}

		//Keeps the Player in Bounds
		if(dx>=265) {
				player.transPlayer(265-dx, 0);
				dx=265;
			}
			if(dx<=-265) {
				player.transPlayer(-265-dx, 0);
				dx=-265;
			}
		
		//Bullet Capper
		if(ticks%200==0) {
			bulletsInTick=0;
			bulletsCapped=false;
		}
		}
		
		
		}
		
	

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		
		if(i==KeyEvent.VK_SPACE&&!bulletsCapped&&!paused) {
			
			bullets.add(new Bullet(player.getBulletPoint()+2,player.getCenterY()-30,Color.cyan));
			bulletsInTick++;
			if(gunKeyHeld) {
			if(bulletsInTick>=1){
			bulletsCapped=true;}
			}
			if(!gunKeyHeld&&bulletsInTick*4>=5) {
				bulletsCapped=true;
			}
			gunKeyHeld=true;
			lastKey=0;
		}
		
		/*Big Jumps
		if(i==KeyEvent.VK_Z&&!paused) {
			if(dx>=-265){
			player.transPlayer(-60,0 );
			dx-=60;
			
				}
		}
		
		
		if(i==KeyEvent.VK_C&&!paused) {
			{
			player.transPlayer(60,0 );
			dx+=60;
			}
			}*/
		
		//Fluid movement
		if(i==KeyEvent.VK_A&&!paused||i==KeyEvent.VK_LEFT&&!paused) {
			direction=-1;}
		if(i==KeyEvent.VK_D&&!paused||i==KeyEvent.VK_RIGHT&&!paused) {
			direction=1;}
		//Pause
		if(i==KeyEvent.VK_CONTROL) {
			paused=!paused;
		}
		}
		

	@Override
	public void keyReleased(KeyEvent e) {
		int i =e.getKeyCode();
		if(i==e.KEY_RELEASED)
			bulletsCapped=false;
			gunKeyHeld=false;
			
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
