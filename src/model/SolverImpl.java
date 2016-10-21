package model;

import java.util.ArrayList;
import java.util.List;

import model.contract.Solver;

//Résout le sudoku
public class SolverImpl implements Solver 
{
	
	private GrilleImpl grille;
	private List<CaseImpl> casesAvecContraintesCreees;
	private List<ConstraintImpl> contraintes;
	
	public SolverImpl(GrilleImpl grille)
	{
		this.grille=grille;
		this.casesAvecContraintesCreees=new ArrayList<CaseImpl>();
		this.contraintes=new ArrayList<ConstraintImpl>();
	}
	
	
	
	@Override
	public void solve() 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void constraintsGenerator()
	{
		for(int i=0; i<this.grille.getCases().length; i++)
		{
			for(int j=0; j<this.grille.getCases()[i].length; j++)
			{
				CaseImpl caseParcourue=this.grille.getCases()[i][j];
				List<CaseImpl> casesLiees=new ArrayList<CaseImpl>();
				casesLiees.addAll(getCasesLigne(caseParcourue));
				casesLiees.addAll(getCasesColonne((caseParcourue)));
				casesLiees.addAll(getCasesBlocRestantes((caseParcourue)));
				
				for(CaseImpl caseLiee:casesLiees)
				{
					//Créer une contrainte si la caseLiee n'a pas été une caseParcourue
					if(!this.casesAvecContraintesCreees.contains(caseLiee))
					{
						this.contraintes.add(new ConstraintImpl(caseParcourue, caseLiee));
					}
				}
				this.casesAvecContraintesCreees.add(caseParcourue);
			}
		}
	}
	
	//Va réduire le domaine des valeurs possibles des cases de la grille
	//En utilisant les contraintes
	public void arcConsistency() 
	{
		List<ConstraintImpl> contraintesTestees=new ArrayList<ConstraintImpl>(this.contraintes);
		while(!contraintesTestees.isEmpty())
		{
			ConstraintImpl contrainteParcourue=contraintesTestees.get(0);
			contraintesTestees.remove(0);
			boolean removed=domainReducer(contrainteParcourue);
			//...Avec les voisins
		}
		
	}
	
	//Supprime des valeurs du domaine et retourne le fait d'avoir supprimer qqchose ou non
	public boolean domainReducer(ConstraintImpl contrainte) 
	{
		List<Integer> indexsASupprimer=new ArrayList<Integer>();		
		for(int i=0; i<contrainte.getCase1().getDomain().size(); i++)
		{
			boolean satisfiable=false;
			int k=0;
			while(!satisfiable && k<contrainte.getCase2().getDomain().size())
			{
				//Est ce qu'on peut avoir des valeurs différentes??
				if(contrainte.getCase1().getDomain().get(i)!=
						contrainte.getCase2().getDomain().get(k))
				{
					satisfiable=true;
				}
				else
				{
					k++;
				}
			}
			if(!satisfiable)
			{
				//Index des valeurs dans le domaine de la case1 à supprimer
				indexsASupprimer.add(i);
			}
		}
		//On supprime les valeurs du domaine
		for(int index:indexsASupprimer)
		{
			contrainte.getCase1().getDomain().remove(index);
		}
		
		return !indexsASupprimer.isEmpty();
	}
	

	@Override
	public List<CaseImpl> getCasesLigne(CaseImpl maCase) 
	{
		List<CaseImpl> casesLigne=new ArrayList<CaseImpl>();
		for (int j=0; j < 9; j++)
	    {
			CaseImpl caseParcourue=this.grille.getCase(maCase.getX(), j);
	    	if (!(caseParcourue == maCase))
	    	{
	    		casesLigne.add(caseParcourue);
	    	}
	    }
	    return casesLigne;
	}
	
	@Override
	public List<CaseImpl> getCasesColonne(CaseImpl maCase) 
	{
		List<CaseImpl> casesColonne=new ArrayList<CaseImpl>();
		for (int i=0; i < 9; i++)
	    {
			CaseImpl caseParcourue=this.grille.getCase(i, maCase.getY());
	    	if (!(caseParcourue == maCase))
	    	{
	    		casesColonne.add(caseParcourue);
	    	}
	    }
	    return casesColonne;
	}
	
	@Override
	public List<CaseImpl> getCasesBlocRestantes(CaseImpl maCase) 
	{
		List<CaseImpl> casesBlocRestantes=new ArrayList<CaseImpl>();
		int minXBloc = 3*(maCase.getX()/3);
	    int minYBloc = 3*(maCase.getY()/3);
	    for (int i=minXBloc; i < minXBloc+3; i++)
	    {
	    	for (int j=minYBloc; j < minYBloc+3; j++)
	        {
	    		CaseImpl caseParcourue=this.grille.getCase(i, j);
		   		if (caseParcourue.getX()!=maCase.getX() && 
		   				caseParcourue.getY()!=maCase.getY())
		        {
		   			casesBlocRestantes.add(caseParcourue);
		        }
	        }
	    }
	    return casesBlocRestantes;
	}



	
	
}
