package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.contract.Solver;

//Résout le sudoku
public class SolverImpl extends Thread implements Solver 
{
	
	private GrilleImpl grille;
	private List<CaseImpl> casesAvecContraintesCreees;
	private List<ConstraintImpl> contraintes;
	private List<CaseImpl> casesNonAssigneesTrieeParTailleDomaine;
	
	public SolverImpl(GrilleImpl grille)
	{
		this.grille=grille;
		this.casesAvecContraintesCreees=new ArrayList<CaseImpl>();
		this.contraintes=new ArrayList<ConstraintImpl>();
		constraintsGenerator();
		preDomaineReducer();
		this.casesNonAssigneesTrieeParTailleDomaine=
				this.grille.getCasesNonAssigneesTrieeParTailleDomaine();
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
		long chrono = java.lang.System.currentTimeMillis() ; 
		System.out.println("Initialisation de la résolution");
		
		boolean resolved=backtracking(0);
		
		long chrono2 = java.lang.System.currentTimeMillis() ; 
		long temps = chrono2 - chrono ; 
		System.out.println("Temps ecoule = " + temps/1000.0 + " s") ; 
		if(resolved)
		{
			//Faire un truc
			System.out.println("Grille résolue");
		}
		else
		{
			//Faire un autre truc
			System.out.println("Ta grille est fausse");
		}
	}
	
	public void preDomaineReducer()
	{
		for(int i=0; i <this.grille.getCases().length; i++)
		{
			for(int j=0; j <this.grille.getCases()[i].length; j++)
			{
				if(this.grille.getCases()[i][j].getValue()!=0)
				{
					//Prélèvement des contraintes de la case
			    	List<CaseImpl> casesLiees=getCasesLiees(this.grille.getCases()[i][j]);
			    	
			    	for(CaseImpl maCaseLiee: casesLiees)
			    	{
			    		maCaseLiee.getDomain().remove(this.grille.getCases()[i][j].getValue());
			    	}
			    	
				}
			}
		}
	}
	
	//Pos est le numéro de la case, sert pour faire du récursif
	public boolean backtracking(int pos) 
	{
		//Si on est au bout du sudoku, c'est qu'on a pas eu de blocages :)
		if (pos == this.casesNonAssigneesTrieeParTailleDomaine.size())
		{
			return true;
		}
		
	    CaseImpl maCase=this.casesNonAssigneesTrieeParTailleDomaine.get(pos);
	    
    	//Prélèvement des contraintes de la case
    	List<ConstraintImpl> contraintesDeLaCase=getConstraints(maCase);	    
	    //SAVE domaine de la case
	  	List<Integer> domainSave=new ArrayList<Integer>(maCase.getDomain());
	    
	  	//Pour chaque valeur possible
	    for(Integer value: maCase.getDomain())
	    {
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
	    		k++;
	    	}
	    	
	    	//Si cela satisfait toutes les contraintes de la case
	        if (constraintsSatisfied)
	        {
	        	//Reduction des domaines des cases futures+check si unDomaineVide
	        	/*
	        	 * if(forwardChecking(maCase, pos))
	        	{
	        		return false;
	        	}
	        	*/
	        	
	        	//arcConsistency(maCase);
	        	
	        	//On passe à la suite
	            if (backtracking(pos+1))
	            {
	            	return true;
	            }
	            /*
	            else
	            {
	            	 reverseChecking(maCase, pos);
	            }
	            */
	        }
	    }
	    maCase.setValue(0);
	    maCase.setDomain(domainSave);

	    return false;
	}
	

	
	public boolean forwardChecking(CaseImpl maCase, int pos) 
	{
		List<CaseImpl> casesLiees=getCasesLiees(maCase);
		
		int k=0;
		boolean unDomaineVide=false;
		while(!unDomaineVide && k < casesLiees.size())
		{
			int posCaseLiee=casesLiees.get(k).getY()+casesLiees.get(k).getX()*9;
			if(posCaseLiee>pos)
			{
				casesLiees.get(k).getDomain().remove((Integer)maCase.getValue());
				if(casesLiees.get(k).getDomain().isEmpty())
				{
					unDomaineVide=true;
				}
			}
			k++;
			
		}
		return unDomaineVide;
	}
	
	public void reverseChecking(CaseImpl maCase, int pos) 
	{		
		List<CaseImpl> casesLiees=getCasesLiees(maCase);
	
		for(CaseImpl caseLiee: casesLiees)
		{
			int posCaseLiee=caseLiee.getY()+caseLiee.getX()*9;
			if(posCaseLiee>pos)
			{
				caseLiee.getDomain().add(maCase.getValue());
			}
		}
	}
	
	
	//Va réduire le domaine des valeurs possibles de la case est de ses voisins,
	//En utilisant les contraintes
	public void arcConsistency(CaseImpl maCase) 
	{
		LinkedList<ConstraintImpl> contraintesATester=getConstraints(maCase);
		while(!contraintesATester.isEmpty())
		{
			ConstraintImpl contrainteParcourue=contraintesATester.getFirst();
			boolean removed=domainReducerAC(contrainteParcourue, maCase);
			contraintesATester.removeFirst();
			//Avec les voisins/cases liées
			if(removed)
			{
				List<CaseImpl> casesLiees=getCasesLiees(maCase);
				for(CaseImpl caseLiee:casesLiees)
				{
					ConstraintImpl contrainteAvecCaseLiee=getConstraint(caseLiee, maCase);
					contraintesATester.addFirst(contrainteAvecCaseLiee);
				}
			}
		}
		
	}
	
	//Pour ArcConsistency
	//Supprime des valeurs du domaine et retourne le fait d'avoir supprimer qqchose ou non
	public boolean domainReducerAC(ConstraintImpl contrainte, CaseImpl maCase) 
	{
		boolean somethingRemoved=false;
		Iterator<Integer> domainIterator=maCase.getDomain().iterator();
		while(domainIterator.hasNext())
		{
			Integer valeurPossible=domainIterator.next();
			
			boolean satisfiable=false;
			int k=0;
			while(!satisfiable && 
					k<contrainte.getOtherCase(maCase).getDomain().size())
			{
				//Est ce qu'on peut avoir des valeurs différentes??
				if(valeurPossible!=
						contrainte.getOtherCase(maCase).getDomain().get(k))
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
				domainIterator.remove();
				somethingRemoved=true;
			}
		}
		
		return somethingRemoved;
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
