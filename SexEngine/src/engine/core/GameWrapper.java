package engine.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.input.Buttons;
import engine.input.Input;
import engine.input.Keys;


public class GameWrapper
extends JPanel
{
	
	// Fields.
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	
	private GameLoopThread updateThread;
	private GameLoopThread renderThread;
	
	Object paintSync = new Object();
	boolean waitingOnPaint = false;
	
	boolean finishedPaint = true;
	
	BufferedImage backBuffer;
	BufferedImage frontBuffer;
	BufferedImage paintBuffer;
	
	Dimension panelDimension = new Dimension(1, 1);
	
	// Constructors.
	public 
	GameWrapper()
	{
		updateThread = new UpdateThread(this, 80);
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
				
				synchronized(paintSync)
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
		
		synchronized(paintSync)
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
		
		God.TransformManager.update(gameTime);
		God.ScriptManager.update(gameTime);
		
		
		
		if (Input.isKeyPressed(Keys.Escape))
		{
			exit();
		}
		
		RenderStateManager.finishUpdatingState();
	}
	
	public void 
	paint(Graphics g)
	{
		synchronized(paintSync)
		{
			finishedPaint = false;
			paintBuffer = frontBuffer;
		}
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		if (paintBuffer != null)
			g2d.drawImage(paintBuffer, 0, 0, this);
		
		synchronized(paintSync)
		{
			finishedPaint = true;
			
			if (waitingOnPaint)
				paintSync.notifyAll();
		}
	}
	
	void 
	render(Graphics2D g2d)
	{
		RenderStateManager.startRenderState();
		
		God.RenderingManager.render(g2d);
		
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
		
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocation(0, 0);
		frame.setSize(1920, 1080);//panelDimension.width, panelDimension.height);
		
		frame.addKeyListener(Input.Instance());
		
		frame.setVisible(true);
		
		System.out.println(this.getWidth() +", " + this.getHeight());
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
	
}
