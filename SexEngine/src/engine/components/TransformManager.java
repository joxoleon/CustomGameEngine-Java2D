package engine.components;

import engine.core.Actor;
import engine.datastructures.Pool;

public class TransformManager
{
	Pool<TransformComponent> transformComponentPool = null;
	
	public
	TransformManager(int poolInitialSize, int poolMinSize, int poolMinSizeThreshold, int poolMaxSize, int poolMaxSizeThreshold)
	{
		transformComponentPool = new Pool<TransformComponent>(
			TransformComponent.class,
			poolInitialSize,
			poolMinSize,
			poolMinSizeThreshold,
			poolMaxSize,
			poolMaxSizeThreshold);
	}
	
	public TransformComponent
	instantiate(Actor parent)
	{
		TransformComponent component = transformComponentPool.allocate();
		component.initialize(parent);
		
		
		return component;
	}
	
	public void
	destroy(TransformComponent component)
	{
		transformComponentPool.deallocate(component);
	}
	
	public void
	update()
	{
		transformComponentPool.update();
	}
	
}
