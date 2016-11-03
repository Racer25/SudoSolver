package controller;

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.GrilleImpl;
import view.EntreeManuelleView;
import view.GrilleViewImpl;

public class EcouteurBoutonEntrerManuellementUneGrille implements ActionListener{

	//Variables
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private GrilleViewImpl vueGrille;
	
	public EcouteurBoutonEntrerManuellementUneGrille(GrilleViewImpl vueGrille,GrilleImpl grilleInitiale)
	{
		this.grilleFinale = vueGrille.getGrilleImpl();
		this.grilleInitiale = grilleInitiale;
		this.vueGrille = vueGrille;
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		@SuppressWarnings("unused")
		EntreeManuelleView entreeManuelleView = new EntreeManuelleView(vueGrille,grilleFinale,grilleInitiale);
	}

}
