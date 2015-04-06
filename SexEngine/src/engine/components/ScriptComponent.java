package engine.components;

import engine.core.Actor;
import engine.core.GameTime;
import engine.core.God;

public abstract class ScriptComponent
extends GameComponent
{
	public
	ScriptComponent(Actor parent)
	{
		this.parent = parent;
		
		God.ScriptManager.addScript(this);
	}
	
	public abstract void
	onCreate();
	
	public abstract void
	onUpdate(GameTime gameTime);
	
	public abstract void
	onDestroy();

	@Override
	public abstract	String 
	getName();

	@Override
	protected void 
	setEnabled(boolean enabled)
	{
		God.ScriptManager.removeScript(this);
	}

	@Override
	protected void 
	destroyComponent()
	{
		parent = null;
	}

}