package test;

//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.geom.Rectangle2D;

import engine.components.TransformComponent;
import engine.core.Actor;
//import engine.core.GameTime;
import engine.core.GameWrapper;
//import engine.core.God;

public class Game
extends GameWrapper
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	private Rectangle2D rec = new Rectangle2D.Double(0, 0, 100, 150);
	
	public
	Game()
	{
		super();
		
		Actor[] actors = new Actor[20];
		
		for (int i = 0; i < actors.length; i++)
		{
			actors[i] = new Actor();
			TransformComponent transform = actors[i].getTransformComponent();
			if (transform != null)
			{
				transform.translate(i * 10, i * 10);
			}
		}
		
		
		
	}
	
	public static void
	main(String[] args)
	{
		Game game = new Game();
		game.start();
	}
}
