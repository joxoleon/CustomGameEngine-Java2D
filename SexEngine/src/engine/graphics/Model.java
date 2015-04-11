package engine.graphics;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class Model
{
	// Fields.
	private String name;
	private LinkedList<Sprite> sprites = new LinkedList<Sprite>();
	
	
	// Methods.
	public void 
	render(Graphics2D g2d)
	{
		for (Sprite sprite : sprites)
		{
			sprite.render(g2d, true);
		}
	}
	
	public 
	Model(String name)
	{
		this.name = name;
	}
	
	public Sprite 
	getSprite(String name)
	{
		for (Sprite sprite : sprites)
		{
			if(sprite.getName().equals(name))
				return sprite;
		}
		
		System.err.println("Sprite: " + name + " in model: " + this.name + " not found!");
		return null;
	}
	
	public void
	addSprite(Sprite sprite)
	{
		sprites.addLast(sprite);
	}
	
	public String 
	getName()
	{
		return name;
	}

}
