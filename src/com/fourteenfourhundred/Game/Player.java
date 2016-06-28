package com.fourteenfourhundred.Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player {

	public static int size=20;
	
	public static double rot=0;
	public static int speed=2;
	public static int speedCap=12;
	
	public static int xCam=-1*random(450,999);
	public static int yCam=-1*random(450,999);
	
	public static int vx=0;
	public static int vy=0;
	
	public static int x=Math.abs(xCam)+(Game.width/2);
	public static int y=Math.abs(yCam)+(Game.height/2);	
	
	public static String username="Marc (Java)";
	
	public static void draw(Graphics2D g){
  	  	g.rotate(rot,x+xCam+(Player.size/2),y+yCam+(Player.size/2));
        
        g.setColor(Color.RED);
        g.fillRect(x+xCam, y+yCam, Player.size, Player.size);
        
        
    	g.rotate(-rot,x+xCam+(Player.size/2),y+yCam+(Player.size/2));
        
    	
    	
    	//consoider moving some of this to a seperate thread or  something, because there is lag
    	
    	MotionHandler.capSpeed();
    	
    	if(MotionHandler.isCollision()){
    		Player.vx*=-1;
    		Player.vy*=-1;
    	}
    	
    	Player.x+=Player.vx;
  		Player.y+=Player.vy; 

  		Player.xCam-=Player.vx;
  		Player.yCam-=Player.vy;

	}
	
	public static Rectangle getBounds(){
		return new Rectangle(x+xCam, y+yCam, Player.size, Player.size);
	}
	
	public static Rectangle getNewBounds(){
		return new Rectangle(x+xCam+vx, y+yCam+vy, Player.size, Player.size);
	}
	
	public static int random(int min,int max){
		Random rand = new Random();
		return rand.nextInt(max) + min;
	}
	
}
