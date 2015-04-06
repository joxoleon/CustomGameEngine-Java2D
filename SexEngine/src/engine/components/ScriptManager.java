package engine.components;

import java.util.LinkedList;

import engine.core.GameTime;

public class ScriptManager
{
	LinkedList<ScriptComponent> scripts = new LinkedList<ScriptComponent>();
	
	public void
	addScript(ScriptComponent script)
	{
		scripts.add(script);
	}
	
	public void
	removeScript(ScriptComponent script)
	{
		//script.onDestroy();
		scripts.remove(script);
	}
	
	public void
	update(GameTime gameTime)
	{
		for(ScriptComponent script : scripts)
		{
			if (script.parent.isDestroyed() == false)
			{
				script.onUpdate(gameTime);
			}
			else
			{
				// Unisti. Pazi na for petlju za listu.
			}
		}
	}
}
