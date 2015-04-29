package engine.graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import engine.components.RenderingComponent;
import engine.datastructures.Vector3;
import engine.utility.MathHelper;

public class Sprite
{
	// Fields.
	protected BufferedImage image;
	private String name;
	
	private Vector3 position = new Vector3();
	private float rotation;
	protected Vector3 initialScale = new Vector3(1, 1, 1);
	private Vector3 userScale = new Vector3(1, 1, 1);
	
	private AffineTransform backupTransform;
	
	public boolean isSelfRotating;
	private boolean hasOffset;
	
	
	// Constructors.
	public
	Sprite(String name, BufferedImage image, int width, int height)
	{
		this.name = name;
		this.image = image;
		
		initialScale.x = (float)width / (float)image.getWidth();
		initialScale.y = (float)height / (float)image.getHeight();
	}
	
	// Constructor for cloning.
	protected 
	Sprite(BufferedImage image, float initialScalex, float initialScaley, String name)
	{
		this.name = name;
		this.image = image;
		initialScale.x = initialScalex;
		initialScale.y = initialScaley;
	}
		
	
	// Methods.
	private void
	initGraphicsContext(Graphics2D g2d)
	{
		backupTransform = g2d.getTransform();
		if (hasOffset)
		{
			g2d.translate(position.x, position.y);
			if(isSelfRotating)
			{
				RenderingComponent.reverseCurrentRotation(g2d);
			}
			g2d.rotate(rotation);
			g2d.scale(initialScale.x * userScale.x, initialScale.y * userScale.y);
		}
		else
		{
			g2d.scale(initialScale.x * userScale.x, initialScale.y * userScale.y);
		}
	}
	
	private void
	restoreGraphicsContext(Graphics2D g2d)
	{
		g2d.setTransform(backupTransform);
	}
	
	public void
	render(Graphics2D g2d, boolean isCentered)
	{
		initGraphicsContext(g2d);
				
		float offsetX = 0, offsetY = 0;
		
		if (isCentered == true)
		{
			offsetX = image.getWidth() / 2.0f;
			offsetY = image.getHeight() / 2.0f;
		}
		
		
		g2d.drawImage(image, (int)-offsetX, (int)-offsetY, null);
		
		restoreGraphicsContext(g2d);
	}
	
	public void
	render(Graphics2D g2d, float posx, float posy, float width, float height, boolean isCentered)
	{
		float offsetX = 0, offsetY = 0;
		
		if (isCentered == true)
		{
			offsetX = image.getWidth() / 2.0f;
			offsetY = image.getHeight() / 2.0f;
		}
		
		g2d.drawImage(image, (int)posx, (int)posy, (int)width, (int)height, null);
	}
	
	public String 
	getName()
	{
		return name;
	}
	
	public int
	getWidth()
	{
		return (int)(image.getWidth() * initialScale.x * userScale.x);// * userScale.x * initialScale.x);
	}
	
	public int
	getHeight()
	{
		return (int)(image.getHeight() * initialScale.y * userScale.y);// * userScale.y * initialScale.y);
	}
	
	public void
	setPosition(float x, float y)
	{
		position.x = x;
		position.y = y;
		
		updateOffset();
	}
	
	public void
	setRotation(float rotation)
	{
		this.rotation = MathHelper.clampAngle(rotation);
		
		updateOffset();
	}
	
	public void
	setScale(float x, float y)
	{
		userScale.x = x;
		userScale.y = y;
		
		updateOffset();
	}
	
	private void
	updateOffset()
	{
		if (position.x == 0 && position.y == 0 &
			rotation == 0.0f)
		{
			hasOffset = false;
		}
		else
		{
			hasOffset = true;
		}
	}
	
	public BufferedImage
	getImage()
	{
		return image;
	}
	
	public Sprite
	clone()
	{
		return new Sprite(image, initialScale.x, initialScale.y, this.name + "Clone");
	}
	
	public String
	toString()
	{
		return new String("Sprite - Name: " + name + "  position: " + position + " , rotation: " + rotation + " , InitialScale: " + initialScale + ", UserScale: " + userScale);
	}

}
