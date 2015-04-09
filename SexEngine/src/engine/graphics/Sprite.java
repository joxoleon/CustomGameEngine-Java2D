package engine.graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import engine.core.GameTime;
import engine.datastructures.Vector3;
import engine.utility.MathHelper;

public class Sprite
{
	private Vector3 position = new Vector3();
	private float rotation;
	private Vector3 initialScale = new Vector3(1, 1, 1);
	private Vector3 userScale = new Vector3(1, 1, 1);
	
	private BufferedImage simpleSpriteImage;
	private BufferedImage[] spriteSheetImages;
	private int currentSpriteSheetImage;
	
	private boolean isSpriteSheet = false;
	private boolean hasTransform = false;
	
	private AffineTransform backupTransform;
	
	public Sprite(BufferedImage image, boolean hasTransform)
	{
		this(image, image.getWidth(), image.getHeight(), hasTransform);
	}
	
	public Sprite(BufferedImage image, int width, int height, boolean hasTransform)
	{
		this.simpleSpriteImage = image;
		this.isSpriteSheet = false;
		this.hasTransform = hasTransform;
		
		initialScale.x = (float)width / (float)image.getWidth();
		initialScale.y = (float)height / (float)image.getHeight();
	}
	
	public Sprite(BufferedImage[] images, boolean hasOffset)
	{
		this.spriteSheetImages = images;
		this.isSpriteSheet = true;
		this.hasTransform = hasOffset;
		
		
	}
	
	public void update(GameTime gameTime)
	{
		
	}
	
	public void render(Graphics2D g2d, boolean isCentered)
	{
		float offsetX = 0, offsetY = 0;
		
		if (isCentered == true)
		{
			offsetX = simpleSpriteImage.getWidth() / 2.0f;
			offsetY = simpleSpriteImage.getHeight() / 2.0f;
		}
		
//		if(hasTransform)
//		{
			backupTransform = g2d.getTransform();
			g2d.translate(position.x, position.y);
			g2d.rotate(rotation);
			g2d.scale(initialScale.x * userScale.x, initialScale.y * userScale.y);
//		}
		
		if (isSpriteSheet == false)
			g2d.drawImage(simpleSpriteImage, (int)-offsetX, (int)-offsetY, null);
//		else
			//Logika za iscrtavanje spriteSheet-a
		
		
		
		
//		if(hasTransform)
//		{
			g2d.setTransform(backupTransform);
		
	}

	
	
	public void
	setPosition(float x, float y)
	{
		position.x = x;
		position.y = y;
	}
	
	public void
	setRotation(float rotation)
	{
		this.rotation = MathHelper.clampAngle(rotation);
	}
	
	public void
	setScale(float x, float y)
	{
		userScale.x = x;
		userScale.y = y;
	}

}
