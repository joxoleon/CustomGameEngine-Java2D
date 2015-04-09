package engine.components;

import java.util.LinkedList;
import java.util.ListIterator;

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
		
		ListIterator<ScriptComponent> iterator = scripts.listIterator();
		
		while(iterator.hasNext())
		{
			ScriptComponent component = iterator.next();
			if(component.parent.isDestroyed() == false)
			{
				if(component.isEnabled() == true)
					component.onUpdate(gameTime);
			}
			else
			{
				iterator.remove();
			}
		}
		
	}
}
