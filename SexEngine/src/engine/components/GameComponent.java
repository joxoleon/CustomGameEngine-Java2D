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
	
	public void enable()
	{
		isEnabled = true;
	}
	
	public void disable()
	{
		isEnabled = false;
	}
	
	

}
