package engine.components;
import engine.core.GameObject;
import engine.core.IMultiRenderState;
import engine.core.RenderStateManager;
import engine.datastructures.Vector3;



public class TransformComponent extends GameComponent implements IMultiRenderState
{
	//Fields
//	Vector3 position;
//	double rotation;
//	Vector3 scale;
//	
	private Vector3[] positions;
	private double[] rotations;
	private Vector3[] scales;
	
	private int updating;
	private int rendering;
	
	
	
	//Constructors
	public 
	TransformComponent()
	{
	}
	
	//Methods
	public void
	initialize(GameObject parent, boolean isRendering)
	{
		this.parent = parent;
		
		if(isRendering)
		{
			positions = new Vector3[3];
			rotations = new double[3];
			scales = new Vector3[3];
			
			// Prijavljivanje u RenderStateManager
			
			
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
	
	public void
	updateRenderingIndex()
	{
		rendering = RenderStateManager.getRenderState();
	}
	
	
	//Methods
	public final String 
	getName() 
	{
		return "Transform";
	}
	
	
}
