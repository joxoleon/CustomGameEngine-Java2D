package engine.components;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import engine.core.Actor;
import engine.core.GameTime;
import engine.core.God;
import engine.datastructures.Vector3;

public class RenderingComponent 
extends GameComponent
{
	//Fields
	boolean isLastRender = false;
	boolean drawForwardVector = true;

	//Methods
	void 
	initialize(Actor parent)
	{
		this.parent = parent;
	}

	public void
	render(Graphics2D g2d)
	{
		AffineTransform aft = g2d.getTransform();
		
		Vector3 position = parent.getTransformComponent().getPosition();
		double rotation = parent.getTransformComponent().getRotation();
		
		Polygon p = new Polygon();
		p.addPoint(-10, 7);
		p.addPoint(10, 7);
		p.addPoint(0, -7);
		
		g2d.setPaint(Color.black);
		g2d.translate(position.x, position.y);
		g2d.rotate(rotation);
		g2d.fill(p);
		
		if(drawForwardVector == true)
		{
			g2d.setPaint(Color.red);
			g2d.drawLine(0, 0, 0, -10);
		}
		g2d.setTransform(aft);
	}
	
	@Override
	public String 
	getName()
	{
		return "RenderingComponent";
	}

	@Override
	protected void 
	setEnabled(boolean enabled)
	{		
	}

	@Override
	protected void 
	destroyComponent()
	{
		God.RenderingManager.destroy(this);
		//Ukloni reference
	}
	
	
	protected final void 
	setLastRender()
	{
		isLastRender = true;
	}
	
	public final boolean
	isLastRender()
	{
		return isLastRender;
	}
}
