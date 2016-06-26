package com.fourteenfourhundred.Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;


public class ImageManager {

	static Map<String,BufferedImage> images=new HashMap<String,BufferedImage>();
	
	public static void loadImages(){
		try {
			images.put("grass", ImageIO.read(new File("images/Grass.png")));
			images.put("tree", ImageIO.read(new File("images/Tree1.png")));
			images.put("tallgrass", ImageIO.read(new File("images/TallGrass1.png")));
			images.put("stone", ImageIO.read(new File("images/stone.png")));
			images.put("player", ImageIO.read(new File("images/player.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getImage(String image){		
		return images.get(image);
	}
	
	
}
