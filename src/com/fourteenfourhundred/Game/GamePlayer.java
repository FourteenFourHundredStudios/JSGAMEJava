package com.fourteenfourhundred.Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class GamePlayer {
	
	public static ArrayList <GamePlayer> Players= new ArrayList <GamePlayer>();
	public String username;
	public double rot;
	public int x=0;
	public int y=0;
	
	public GamePlayer(int x,int y,String username,double rot){
		this.x=x;
		this.y=y;
		this.rot=rot;
		this.username=username;
	}
	
	public void paintPlayer(Graphics2D g){
		
		g.rotate(rot+360,x+Player.xCam+(Player.size/2),y+Player.yCam+(Player.size/2));
        
        g.setColor(Color.RED);
        //g.fillRect(x+Player.xCam, y+Player.yCam, Player.size, Player.size);
       // g.fillRect(x, y, width, height);
        g.drawImage(Game.imageManager.getImage("player"),x+Player.xCam,y+Player.yCam,null);
        
        
    	g.rotate(-(rot+360),x+Player.xCam+(Player.size/2),y+Player.yCam+(Player.size/2));
    	
    	g.setColor(Color.BLACK);
		g.drawString(username, x+Player.xCam, (y-10)+Player.yCam);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x+Player.xCam,y+Player.yCam,30,30);
	}
	
	public static void paintPlayers(Graphics2D g){
		try{
			Iterator<GamePlayer> i = Players.iterator();
			while (i.hasNext()) {
				GamePlayer s = i.next();
				if(s.x<Game.width-Player.xCam && s.x>-30-Player.xCam&& s.y<Game.height-Player.yCam && s.y>-30-Player.yCam){
					s.paintPlayer(g);
				}
			}
		}catch(ConcurrentModificationException e){
			//for some reason still throws exception, but no issue in actual game
		}
	}
	
	
	
}
