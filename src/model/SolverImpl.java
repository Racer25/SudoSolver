package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import model.contract.Solver;
import view.Chronometre;

//Résout le sudoku
public class SolverImpl extends Thread implements Solver 
{
	
	private GrilleImpl grille;
	private List<CaseImpl> casesAvecContraintesCreees;
	private List<ConstraintImpl> contraintes;
	private List<CaseImpl> casesNonAssigneesTrieeParTailleDomaine;
	private Chronometre chronometre;
	private JButton start;
	
	public SolverImpl(GrilleImpl grille, Chronometre chronometre, JButton start)
	{
		this.grille=grille;
		this.start = start;
		this.chronometre = chronometre;
		this.casesAvecContraintesCreees=new ArrayList<CaseImpl>();
		this.contraintes=new ArrayList<ConstraintImpl>();
		//constraintsGenerator();
		preDomaineReducer();
		this.casesNonAssigneesTrieeParTailleDomaine=
				this.grille.getCasesNonAssigneesTrieeParTailleDomaineEtFixerPriorites();
	}
	
	public void constraintsGenerator()
	{
		for(int i=0; i<this.grille.getCases().length; i++)
		{
			for(int j=0; j<this.grille.getCases()[i].length; j++)
			{
				CaseImpl caseParcourue=this.grille.getCases()[i][j];
				List<CaseImpl> casesLiees=this.grille.getCases()[i][j].getVoisins();
				
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
	
	public void preDomaineReducer()
	{
		for(int i=0; i <this.grille.getCases().length; i++)
		{
			for(int j=0; j <this.grille.getCases()[i].length; j++)
			{
				if(this.grille.getCases()[i][j].getValue()!=0)
				{
					//Prélèvement des voisins de la case
			    	List<CaseImpl> casesLiees=this.grille.getCases()[i][j].getVoisins();
			    	
			    	for(CaseImpl maCaseLiee: casesLiees)
			    	{
			    		maCaseLiee.getDomain().remove(this.grille.getCases()[i][j].getValue());
			    	}
			    	
				}
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
		System.out.println("Initialisation de la résolution");
		
		boolean resolved=backtracking(0);

		if(resolved)
		{
			//Arret du chronometre
			chronometre.arreter();
			//Faire un truc
			System.out.println("Grille resolue");
			
		}
		else
		{
			//Arret du chronometre
			chronometre.arreter();
			
			//Faire un autre truc
			System.out.println("Ta grille est fausse");
			
		}
		
		//Acces aux boutons
		start.setEnabled(true);
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
	    
	    //SAVE domaine de la case
	  	List<Integer> domainSave=new ArrayList<Integer>(maCase.getDomain());
	    
	  	//Pour chaque valeur possible
	    for(Integer value: maCase.getDomain())
	    {
	    	maCase.setValue(value);
	    	
	    	//Si cela satisfait toutes les contraintes de la case
	        if (isOkay(maCase))
	        {
	        	//Reduction des domaines des cases futures+check si unDomaineVide
	        	/*
	        	if(forwardChecking(maCase, pos))
	        	{
	        		return false;
	        	}
	        	*/
	        	
	        	//arcConsistency(maCase, pos);
	        	
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
	
	//Vérifie si la valeur actuelle de la case est possible au vue de l'était actuel de la grille
	//A APPELER SUR UNE CASE NON VIDE/NON ASSIGNEE
	public boolean isOkay(CaseImpl maCase)
	{
		//Cette valeur satisfait-elle toutes les contraintes?
    	boolean constraintsSatisfied=true;
    	int k=0;
    	while(constraintsSatisfied && k<maCase.getVoisins().size())
    	{
    		if(maCase.getValue()==maCase.getVoisins().get(k).getValue())
    		{
    			constraintsSatisfied=false;
    		}
    		k++;
    	}
    	return constraintsSatisfied;
	}

	
	public boolean forwardChecking(CaseImpl maCase, int pos) 
	{
		List<CaseImpl> casesLiees=maCase.getVoisins();
		
		int k=0;
		boolean unDomaineVide=false;
		while(!unDomaineVide && k < casesLiees.size())
		{
			//Problème ici
			int posCaseLiee=casesLiees.get(k).getPrioriteTraitement();
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
		List<CaseImpl> casesLiees=maCase.getVoisins();
	
		for(CaseImpl caseLiee: casesLiees)
		{
			int posCaseLiee=caseLiee.getPrioriteTraitement();
			if(posCaseLiee>pos)
			{
				caseLiee.getDomain().add(maCase.getValue());
			}
		}
	}
	
	
	//Va réduire le domaine des valeurs possibles de la case est de ses voisins,
	//En utilisant les contraintes
	public void arcConsistency(CaseImpl maCase, int pos) 
	{
		LinkedList<CaseImpl> voisinsATester=new LinkedList<CaseImpl>(maCase.getVoisins());
		while(!voisinsATester.isEmpty())
		{
			CaseImpl voisinParcouru=voisinsATester.getFirst();
			if(voisinParcouru.getPrioriteTraitement()>pos)
			{
				boolean removed=domainReducerAC(voisinParcouru, maCase, pos);
				voisinsATester.removeFirst();
				//Avec les voisins/cases liées
				if(removed)
				{
					List<CaseImpl> casesLiees=maCase.getVoisins();
					for(CaseImpl caseLiee:casesLiees)
					{
						voisinsATester.addFirst(caseLiee);
					}
				}
			}
		}
	}
	
	//Pour ArcConsistency
	//Supprime des valeurs du domaine et retourne le fait d'avoir supprimer qqchose ou non
	public boolean domainReducerAC(CaseImpl voisin, CaseImpl maCase, int pos) 
	{
		boolean somethingRemoved=false;
		Iterator<Integer> domainIterator=maCase.getDomain().iterator();
		while(domainIterator.hasNext())
		{
			Integer valeurPossible=domainIterator.next();
			
			boolean satisfiable=false;
			int k=0;
			while(!satisfiable && k<voisin.getDomain().size())
			{
				//Est ce qu'on peut avoir des valeurs différentes??
				if(valeurPossible!=voisin.getDomain().get(k))
				{
					satisfiable=true;
				}
				k++;
			}
			if(!satisfiable)
			{
				domainIterator.remove();
				somethingRemoved=true;
			}
		}
		
		return somethingRemoved;
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
