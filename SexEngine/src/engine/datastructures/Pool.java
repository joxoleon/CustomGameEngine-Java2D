package engine.datastructures;

import java.util.LinkedList;

public class Pool<T>
{
	private Class<T> aClass;
	
	private int minFreeObjects;
	private int maxFreeObjects;
	
	private int minFreeObjectsThreshold;
	private int maxFreeObjectsThreshold;
	
	private LinkedList<T> freeObjects;
	
	public Pool(Class<T> aClass, int initialNumberOfObjects, int minFreeObjects, int minFreeObjectsThreshold, int maxFreeObjects, int maxFreeObjectsThreshold)
	{
		this.aClass = aClass;
		freeObjects = new LinkedList<T>();
		this.minFreeObjects = minFreeObjects;
		this.maxFreeObjects = maxFreeObjects;
		this.minFreeObjectsThreshold = minFreeObjectsThreshold;
		this.maxFreeObjectsThreshold = maxFreeObjectsThreshold;
		
		initialNumberOfObjects = Math.max(minFreeObjects, initialNumberOfObjects);
		initialNumberOfObjects = Math.min(maxFreeObjects, initialNumberOfObjects);
		
		if(maxFreeObjects > maxFreeObjects)
		{
			int min = minFreeObjects;
			minFreeObjects = maxFreeObjects;
			maxFreeObjects = min;
		}	
		
		for (int i = 0; i < initialNumberOfObjects; i++)
		{
			try
			{
				T t = aClass.newInstance();
				freeObjects.add(t);
			} 
			catch (InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
	public void update()
	{
		if (freeObjects.size() < minFreeObjectsThreshold)
		{
			int bounds = minFreeObjects - minFreeObjects;
			for (int i = 0; i < bounds; i++)
			{
				try
				{
					T t = aClass.newInstance();
					freeObjects.add(t);
				} 
				catch (InstantiationException | IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		if (freeObjects.size() > maxFreeObjectsThreshold)
		{
			int bounds = maxFreeObjectsThreshold - maxFreeObjects;
			for (int i = 0; i < bounds; i++)
			{
				freeObjects.removeFirst();
				//Destroy/Deallocate objects.
			}
		}
	}
	
	public T allocate()
	{
		if(freeObjects.size() > 0)
		{
			return freeObjects.removeLast();
		}
		else
			try
			{
				return aClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
				return null;
			}
	}
	
	public void deallocate(T object)
	{
		if (object != null)
		{
			freeObjects.addFirst(object);
		}
	}


}
