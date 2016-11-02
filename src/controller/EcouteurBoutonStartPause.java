package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import model.GrilleImpl;
import model.SolverImpl;
import model.contract.Solver;
import view.Chronometre;

public class EcouteurBoutonStartPause implements ActionListener{

	//Variables
	private GrilleImpl grille;
	private EcouteurAlgoView ecouteurAlgoView;
	
	public EcouteurBoutonStartPause(EcouteurAlgoView ecouteurAlgoView){
		this.ecouteurAlgoView=ecouteurAlgoView;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//Résolution
		Solver solver = new SolverImpl(ecouteurAlgoView, grille, 0);
		solver.start();
	
	}

	public GrilleImpl getGrille() {
		return grille;
	}

	public void setGrille(GrilleImpl grille) {
		this.grille = grille;
	}

}
