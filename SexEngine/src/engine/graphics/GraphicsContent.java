package engine.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class GraphicsContent
{
	private HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	
	public void
	loadImage(String name, String path)
	{
		BufferedImage image = null;
		try 
		{
		    image = ImageIO.read(new File(path));
		    
		    images.put(name, image);
		} 
		catch (IOException e) 
		{
			System.err.println("File not found! Path: " + path);
		}
	}
	
	public void
	unloadImage(String name)
	{
		images.remove(name);
	}
	
	public void
	unloadAllImages()
	{
		images.clear();
	}
	
	public BufferedImage
	getImage(String name)
	{
		BufferedImage image = images.get(name);
		
		if (image == null)
		{			System.err.println("Image not found in graphics content! Name: " + name);
		}
		
		return image;
	}
	
}