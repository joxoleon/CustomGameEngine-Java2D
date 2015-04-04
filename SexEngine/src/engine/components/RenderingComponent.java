package engine.components;
import engine.core.Actor;

public class RenderingComponent 
extends GameComponent
{
	
	//Fields
	private boolean isLastRender = false;
	

	
	void 
	initialize(Actor parent)
	{
		this.parent = parent;
	}

	@Override
	public String 
	getName()
	{
		return "RenderingComponent";
	}

	@Override
	protected void 
	setEnabled(boolean enabled)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void 
	destroyComponent()
	{
		// TODO Auto-generated method stub
		
	}
	
	
	protected final void 
	setLastRender()
	{
		isLastRender = true;
	}
}
