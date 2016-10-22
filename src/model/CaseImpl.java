package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class CaseImpl extends Observable
{	
	//Valeur de 0 à 9 inclus (0 signifiant VIDE)
	private int value;
	
	private int x;
	private int y;
	
	private List<Integer> domain;
	
	
	//Constructors
	public CaseImpl(int value, int x, int y)
	{
		this.x=x;
		this.y=y;
		this.value=value;
		this.domain=new ArrayList<Integer>();
		//Domaine par défaut
		for(int i=1; i<=9; i++)
		{
			this.domain.add(i);
		}
		
	}
	
	public CaseImpl(int value, int x, int y, List<Integer> domain)
	{
		this.x=x;
		this.y=y;
		this.value=value;
		this.domain=domain;
	}

	
	//Getters and Setters
	public int getValue() 
	{
		return value;
	}

	public void setValue(int value) 
	{
		this.value = value;
		notifyObservers(value);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public List<Integer> getDomain() {
		return domain;
	}

	public void setDomain(List<Integer> domain) {
		this.domain = domain;
	}
	
	
}
