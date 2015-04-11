package engine.components;

import java.awt.Graphics2D;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.ListIterator;

import engine.core.Actor;
import engine.core.GameTime;
import engine.datastructures.Pool;
import engine.graphics.SpriteSheet;

public class RenderingManager
{
	Pool<RenderingComponent> renderingComponentPool = null;
	
	LinkedList<RenderingComponent> renderingComponents = new LinkedList<RenderingComponent>();
	LinkedList<RenderingComponent> newRenderingComponents;
	
	LinkedList<SpriteSheet> spriteSheets = new LinkedList<SpriteSheet>();
	LinkedList<SpriteSheet> newSpriteSheets;
	
	public
	RenderingManager(int poolInitialSize, int poolMinSize, int poolMinSizeThreshold, int poolMaxSize, int poolMaxSizeThreshold)
	{
		synchronized(this)
		{
			renderingComponentPool = new Pool<RenderingComponent>(
				RenderingComponent.class,
				poolInitialSize,
				poolMinSize,
				poolMinSizeThreshold,
				poolMaxSize,
				poolMaxSizeThreshold);
			
			newRenderingComponents = new LinkedList<RenderingComponent>();
			newSpriteSheets = new LinkedList<SpriteSheet>();
		}
	}
	
	public RenderingComponent
	instantiate(Actor parent)
	{
		RenderingComponent component;
		synchronized(this)
		{
			component = renderingComponentPool.allocate();
		}
		component.initialize(parent);
		synchronized(this)
		{
			newRenderingComponents.add(component);	
		}
		
		return component;
	}
	
	public void
	destroy(RenderingComponent component)
	{
		synchronized(this)
		{
			renderingComponentPool.deallocate(component);
		}
	}
	
	public void
	render(Graphics2D g2d, GameTime gameTime)
	{
		synchronized(this)
		{
			renderingComponents.addAll(newRenderingComponents);
			newRenderingComponents.clear();
			
			spriteSheets.addAll(newSpriteSheets);
			newSpriteSheets.clear();
		}
		
		for (SpriteSheet spriteSheet : spriteSheets)
		{
			spriteSheet.update(gameTime);
		}
		
		
		ListIterator<RenderingComponent> iterator = renderingComponents.listIterator();
		
		while(iterator.hasNext())
		{
			RenderingComponent component = (RenderingComponent)iterator.next();
			if(component.parent.isDestroyed() == true)
			{
				iterator.remove();
				component.isLastRender = true;
			}
			else
			{
				if(component.isEnabled() == true)
					component.render(g2d);
			}
		}
		
		synchronized(this)
		{
			renderingComponentPool.update();
		}
	}
	
	public void
	registerSpriteSheet(SpriteSheet spriteSheet)
	{
		synchronized(this)
		{
			newSpriteSheets.add(spriteSheet);
		}
	}
	
}
