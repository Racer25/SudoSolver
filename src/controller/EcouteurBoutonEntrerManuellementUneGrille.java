package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GrilleImpl;
import view.EntreeManuelleView;
import view.GrilleViewImpl;

public class EcouteurBoutonEntrerManuellementUneGrille implements ActionListener{

	//Variables
	private GrilleViewImpl vueGrilleInitiale;
	private GrilleImpl grilleInitiale;
	private GrilleViewImpl vueGrilleFinale;
	private GrilleImpl grilleFinale;
	
	public EcouteurBoutonEntrerManuellementUneGrille(GrilleViewImpl vueGrilleInitiale, GrilleViewImpl vueGrilleFinale){
		
		this.grilleFinale = vueGrilleFinale.getGrilleImpl();
		this.grilleInitiale = vueGrilleInitiale.getGrilleImpl();
		this.vueGrilleFinale = vueGrilleFinale;
		this.vueGrilleInitiale = vueGrilleInitiale;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		EntreeManuelleView entreeManuelleView = new EntreeManuelleView();
		
	}

}
