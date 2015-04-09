package test;

import java.util.LinkedList;
import java.util.ListIterator;

public class TestMain
{

	public static void main(String[] args)
	{
		LinkedList<Integer> list;
		list = new LinkedList<Integer>();
		
		for (int i = 0; i < 10; i++)
		{
			list.add(i + 1);
		}
		
		ListIterator<Integer> myIterator;
		myIterator = list.listIterator();
		
		while(myIterator.hasNext())
		{
			Integer value = myIterator.next();
			if(value > 3)
			{
				myIterator.remove();
			}
			
		}
		
		myIterator = list.listIterator();
		
		while(myIterator.hasNext())
		{
			System.out.println(myIterator.next());
		}
		
	}
}
