package model;

//Imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import controller.EcouteurAlgoView;
import model.contract.Solver;
import model.utils.BooleanObservable;

//Resout le sudoku
public class SolverImpl extends Thread implements Solver 
{
	//Variables
	private GrilleImpl grille;
	private List<CaseImpl> casesNonAssigneesTrieeParTailleDomaine;
	private BooleanObservable booleanObservable;
	private int algo;

	public SolverImpl(EcouteurAlgoView ecouteurAlgoView, GrilleImpl grille, int algo) 
	{
		this.algo = algo;
		this.booleanObservable = new BooleanObservable();
		this.booleanObservable.addObserver(ecouteurAlgoView);
		this.grille = grille;
		preDomaineReducer();
		this.casesNonAssigneesTrieeParTailleDomaine = this.grille
				.getCasesNonAssigneesTrieeParTailleDomaineEtFixerPriorites();
	}

	public void preDomaineReducer() 
	{
		for (int i = 0; i < this.grille.getCases().length; i++)
		{
			for (int j = 0; j < this.grille.getCases()[i].length; j++) 
			{
				if (this.grille.getCases()[i][j].getValue() != 0) 
				{
					// Prélèvement des voisins de la case
					List<CaseImpl> casesLiees = this.grille.getCases()[i][j].getVoisins();

					for (CaseImpl maCaseLiee : casesLiees) 
					{
						maCaseLiee.getDomain().remove(this.grille.getCases()[i][j].getValue());
					}

				}
			}
		}
	}

	public void run() 
	{
		this.booleanObservable.setEnCoursDeCalcul(true);
		solve();
	}

	public void solve() 
	{
		System.out.println("Initialisation de la résolution");
		boolean resolved = false;
		if (this.algo == 0) 
		{
			System.out.println("Choix : Forward Checking");
			resolved = backtrackingFC(0);
		}
		else if (this.algo == 1) 
		{
			System.out.println("Choix : Arc Consistency");
			resolved = backtrackingAC(0);
		}
		else 
		{
			System.out.println("Choix : Sans propagation de contrainte");
			resolved = backtracking(0);
		}
		if (resolved) 
		{
			// Faire un truc
			System.out.println("Grille resolue");
		}
		else 
		{
			// Faire un autre truc
			System.out.println("Ta grille est fausse");
		}

		// Fin de la resolution
		this.booleanObservable.setEnCoursDeCalcul(false);
	}

	// Pos est le numéro de la case, sert pour faire du récursif
	public boolean backtrackingFC(int pos)
	{
		// Si on est au bout du sudoku, c'est qu'on a pas eu de blocages :)
		if (pos == this.casesNonAssigneesTrieeParTailleDomaine.size()) 
		{
			return true;
		}

		CaseImpl maCase = this.casesNonAssigneesTrieeParTailleDomaine.get(pos);

		// SAVE domaine de la case
		// List<Integer> domainSave=new ArrayList<Integer>(maCase.getDomain());

		// Pour chaque valeur possible
		for (Integer value : maCase.getDomain()) 
		{
			maCase.setValue(value);

			// Si cela satisfait toutes les contraintes de la case
			if (isOkay(maCase)) 
			{
				// Copie profondes des domaines des voisins
				List<CaseImpl> voisins = maCase.getVoisins();
				List<List<Integer>> domainsVoisinsSave = new ArrayList<List<Integer>>();

				// Petite sauvegarde voisin
				for (CaseImpl voisin : voisins) 
				{
					List<Integer> domaine = new ArrayList<Integer>();
					for (Integer val : voisin.getDomain())
					{
						domaine.add(val);
					}
					domainsVoisinsSave.add(domaine);
				}

				// Reduction des domaines des cases futures
				forwardChecking(maCase, pos);

				// On passe à la suite
				if (backtracking(pos + 1)) 
				{
					return true;
				} 
				else
				{
					// Utilisation de la sauvegarde profonde des domaines
					for (int i = 0; i < domainsVoisinsSave.size(); i++) 
					{
						int posVoisin = voisins.get(i).getPrioriteTraitement();
						if (posVoisin > pos) 
						{
							voisins.get(i).setDomain(domainsVoisinsSave.get(i));
						}

					}
				}
			}
		}
		maCase.setValue(0);

		return false;
	}

