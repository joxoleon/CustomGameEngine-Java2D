package engine.components;

import java.util.LinkedList;

import engine.core.Actor;
import engine.datastructures.Pool;

public class RenderingManager
{
	Pool<RenderingComponent> renderingComponentPool = null;
	LinkedList<RenderingComponent> activeRenderingComponents = new LinkedList<RenderingComponent>();
	
	public
	RenderingManager(int poolInitialSize, int poolMinSize, int poolMinSizeThreshold, int poolMaxSize, int poolMaxSizeThreshold)
	{
		renderingComponentPool = new Pool<RenderingComponent>(
			RenderingComponent.class,
			poolInitialSize,
			poolMinSize,
			poolMinSizeThreshold,
			poolMaxSize,
			poolMaxSizeThreshold);
	}
	
	public RenderingComponent
	instantiate(Actor parent)
	{
		RenderingComponent component = renderingComponentPool.allocate();
		component.initialize(parent);
		
		return component;
	}
	
	private void 
	prepareToDie(RenderingComponent component)
	{
		activeRenderingComponents.remove(component);
	}
	
	public void
	destroy(RenderingComponent component)
	{
		renderingComponentPool.deallocate(component);
	}
	
	public void
	update()
	{
		renderingComponentPool.update();
	}
	
}
