package com.fourteenfourhundred.Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.JSONObject;


public class Multiplayer {

	Socket user;
	static BufferedWriter bw;
	
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
	
	static void sendData(String label, String data){
		try{
			bw.write(label+"=>"+data);
			bw.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static void sendData(String label, JSONObject data){
		try{
			bw.write(label+"=>"+data.toString());
			bw.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void onData(String label,String data){
		
		//System.out.println("");
		switch(label){
			//lol this should be "tileData". I called tiles "blocks" when I was first writing the html5 version 
			case "blockData":
				JSONObject obj = new JSONObject(data);
				Tile.Tiles.add(new Tile(obj.getInt("x"),obj.getInt("y"),obj.getString("image")));
				break;
			case "setTile":
				obj = new JSONObject(data);
				Tile.setTile(obj.getInt("x"),obj.getInt("y"),obj.getString("value"));
				break;
			case "playerData":
				obj = new JSONObject(data);
					if(!obj.get("username").equals(Player.username)){
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
				}
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
