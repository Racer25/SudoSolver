package model.utils;

//Imports
import java.util.Observable;

public class BooleanObservable extends Observable
{
	//Variables
	private boolean enCoursDeCalcul;
	
	public BooleanObservable() 
	{
		this.enCoursDeCalcul=false;
	}

	public boolean isEnCoursDeCalcul()
	{
		return enCoursDeCalcul;
	}

	public void setEnCoursDeCalcul(boolean enCoursDeCalcul) 
	{
		this.enCoursDeCalcul = enCoursDeCalcul;
		setChanged();
		notifyObservers(enCoursDeCalcul);
	}
}
