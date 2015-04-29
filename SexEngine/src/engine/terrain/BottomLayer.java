package engine.terrain;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.graphics.Sprite;

public class BottomLayer implements TerrainLayer
{
	private Sprite currentTile;
	
	private int rows, columns;
	

	@Override
	public void render(Graphics2D g2d) 
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				int x = j * currentTile.getWidth();
				int y = i * currentTile.getHeight();
				currentTile.render(g2d, x, y, currentTile.getHeight(), currentTile.getWidth() ,false);
			}
		}
		
//		BufferedImage image = currentTile.getImage();
//		
//		g2d.drawImage(image, 0, 0, null);
//		g2d.drawImage(image, 900, 900, null);
		
		
	}
	
	private void
	calculateMatrixSize()
	{
		columns = Terrain.width / currentTile.getWidth();
		rows = Terrain.height / currentTile.getHeight();
		
	}
	
	@Override
	public void
	prepareLayer()
	{
		calculateMatrixSize();
	}
	
	public void
	setBottomTile(Sprite bottomTile)
	{
		this.currentTile = bottomTile;
	}
	
}
