package engine.terrain;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import engine.core.God;
import engine.graphics.GraphicsContent;
import engine.graphics.Sprite;

public class Terrain
{
	public static int width = 3000, height = 3000;
	private static LinkedList<TerrainLayer> layers = new LinkedList<TerrainLayer>();
	
	private static BufferedImage terrain = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	
	public static void
	initializeTerrain()
	{
		BottomLayer bl = new BottomLayer();
		BufferedImage img= God.GraphicsContent.getImage("GrassTile1");
		Sprite bottomTile = new Sprite("Grass01", img, 450, 450);
		bl.setBottomTile(bottomTile);
		
		addLayer(bl);
	}
	
	public static void
	renderTerrain(Graphics2D g2d)
	{
		g2d.drawImage(terrain, 0, 0, null);
	}
	
	public static void
	addLayer(TerrainLayer layer)
	{
		layer.prepareLayer();
		layers.add(layer);
		Graphics2D g2d = (Graphics2D) terrain.getGraphics();
		layer.render(g2d);
	}
	
}
