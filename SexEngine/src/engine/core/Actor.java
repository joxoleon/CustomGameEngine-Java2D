package engine.core;

import java.util.HashMap;

import engine.components.GameComponent;
import engine.components.TransformComponent;
import engine.god.God;



public class Actor extends GameObject
{
	//Fields
	private TransformComponent transform;
	
	private HashMap<String, GameComponent> components = new HashMap<String, GameComponent>();
	
	
	//Constructors
	public
	Actor()
	{
		this.transform = God.TransformManager.instantiate(this);
		this.addComponent(transform);
	}
	
	
	//Methods
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
