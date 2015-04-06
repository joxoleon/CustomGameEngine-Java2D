package test;

import engine.components.ScriptComponent;
import engine.core.Actor;
import engine.core.GameTime;
import engine.datastructures.Vector3;
import engine.input.Input;
import engine.input.Keys;

public class PlayerControl
extends ScriptComponent
{
	private Vector3 moveDistance = new Vector3();
	private float moveSpeed = 50.0f;
	
	public
	PlayerControl(Actor parent)
	{
		super(parent);
	}

	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(GameTime gameTime)
	{
		moveDistance.set(0, 0, 0);
		
		if (Input.isKeyDown(Keys.W))
		{
			moveDistance.y -= 1;
		}
		
		if (Input.isKeyDown(Keys.S))
		{
			moveDistance.y += 1;
		}
		
		if (Input.isKeyDown(Keys.A))
		{
			moveDistance.x -= 1;
		}
		
		if (Input.isKeyDown(Keys.D))
		{
			moveDistance.x += 1;
		}
		
		System.out.println(moveDistance.x + " " + moveDistance.y);
		
		if (moveDistance.magnitude() > 0)
		{
			moveDistance.normalize();
		}
		
		moveDistance.mul(moveSpeed * gameTime.dt_s());
		
		float rotation = 0;
		
		if (Input.isKeyDown(Keys.Q))
		{
			rotation -= 0.1;
		}
		
		if (Input.isKeyDown(Keys.E))
		{
			rotation += 0.1;
		}
		
		parent.getTransformComponent().rotate(rotation);
		
		moveDistance = moveDistance.rotate((float)parent.getTransformComponent().getRotation());
		parent.getTransformComponent().translate(moveDistance);
		
		System.out.println(moveDistance.x + " " + moveDistance.y);
	}

	@Override
	public void onDestroy()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
