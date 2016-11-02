package controller;

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.GrilleImpl;
import model.SolverImpl;
import model.contract.Solver;
import view.WindowImpl;

public class EcouteurBoutonStartPause implements ActionListener
{

	//Variables
	private GrilleImpl grille;
	private EcouteurAlgoView ecouteurAlgoView;
	private WindowImpl frame;
	
	public EcouteurBoutonStartPause(EcouteurAlgoView ecouteurAlgoView, WindowImpl frame, GrilleImpl grille)
	{
		this.ecouteurAlgoView=ecouteurAlgoView;
		this.frame=frame;
		this.grille=grille;
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{		
		//Resolution
		Solver solver = new SolverImpl(this.ecouteurAlgoView, this.grille, 
				this.frame.getAlgorithme());
		solver.start();
	}

	public GrilleImpl getGrille() 
	{
		return grille;
	}

	public void setGrille(GrilleImpl grille) 
	{
		this.grille = grille;
	}

}
