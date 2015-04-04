package engine.components;

import engine.core.GameObject;
import engine.datastructures.Pool;

public class TransformManager
{
	Pool<TransformComponent> transformPool = null;
	
	public
	TransformManager(int poolInitialSize, int poolMinSize, int poolMinSizeThreshold, int poolMaxSize, int poolMaxSizeThreshold)
	{
		transformPool = new Pool<TransformComponent>(
			TransformComponent.class,
			poolInitialSize,
			poolMinSize,
			poolMinSizeThreshold,
			poolMaxSize,
			poolMaxSizeThreshold);
	}
	
	public TransformComponent
	instantiate(GameObject parent, boolean isRendering)
	{
		TransformComponent component = transformPool.allocate();
		component.initialize(parent, isRendering);
		
		return component;
	}
	
	public void
	destroy(TransformComponent component)
	{
		transformPool.deallocate(component);
	}
	
	public void
	update()
	{
		transformPool.update();
	}
	
}
