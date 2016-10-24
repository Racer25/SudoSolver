package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.contract.Grille;

public class GrilleImpl implements Grille
{
	private CaseImpl[][] cases;
	
	public GrilleImpl(CaseImpl[][] cases)
	{
		super();
		this.cases=cases;
	}
	
	public GrilleImpl()
	{
		super();
		this.cases = new CaseImpl[9][9];
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				cases[i][j] = new CaseImpl(0,i,j);
			}
		}
	}

	public CaseImpl[][] getCases() {
		return cases;
	}
	
	public List<CaseImpl> getCasesNonAssigneesTrieeParTailleDomaine() 
	{
		List<CaseImpl> casesNonAssigneesTrieeParTailleDomaine =new ArrayList<CaseImpl>();
		for(int i=0; i <this.getCases().length; i++)
		{
			for(int j=0; j <this.getCases()[i].length; j++)
			{
				CaseImpl maCase=this.getCase(i, j);
				if(maCase.getValue()==0)
				{
					casesNonAssigneesTrieeParTailleDomaine.add(maCase);
				}
			}
		}
		
		Collections.sort(casesNonAssigneesTrieeParTailleDomaine);
		
		System.out.println("Domaine mini: "+casesNonAssigneesTrieeParTailleDomaine.get(0).getDomain().size()+
				"à la case x="+casesNonAssigneesTrieeParTailleDomaine.get(0).getX()+", y="+
				casesNonAssigneesTrieeParTailleDomaine.get(0).getY());
		System.out.println("Domaine maxi: "+casesNonAssigneesTrieeParTailleDomaine.get(casesNonAssigneesTrieeParTailleDomaine.size()-1).getDomain().size());
		
		return casesNonAssigneesTrieeParTailleDomaine;
	}

	public void setCases(CaseImpl[][] cases) {
		this.cases = cases;
	}

	@Override
	public CaseImpl getCase(int x, int y)
	{
		return cases[x][y];
	}
	
	public void affichage()
	{
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				if(j==8)
				{
					System.out.print(cases[i][j].getValue()+"\n");
				}
				else
				{
					System.out.print(cases[i][j].getValue()+" ");
				}
				
			}
		}
	}
}
