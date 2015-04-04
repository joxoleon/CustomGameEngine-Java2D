package engine.components;

import engine.core.GameObject;



public abstract class GameComponent 
{
	//Fields
	protected String name;
	protected GameObject parent;
	
	
	//Constructors
	public 
	GameComponent()
	{
	}
	
	
	//Methods
	public abstract String 	
	getName();
	
	public GameObject
	getParent()
	{
		return parent;
	}
	
	
	
}
