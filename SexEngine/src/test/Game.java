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
	private static final long serialVersionUID = 1L;

	
	public
	Game()
	{
		super();
		
//		Actor[] actors = new Actor[5000];
//		
//		for (int i = 0; i < 50; i++)
//		{
//			for(int j = 0; j < 100; j++)
//			{
//				actors[i] = new Actor();
//				TransformComponent transform = actors[i].getTransformComponent();
//				if (transform != null)
//				{
//					transform.translate(j * 20, (i * 20));
//				}
//				
//
//					PlayerControl controlScript = new PlayerControl(actors[i]);
//					actors[i].addComponent(controlScript);
//			}
//		}
		
	}
	
	public static void
	main(String[] args)
	{
		Game game = new Game();
		game.start();
	}
}
