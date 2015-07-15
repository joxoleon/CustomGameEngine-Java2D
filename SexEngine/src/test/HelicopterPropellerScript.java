package test;

import engine.components.ScriptComponent;
import engine.core.GameTime;
import engine.graphics.Model;
import engine.graphics.Sprite;

public class HelicopterPropellerScript
extends ScriptComponent
{
	private Model model;
	private int numberOfPropellers;
	private Sprite[] propellers;
	private double[] propellerSpeeds;
	private double[] propellerRotationAngles;
	private double propellerBaseSpeed = 10;


	
	@Override
	public void 
	onAttach()
	{
		model = parent.getRenderingComponent().getModel();
		numberOfPropellers = model.numOfSprites() - 1;
		propellers = new Sprite[numberOfPropellers];
		propellerSpeeds = new double[numberOfPropellers];
		propellerRotationAngles = new double[numberOfPropellers];
		for (int i = 0; i < propellers.length; i++)
		{
			propellers[i] = model.getSprite("Propeller" + (i + 1));
			propellerSpeeds[i] = propellerBaseSpeed + 10 * i;
			
		}
		
		for (Sprite propeller : propellers)
		{
			propeller.isSelfRotating = true;
		}

	}

	@Override
	public void 
	onUpdate(GameTime gameTime)
	{

		for (int i = 0; i < propellers.length; i++)
		{
			propellerRotationAngles[i] += propellerSpeeds[i] * gameTime.dt_s();
			propellers[i].setRotation((float)propellerRotationAngles[i]);
		}
	}

	@Override
	public void 
	onDestroy()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String 
	getName()
	{
		// TODO Auto-generated method stub
		return "HelicopterPropellerScript";
	}
	
	public void
	updateModel()
	{
		onAttach();
	}



}
