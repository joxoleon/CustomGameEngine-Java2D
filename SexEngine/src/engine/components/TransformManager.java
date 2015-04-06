package engine.components;

import java.util.LinkedList;

import engine.core.Actor;
import engine.core.GameTime;
import engine.datastructures.Pool;

public class TransformManager
{
	Pool<TransformComponent> transformComponentPool = null;
	LinkedList<TransformComponent> transformComponents = new LinkedList<TransformComponent>();
	
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
		transformComponents.add(component);
		
		return component;
	}
	
	public void
	destroy(TransformComponent component)
	{
		transformComponentPool.deallocate(component);
	}
	
	public void
	prepareToDie(TransformComponent component)
	{
		transformComponents.remove(component);
	}
	
	public void
	update(GameTime gameTime)
	{
		for(TransformComponent component : transformComponents)
			component.update(gameTime);
		transformComponentPool.update();
	}
	
}
