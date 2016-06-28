package com.fourteenfourhundred.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.json.JSONObject;

public class Item {
	
	public static ArrayList<Item> items= new ArrayList<Item>();
	public static int selectedItem=0;
	public static BufferedImage itemHolderSelected;
	public static BufferedImage itemHolder;
	
	public String item;
	public int count;
	
	public Item(String item,int count){
		this.item=item;
		this.count=count;
		itemHolderSelected=Game.imageManager.getImage("itemHolderSelected");
		itemHolder=Game.imageManager.getImage("itemHolder");
	}
	
	public void onAction(int x,int y){
		//Tile.setTile(x, y, item);
		//Multiplayer.s
		JSONObject data=new JSONObject();
		data.put("x", x);
		data.put("y", y);
		data.put("value", item);
		System.out.println("");
		Multiplayer.sendData("setTile", data);
	}
	
	public static void addItem(String item,int count){
		items.add(new Item(item,count));
	}
	
	public static void paint(Graphics g){
		
		if(items.size()>0){
			g.setColor(Color.BLACK);
			g.setFont(new Font("Helvetica", Font.BOLD, 30));
			g.drawString(items.get(selectedItem).item,29, (Game.height-110));
			for(int i=0;i<items.size();i++){
				Item item=items.get(i);
				if(i==selectedItem){
					g.drawImage(itemHolderSelected, ((30+50)*i)+30, Game.height-100,60,60,null);
				}else{
					g.drawImage(itemHolder, ((30+50)*i)+30, Game.height-100,60,60,null);
				}
				BufferedImage img=Game.imageManager.getImage(item.item);
		
				g.drawImage(img, ((30+50)*i)+45, (Game.height-100)+15,30,30,null);
	
				g.setFont(new Font("Helvetica", Font.PLAIN, 15));
				g.drawString(item.count+"",((30+50)*i)+36, (Game.height-100)+18);
	
			}
		}
	}

	public static void action(int x, int y) {
		items.get(selectedItem).onAction(x, y);
	}
	
}
