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
	
	public EcouteurBoutonEntrerManuellementUneGrille(GrilleViewImpl vueGrille,GrilleImpl grilleInitiale){
		
		this.grilleFinale = vueGrille.getGrilleImpl();
		this.grilleInitiale = grilleInitiale;
		this.vueGrilleFinale = vueGrilleFinale;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		EntreeManuelleView entreeManuelleView = new EntreeManuelleView();
		
	}

}
