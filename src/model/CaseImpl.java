package model;

import java.util.Observable;

public class CaseImpl extends Observable
{
	private int value;

	public int getValue() 
	{
		return value;
	}

	public void setValue(int value) 
	{
		this.value = value;
		notifyObservers(value);
	}
}
