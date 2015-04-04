package engine.core;

import java.util.HashMap;

import engine.components.GameComponent;
import engine.components.RenderingComponent;
import engine.components.TransformComponent;
import engine.god.God;



public class Actor extends GameObject
{
	//Fields
	private boolean isDestroyed = false;

	protected TransformComponent transform;	
	protected RenderingComponent renderingComponent;

	private HashMap<String, GameComponent> components = new HashMap<String, GameComponent>();
	
	
	
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
		isDestroyed = true;
	}

	
	public void 
	addComponent(GameComponent component)
	{
		if(components.get(component.getName()) != null)
			removeComponent(component.getName());
			
		components.put(component.getName(), component);
	}
	
	public GameComponent 
	getComponent(String name)
	{
		return components.get(name);
	}
	
	public GameComponent 
	removeComponent(String name)
	{
		return components.remove(name);
	}
	
}
