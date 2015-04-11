package engine.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.datastructures.Vector3;
import engine.graphics.Sprite;
import engine.graphics.SpriteSheet;
import engine.input.Buttons;
import engine.input.Input;
import engine.input.Keys;
import engine.utility.MathHelper;


public class GameWrapper
extends JPanel
{
	// Fields.
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	
	
	private GameLoopThread updateThread;
	private GameLoopThread renderThread;
	
	Object renderSyncObject = new Object();
	
	boolean waitingOnPaint = false;
	boolean finishedPaint = true;
	
	BufferedImage backBuffer;
	BufferedImage frontBuffer;
	BufferedImage paintBuffer;

	
	Dimension panelDimension = new Dimension(1, 1);	
	Vector3 scaleFactor = new Vector3(1, 1, 1);
	
	Dimension worldDimension = new Dimension(1920, 1080);
	
	SpriteSheet ss1;
	
	// Constructors.
	public 
	GameWrapper()
	{
		updateThread = new UpdateThread(this, 60);
		renderThread = new RenderThread(this, 60);
		
		RenderStateManager.updateThreadID = updateThread.getId();
		RenderStateManager.renderThreadID = renderThread.getId();
		
		frame = new JFrame("GameWrapper");
		
		frame.addWindowListener(new WindowAdapter()
		{
			
			public void windowClosing(WindowEvent e)
			{
				updateThread.exitThread();
				renderThread.exitThread();
				
				try
				{
					updateThread.join();
					renderThread.join();
				} catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
				
				System.exit(0);
			}
		});
		
		frame.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				panelDimension = getSize();
				calculateScaleFactor();
				
				synchronized(renderSyncObject)
				{
					paintBuffer = frontBuffer;
				}
			}
		});
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		initializeWindow();
		RenderStateManager.initializeStates();
		initializeKeyboardInput();
		initializeMouseInput();
		
		initializeGraphicsContent();
		initializeAudioContent();
		
		synchronized(renderSyncObject)
		{
			paintBuffer = frontBuffer;
		}
		
		ss1 = new SpriteSheet(God.GraphicsContent.getSpriteSheet("Solaire"), 320, 320, 1.0f);
	}
	
	// Methods.
	void 
	update(GameTime gameTime)
	{

		RenderStateManager.startUpdatingState();
		
		// Fizika.
		
		
		// Input.
		Input.SwitchStates();
		
		if (Input.isKeyPressed(Keys.Escape))
		{
			exit();
		}
		
		God.TransformManager.update(gameTime);
		God.ScriptManager.update(gameTime);
		
		if (Input.isKeyDown(Keys.Right))
		{
			this.ss1.playAnimation();
		}
		else
		{
			this.ss1.stopAnimation();
		}
		
		
		RenderStateManager.finishUpdatingState();
	}
	
	public void 
	paint(Graphics g)
	{
		synchronized(renderSyncObject)
		{
			finishedPaint = false;
			paintBuffer = frontBuffer;
		}
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		if (paintBuffer != null)
			g2d.drawImage(paintBuffer, 0, 0, this);
		
		synchronized(renderSyncObject)
		{
			finishedPaint = true;
			
			if (waitingOnPaint)
				renderSyncObject.notifyAll();
		}
	}
	
	void 
	render(Graphics2D g2d, GameTime gameTime)
	{
		RenderStateManager.startRenderState();
		
		g2d.scale(scaleFactor.x, scaleFactor.y);
		
		God.RenderingManager.render(g2d, gameTime);
		
		Sprite s1 = new Sprite(God.GraphicsContent.getImage("Jagoda"), 320, 320);
		Sprite s2 = new Sprite(God.GraphicsContent.getImage("Mesec"), 320, 320);
		Sprite s3 = new Sprite(God.GraphicsContent.getImage("Kugla"), 320, 320);
		
//		SpriteSheet ss1 = new SpriteSheet(God.GraphicsContent.getSpriteSheet("Plavusa"), 320, 320, 5.0f);
		
		s1.setPosition(160, 160);
		
		s2.setPosition(480, 160);
//		s2.setScale(0.3f, 1.0f);
		
		s3.setPosition(800, 160);
//		s3.setRotation(-MathHelper.PIOverFour);;
//		s3.setScale(0.2f, 0.2f); 
		
		ss1.setPosition(1120, 160);
	
		s1.render(g2d, true);
		s2.render(g2d, true);
		s3.render(g2d, true);
		
		ss1.playAnimation();
		ss1.render(g2d, true);
		
		RenderStateManager.finishRenderState();
		
	}
	
	protected final void
	start()
	{
		updateThread.start();
		renderThread.start();
	}
	
	public void
	exit()
	{
		updateThread.exitThread();
		renderThread.exitThread();
		
		System.exit(0);
	}
	
	private void
	initializeWindow()
	{
		panelDimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.getContentPane().add(this);
		
		frame.setUndecorated(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocation(0, 0);
		frame.setSize(600, 600);
		
		calculateScaleFactor();
		
		frame.addKeyListener(Input.Instance());
		
		frame.setVisible(true);
	}
	
	private void
	calculateScaleFactor()
	{
		scaleFactor.x = (float)panelDimension.width / (float)worldDimension.width;
		scaleFactor.y = (float)panelDimension.height / (float)worldDimension.height;
	}

	private void
	initializeKeyboardInput()
	{
		this.addKeyListener(Input.Instance());
		
		Input.Instance().setFocusable(true);
		Input.Instance().requestFocusInWindow();
	}
	
	private void
	initializeMouseInput()
	{
		this.addMouseListener(Input.Instance());
		this.addMouseMotionListener(Input.Instance());
		
		Input.Instance().setFocusable(true);
		Input.Instance().requestFocusInWindow();
	}

	private void
	initializeGraphicsContent()
	{
		String resourcePath = "resources/images/images.txt";
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(resourcePath));
			
			String line = reader.readLine();
			
			while(line != null)
			{
				String[] tokens = line.split(" ");
				if (tokens.length == 2)
				{
					God.GraphicsContent.loadImage(tokens[0], tokens[1]);
				}
				else if (tokens.length == 9)
				{
					God.GraphicsContent.loadSpriteSheet(
						tokens[0],
						tokens[1],
						Integer.parseInt(tokens[2]),
						Integer.parseInt(tokens[3]),
						Integer.parseInt(tokens[4]),
						Integer.parseInt(tokens[5]),
						Integer.parseInt(tokens[6]),
						Integer.parseInt(tokens[7]),
						Integer.parseInt(tokens[8]));
				}
				else
				{
					System.err.println("Unexpected input in images resource file! File path: " + resourcePath);
				}
				
				line = reader.readLine();			
			}
			
			reader.close();
			
		}
		catch (NumberFormatException e)
		{
			System.err.println("Could not parse an integer from images resource file! File path: " + resourcePath);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Could not load image resource file! File path: " + resourcePath);
		}
		catch(IOException e)
		{
			System.err.println("Error reading file line! File path: " + resourcePath);
		}
	}
	
	private void
	initializeAudioContent()
	{
		
	}
		
	public int 
	getScreenWidth()
	{
		return panelDimension.width;
	}
	
	public int 
	getScreenHeight()
	{
		return panelDimension.height;
	}
	
	public int 
	getWorldWidth()
	{
		return worldDimension.width;
	}
	
	public int 
	getWorldHeight()
	{
		return worldDimension.height;
	}
	
}
