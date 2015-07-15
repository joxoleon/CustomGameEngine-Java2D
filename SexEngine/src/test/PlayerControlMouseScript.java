package test;


import engine.components.ScriptComponent;
import engine.core.GameTime;
import engine.datastructures.Vector3;
import engine.input.Input;
import engine.input.Keys;
import engine.utility.MathHelper;

public class PlayerControlMouseScript
extends ScriptComponent
{
	private float moveSpeed = 350.0f;
	private boolean isRelative = true;

	@Override
	public void onAttach()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(GameTime gameTime)
	{
		float forward = 0;
		float right = 0;
		
		if (Input.isKeyDown(Keys.W))
			forward -= 1;
		if (Input.isKeyDown(Keys.S))
			forward += 1;
		if (Input.isKeyDown(Keys.A))
			right -= 1;
		if (Input.isKeyDown(Keys.D))
			right += 1;
		if (forward * forward + right * right > 1)
		{
			forward /= MathHelper.SQRT2;
			right /= MathHelper.SQRT2;
		}
		
		Vector3 moveVector = null;
		
		if(isRelative == true)
		{
			Vector3 forwardMoveVector = parent.getTransformComponent().getFront();
			Vector3 rightMoveVector = parent.getTransformComponent().getRight();
			
			forwardMoveVector.mul(-forward);
			rightMoveVector.mul(right);
			moveVector = Vector3.sum(forwardMoveVector, rightMoveVector);
		}
		else
		{
			moveVector = new Vector3(right, forward, 0);
		}
		
		moveVector.mul(moveSpeed * gameTime.dt_s());
		parent.getTransformComponent().translate(moveVector);
		
		
		
		
		
		
		// Set rotation
		Vector3 mousePosition = new Vector3(Input.getMousePos().x, Input.getMousePos().y, 0);
		Vector3 playerPosition = parent.getTransformComponent().getPosition();
		Vector3 newDirectionVector = Vector3.dif(mousePosition, playerPosition);
		
		newDirectionVector.z = 0;
		newDirectionVector.normalize();
		
		// Get angle intensity
		Vector3 upVector = new Vector3(0, -1, 0);		
		float cosL = Vector3.dot(upVector, newDirectionVector);
		MathHelper.clamp(cosL, -1, 1);
		double angle = Math.acos(cosL);
		
		// Get angle sign.
		Vector3 sinL = Vector3.cross(upVector, newDirectionVector);
		if(sinL.z < 0)
			angle = -angle;

		// Set angle.
		parent.getTransformComponent().setRotation(angle);
				
		
		
		
		if(Input.isKeyDown(Keys.O))
		{
			parent.getTransformComponent().scale(new Vector3(0.9f, 0.9f, 1));
			System.out.println("kurac");
		}
		
		if(Input.isKeyDown(Keys.P))
		{
			parent.getTransformComponent().scale(new Vector3(1.1f, 1.1f, 1));
		}
	}

	@Override
	public void onDestroy()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName()
	{
		return "PlayerControlMouseScript";
	}

}
