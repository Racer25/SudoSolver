package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.GrilleImpl;
import model.SolverImpl;
import model.contract.Solver;

public class EcouteurBoutonStartPause implements ActionListener{

	//Variables
	private ImageIcon imageBoutonStartPause;
	private JButton boutonStartPause; 
	private GrilleImpl grille;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
		//Résolution
		Solver solver=new SolverImpl(grille);
		solver.start();
	}

	public GrilleImpl getGrille() {
		return grille;
	}

	public void setGrille(GrilleImpl grille) {
		this.grille = grille;
	}

}