	// Pos est le numero de la case, sert pour faire du recursif
	public boolean backtrackingAC(int pos) 
	{
		// Si on est au bout du sudoku, c'est qu'on a pas eu de blocages :)
		if (pos == this.casesNonAssigneesTrieeParTailleDomaine.size())
		{
			return true;
		}

		CaseImpl maCase = this.casesNonAssigneesTrieeParTailleDomaine.get(pos);

		// SAVE domaine de la case
		// List<Integer> domainSave=new ArrayList<Integer>(maCase.getDomain());

		// Pour chaque valeur possible
		for (Integer value : maCase.getDomain()) 
		{
			maCase.setValue(value);

			// Si cela satisfait toutes les contraintes de la case
			if (isOkay(maCase)) 
			{
				// Grosse sauvegarde tout >pos
				List<List<Integer>> domainsSave = new ArrayList<List<Integer>>();
				for (CaseImpl maCaseNonAssignee : this.casesNonAssigneesTrieeParTailleDomaine) 
				{
					List<Integer> domaine = new ArrayList<Integer>();
					for (Integer val : maCaseNonAssignee.getDomain())
					{
						domaine.add(val);
					}
					domainsSave.add(domaine);
				}

				// Reduction des domaines des cases futures
				arcConsistency(maCase, pos);

				// On passe à la suite
				if (backtracking(pos + 1)) 
				{
					return true;
				}

				else 
				{
					// Utilisation de la sauvegarde profonde des domaines
					for (int i = 0; i < this.casesNonAssigneesTrieeParTailleDomaine.size(); i++) 
					{
						if (this.casesNonAssigneesTrieeParTailleDomaine.get(i).getPrioriteTraitement() > pos) {
							this.casesNonAssigneesTrieeParTailleDomaine.get(i).setDomain(domainsSave.get(i));
						}
					}

				}

			}
		}
		maCase.setValue(0);

		return false;
	}

	// Pos est le numero de la case, sert pour faire du recursif
	public boolean backtracking(int pos) 
	{
		// Si on est au bout du sudoku, c'est qu'on a pas eu de blocages :)
		if (pos == this.casesNonAssigneesTrieeParTailleDomaine.size()) 
		{
			return true;
		}

		CaseImpl maCase = this.casesNonAssigneesTrieeParTailleDomaine.get(pos);

		// Pour chaque valeur possible
		for (Integer value : maCase.getDomain()) 
		{
			maCase.setValue(value);

			// Si cela satisfait toutes les contraintes de la case
			if (isOkay(maCase)) 
			{
				// Copie profondes des domaines des voisins
				List<CaseImpl> voisins = maCase.getVoisins();
				List<List<Integer>> domainsVoisinsSave = new ArrayList<List<Integer>>();

				// Petite sauvegarde voisin
				for (CaseImpl voisin : voisins) 
				{
					List<Integer> domaine = new ArrayList<Integer>();
					for (Integer val : voisin.getDomain()) 
					{
						domaine.add(val);
					}
					domainsVoisinsSave.add(domaine);
				}

				// Grosse sauvegarde tout >pos
				List<List<Integer>> domainsSave = new ArrayList<List<Integer>>();
				for (CaseImpl maCaseNonAssignee : this.casesNonAssigneesTrieeParTailleDomaine)
				{
					List<Integer> domaine = new ArrayList<Integer>();
					for (Integer val : maCaseNonAssignee.getDomain()) 
					{
						domaine.add(val);
					}
					domainsSave.add(domaine);
				}

				// Reduction des domaines des cases futures
				if (this.algo == 0)
				{
					forwardChecking(maCase, pos);
				}
				else if (this.algo == 1)
				{
					arcConsistency(maCase, pos);
				}

				// On passe à la suite
				if (backtracking(pos + 1))
				{
					return true;
				}

				else 
				{
					// Utilisation de la sauvegarde profonde des domaines
					if (this.algo == 0) 
					{

					} 
					else if (this.algo == 1) 
					{

					}
					for (int i = 0; i < this.casesNonAssigneesTrieeParTailleDomaine.size(); i++) 
					{
						if (this.casesNonAssigneesTrieeParTailleDomaine.get(i).getPrioriteTraitement() > pos) 
						{
							this.casesNonAssigneesTrieeParTailleDomaine.get(i).setDomain(domainsSave.get(i));
						}
					}
				}
			}
		}
		maCase.setValue(0);
		return false;
	}

