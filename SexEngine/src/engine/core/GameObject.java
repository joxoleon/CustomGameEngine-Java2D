package engine.core;


public class GameObject 
{
	//Fields	
	private static int IDGenerator = 0;
	private int ID = ++IDGenerator;
	
	private String tag = "";
	
	
	//Methods
	public int 
	getID()
	{
		return ID;
	}
	
	public void 
	setTag(String tag)
	{
		if(tag != null)
			this.tag = tag;
	}
	
	public String 
	getTag()
	{
		return tag;
	}
	
}
