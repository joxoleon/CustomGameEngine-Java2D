package engine.core;

import engine.components.TransformManager;

public final class God
{

	
	
	
	
	//Fields
	public static final TransformManager TransformManager = new TransformManager(20, 10, 5, 30, 50);
//	private static LinkedList<DestructionElem> actorsForDestruction = new LinkedList<DestructionElem>();
	// Potrebno je ubaciti strukturu koja sadrzi Actora i destructionCounter kako bi se vreme unistavnja objekta pratilo. Ili mozda realizovati to
	// na neki manje gej nacin.
	
	private static DestructionElem destructionHead;
	
	public static void setForDestruction(Actor actor, double destructionTime)
	{
		DestructionElem de = new DestructionElem(actor, destructionTime);
		
		destructionHead.prev = de;
		de.next = destructionHead;
		destructionHead = de;
	
	}
	
	public static void update(GameTime gameTime)
	{
		DestructionElem curr = destructionHead;
		
		while(curr != null)
		{
			if(curr.destructionTime <= 0 && 
			   curr.actor.getRenderingComponent().isLastRender() &&
			   curr.updateCounter >= 1)
			{
				if(curr.prev != null)
					curr.prev.next = curr.next;
				else
				{
					destructionHead = destructionHead.next;
					curr = destructionHead;
				}
				
				if(curr.next != null)
					curr.next.prev = curr.prev;
				
				DestructionElem next = curr.next;
				curr.next = null;
				curr.prev = null;
				
				curr.actor.finalDestroy();
				
				curr = next;
			}
			else
			{
				curr.destructionTime -= gameTime.dt_s();
				curr.updateCounter++;
				curr = curr.next;
				
			}
		}
		
	}
	
	
	
	private
	God()
	{
	}
}


class DestructionElem
{
	public double destructionTime;
	public int updateCounter;
	public Actor actor;
	
	public DestructionElem prev, next;
	
	public DestructionElem(Actor actor, double destructionTime)
	{
		this.actor = actor;
		this.destructionTime = destructionTime;
	}
}