	// Verifie si la valeur actuelle de la case est possible au vue de l'etat
	// actuel de la grille
	// A APPELER SUR UNE CASE NON VIDE/NON ASSIGNEE
	public boolean isOkay(CaseImpl maCase)
	{
		// Cette valeur satisfait-elle toutes les contraintes?
		boolean constraintsSatisfied = true;
		int k = 0;
		while (constraintsSatisfied && k < maCase.getVoisins().size()) 
		{
			if (maCase.getValue() == maCase.getVoisins().get(k).getValue()) 
			{
				constraintsSatisfied = false;
			}
			k++;
		}
		return constraintsSatisfied;
	}

	public void forwardChecking(CaseImpl maCase, int pos) 
	{
		List<CaseImpl> casesLiees = maCase.getVoisins();
		int k = 0;
		boolean unDomaineVide = false;
		while (!unDomaineVide && k < casesLiees.size()) {
			// Probleme ici
			int posCaseLiee = casesLiees.get(k).getPrioriteTraitement();
			if (posCaseLiee > pos) {
				casesLiees.get(k).getDomain().remove(maCase.getValue());
				if (casesLiees.get(k).getDomain().isEmpty()) {
					unDomaineVide = true;
				}
			}
			k++;

		}
	}

	// Va reduire le domaine des valeurs possibles de la case est de ses
	// voisins,
	// En utilisant les contraintes
	public void arcConsistency(CaseImpl maCase, int pos) 
	{
		LinkedList<CaseImpl[]> couplesATester = new LinkedList<CaseImpl[]>();
		for (CaseImpl voisin : maCase.getVoisins()) 
		{
			CaseImpl[] couple = { maCase, voisin };
			couplesATester.add(couple);
		}
		while (!couplesATester.isEmpty()) 
		{
			CaseImpl[] coupleParcouru = couplesATester.getFirst();
			boolean removed = false;
			if (coupleParcouru[1].getPrioriteTraitement() > pos) 
			{
				removed = domainReducerAC(coupleParcouru[1], coupleParcouru[0], pos);
			}
			couplesATester.removeFirst();
			if (coupleParcouru[1].getPrioriteTraitement() > pos) 
			{
				// Avec les voisins/cases liees
				if (removed) 
				{
					List<CaseImpl> voisinsDeVoisin = coupleParcouru[1].getVoisins();
					for (CaseImpl voisinDeVoisin : voisinsDeVoisin) 
					{
						if (voisinDeVoisin.getPrioriteTraitement() > pos) 
						{
							CaseImpl[] newCouple = { coupleParcouru[1], voisinDeVoisin };
							couplesATester.addFirst(newCouple);
						}
					}
				}
			}

		}
	}

	// Pour ArcConsistency
	// Supprime des valeurs du domaine et retourne le fait d'avoir supprimer
	// qqchose ou non
	public boolean domainReducerAC(CaseImpl voisin, CaseImpl maCase, int pos)
	{
		boolean somethingRemoved = false;
		Iterator<Integer> domainIterator = maCase.getDomain().iterator();
		while (domainIterator.hasNext()) 
		{
			Integer valeurPossible = domainIterator.next();
			boolean satisfiable = false;
			int k = 0;
			while (!satisfiable && k < voisin.getDomain().size()) {
				// Est ce qu'on peut avoir des valeurs differentes??
				if (valeurPossible != voisin.getDomain().get(k))
				{
					satisfiable = true;
				}
				k++;
			}
			if (!satisfiable) 
			{
				domainIterator.remove();
				somethingRemoved = true;
			}
		}

		return somethingRemoved;
	}

}
