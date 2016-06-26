package com.fourteenfourhundred.Game;

import java.awt.Graphics;
import java.util.ArrayList;

public class Entity {

	public static ArrayList<Entity> Entities = new  ArrayList<Entity>();
	
	public static void paintEntities(Graphics g){
		for(Entity s: Entities){
			s.paint(g);
		}
	}
	
	public void paint(Graphics g){
		
	}
	
}
