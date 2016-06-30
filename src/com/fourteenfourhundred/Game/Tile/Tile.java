package com.fourteenfourhundred.Game.Tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import com.fourteenfourhundred.Game.Game;
import com.fourteenfourhundred.Game.CurrentPlayer.Player;


public class Tile {
	
	public int x=0;
	public int y=0;
	public String image;
	public static ArrayList <Tile> Tiles= new ArrayList <Tile>();
	
	static boolean shading=false;
	
	public Tile(int x,int y,String image){
		this.x=x;
		this.y=y;
		this.image=image;
	}
	
	public void paintTile(Graphics g){
		g.drawImage(Game.imageManager.getImage(image),x+Player.xCam,y+Player.yCam,null);
	}
	
	public void paintShadedTile(Graphics g){
		BufferedImage img=Game.imageManager.getImage(image);
		g.drawImage(shade(img),x+Player.xCam,y+Player.yCam,null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x+Player.xCam,y+Player.yCam,30,30);
	}
	
	
	public  BufferedImage shade(BufferedImage img) {
		
		BufferedImage imgs=img;
		
	    for (int x = 0; x < imgs.getWidth(); x++) {
	        for (int y = 0; y < imgs.getHeight(); y++) {

	            Color color = new Color(imgs.getRGB(x, y));

	            Color brighter = color.darker();
	           // brighter.brighter();

	            imgs.setRGB(x, y, brighter.getRGB());
	        }
	    }
	    return imgs;
	}
	
	public static void paintMap(Graphics g){
		try{
			Iterator<Tile> i = Tiles.iterator();
			while (i.hasNext()) {
				Tile s = i.next();
				if(s.x<Game.width-Player.xCam && s.x>-30-Player.xCam&& s.y<Game.height-Player.yCam && s.y>-30-Player.yCam){
					if(!shading){
						s.paintTile(g);
					}else{
						s.paintShadedTile(g);
					}
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
