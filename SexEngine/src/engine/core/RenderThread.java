package engine.core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class RenderThread
extends GameLoopThread
{
	private GameWrapper gameWrapper;
	
	public RenderThread(GameWrapper gameWrapper, long targetRate)
	{
		super(targetRate);
		
		this.gameWrapper = gameWrapper;
	}

	@Override
	public void 
	threadLogic()
	{
		if (gameWrapper.panelDimension == null || 
			gameWrapper.panelDimension.width <= 0 || 
			gameWrapper.panelDimension.height <= 0)
		{
			gameWrapper.backBuffer = null;
		}
		else
		{
			gameWrapper.backBuffer = new BufferedImage(
				gameWrapper.panelDimension.width,
				gameWrapper.panelDimension.height,
				BufferedImage.TYPE_INT_ARGB);
		}
		
		if (gameWrapper.backBuffer != null)
		{
			Graphics2D g2d = (Graphics2D)gameWrapper.backBuffer.getGraphics();
		
			gameWrapper.render(g2d);
		
		
			synchronized(gameWrapper.paintSync)
			{
				while(gameWrapper.finishedPaint == false)
				{
					gameWrapper.waitingOnPaint = true;
					try
					{
						gameWrapper.paintSync.wait();
					} catch (InterruptedException e)
					{
					}
				}
				gameWrapper.waitingOnPaint = false;
				gameWrapper.frontBuffer = gameWrapper.backBuffer;
			}
			
			gameWrapper.repaint();
		}
	}
	
}
