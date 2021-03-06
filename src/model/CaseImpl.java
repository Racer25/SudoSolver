package model;

//Imports
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CaseImpl extends Observable implements Comparable<CaseImpl>
{	
	//Valeur de 0 a 9 inclus (0 signifiant VIDE)
	//Variables
	private Integer value;
	private int x;
	private int y;
	private List<Integer> domain;
	private List<CaseImpl> voisins;
	private int prioriteTraitement;

	//Constructors
	public CaseImpl(int value, int x, int y)
	{
		this.x=x;
		this.y=y;
		this.value=value;
		this.domain=new ArrayList<Integer>();
		//Domaine par defaut
		if(value==0)
		{
			for(int i=1; i<=9; i++)
			{
				this.domain.add(i);
			}
		}
		//Domaine si assigne
		else
		{
			this.domain.add(value);
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
	public Integer getValue() 
	{
		return value;
	}

	public void setValue(Integer value) 
	{
		this.value = value;
		setChanged();
		notifyObservers(value);
	}

	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public List<Integer> getDomain() 
	{
		return domain;
	}

	public void setDomain(List<Integer> domain) 
	{
		this.domain = domain;
	}

	@Override
	public int compareTo(CaseImpl o) 
	{
		if(this.getDomain().size()<o.getDomain().size())
		{
			return -1;
		}
		else if(this.getDomain().size()==o.getDomain().size())
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	public List<CaseImpl> getVoisins() 
	{
		return voisins;
	}

	public void setVoisins(List<CaseImpl> voisins) 
	{
		this.voisins = voisins;
	}

	public int getPrioriteTraitement() 
	{
		return prioriteTraitement;
	}

	public void setPrioriteTraitement(int prioriteTraitement) 
	{
		this.prioriteTraitement = prioriteTraitement;
	}
	
}
