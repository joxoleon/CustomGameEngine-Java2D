package engine.core;

public class UpdateThread
extends GameLoopThread
{
	private GameWrapper gameWrapper;
	
	public UpdateThread(GameWrapper gameWrapper, long targetRate)
	{
		super(targetRate);
		
		this.gameWrapper = gameWrapper;
	}

	@Override
	public void 
	threadLogic()
	{
		gameWrapper.update(gameTime());
	}
	
}
