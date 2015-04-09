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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.datastructures.Vector3;
import engine.graphics.Sprite;
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
	render(Graphics2D g2d)
	{

		RenderStateManager.startRenderState();
		
		g2d.scale(scaleFactor.x, scaleFactor.y);
		
//		God.RenderingManager.render(g2d)
		
		Sprite s1 = new Sprite(God.GraphicsContent.getImage("Jagoda"), 320, 320, true);
		Sprite s2 = new Sprite(God.GraphicsContent.getImage("Mesec"), 320, 320, true);
		Sprite s3 = new Sprite(God.GraphicsContent.getImage("Kugla"), 320, 320, true);
		
		s1.setPosition(160, 160);
		
		s2.setPosition(480, 160);
//		s2.setScale(0.3f, 1.0f);
		
		s3.setPosition(800, 160);
		s3.setRotation(-MathHelper.PIOverFour);;
		s3.setScale(0.2f, 0.2f);
	
		s1.render(g2d, true);
		s2.render(g2d, true);
		s3.render(g2d, true);
		
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
		God.GraphicsContent.loadImage("Jagoda", "resources/images/Strawberry_01.jpg");
		God.GraphicsContent.loadImage("Mesec", "resources/images/Moon_01.jpg");
		God.GraphicsContent.loadImage("Kugla", "resources/images/GlassBall_01.jpg");
	}
	
	private void
	initializeAudioContent()
	{
		
	}
		
	public int getScreenWidth()
	{
		return panelDimension.width;
	}
	
	public int getScreenHeight()
	{
		return panelDimension.height;
	}
	
	public int getWorldWidth()
	{
		return worldDimension.width;
	}
	
	public int getWorldHeight()
	{
		return worldDimension.height;
	}
	
}
