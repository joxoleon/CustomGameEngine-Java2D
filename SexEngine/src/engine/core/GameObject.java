package engine.core;

import java.util.HashMap;
import java.util.Map;

import engine.components.GameComponent;
import engine.components.TransformComponent;
import engine.god.God;



public class GameObject 
{
	//Fields	
	private static int IDGenerator = 0;
	private int ID = ++IDGenerator;
	
	private String tag = "";
	
	private TransformComponent transform;
	
	private HashMap components = new HashMap();
	
	
	//Constructors
	public
	GameObject()
	{
		this.transform = God.TransformManager.instantiate(this, true);
		this.addComponent(transform);
	}
	
	
	//Methods
	public int 
	getID()
	{
		return ID;
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
		return (GameComponent)components.get(name);
	}
	
	public GameComponent 
	removeComponent(String name)
	{
		return (GameComponent)components.remove(name);
	}
	
	public void 
	setTag(String tag)
	{
		if(tag != null)
			this.tag = tag;
	}
	
	public String 
	getTag()
	{
		return tag;
	}
	
}
