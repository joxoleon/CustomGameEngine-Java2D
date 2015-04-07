package engine.components;
import engine.core.Actor;
import engine.core.GameTime;
import engine.core.God;
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
		
		for (int i = 0; i < 3; i++)
		{
			positions[i] = new Vector3();
			rotations[i] = 0.0;
			scales[i] = new Vector3();
		}

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

	void
	update(GameTime gameTime)
	{
		updateRenderState();
	}
	
	@Override
	protected void 
	setEnabled(boolean enabled)
	{
		// Transform ne moze da se disable-uje.
		God.TransformManager.prepareToDie(this);
	}
	
	@Override
	protected void 
	destroyComponent()
	{
		God.TransformManager.destroy(this);
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
		
		if (index != RenderStateManager.getRenderThreadID())
		{
			return positions[updating];
		}
		else// if (index == RenderStateManager.getRenderThreadID())
		{
			return positions[RenderStateManager.getRenderState()];
		}
//		else
//		{
//			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
//			return null;
//		}
	}
	
	public void
	setPosition(Vector3 position)
	{
		long index = Thread.currentThread().getId();
		
		if (index != RenderStateManager.getRenderThreadID())
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
		
		if (index != RenderStateManager.getRenderThreadID())
		{
			return rotations[updating];
		}
		else //if (index == RenderStateManager.getRenderThreadID())
		{
			return rotations[RenderStateManager.getRenderState()];
		}
//		else
//		{
//			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
//			return 0;
//		}
	}
	
	public void
	setRotation(double rotation)
	{
		long index = Thread.currentThread().getId();
		
		if (index != RenderStateManager.getRenderThreadID())
		{
			rotations[updating] = rotation;
			rotations[updating] = MathHelper.clampAngle(rotations[updating]);
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
		
		if (index != RenderStateManager.getRenderThreadID())
		{
			return scales[updating];
		}
		else //if (index == RenderStateManager.getRenderThreadID())
		{
			return scales[RenderStateManager.getRenderState()];
		}
//		else
//		{
//			System.err.println("Greska : Neregistrovani Thread pristupa podacima(Transform Component)!");
//			return null;
//		}
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
		
		if (index != RenderStateManager.getRenderThreadID())
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
		
		if (index != RenderStateManager.getRenderThreadID())
		{
			positions[updating].add(distance);
		}
		else
		{
			System.err.println("Greska : Neregistrovani Thread pristupa podacima (Transform Component)!");
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
			rotations[updating] = MathHelper.clampAngle(rotations[updating]);
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

	public Vector3
	getFront()
	{
		Vector3 forward = new Vector3(0, -1, 0);
		forward.rotate((float)getRotation());
		System.out.println(forward);
		return forward;
	}
	
	public Vector3
	getRight()
	{
		Vector3 right = new Vector3(1, 0, 0);
		right.rotate((float)(getRotation()));
		return right;
	}
	
}
