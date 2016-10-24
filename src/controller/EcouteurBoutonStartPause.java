package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Chronometre;
import model.GrilleImpl;
import model.SolverImpl;
import model.contract.Solver;

public class EcouteurBoutonStartPause implements ActionListener{

	//Variables
	private GrilleImpl grille;
	private Chronometre chronometre;
	
	public EcouteurBoutonStartPause(Chronometre chronometre){
		this.chronometre = chronometre;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		//Remise à zéro du chronometre
		chronometre.tare();
		
		//Lancement du chronometre 
		chronometre.lancer();
		
		//RÃ©solution
		Solver solver = new SolverImpl(grille,chronometre);
		solver.start();
	
	}

	public GrilleImpl getGrille() {
		return grille;
	}

	public void setGrille(GrilleImpl grille) {
		this.grille = grille;
	}

}
