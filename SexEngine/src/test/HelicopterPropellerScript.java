package test;

import engine.components.ScriptComponent;
import engine.core.GameTime;
import engine.graphics.Model;
import engine.graphics.Sprite;

public class HelicopterPropellerScript
extends ScriptComponent
{
	private Model model;
	private Sprite propeller1;
	private Sprite propeller2;
	
	private double p1RotationSpeed = 12;
	private double p2RotationSpeed = 24;
	
	private double p1RotationAngle;
	private double p2RotationAngle;


	@Override
	public void 
	onCreate()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void 
	onAttach()
	{
		model = parent.getRenderingComponent().getModel();
		propeller1 = model.getSprite("Propeller1");
		propeller2 = model.getSprite("Propeller2");
		
		propeller1.isRotatingWithoutModel = true;
//		propeller2.isRotatingWithoutModel = true;
		
	}

	@Override
	public void 
	onUpdate(GameTime gameTime)
	{
		p1RotationAngle += p1RotationSpeed * gameTime.dt_s();
		p2RotationAngle += p2RotationSpeed * gameTime.dt_s();
		
		propeller1.setRotation((float)p1RotationAngle );
		propeller2.setRotation((float)p2RotationAngle);
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



}
