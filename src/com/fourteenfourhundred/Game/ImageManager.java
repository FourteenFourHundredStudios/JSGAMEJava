package com.fourteenfourhundred.Game;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


public class ImageManager {

	Map<String,GameImage> images=new HashMap<String,GameImage>();
	
	public ImageManager(){
		loadImages();
	}
	
	public void loadImages(){
		try {	
			images.put("tree", new GameImage(new BufferedImage[]{
				ImageIO.read(new File("images/Tree1.png")),
				ImageIO.read(new File("images/Tree2.png"))
			}));
			images.put("tallgrass", new GameImage(new BufferedImage[]{
				ImageIO.read(new File("images/TallGrass1.png")),
				ImageIO.read(new File("images/TallGrass2.png"))
			}));
			images.put("grass", new GameImage(ImageIO.read(new File("images/Grass.png"))));
			images.put("stone", new GameImage(ImageIO.read(new File("images/stone.png"))));
			images.put("player", new GameImage(ImageIO.read(new File("images/player.png"))));
			
			images.put("itemHolder", new GameImage(ImageIO.read(new File("images/itemHolder.png"))));
			images.put("itemHolderSelected", new GameImage(ImageIO.read(new File("images/itemHolderSelected.png"))));
			
			
			new updateImages().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 class GameImage {
		BufferedImage image;
		BufferedImage[] images;
		int currentImageFrame=0;
		boolean isArray;
		public GameImage(BufferedImage[] images){
			isArray=true;
			this.images=images;
		}
		public GameImage(BufferedImage image){
			isArray=false;
			this.image=image;
		}
		public void nextImage(){
			if(isArray){
				if(currentImageFrame==images.length-1){
					currentImageFrame=0;
				}else{
					currentImageFrame++;
				}
			}
		}
		public BufferedImage getImage(){
			if(isArray){
				return images[currentImageFrame];
			}else{
				return image;
			}
		}
	}
	
	 class updateImages extends Thread{
		public void run(){
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}

				for(GameImage image:images.values()){
					image.nextImage();
				}
				
			}
		}
	}
	
	
	
	public BufferedImage getImage(String image){
		//System.out.println(images.get(image));
		return images.get(image).getImage();
		//return images.get(image);
	}
	
	
}
