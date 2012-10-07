package controlClasses;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tournament implements Serializable{
	/*
	 * Test!
	 */
	private String name;
	private boolean[] map;
	public static final int NUM_MAPS = 20;
	
	public Tournament(String n)
	{
		name = n;
		map = new boolean[NUM_MAPS];
		
		for(int i = 0; i < NUM_MAPS; i++)
			map[i] = false;
	}
	
	public boolean isBeaten(int i)
	{
		return map[i];
	}
	
	public void setBeaten(boolean b, int i)
	{
		map[i] = b;
	}
	
	public boolean[] get()
	{
		return map;
	}
	
	public String getName()
	{
		return name;
	}
}
