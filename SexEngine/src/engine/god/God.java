package engine.god;

import java.util.LinkedList;

import engine.components.TransformManager;
import engine.core.Actor;

public final class God
{
	//Fields
	public static final TransformManager TransformManager = new TransformManager(20, 10, 5, 30, 50);
	private static LinkedList<Actor> actorsForDestruction = new LinkedList<Actor>();
	// Potrebno je ubaciti strukturu koja sadrzi Actora i destructionCounter kako bi se vreme unistavnja objekta pratilo. Ili mozda realizovati to
	// na neki manje gej nacin.
	
	public static void setForDestruction(Actor actor)
	{
		actorsForDestruction.add(actor);
	}
	
	
	
	
	private
	God()
	{
	}
}
