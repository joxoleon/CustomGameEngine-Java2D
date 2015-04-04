package engine.core;

public abstract class GameLoopThread
extends Thread
{
	private boolean run = true;
	private GameTime gameTime;
	protected GameTime gameTime()
	{
		return gameTime;
	}
	
	protected GameLoopThread(long targetRate)
	{
		this.gameTime = new GameTime(targetRate);
	}
	
	@Override
	public final void
	run()
	{
		run = true;
		gameTime.startCounting();
		while (run == true)
		{
			try
			{
				gameTime.beginCycle();
				
				threadLogic();
				
				long sleepTime = gameTime.calculateSleepTime();
				Thread.sleep(Math.max(0, sleepTime));
			} catch (InterruptedException e)
			{
			}
		}
	}
	
	public abstract void
	threadLogic();
	
	public void exitThread()
	{
		run = false;
		interrupt();
	}
}
