package engine.graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import engine.datastructures.Vector3;
import engine.utility.MathHelper;

public class Sprite
{
	protected BufferedImage image;
	
	private boolean hasOffset = false;
	
	private Vector3 position = new Vector3();
	private float rotation;
	private Vector3 initialScale = new Vector3(1, 1, 1);
	private Vector3 userScale = new Vector3(1, 1, 1);
	
	private AffineTransform backupTransform;
	
	public
	Sprite(BufferedImage image, int width, int height)
	{
		this.image = image;
		
		initialScale.x = (float)width / (float)image.getWidth();
		initialScale.y = (float)height / (float)image.getHeight();
	}
		
	private void
	initGraphicsContext(Graphics2D g2d)
	{
		backupTransform = g2d.getTransform();
		if (hasOffset)
		{
			g2d.translate(position.x, position.y);
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
	
}
