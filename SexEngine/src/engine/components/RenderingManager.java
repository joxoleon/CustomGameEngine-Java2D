package engine.components;

import java.awt.Graphics2D;
import java.util.LinkedList;

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
	
	private void 
	prepareToDie(RenderingComponent component)
	{
		renderingComponents.remove(component);
		component.isLastRender = true;
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
		
		for(RenderingComponent component : renderingComponents)
		{
			if(component.parent.isDestroyed())
			{
				prepareToDie(component);
			}
			else
			{
				component.render(g2d);
			}
		}
		
		synchronized(this)
		{
			renderingComponentPool.update();
		}
	}
	
}
