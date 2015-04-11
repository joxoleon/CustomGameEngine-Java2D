package engine.components;

import engine.core.Actor;

public abstract class GameComponent 
{
	
	//Fields
	protected Actor parent;
	private boolean isEnabled = false;
	
	
	//Constructors
	public 
	GameComponent()
	{
	}
	
	//Methods
	public abstract String 	
	getName();
	
	public Actor
	getParent()
	{
		return parent;
	}
	
	public void 
	setEnabled(boolean enabled)
	{
		isEnabled = enabled;
	}
	
	public boolean 
	isEnabled()
	{
		return isEnabled;
	}

	public void
	destroy()
	{
		destroyComponent();
		parent = null;
	}
	
	protected abstract void 
	destroyComponent();

}
