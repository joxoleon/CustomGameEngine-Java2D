package engine.components;

import java.awt.Graphics2D;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.ListIterator;

import engine.core.Actor;
import engine.core.GameTime;
import engine.datastructures.Pool;

public class RenderingManager
{
	Pool<RenderingComponent> renderingComponentPool = null;
	LinkedList<RenderingComponent> renderingComponents = new LinkedList<RenderingComponent>();
	LinkedList<RenderingComponent> newRenderingComponents;
	
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
	render(Graphics2D g2d)
	{
		synchronized(this)
		{
			renderingComponents.addAll(newRenderingComponents);
			newRenderingComponents.clear();
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
	
}
