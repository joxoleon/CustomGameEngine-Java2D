package engine.core;

public class GameTime
{
	private long targetRate;
	private double targetPeriod;
	
	private long startTime;
	private long elapsedTime;
	private double dt_ms;
	private double dt_s;
	
	private double timeScale = 1.0;
	
	public
	GameTime(long targetRate)
	{
		this.targetRate = targetRate;
		if (this.targetRate <= 0)
		{
			this.targetRate = 60;
		}
		
		this.targetPeriod = 1.0 / this.targetRate;
	}
	
	public void
	startCounting()
	{
		startTime = System.nanoTime();
		elapsedTime = 0;
	}
	
	public void 
	beginCycle()
	{
		long currentTime = System.nanoTime();
		elapsedTime = currentTime - startTime;
		startTime = currentTime;
		
		dt_ms = elapsedTime / 1000000.0;
		dt_s = dt_ms / 1000.0;
	}
	
	public long 
	calculateSleepTime()
	{
		return ((long)(targetPeriod * 1000000000 - (System.nanoTime() - startTime)) / 1000000);
	}
	
	private double 
	dt_ms()
	{
		return dt_ms;
	}
	
	public double 
	dt_s()
	{
		return dt_s;
	}

	public double 
	getTimeScale()
	{
		return timeScale;
	}

	public void 
	setTimeScale(double timeScale)
	{
		this.timeScale = timeScale;
	}
		
}


