package engine.input;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Input
extends JPanel
implements KeyListener, MouseListener, MouseMotionListener
{
	private static Input 			input 					= new Input();
	
	private static KeyboardState 	previousKeyboardState 	= new KeyboardState();
	private static KeyboardState 	currentKeyboardState 	= new KeyboardState();
	private static KeyboardState 	nextKeyboardState 		= new KeyboardState();
	
	private static MouseState 		previousMouseState 		= new MouseState();
	private static MouseState 		currentMouseState 		= new MouseState();
	private static MouseState 		nextMouseState 			= new MouseState();
	
	public 	static Input
	Instance()
	{
		return input;
	}
	
	public static void
	SwitchStates()
	{
		previousKeyboardState = currentKeyboardState;
		currentKeyboardState = nextKeyboardState;
		nextKeyboardState = new KeyboardState(nextKeyboardState);	// Copy constructor.
		
		previousMouseState = currentMouseState;
		currentMouseState = nextMouseState;
		nextMouseState = new MouseState(nextMouseState);
	}
	
	// Keyboard methods.
	public static boolean
	isKeyDown(Keys key)
	{
		return currentKeyboardState.isKeyDown(key);
	}
	
	public static boolean
	isKeyUp(Keys key)
	{
		return currentKeyboardState.isKeyUp(key);
	}
	
	public static boolean
	isKeyPressed(Keys key)
	{
		return currentKeyboardState.isKeyDown(key) && previousKeyboardState.isKeyUp(key);
	}
	
	public static boolean
	isKeyReleased(Keys key)
	{
		return currentKeyboardState.isKeyUp(key) && previousKeyboardState.isKeyDown(key);
	}
	
	
	// Mouse methods.
	public static boolean isButtonDown(Buttons button)
    {
        return currentMouseState.isButtonDown(button);
    }
    
    public static boolean isButtonUp(Buttons button)
    {
        return currentMouseState.isButtonUp(button);
    }
    
    public static boolean isButtonPressed(Buttons button)
    {
        return (currentMouseState.isButtonDown(button) && previousMouseState.isButtonUp(button));
    }
    
    public static boolean isButtonReleased(Buttons button)
    {
        return (currentMouseState.isButtonUp(button) && previousMouseState.isButtonDown(button));
    }
    
    public static Point getMousePos()
    {
        Point p = currentMouseState.getPosition();
        return p;
    }
    
    public static void setMousePos(int x, int y)
    {
    	currentMouseState.setPosition(x, y);
    }
    
    public static void setMousePosition(Point p)
    {
        currentMouseState.setPosition(p.x, p.y);
    }
    
    public static Point getMouseMove()
    {
        Point p = new Point();
        
        p.x = currentMouseState.position.x - previousMouseState.position.x;
        p.y = currentMouseState.position.y - previousMouseState.position.y;
        
        return p;
    }
	
	
	
	
	
	private
	Input()
	{
	}
	
	
	@Override
	public void
	mouseClicked(MouseEvent event)
	{
	}

	@Override
	public void
	mouseEntered(MouseEvent event)
	{
	}

	@Override
	public void
	mouseExited(MouseEvent event)
	{
	}

	@Override
	public void
	mousePressed(MouseEvent event)
	{
		switch (event.getButton())
        {
            case MouseEvent.BUTTON1:
            {
                input.nextMouseState.buttonStates.remove(Buttons.LeftButton);
                input.nextMouseState.buttonStates.put(Buttons.LeftButton, ButtonState.PRESSED);
            } break;
                
            case MouseEvent.BUTTON2:
            {
            	input.nextMouseState.buttonStates.remove(Buttons.MiddleButton);
            	input.nextMouseState.buttonStates.put(Buttons.MiddleButton, ButtonState.PRESSED);
            } break;
                
            case MouseEvent.BUTTON3:
            {
            	input.nextMouseState.buttonStates.remove(Buttons.RightButton);
            	input.nextMouseState.buttonStates.put(Buttons.RightButton, ButtonState.PRESSED);
            } break;
                
            default:
                break;
        }
	}

	@Override
	public void
	mouseReleased(MouseEvent event)
	{
		switch (event.getButton())
        {
            case MouseEvent.BUTTON1:
            {
            	input.nextMouseState.buttonStates.remove(Buttons.LeftButton);
            	input.nextMouseState.buttonStates.put(Buttons.LeftButton, ButtonState.RELEASED);
            } break;
                
            case MouseEvent.BUTTON2:
            {
            	input.nextMouseState.buttonStates.remove(Buttons.MiddleButton);
            	input.nextMouseState.buttonStates.put(Buttons.MiddleButton, ButtonState.RELEASED);
            } break;
                    
            case MouseEvent.BUTTON3:
            {
            	input.nextMouseState.buttonStates.remove(Buttons.RightButton);
            	input.nextMouseState.buttonStates.put(Buttons.RightButton, ButtonState.RELEASED);
            } break;
        }
	}
	
	
	
	@Override
	public void
	mouseDragged(MouseEvent event)
	{
	}

	@Override
	public void
	mouseMoved(MouseEvent event)
	{
		input.nextMouseState.position = event.getLocationOnScreen();
	}
	
	

	@Override
	public void
	keyPressed(KeyEvent event)
	{
		switch (event.getKeyCode())
        {
            case KeyEvent.VK_A:
            {
                input.nextKeyboardState.keyStates.remove(Keys.A);
                input.nextKeyboardState.keyStates.put(Keys.A, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_ADD:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Add);
                input.nextKeyboardState.keyStates.put(Keys.Add, KeyState.KEY_DOWN);
            } break;
               
            case KeyEvent.VK_B:
            {
                input.nextKeyboardState.keyStates.remove(Keys.B);
                input.nextKeyboardState.keyStates.put(Keys.B, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_BACK_SPACE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Back);
                input.nextKeyboardState.keyStates.put(Keys.Back, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_C:
            {
                input.nextKeyboardState.keyStates.remove(Keys.C);
                input.nextKeyboardState.keyStates.put(Keys.C, KeyState.KEY_DOWN);
            } break;
            
            case KeyEvent.VK_CAPS_LOCK:
            {
                input.nextKeyboardState.keyStates.remove(Keys.CapsLock);
                input.nextKeyboardState.keyStates.put(Keys.CapsLock, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_D:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D);
                input.nextKeyboardState.keyStates.put(Keys.D, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_0:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D0);
                input.nextKeyboardState.keyStates.put(Keys.D0, KeyState.KEY_DOWN);
            } break;
                    
            case KeyEvent.VK_1:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D1);
                input.nextKeyboardState.keyStates.put(Keys.D1, KeyState.KEY_DOWN);
            } break;
                        
            case KeyEvent.VK_2:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D2);
                input.nextKeyboardState.keyStates.put(Keys.D2, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_3:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D3);
                input.nextKeyboardState.keyStates.put(Keys.D3, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_4:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D4);
                input.nextKeyboardState.keyStates.put(Keys.D4, KeyState.KEY_DOWN);
            } break;
            
            case KeyEvent.VK_5:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D5);
                input.nextKeyboardState.keyStates.put(Keys.D5, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_6:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D6);
                input.nextKeyboardState.keyStates.put(Keys.D6, KeyState.KEY_DOWN);
            } break;
                        
            case KeyEvent.VK_7:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D7);
                input.nextKeyboardState.keyStates.put(Keys.D7, KeyState.KEY_DOWN);
            } break;
                                
            case KeyEvent.VK_8:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D8);
                input.nextKeyboardState.keyStates.put(Keys.D8, KeyState.KEY_DOWN);
            } break;
                                        
            case KeyEvent.VK_9:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D9);
                input.nextKeyboardState.keyStates.put(Keys.D9, KeyState.KEY_DOWN);
            } break;
                                                
            case KeyEvent.VK_DELETE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Delete);
                input.nextKeyboardState.keyStates.put(Keys.Delete, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_DIVIDE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Divide);
                input.nextKeyboardState.keyStates.put(Keys.Divide, KeyState.KEY_DOWN);
            } break;
                    
            case KeyEvent.VK_DOWN:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Down);
                input.nextKeyboardState.keyStates.put(Keys.Down, KeyState.KEY_DOWN);
            } break;
                        
            case KeyEvent.VK_E:
            {
                input.nextKeyboardState.keyStates.remove(Keys.E);
                input.nextKeyboardState.keyStates.put(Keys.E, KeyState.KEY_DOWN);
            } break;
                            
            case KeyEvent.VK_END:
            {
                input.nextKeyboardState.keyStates.remove(Keys.End);
                input.nextKeyboardState.keyStates.put(Keys.End, KeyState.KEY_DOWN);
            } break;
                                
            case KeyEvent.VK_ENTER:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Enter);
                input.nextKeyboardState.keyStates.put(Keys.Enter, KeyState.KEY_DOWN);
            } break;
                                    
            case KeyEvent.VK_ESCAPE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Escape);
                input.nextKeyboardState.keyStates.put(Keys.Escape, KeyState.KEY_DOWN);
            } break;
                                        
            case KeyEvent.VK_F:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F);
                input.nextKeyboardState.keyStates.put(Keys.F, KeyState.KEY_DOWN);
            } break;
            
            case KeyEvent.VK_F1:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F1);
                input.nextKeyboardState.keyStates.put(Keys.F1, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F2:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F2);
                input.nextKeyboardState.keyStates.put(Keys.F2, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F3:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F3);
                input.nextKeyboardState.keyStates.put(Keys.F3, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F4:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F4);
                input.nextKeyboardState.keyStates.put(Keys.F4, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F5:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F5);
                input.nextKeyboardState.keyStates.put(Keys.F5, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F6:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F6);
                input.nextKeyboardState.keyStates.put(Keys.F6, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F7:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F7);
                input.nextKeyboardState.keyStates.put(Keys.F7, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F8:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F8);
                input.nextKeyboardState.keyStates.put(Keys.F8, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F9:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F9);
                input.nextKeyboardState.keyStates.put(Keys.F9, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F10:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F10);
                input.nextKeyboardState.keyStates.put(Keys.F10, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F11:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F11);
                input.nextKeyboardState.keyStates.put(Keys.F11, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_F12:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F12);
                input.nextKeyboardState.keyStates.put(Keys.F12, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_G:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F12);
                input.nextKeyboardState.keyStates.put(Keys.F12, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_H:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F12);
                input.nextKeyboardState.keyStates.put(Keys.F12, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_HOME:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F12);
                input.nextKeyboardState.keyStates.put(Keys.F12, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_I:
            {
                input.nextKeyboardState.keyStates.remove(Keys.I);
                input.nextKeyboardState.keyStates.put(Keys.I, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_INSERT:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Insert);
                input.nextKeyboardState.keyStates.put(Keys.Insert, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_J:
            {
                input.nextKeyboardState.keyStates.remove(Keys.J);
                input.nextKeyboardState.keyStates.put(Keys.J, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_K:
            {
                input.nextKeyboardState.keyStates.remove(Keys.K);
                input.nextKeyboardState.keyStates.put(Keys.K, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_L:
            {
                input.nextKeyboardState.keyStates.remove(Keys.L);
                input.nextKeyboardState.keyStates.put(Keys.L, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_LEFT:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Left);
                input.nextKeyboardState.keyStates.put(Keys.Left, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_ALT:
            {
                input.nextKeyboardState.keyStates.remove(Keys.LeftAlt);
                input.nextKeyboardState.keyStates.put(Keys.LeftAlt, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_CONTROL:
            {
                if (event.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
                {
                    input.nextKeyboardState.keyStates.remove(Keys.LeftControl);
                    input.nextKeyboardState.keyStates.put(Keys.LeftControl, KeyState.KEY_DOWN);
                }
                else if (event.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT)
                {
                    input.nextKeyboardState.keyStates.remove(Keys.RightControl);
                    input.nextKeyboardState.keyStates.put(Keys.RightControl, KeyState.KEY_DOWN);
                }
            } break;
                
            case KeyEvent.VK_SHIFT:
            {
                if (event.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
                {
                    input.nextKeyboardState.keyStates.remove(Keys.LeftShift);
                    input.nextKeyboardState.keyStates.put(Keys.LeftShift, KeyState.KEY_DOWN);
                }
                else if (event.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT)
                {
                    input.nextKeyboardState.keyStates.remove(Keys.RightShift);
                    input.nextKeyboardState.keyStates.put(Keys.RightShift, KeyState.KEY_DOWN);
                }
            } break;
                
            case KeyEvent.VK_M:
            {
                input.nextKeyboardState.keyStates.remove(Keys.M);
                input.nextKeyboardState.keyStates.put(Keys.M, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_MULTIPLY:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Multiply);
                input.nextKeyboardState.keyStates.put(Keys.Multiply, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_N:
            {
                input.nextKeyboardState.keyStates.remove(Keys.N);
                input.nextKeyboardState.keyStates.put(Keys.N, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUM_LOCK:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumLock);
                input.nextKeyboardState.keyStates.put(Keys.NumLock, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUMPAD0:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad0);
                input.nextKeyboardState.keyStates.put(Keys.NumPad0, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUMPAD1:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad1);
                input.nextKeyboardState.keyStates.put(Keys.NumPad1, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUMPAD2:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad2);
                input.nextKeyboardState.keyStates.put(Keys.NumPad2, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUMPAD3:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad3);
                input.nextKeyboardState.keyStates.put(Keys.NumPad3, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUMPAD4:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad4);
                input.nextKeyboardState.keyStates.put(Keys.NumPad4, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUMPAD5:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad5);
                input.nextKeyboardState.keyStates.put(Keys.NumPad5, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUMPAD6:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad6);
                input.nextKeyboardState.keyStates.put(Keys.NumPad6, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUMPAD7:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad7);
                input.nextKeyboardState.keyStates.put(Keys.NumPad7, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUMPAD8:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad8);
                input.nextKeyboardState.keyStates.put(Keys.NumPad8, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_NUMPAD9:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad9);
                input.nextKeyboardState.keyStates.put(Keys.NumPad9, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_O:
            {
                input.nextKeyboardState.keyStates.remove(Keys.O);
                input.nextKeyboardState.keyStates.put(Keys.O, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_P:
            {
                input.nextKeyboardState.keyStates.remove(Keys.P);
                input.nextKeyboardState.keyStates.put(Keys.P, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_PAGE_DOWN:
            {
                input.nextKeyboardState.keyStates.remove(Keys.PageDown);
                input.nextKeyboardState.keyStates.put(Keys.PageDown, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_PAGE_UP:
            {
                input.nextKeyboardState.keyStates.remove(Keys.PageUp);
                input.nextKeyboardState.keyStates.put(Keys.PageUp, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_PAUSE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Pause);
                input.nextKeyboardState.keyStates.put(Keys.Pause, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_PRINTSCREEN:
            {
                input.nextKeyboardState.keyStates.remove(Keys.PrintScreen);
                input.nextKeyboardState.keyStates.put(Keys.PrintScreen, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_Q:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Q);
                input.nextKeyboardState.keyStates.put(Keys.Q, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_R:
            {
                input.nextKeyboardState.keyStates.remove(Keys.R);
                input.nextKeyboardState.keyStates.put(Keys.R, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_RIGHT:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Right);
                input.nextKeyboardState.keyStates.put(Keys.Right, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_S:
            {
                input.nextKeyboardState.keyStates.remove(Keys.S);
                input.nextKeyboardState.keyStates.put(Keys.S, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_SCROLL_LOCK:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Scroll);
                input.nextKeyboardState.keyStates.put(Keys.Scroll, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_SPACE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Space);
                input.nextKeyboardState.keyStates.put(Keys.Space, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_SUBTRACT:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Subtract);
                input.nextKeyboardState.keyStates.put(Keys.Subtract, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_T:
            {
                input.nextKeyboardState.keyStates.remove(Keys.T);
                input.nextKeyboardState.keyStates.put(Keys.T, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_TAB:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Tab);
                input.nextKeyboardState.keyStates.put(Keys.Tab, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_U:
            {
                input.nextKeyboardState.keyStates.remove(Keys.U);
                input.nextKeyboardState.keyStates.put(Keys.U, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_UP:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Up);
                input.nextKeyboardState.keyStates.put(Keys.Up, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_V:
            {
                input.nextKeyboardState.keyStates.remove(Keys.V);
                input.nextKeyboardState.keyStates.put(Keys.V, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_W:
            {
                input.nextKeyboardState.keyStates.remove(Keys.W);
                input.nextKeyboardState.keyStates.put(Keys.W, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_X:
            {
                input.nextKeyboardState.keyStates.remove(Keys.X);
                input.nextKeyboardState.keyStates.put(Keys.X, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_Y:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Y);
                input.nextKeyboardState.keyStates.put(Keys.Y, KeyState.KEY_DOWN);
            } break;
                
            case KeyEvent.VK_Z:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Z);
                input.nextKeyboardState.keyStates.put(Keys.Z, KeyState.KEY_DOWN);
            } break;
                
            default:
                break;
        }
	}

	@Override
	public void
	keyReleased(KeyEvent event)
	{
		switch (event.getKeyCode())
        {
            case KeyEvent.VK_A:
            {
                input.nextKeyboardState.keyStates.remove(Keys.A);
                input.nextKeyboardState.keyStates.put(Keys.A, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_ADD:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Add);
                input.nextKeyboardState.keyStates.put(Keys.Add, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_B:
            {
                input.nextKeyboardState.keyStates.remove(Keys.B);
                input.nextKeyboardState.keyStates.put(Keys.B, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_BACK_SPACE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Back);
                input.nextKeyboardState.keyStates.put(Keys.Back, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_C:
            {
                input.nextKeyboardState.keyStates.remove(Keys.C);
                input.nextKeyboardState.keyStates.put(Keys.C, KeyState.KEY_UP);
            } break;
            
            case KeyEvent.VK_CAPS_LOCK:
            {
                input.nextKeyboardState.keyStates.remove(Keys.CapsLock);
                input.nextKeyboardState.keyStates.put(Keys.CapsLock, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_D:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D);
                input.nextKeyboardState.keyStates.put(Keys.D, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_0:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D0);
                input.nextKeyboardState.keyStates.put(Keys.D0, KeyState.KEY_UP);
            } break;
                    
            case KeyEvent.VK_1:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D1);
                input.nextKeyboardState.keyStates.put(Keys.D1, KeyState.KEY_UP);
            } break;
                        
            case KeyEvent.VK_2:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D2);
                input.nextKeyboardState.keyStates.put(Keys.D2, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_3:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D3);
                input.nextKeyboardState.keyStates.put(Keys.D3, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_4:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D4);
                input.nextKeyboardState.keyStates.put(Keys.D4, KeyState.KEY_UP);
            } break;
            
            case KeyEvent.VK_5:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D5);
                input.nextKeyboardState.keyStates.put(Keys.D5, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_6:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D6);
                input.nextKeyboardState.keyStates.put(Keys.D6, KeyState.KEY_UP);
            } break;
                        
            case KeyEvent.VK_7:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D7);
                input.nextKeyboardState.keyStates.put(Keys.D7, KeyState.KEY_UP);
            } break;
                                
            case KeyEvent.VK_8:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D8);
                input.nextKeyboardState.keyStates.put(Keys.D8, KeyState.KEY_UP);
            } break;
                                        
            case KeyEvent.VK_9:
            {
                input.nextKeyboardState.keyStates.remove(Keys.D9);
                input.nextKeyboardState.keyStates.put(Keys.D9, KeyState.KEY_UP);
            } break;
                                                
            case KeyEvent.VK_DELETE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Delete);
                input.nextKeyboardState.keyStates.put(Keys.Delete, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_DIVIDE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Divide);
                input.nextKeyboardState.keyStates.put(Keys.Divide, KeyState.KEY_UP);
            } break;
                    
            case KeyEvent.VK_DOWN:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Down);
                input.nextKeyboardState.keyStates.put(Keys.Down, KeyState.KEY_UP);
            } break;
                        
            case KeyEvent.VK_E:
            {
                input.nextKeyboardState.keyStates.remove(Keys.E);
                input.nextKeyboardState.keyStates.put(Keys.E, KeyState.KEY_UP);
            } break;
                            
            case KeyEvent.VK_END:
            {
                input.nextKeyboardState.keyStates.remove(Keys.End);
                input.nextKeyboardState.keyStates.put(Keys.End, KeyState.KEY_UP);
            } break;
                                
            case KeyEvent.VK_ENTER:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Enter);
                input.nextKeyboardState.keyStates.put(Keys.Enter, KeyState.KEY_UP);
            } break;
                                    
            case KeyEvent.VK_ESCAPE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Escape);
                input.nextKeyboardState.keyStates.put(Keys.Escape, KeyState.KEY_UP);
            } break;
                                        
            case KeyEvent.VK_F:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F);
                input.nextKeyboardState.keyStates.put(Keys.F, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F1:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F1);
                input.nextKeyboardState.keyStates.put(Keys.F1, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F2:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F2);
                input.nextKeyboardState.keyStates.put(Keys.F2, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F3:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F3);
                input.nextKeyboardState.keyStates.put(Keys.F3, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F4:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F4);
                input.nextKeyboardState.keyStates.put(Keys.F4, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F5:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F5);
                input.nextKeyboardState.keyStates.put(Keys.F5, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F6:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F6);
                input.nextKeyboardState.keyStates.put(Keys.F6, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F7:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F7);
                input.nextKeyboardState.keyStates.put(Keys.F7, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F8:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F8);
                input.nextKeyboardState.keyStates.put(Keys.F8, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F9:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F9);
                input.nextKeyboardState.keyStates.put(Keys.F9, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F10:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F10);
                input.nextKeyboardState.keyStates.put(Keys.F10, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F11:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F11);
                input.nextKeyboardState.keyStates.put(Keys.F11, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_F12:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F12);
                input.nextKeyboardState.keyStates.put(Keys.F12, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_G:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F12);
                input.nextKeyboardState.keyStates.put(Keys.F12, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_H:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F12);
                input.nextKeyboardState.keyStates.put(Keys.F12, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_HOME:
            {
                input.nextKeyboardState.keyStates.remove(Keys.F12);
                input.nextKeyboardState.keyStates.put(Keys.F12, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_I:
            {
                input.nextKeyboardState.keyStates.remove(Keys.I);
                input.nextKeyboardState.keyStates.put(Keys.I, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_INSERT:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Insert);
                input.nextKeyboardState.keyStates.put(Keys.Insert, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_J:
            {
                input.nextKeyboardState.keyStates.remove(Keys.J);
                input.nextKeyboardState.keyStates.put(Keys.J, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_K:
            {
                input.nextKeyboardState.keyStates.remove(Keys.K);
                input.nextKeyboardState.keyStates.put(Keys.K, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_L:
            {
                input.nextKeyboardState.keyStates.remove(Keys.L);
                input.nextKeyboardState.keyStates.put(Keys.L, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_LEFT:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Left);
                input.nextKeyboardState.keyStates.put(Keys.Left, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_ALT:
            {
                input.nextKeyboardState.keyStates.remove(Keys.LeftAlt);
                input.nextKeyboardState.keyStates.put(Keys.LeftAlt, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_CONTROL:
            {
                if (event.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
                {
                    input.nextKeyboardState.keyStates.remove(Keys.LeftControl);
                    input.nextKeyboardState.keyStates.put(Keys.LeftControl, KeyState.KEY_UP);
                }
                else if (event.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT)
                {
                    input.nextKeyboardState.keyStates.remove(Keys.RightControl);
                    input.nextKeyboardState.keyStates.put(Keys.RightControl, KeyState.KEY_UP);
                }
            } break;
                
            case KeyEvent.VK_SHIFT:
            {
                if (event.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
                {
                    input.nextKeyboardState.keyStates.remove(Keys.LeftShift);
                    input.nextKeyboardState.keyStates.put(Keys.LeftShift, KeyState.KEY_UP);
                }
                else if (event.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT)
                {
                    input.nextKeyboardState.keyStates.remove(Keys.RightShift);
                    input.nextKeyboardState.keyStates.put(Keys.RightShift, KeyState.KEY_UP);
                }
            } break;
                
            case KeyEvent.VK_M:
            {
                input.nextKeyboardState.keyStates.remove(Keys.M);
                input.nextKeyboardState.keyStates.put(Keys.M, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_MULTIPLY:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Multiply);
                input.nextKeyboardState.keyStates.put(Keys.Multiply, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_N:
            {
                input.nextKeyboardState.keyStates.remove(Keys.N);
                input.nextKeyboardState.keyStates.put(Keys.N, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUM_LOCK:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumLock);
                input.nextKeyboardState.keyStates.put(Keys.NumLock, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUMPAD0:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad0);
                input.nextKeyboardState.keyStates.put(Keys.NumPad0, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUMPAD1:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad1);
                input.nextKeyboardState.keyStates.put(Keys.NumPad1, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUMPAD2:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad2);
                input.nextKeyboardState.keyStates.put(Keys.NumPad2, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUMPAD3:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad3);
                input.nextKeyboardState.keyStates.put(Keys.NumPad3, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUMPAD4:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad4);
                input.nextKeyboardState.keyStates.put(Keys.NumPad4, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUMPAD5:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad5);
                input.nextKeyboardState.keyStates.put(Keys.NumPad5, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUMPAD6:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad6);
                input.nextKeyboardState.keyStates.put(Keys.NumPad6, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUMPAD7:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad7);
                input.nextKeyboardState.keyStates.put(Keys.NumPad7, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUMPAD8:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad8);
                input.nextKeyboardState.keyStates.put(Keys.NumPad8, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_NUMPAD9:
            {
                input.nextKeyboardState.keyStates.remove(Keys.NumPad9);
                input.nextKeyboardState.keyStates.put(Keys.NumPad9, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_O:
            {
                input.nextKeyboardState.keyStates.remove(Keys.O);
                input.nextKeyboardState.keyStates.put(Keys.O, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_P:
            {
                input.nextKeyboardState.keyStates.remove(Keys.P);
                input.nextKeyboardState.keyStates.put(Keys.P, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_PAGE_DOWN:
            {
                input.nextKeyboardState.keyStates.remove(Keys.PageDown);
                input.nextKeyboardState.keyStates.put(Keys.PageDown, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_PAGE_UP:
            {
                input.nextKeyboardState.keyStates.remove(Keys.PageUp);
                input.nextKeyboardState.keyStates.put(Keys.PageUp, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_PAUSE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Pause);
                input.nextKeyboardState.keyStates.put(Keys.Pause, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_PRINTSCREEN:
            {
                input.nextKeyboardState.keyStates.remove(Keys.PrintScreen);
                input.nextKeyboardState.keyStates.put(Keys.PrintScreen, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_Q:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Q);
                input.nextKeyboardState.keyStates.put(Keys.Q, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_R:
            {
                input.nextKeyboardState.keyStates.remove(Keys.R);
                input.nextKeyboardState.keyStates.put(Keys.R, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_RIGHT:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Right);
                input.nextKeyboardState.keyStates.put(Keys.Right, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_S:
            {
                input.nextKeyboardState.keyStates.remove(Keys.S);
                input.nextKeyboardState.keyStates.put(Keys.S, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_SCROLL_LOCK:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Scroll);
                input.nextKeyboardState.keyStates.put(Keys.Scroll, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_SPACE:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Space);
                input.nextKeyboardState.keyStates.put(Keys.Space, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_SUBTRACT:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Subtract);
                input.nextKeyboardState.keyStates.put(Keys.Subtract, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_T:
            {
                input.nextKeyboardState.keyStates.remove(Keys.T);
                input.nextKeyboardState.keyStates.put(Keys.T, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_TAB:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Tab);
                input.nextKeyboardState.keyStates.put(Keys.Tab, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_U:
            {
                input.nextKeyboardState.keyStates.remove(Keys.U);
                input.nextKeyboardState.keyStates.put(Keys.U, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_UP:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Up);
                input.nextKeyboardState.keyStates.put(Keys.Up, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_V:
            {
                input.nextKeyboardState.keyStates.remove(Keys.V);
                input.nextKeyboardState.keyStates.put(Keys.V, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_W:
            {
                input.nextKeyboardState.keyStates.remove(Keys.W);
                input.nextKeyboardState.keyStates.put(Keys.W, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_X:
            {
                input.nextKeyboardState.keyStates.remove(Keys.X);
                input.nextKeyboardState.keyStates.put(Keys.X, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_Y:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Y);
                input.nextKeyboardState.keyStates.put(Keys.Y, KeyState.KEY_UP);
            } break;
                
            case KeyEvent.VK_Z:
            {
                input.nextKeyboardState.keyStates.remove(Keys.Z);
                input.nextKeyboardState.keyStates.put(Keys.Z, KeyState.KEY_UP);
            } break;
                
            default:
                break;
        }
	}

	@Override
	public void
	keyTyped(KeyEvent event)
	{
	}

	
}
