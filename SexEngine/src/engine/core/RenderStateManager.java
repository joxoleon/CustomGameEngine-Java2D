package engine.core;
import java.util.LinkedList;


public final class 
RenderStateManager
{
	//Fields
	private static LinkedList<Integer> states = new LinkedList<Integer>();
	
	private static int mostRecentlyUpdated = 0;
	private static int updating = 0;
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
		states.addLast(0);
		states.addLast(1);
		states.addLast(2);		
	}
	
	
	public synchronized static int
	getRenderState()
	{
		return rendering;
	}
	
	public synchronized static int
	getUpdatedState()
	{
		return mostRecentlyUpdated;
	}
	
	public synchronized static int
	getUpdatingState()
	{
		return updating;
	}
	
	
	
	public synchronized static void
	startRenderState()
	{
		rendering = states.removeFirst();
	}
	
	public synchronized static void
	finishRenderState()
	{
		states.addLast(rendering);
		rendering = -1;
	}
	
	public synchronized static void
	startUpdatingState()
	{
		updating = states.removeLast();
	}
	
	public synchronized static void
	finishUpdatingState()
	{
		states.addFirst(updating);
		mostRecentlyUpdated = updating;
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
