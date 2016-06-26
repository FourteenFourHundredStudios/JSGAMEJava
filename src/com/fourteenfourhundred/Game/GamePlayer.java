package com.fourteenfourhundred.Game;

import java.awt.Graphics;
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
		
	}
	
	public void paintPlayer(Graphics g){
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x+Player.xCam,y+Player.yCam,30,30);
	}
	
	public static void paintPlayers(Graphics g){
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
