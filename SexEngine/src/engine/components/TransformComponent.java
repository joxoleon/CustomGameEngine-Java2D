package engine.components;
import engine.core.Actor;
import engine.core.IMultiRenderState;
import engine.core.RenderStateManager;
import engine.datastructures.Vector3;
import engine.utility.MathHelper;



public class TransformComponent 
extends GameComponent 
implements IMultiRenderState
{
	//Fields
	private Vector3[] positions;
	private double[] rotations;
	private Vector3[] scales;
	
	private int updating;
	
	//Constructors
	public 
	TransformComponent()
	{
	}
	
	//Methods
	public void
	initialize(Actor parent)
	{
		this.parent = parent;
	
		positions = new Vector3[3];
		rotations = new double[3];
		scales = new Vector3[3];		

	}
	
	public void 
	updateRenderState()
	{
		int updated = RenderStateManager.getUpdatedState();
		updating = RenderStateManager.getUpdatingState();
				
		positions[updating].set(positions[updated]);
		rotations[updating] = rotations[updated];
		scales[updating].set(scales[updated]);
	}	

	@Override
	protected void 
	setEnabled(boolean enabled)
	{
		// Transform ne moze da se disable-uje.
	}
	
	@Override
	protected void 
	destroyComponent()
	{
		
	}
	
	public final String 
	getName() 
	{
		return "TransformComponent";
	}
	
	public Vector3
	getPosition()
	{
		long index = Thread.currentThread().getId();
		
		if (index == RenderStateManager.getUpdateThreadID())
		{
			return positions[updating];
		}
		else if (index == RenderStateManager.getRenderThreadID())
		{
			return positions[RenderStateManager.getRenderState()];
		}
		else
		{
			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
			return null;
		}
	}
	
	public void
	setPosition(Vector3 position)
	{
		long index = Thread.currentThread().getId();
		
		if (index == RenderStateManager.getUpdateThreadID())
		{
			positions[updating].set(position);
		}
		else
		{
			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
		}
		
	}
	
	public double
	getRotation()
	{
		long index = Thread.currentThread().getId();
		
		if (index == RenderStateManager.getUpdateThreadID())
		{
			return rotations[updating];
		}
		else if (index == RenderStateManager.getRenderThreadID())
		{
			return rotations[RenderStateManager.getRenderState()];
		}
		else
		{
			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
			return 0;
		}
	}
	
	public void
	setRotation(double rotation)
	{
		long index = Thread.currentThread().getId();
		
		if (index == RenderStateManager.getUpdateThreadID())
		{
			rotations[updating] = rotation;
			rotations[updating] = MathHelper.clamp(rotations[updating], -MathHelper.PI, MathHelper.PI);
		}
		else
		{
			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
		}
		
	}
	
	public Vector3
	getScale()
	{
		long index = Thread.currentThread().getId();
		
		if (index == RenderStateManager.getUpdateThreadID())
		{
			return scales[updating];
		}
		else if (index == RenderStateManager.getRenderThreadID())
		{
			return scales[RenderStateManager.getRenderState()];
		}
		else
		{
			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
			return null;
		}
	}
	
	public void
	setScale(Vector3 scale)
	{
		if (scale.x == 0 || scale.y == 0 || scale.z == 0)
		{
			System.err.println("Greska : Skaliranje na 0, majmune, ovaj matori!");
			return;
		}
		
		long index = Thread.currentThread().getId();
		
		if (index == RenderStateManager.getUpdateThreadID())
		{
			scales[updating].set(scale);
		}
		else
		{
			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
		}
		
	}
	
	public void
	translate(Vector3 distance)
	{
		long index = Thread.currentThread().getId();
		
		if (index == RenderStateManager.getUpdateThreadID())
		{
			positions[updating].add(distance);
		}
		else
		{
			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
		}
	}
	
	public void
	translate(float x, float y)
	{
		translate(new Vector3(x, y, 0));
	}
	
	public void
	rotate(double angle)
	{
		long index = Thread.currentThread().getId();
		
		if (index == RenderStateManager.getUpdateThreadID())
		{
			rotations[updating] += angle;
			rotations[updating] = MathHelper.clamp(rotations[updating], -MathHelper.PI, MathHelper.PI);
		}
		else
		{
			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
		}
	}
	
	public void
	scale(Vector3 scale)
	{
		if (scale.x == 0 || scale.y == 0 || scale.z == 0)
		{
			System.err.println("Greska : Skaliranje na 0, majmune, ovaj matori!");
			return;
		}
		
		long index = Thread.currentThread().getId();
		
		if (index == RenderStateManager.getUpdateThreadID())
		{
			scales[updating].mul(scale);
		}
		else
		{
			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
		}
	}

	
}
