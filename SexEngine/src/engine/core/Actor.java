package engine.core;

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
		
		this.renderingComponent = God.RenderingManager.instantiate(this);
		this.addComponent(renderingComponent);
		
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
	
	void
	finalDestroy()
	{
		for(GameComponent component : components)
		{
			component.destroy();
		}
		components.clear();
	}

	// Ako postoji komponenta, samo inicijalizovati novim vrijednostima, ne uklanjati.
	public void 
	addComponent(GameComponent component)
	{		
		GameComponent prevComponent = getComponent(component.getName());
		
		if(prevComponent != null)
		{
			if (component.getName().equals("TransformComponent") ||
				component.getName().equals("RenderingComponent"))
			{
				return;
			}
		}
		
		if (prevComponent != null)
		{
			removeComponent(prevComponent.getName());
		}
		
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
		if (name == null ||
			name.equals("TransformComponent") ||
			name.equals("RenderingComponent"))
		{
			return null;
		}
		
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
	
	public boolean
	isDestroyed()
	{
		return isDestroyed;
	}
	
}
