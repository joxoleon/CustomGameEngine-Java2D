package engine.core;

import java.util.HashMap;
import java.util.LinkedList;

import engine.components.GameComponent;
import engine.components.RenderingComponent;
import engine.components.TransformComponent;



public class Actor extends GameObject
{
	//Fields
	private boolean isDestroyed = false;

	protected TransformComponent transform;
	protected RenderingComponent renderingComponent;

	//private HashMap<String, GameComponent> components = new HashMap<String, GameComponent>();
	LinkedList<GameComponent> components = new LinkedList<GameComponent>();
	
	
	
	//Constructors
	public
	Actor()
	{
		this.transform = God.TransformManager.instantiate(this);
		this.addComponent(transform);
		
		// Dodavanje rendering componente. Imaj u vidu da bi mogao da prosledis Sprite ili neka slicna sranja pa da se to ovde inicijalizuje.
	}
	
	
	//Methods
	public void 
	destroy()
	{
		if (isDestroyed == false)
		{
			isDestroyed = true;
			for(GameComponent component : components)
				component.disable();
			
			//Pozvadi God.destroy(this, vreme);
		}
	}
	
	void finalDestroy()
	{
		for(GameComponent component : components)
		{
			component.destroy();
		}
		components.clear();
	}

	
	public void 
	addComponent(GameComponent component)
	{
		if(getComponent(component.getName()) != null)
			removeComponent(component.getName());
			
		components.add(component);
	}
	
	public GameComponent 
	getComponent(String name)
	{
		for(GameComponent component : components)
		{
			if(component.getName().equals(name))
				return component;
		}
		
		return null;
	}
	
	public GameComponent[]
	getComponents()
	{
		GameComponent[] gameComponents = null;
		components.toArray(gameComponents);
		
		return gameComponents;
	}
	
	public GameComponent 
	removeComponent(String name)
	{
		GameComponent returnComponent = null;
		for(GameComponent component : components)
		{
			if(component.getName().equals(name))
			{
				returnComponent = component;
				components.remove(component);
				return returnComponent;
			}	
		}
		
		return null;
	}
	
	public TransformComponent
	getTransformComponent()
	{
		return transform;
	}
	
	public RenderingComponent
	getRenderingComponent()
	{
		return renderingComponent;
	}
	
}
