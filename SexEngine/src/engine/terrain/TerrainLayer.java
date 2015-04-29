package engine.terrain;

import java.awt.Graphics2D;

public interface TerrainLayer
{

	public void render(Graphics2D g2d);
	
	public void prepareLayer();
}
