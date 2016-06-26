package com.fourteenfourhundred.Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.JSONObject;


public class Multiplayer {

	Socket user;
	BufferedWriter bw;
	
	public Multiplayer(){
		try {
			user= new Socket("73.72.74.4",3002);
			bw=new BufferedWriter(new OutputStreamWriter(user.getOutputStream()));
			new ServerReader().start();
			new PlayerEmit().start();
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
			case "playerData":
				obj = new JSONObject(data);
				for(GamePlayer player:GamePlayer.Players){
					if(player.username.equals(obj.getString("username"))){
						player.x=obj.getInt("x");
						player.y=obj.getInt("y");
						player.rot=obj.getDouble("theta");
						return;
					}
				}
				System.out.println("New player detected: "+obj.getString("username"));
				GamePlayer.Players.add(new GamePlayer(
					obj.getInt("x"),
					obj.getInt("y"),
					obj.getString("username"),
					obj.getDouble("theta")
				));
				break;
		}
	}
	
	class ServerReader extends Thread{
		public void run(){
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(user.getInputStream()));
				while(true){
					//label=>{jsondata:data}
					String data=br.readLine();
					onData(data.split("=>")[0],data.split("=>")[1]);
				}
			}catch(Exception e){
				
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	//cd "/Users/Marc/Documents/Code/JSGAME";node server.js;
	class PlayerEmit extends Thread{
		public void run(){
			try{
				while(true){
					JSONObject player = new JSONObject();
					player.put("x", Player.x);
					player.put("y", Player.y);
					player.put("username", Player.username);
					player.put("theta", Player.rot);
					
					//System.out.println(player.toString());
					bw.write("playerData=>"+player.toString());
					bw.flush();
					
					Thread.sleep(10);
				}
			}catch(Exception e){
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
}
