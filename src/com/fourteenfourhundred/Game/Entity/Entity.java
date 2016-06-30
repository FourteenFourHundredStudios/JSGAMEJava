package com.fourteenfourhundred.Game.Entity;

import java.awt.Graphics;
import java.util.ArrayList;

import org.json.JSONObject;

import com.fourteenfourhundred.Game.Game;
import com.fourteenfourhundred.Game.CurrentPlayer.Player;

public class Entity {

	public static ArrayList<Entity> Entities = new  ArrayList<Entity>();
	JSONObject data;
	
	public Entity(JSONObject data){
		this.data=data;
	}
	
	public static void paintEntities(Graphics g){
		for(Entity s: Entities){
			s.paint(g);
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(Game.imageManager.getImage(data.getString("value")),data.getInt("x")+Player.xCam,data.getInt("y")+Player.yCam,null);
	}
	
}
