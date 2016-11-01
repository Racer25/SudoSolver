package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GrilleImpl;
import view.Chronometre;

public class EcouteurBoutonReset implements ActionListener{

	//Variables 
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private Chronometre chronometre;
	
	public EcouteurBoutonReset(Chronometre chronometre,GrilleImpl grilleInitiale, GrilleImpl grilleFinale){
		this.grilleFinale = grilleFinale;
		this.grilleInitiale = grilleInitiale;
		this.chronometre = chronometre;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 9 ; i++){
			for(int j = 0 ; j < 9 ; j++){
				grilleFinale.getCase(i, j).setValue(grilleInitiale.getCase(i, j).getValue());
			}
		}
		chronometre.tare();
	}

}
