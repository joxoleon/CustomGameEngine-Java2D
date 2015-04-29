package engine.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class GraphicsContent
{
	// Fields.
	private HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	
	private HashMap<String, BufferedImage[]> spriteSheets = new HashMap<String, BufferedImage[]>();
	
	
	// Methods.
	public void
	loadSprites(String resourcePath)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(resourcePath));
			
			String line = reader.readLine();
			
			while(line != null)
			{
				
				if (line.equals("") || line.startsWith("//"))
				{
					line = reader.readLine();
					continue;
				}
				
				String[] tokens = line.split(" ");
				if (tokens.length == 2)
				{
					loadImage(tokens[0], tokens[1]);
				}
				else
				{
					System.err.println("Unexpected input in images resource file! File path: " + resourcePath);
				}
				
				line = reader.readLine();			
			}
			
			reader.close();
		}
		catch (NumberFormatException e)
		{
			System.err.println("Could not parse an integer from images resource file! File path: " + resourcePath);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Could not load image resource file! File path: " + resourcePath);
		}
		catch(IOException e)
		{
			System.err.println("Error reading file line! File path: " + resourcePath);
		}
	}
	
	public void
	loadSpriteSheets(String resourcePath)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(resourcePath));
			
			String line = reader.readLine();
			
			while(line != null)
			{
				
				if (line.equals("") || line.startsWith("//"))
				{
					line = reader.readLine();
					continue;
				}
				
				String[] tokens = line.split(" ");
				if (tokens.length == 9)
				{
					loadSpriteSheet(
						tokens[0],
						tokens[1],
						Integer.parseInt(tokens[2]),
						Integer.parseInt(tokens[3]),
						Integer.parseInt(tokens[4]),
						Integer.parseInt(tokens[5]),
						Integer.parseInt(tokens[6]),
						Integer.parseInt(tokens[7]),
						Integer.parseInt(tokens[8]));
				}
				else
				{
					System.err.println("Unexpected input in spritesheets resource file! File path: " + resourcePath);
				}
				
				line = reader.readLine();			
			}
			
			reader.close();
		}
		catch (NumberFormatException e)
		{
			System.err.println("Could not parse an integer from images resource file! File path: " + resourcePath);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Could not load image resource file! File path: " + resourcePath);
		}
		catch(IOException e)
		{
			System.err.println("Error reading file line! File path: " + resourcePath);
		}
	}
	
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
	loadTerrainTiles(String path, int width, int height, int rows, int columns, int count)
	{
		try
		{
			BufferedImage image = ImageIO.read(new File(path));
			BufferedImage[] sprites = new BufferedImage[count];
			for (int i = 0; i < rows; i++)
			{
				for (int j = 0; j < columns; j++)
				{
					if(i * columns + j > count)
						break;
					
					BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2d = (Graphics2D)newImage.getGraphics();
					g2d.drawImage(
							image, 
							0, 0, width, height, 
							j * width, i * height, (j + 1) * width, (i + 1) * height,
							null);
					
					//Dodaj ucitavanje imena iz fajla
					//Dodaj sprajt sa odgovarajucim imenom u images
					
				}
			}
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("File not found! Path: " + path);
		}
	    
	}
	
	public void
	loadSpriteSheet(String name, String path, int xorigin, int yorigin, int width, int height, int rows, int columns, int count)
	{
		try 
		{
		    BufferedImage image = ImageIO.read(new File(path));
		    BufferedImage[] sprites = new BufferedImage[count];
		    
			for (int i = 0; i < rows; i++)
			{
				for (int j = 0; j < columns; j++)
				{
					int index = i * columns + j;
					if (index >= count)
						break;
					
					sprites[index] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2d = (Graphics2D)sprites[index].getGraphics();
					g2d.drawImage(
						image, 
						0, 0, width, height, 
						xorigin + j * width, yorigin + i * height, xorigin + (j + 1) * width, yorigin + (i + 1) * height,
						null);
				}
			}
			
			spriteSheets.put(name, sprites);
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
	unloadSpriteSheet(String name)
	{
		spriteSheets.remove(name);
	}
	
	public void
	unloadAllImages()
	{
		images.clear();
	}
	
	public void
	unloadAllSpriteSheets()
	{
		spriteSheets.clear();
	}
	
	public void
	unloadAllContent()
	{
		unloadAllImages();
		unloadAllSpriteSheets();
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
	
	public BufferedImage[]
	getSpriteSheet(String name)
	{
		BufferedImage[] spriteSheet = spriteSheets.get(name);
		
		if (spriteSheet == null)
		{
			System.err.println("SpriteSheet not found in graphics content! Name: " + name);
		}
		
		return spriteSheet;
	}
	
}

