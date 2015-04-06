package engine.core;
import java.util.LinkedList;


public final class 
RenderStateManager
{
	//Fields	
	private static int updating = 0;
	private static int updated = 0;
	private static int rendering = 0;

	
	static long updateThreadID;
	static long renderThreadID;
	
	
	//Constructors
	private
	RenderStateManager()
	{
	}
	
	
	//Methods
	public synchronized static void
	initializeStates()
	{
	}
	
	
	public synchronized static int
	getRenderState()
	{
		return rendering;
	}
	
	public synchronized static int
	getUpdatedState()
	{
		return updated;
	}
	
	public synchronized static int
	getUpdatingState()
	{
		return updating;
	}
	
	
	
	public synchronized static void
	startRenderState()
	{
		rendering = updated;
	}
	
	public synchronized static void
	finishRenderState()
	{
		rendering = -1;
	}
	
	public synchronized static void
	startUpdatingState()
	{
		for (int i = 0; i < 2; ++i)
		{
			if (i != updated && i != rendering)
			{
				updating = i;
				break;
			}
		}
	}
	
	public synchronized static void
	finishUpdatingState()
	{
		updated = updating;
		updating = -1;
	}
	
	public static long 
	getUpdateThreadID()
	{
		return updateThreadID;
	}
	
	public static long 
	getRenderThreadID()
	{
		return renderThreadID;
	}
	
	
}
