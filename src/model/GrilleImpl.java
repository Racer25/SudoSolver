package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
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
				this.cases[i][j] = new CaseImpl(0,i,j);
			}
		}
		
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				this.cases[i][j].setVoisins(this.getCasesLiees(this.cases[i][j]));
			}
		}
		
	}

	public CaseImpl[][] getCases() {
		return cases;
	}
	
	public List<CaseImpl> getCasesNonAssigneesTrieeParTailleDomaineEtFixerPriorites() 
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

		//Fixer les priorités de chaque case
		for(int i=0; i<casesNonAssigneesTrieeParTailleDomaine.size(); i++)
		{
			casesNonAssigneesTrieeParTailleDomaine.get(i).setPrioriteTraitement(i);
		}
		
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
	
	public List<CaseImpl> getCasesLiees(CaseImpl maCase)
	{
		List<CaseImpl> casesLiees=new LinkedList<CaseImpl>();
		casesLiees.addAll(getCasesLigne(maCase));
		casesLiees.addAll(getCasesColonne((maCase)));
		casesLiees.addAll(getCasesBlocRestantes((maCase)));
		return casesLiees;
	}

	public List<CaseImpl> getCasesLigne(CaseImpl maCase) 
	{
		List<CaseImpl> casesLigne=new LinkedList<CaseImpl>();
		for (int j=0; j < 9; j++)
	    {
			CaseImpl caseParcourue=this.getCase(maCase.getX(), j);
	    	if (!(caseParcourue == maCase))
	    	{
	    		casesLigne.add(caseParcourue);
	    	}
	    }
	    return casesLigne;
	}
	
	public List<CaseImpl> getCasesColonne(CaseImpl maCase) 
	{
		List<CaseImpl> casesColonne=new LinkedList<CaseImpl>();
		for (int i=0; i < 9; i++)
	    {
			CaseImpl caseParcourue=this.getCase(i, maCase.getY());
	    	if (!(caseParcourue == maCase))
	    	{
	    		casesColonne.add(caseParcourue);
	    	}
	    }
	    return casesColonne;
	}
	
	public List<CaseImpl> getCasesBlocRestantes(CaseImpl maCase) 
	{
		List<CaseImpl> casesBlocRestantes=new LinkedList<CaseImpl>();
		int minXBloc = 3*(maCase.getX()/3);
	    int minYBloc = 3*(maCase.getY()/3);
	    for (int i=minXBloc; i < minXBloc+3; i++)
	    {
	    	for (int j=minYBloc; j < minYBloc+3; j++)
	        {
	    		CaseImpl caseParcourue=this.getCase(i, j);
		   		if (caseParcourue.getX()!=maCase.getX() && 
		   				caseParcourue.getY()!=maCase.getY())
		        {
		   			casesBlocRestantes.add(caseParcourue);
		        }
	        }
	    }
	    return casesBlocRestantes;
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
