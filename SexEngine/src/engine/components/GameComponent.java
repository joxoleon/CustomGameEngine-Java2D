package engine.components;

import engine.core.Actor;

public abstract class GameComponent 
{
	
	//Fields
	protected Actor parent;
	protected boolean isEnabled;
	
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
	enable()
	{
		if (isEnabled == false)
		{
			isEnabled = true;
			setEnabled(true);
		}
	}
	
	public void 
	disable()
	{
		if (isEnabled == true)
		{
			isEnabled = false;
			setEnabled(false);
		}
	}
	
	public boolean 
	isEnabled()
	{
		return isEnabled;
	}
	
	protected abstract void 
	setEnabled(boolean enabled);

	public void
	destroy()
	{
		destroyComponent();
		parent = null;
	}
	
	protected abstract void 
	destroyComponent();

}
