package com.fourteenfourhundred.Game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONObject;


public class Multiplayer {

	Socket user;
	
	public Multiplayer(){
		try {
			user= new Socket("73.72.74.4",3002);
			new ServerReader().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onData(String label,String data){
		
		//System.out.println("");
		switch(label){
			case "blockData":
				JSONObject obj = new JSONObject(data);
				Tile.Tiles.add(new Tile(obj.getInt("x"),obj.getInt("y"),obj.getString("image")));
				break;
			case "setTile":
				obj = new JSONObject(data);
				for(int i=0;i<Tile.Tiles.size();i++){
			        Tile tile=Tile.Tiles.get(i);
			        if (tile.x>obj.getInt("x")-30 && tile.x<obj.getInt("x")+30 && tile.y>obj.getInt("y")-30 && tile.y<obj.getInt("y")+30){
			        	Tile.Tiles.get(i).image=obj.getString("value");
			        	break;
			        }
			    }
				break;		
		}
	}
	
	class ServerReader extends Thread{
		public void run(){
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(user.getInputStream()));
				while(true){
					String data=br.readLine();
					onData(data.split("=>")[0],data.split("=>")[1]);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
