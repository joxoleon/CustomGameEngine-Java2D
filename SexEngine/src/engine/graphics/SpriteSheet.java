package engine.graphics;

import java.awt.image.BufferedImage;

import engine.core.GameTime;
import engine.core.God;

public class SpriteSheet
extends Sprite
{
	private BufferedImage[] spriteSheetImages;
	
	private int frameCounter;
	private float frameTime;
	private float targetFrameTime;
	private boolean isPlaying;
	
	public
	SpriteSheet(BufferedImage[] images, boolean hasOffset, float animationTime)
	{
		this(images, images[0].getWidth(), images[0].getHeight(), animationTime);
	}
	
	public
	SpriteSheet(BufferedImage[] images, int width, int height, float animationTime)
	{
		super(images[0], width, height);
		
		this.spriteSheetImages = images;
		
		frameCounter = 0;
		frameTime = 0.0f;
		targetFrameTime = animationTime / images.length;
		
		God.RenderingManager.registerSpriteSheet(this);
	}
	
	public void
	update(GameTime gameTime)
	{
		if (isPlaying == true)
		{
			frameTime += gameTime.dt_s();
			while (frameTime >= targetFrameTime)
			{
				frameTime -= targetFrameTime;
				frameCounter = (frameCounter + 1) % spriteSheetImages.length;
			}
			this.image = this.spriteSheetImages[frameCounter];
		}
	}
	
	public void
	playAnimation()
	{
		if (isPlaying == false)
		{
			frameTime = 0.0f;
			isPlaying = true;
		}
	}
	
	public void
	stopAnimation()
	{
		isPlaying = false;
	}
	
	public void
	resetAnimation()
	{
		isPlaying = false;
		frameCounter = 0;
	}
	


}
