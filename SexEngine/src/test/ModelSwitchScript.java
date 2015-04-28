package test;

import engine.components.ScriptComponent;
import engine.core.GameTime;
import engine.core.God;
import engine.graphics.Model;
import engine.input.Input;
import engine.input.Keys;

public class ModelSwitchScript extends ScriptComponent
{
	private Model[] models;

	
	@Override
	public void onAttach()
	{
		models = new Model[4];
		for (int i = 0; i < models.length; i++)
		{
			models[i] = God.ModelFactory.getFlyweightModel("Helicopter" + (i + 1));
		}
	}

	@Override
	public void onUpdate(GameTime gameTime)
	{
		if(Input.isKeyDown(Keys.D1))
		{
			parent.getRenderingComponent().setModel(models[0]);
			HelicopterPropellerScript propellerScript =  (HelicopterPropellerScript)parent.getComponent("HelicopterPropellerScript");
			if(propellerScript != null)
			{
				propellerScript.updateModel();
			}
		}
		
		if(Input.isKeyDown(Keys.D2))
		{
			parent.getRenderingComponent().setModel(models[1]);
			HelicopterPropellerScript propellerScript =  (HelicopterPropellerScript)parent.getComponent("HelicopterPropellerScript");
			if(propellerScript != null)
			{
				propellerScript.updateModel();
			}
		}
		
		if(Input.isKeyDown(Keys.D3))
		{
			parent.getRenderingComponent().setModel(models[2]);
			HelicopterPropellerScript propellerScript =  (HelicopterPropellerScript)parent.getComponent("HelicopterPropellerScript");
			if(propellerScript != null)
			{
				propellerScript.updateModel();
			}
		}
		
		if(Input.isKeyDown(Keys.D4))
		{
			parent.getRenderingComponent().setModel(models[3]);
			HelicopterPropellerScript propellerScript =  (HelicopterPropellerScript)parent.getComponent("HelicopterPropellerScript");
			if(propellerScript != null)
			{
				propellerScript.updateModel();
			}
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
		// TODO Auto-generated method stub
		return null;
	}

}
