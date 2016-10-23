package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.contract.Solver;

//Résout le sudoku
public class SolverImpl extends Thread implements Solver 
{
	
	private GrilleImpl grille;
	private List<CaseImpl> casesAvecContraintesCreees;
	private List<ConstraintImpl> contraintes;
	
	public SolverImpl(GrilleImpl grille)
	{
		this.grille=grille;
		this.casesAvecContraintesCreees=new ArrayList<CaseImpl>();
		this.contraintes=new ArrayList<ConstraintImpl>();
		constraintsGenerator();
	}
	
	public void constraintsGenerator()
	{
		for(int i=0; i<this.grille.getCases().length; i++)
		{
			for(int j=0; j<this.grille.getCases()[i].length; j++)
			{
				CaseImpl caseParcourue=this.grille.getCases()[i][j];
				List<CaseImpl> casesLiees=getCasesLiees(caseParcourue);
				
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
	
	@Override
	public void run()
	{
		solve();
	}
	
	
	@Override
	public void solve() 
	{
		System.out.println("Initialisation de la resolution");
		boolean resolved=backtracking(0);
		
		if(resolved)
		{
			//Faire un truc
		}
	}
	
	//Pos est le numéro de la case, sert pour faire du récursif
	public boolean backtracking(int pos) 
	{
		//Si on est au bout du sudoku, c'est qu'on a pas eu de blocages :)
		if (pos == 9*9)
		{
			//System.out.println("Dernière case");
			System.out.println("Fin de la resolution");
			return true;
		}
		
		//Prélèvement de la case
	    int i = pos/9;
	    int j = pos%9;
	    CaseImpl maCase=this.grille.getCase(i, j);
	    
    	//Prélèvement des contraintes de la case
    	List<ConstraintImpl> contraintesDeLaCase=getConstraints(maCase);
    	
    	//Si la case n'est pas vide, on avance
	    if (maCase.getValue() != 0)
	    {
	    	//System.out.println("Case non vide, suivante!");
	    	return backtracking(pos+1);
	    }
	    
	    //Si la case est vide, pour chaque valeur possible
	    for (int value : maCase.getDomain())
	    {
	    	//System.out.println("Case Vide, au travail!");
	    	//testons cette valeur dans cette case
	    	maCase.setValue(value);
	    	
	    	//Cette valeur satisfait-elle toutes les contraintes?
	    	boolean constraintsSatisfied=true;
	    	int k=0;
	    	while(constraintsSatisfied && k<contraintesDeLaCase.size())
	    	{
	    		if(!contraintesDeLaCase.get(k).isSatisfied())
	    		{
	    			constraintsSatisfied=false;
	    		}
	    		else
	    		{
	    			k++;
	    		}
	    	}
	    	
	    	//Si cela satisfait toutes les contraintes de la case
	        if (constraintsSatisfied)
	        {
	        	//Reduction des domaines
	        	arcConsistency(maCase);
	        	//On passe à la suite
	            if (backtracking(pos+1))
	            {
	            	return true;
	            }
	        }
	    }
	    maCase.setValue(0);

	    return false;
	}
	
	//Va réduire le domaine des valeurs possibles de la case est de ses voisins,
	//En utilisant les contraintes
	public void arcConsistency(CaseImpl maCase) 
	{
		LinkedList<ConstraintImpl> contraintesATester=getConstraints(maCase);
		while(!contraintesATester.isEmpty())
		{
			ConstraintImpl contrainteParcourue=contraintesATester.get(0);
			contraintesATester.remove(0);
			boolean removed=domainReducer(contrainteParcourue);
			//...Avec les voisins/cases liées
			if(removed)
			{
				List<CaseImpl> casesLiees=getCasesLiees(contrainteParcourue.getCase1());
				for(CaseImpl caseLiee:casesLiees)
				{
					ConstraintImpl contrainteAvecCaseLiee=getConstraint(caseLiee, maCase);
					contraintesATester.addFirst(contrainteAvecCaseLiee);
				}
			}
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
	
	public List<CaseImpl> getCasesLiees(CaseImpl maCase)
	{
		List<CaseImpl> casesLiees=new ArrayList<CaseImpl>();
		casesLiees.addAll(getCasesLigne(maCase));
		casesLiees.addAll(getCasesColonne((maCase)));
		casesLiees.addAll(getCasesBlocRestantes((maCase)));
		return casesLiees;
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
	
	public LinkedList<ConstraintImpl> getConstraints(CaseImpl maCase)
	{
		LinkedList<ConstraintImpl> contraintesDeLaCase=new LinkedList<ConstraintImpl>();
		for(ConstraintImpl contrainte: this.contraintes)
		{
			if(contrainte.getCase1()==maCase || 
					contrainte.getCase2()==maCase)
			{
				contraintesDeLaCase.add(contrainte);
			}
		}
		return contraintesDeLaCase;
	}
	
	public ConstraintImpl getConstraint(CaseImpl maCase1, CaseImpl maCase2)
	{
		ConstraintImpl contrainteDeLaCase=null;
		for(ConstraintImpl contrainte: this.contraintes)
		{
			if(contrainte.getCase1()==maCase1 && contrainte.getCase2()==maCase2)
			{
				contrainteDeLaCase=contrainte;
			}
			else if(contrainte.getCase1()==maCase2 && contrainte.getCase2()==maCase1)
			{
				contrainteDeLaCase=contrainte;
			}
		}
		return contrainteDeLaCase;
	}



	
	
}
