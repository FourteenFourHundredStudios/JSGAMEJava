package com.fourteenfourhundred.Game;

import java.util.ConcurrentModificationException;

public class MotionHandler extends Thread{

	public void run(){
		
		while(true){
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
			if(Player.vx>0){
				Player.vx--;
			} 
			if(Player.vx<0){
				Player.vx++;
			}
			if(Player.vy>0){
				Player.vy--;
			} 
			if(Player.vy<0){
				Player.vy++;
			}
	
		}
	}
	
	public static boolean isCollision(){
		try{
			for(Tile t:Tile.Tiles){
				if(t.getBounds().intersects(Player.getNewBounds())&&(t.image.equals("stone")||t.image.equals("tree"))){
					return true;
				}
			}
		}catch(ConcurrentModificationException e){
			//again very stupid issue
		}
		return false;
	}
	
	public static void capSpeed(){
		if(Player.vx>Player.speedCap){
			Player.vx=Player.speedCap;
		}
		if(Player.vx<-Player.speedCap){
			Player.vx=-Player.speedCap;
		}
		if(Player.vy>Player.speedCap){
			Player.vy=Player.speedCap;
		}
		if(Player.vy<-Player.speedCap){
			Player.vy=-Player.speedCap;
		}
	}
	
}
