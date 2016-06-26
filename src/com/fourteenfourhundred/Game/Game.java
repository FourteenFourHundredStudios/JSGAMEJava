package com.fourteenfourhundred.Game;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener,MouseMotionListener{

	
	/**
	 * WOA GAME 
	 */
	private static final long serialVersionUID = 1075262636678579087L;
	private Image dbImage; 
    private Graphics dbg;
    public static ArrayList<Integer> keysDown=new ArrayList<Integer>();
    public static int width=1000;
    public static int height=600;
    
	int size=50;

	int mx=0;
	int my=0;
	
	public Game(){
		super("Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(width,height);
		this.setVisible(true);		
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.setLocationRelativeTo(null);
		
		new repaint().start();
		new GameController().start();
		new MotionHandler().start();		
		new Multiplayer();		
		ImageManager.loadImages();
		//this.setComponentOrientation(null);	
		
	}
	
	class repaint extends Thread{
		public void run(){
			while(true){				
				try{
					Thread.sleep(10);
				}catch(Exception e){
					
				}
				
				Player.rot= Math.atan2((my-(Player.y+Player.yCam)),(mx-(Player.x+Player.xCam)));
				repaint();				
			}
		}
	}
	
	public void paint(Graphics g){
		dbImage = createImage(width, height);
	    dbg = dbImage.getGraphics();
	    paintComponent((Graphics2D) dbg);
	    g.drawImage(dbImage, 0, 0, null);
	}

    public void paintComponent(Graphics2D g){
        super.paint(g);
        Tile.paintMap(g);
        Player.draw(g);
        GamePlayer.paintPlayers(g);
    }

    
	public static void main(String[] eeee){
		new Game();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		keysDown.add(key);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		Iterator<Integer> i = keysDown.iterator();
		while (i.hasNext()) {
		   int s = i.next();
		   if(s==e.getKeyCode()){
			   i.remove();
		   }
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx=e.getX();
		my=e.getY();
	}
	
}
