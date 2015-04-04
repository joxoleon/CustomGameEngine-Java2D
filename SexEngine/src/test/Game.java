package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import engine.core.GameTime;
import engine.core.GameWrapper;

public class Game
extends GameWrapper
{
	private Rectangle2D rec = new Rectangle2D.Double(0, 0, 100, 150);
	
	public
	Game()
	{
		super();
	}
	
	@Override
	public void
	update(GameTime gameTime)
	{
		super.update(gameTime);
		this.rec.setRect(this.rec.getX() + 2, this.rec.getY() + 1, this.rec.getWidth(), this.rec.getHeight());
	}
	
	@Override
	public void
	render(Graphics2D g2d)
	{
		super.render(g2d);
		g2d.setPaint(Color.black);
		g2d.fill(this.rec);
	}
	
	public static void
	main(String[] args)
	{
		Game game = new Game();
		game.start();
	}
}
