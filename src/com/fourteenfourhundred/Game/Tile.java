package com.fourteenfourhundred.Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;


public class Tile {
	
	public int x=0;
	public int y=0;
	public String image;
	public static ArrayList <Tile> Tiles= new ArrayList <Tile>();
	
	public Tile(int x,int y,String image){
		this.x=x;
		this.y=y;
		this.image=image;
	}
	
	public void paintTile(Graphics g){
		g.drawImage(Game.imageManager.getImage(image),x+Player.xCam,y+Player.yCam,null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x+Player.xCam,y+Player.yCam,30,30);
	}
	
	public static void paintMap(Graphics g){
		try{
			Iterator<Tile> i = Tiles.iterator();
			while (i.hasNext()) {
				Tile s = i.next();
				if(s.x<Game.width-Player.xCam && s.x>-30-Player.xCam&& s.y<Game.height-Player.yCam && s.y>-30-Player.yCam){
					s.paintTile(g);
				}
			}
		}catch(ConcurrentModificationException e){
			//for some reason still throws exception, but no issue in actual game
		}
	}

	public static void setTile(int x, int y, String image) {
		for(int i=0;i<Tile.Tiles.size();i++){
	        Tile tile=Tile.Tiles.get(i);
	        if (tile.x>x-30 && tile.x<x+30 && tile.y>y-30 && tile.y<y+30){
	        	Tile.Tiles.get(i).image=image;
	        	break;
	        }
	    }
		
	}
	
}
