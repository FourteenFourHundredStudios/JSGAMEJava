package com.fourteenfourhundred.Game.CurrentPlayer;

import java.awt.event.KeyEvent;
import java.util.Iterator;

import com.fourteenfourhundred.Game.Game;

public class GameController extends Thread{

	public void run(){
		
		while(true){
			try {
				Thread.sleep(5);
			} catch (Exception e) {
			}
			Iterator<Integer> i = Game.keysDown.iterator();
			while (i.hasNext()) {
			   int s = i.next();
			   if(s==KeyEvent.VK_W){
				   Player.vy-=Player.speed;
			   }
			   if(s==KeyEvent.VK_A){
				   Player.vx-=Player.speed;
			   }
			   if(s==KeyEvent.VK_S){
				   Player.vy+=Player.speed;
			   }
			   if(s==KeyEvent.VK_D){
				   Player.vx+=Player.speed;
			   }
			   
				
		
			}
		}
	}
	
}
