package engine.components;

import engine.core.Actor;

/**
 *	A base abstract class that each Component class extends.
 * 	A Component class is one that contains a single functionality that an Actor(or GameObject) can possess.
 * @author Jovan Radivojsa, Dario Mirovic
 * @version 1.0
 */
public abstract class GameComponent 
{
	
	//Fields
	/** Reference to an Actor which contains the component. */
	protected Actor parent;
	
	/** Tells the ComponentManager if a Component is enabled or not. If it is disabled, the manager is going to skip the update of the given Component */
	private boolean isEnabled = true;
	

	//Constructors
	public 
	GameComponent()
	{
	}
	
	//Methods
	/** When redefined should return he name of the given component */
	public abstract String 	
	getName();
	
	public Actor
	getParent()
	{
		return parent;
	}
	
	public void
	setParent(Actor parent)
	{
		this.parent = parent;
	}
	
	/** Sets the isEnabled status of the Component. */
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

	/** It detaches the Component from its Parent by setting the parent reference to null.
	 * 	A template method pattern is implemented here, when the currently abstract method destroyComponent() is being called from inside of this method.
	 * 	Is called when the Actor is being disassembled (completely destroyed).
	 */
	public void
	destroy()
	{
		destroyComponent();
		parent = null;
	}
	
	/** It is supposed to finalize the "destruction" of the component, before the component is detached from its parent and returned to the designated Component pool.
	 * 	Is called from the destroy() method, as a part of the Template method pattern.
	 */
	protected abstract void 
	destroyComponent();
	
}
