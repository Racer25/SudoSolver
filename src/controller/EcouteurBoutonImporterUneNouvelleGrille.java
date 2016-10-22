package controller;

import java.awt.GridBagConstraints;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GrilleImpl;
import model.SsReaderImpl;
import view.GrilleViewImpl;

public class EcouteurBoutonImporterUneNouvelleGrille implements ActionListener{

	//Variables
	private GrilleViewImpl vueGrilleInitial;
	private GrilleImpl grilleInitiale;
	private SsReaderImpl ssReaderImpl;
	private Random random;
	private GrilleImpl valeurs;
	
	public EcouteurBoutonImporterUneNouvelleGrille(GrilleViewImpl vueGrilleInitial, GrilleImpl grilleInitiale){
		
		this.grilleInitiale = grilleInitiale;
		this.vueGrilleInitial = vueGrilleInitial;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		random = new Random();
		
		//A RETOUCHER PARCOURIR LE DOSSIER
		ssReaderImpl = new SsReaderImpl();
		File repertoire = new File("./grilles");
		String [] listeFichiers;
		listeFichiers=repertoire.list(); 
		int i = random.nextInt(listeFichiers.length);
		if(listeFichiers[i].endsWith(".ss")==true){
			valeurs = ssReaderImpl.lireSs("./grilles/"+listeFichiers[i]);
		}
		System.out.println("Selection de la grille : "+listeFichiers[i]);
				
		//La grille Initiale
		for(int x = 0 ; x < 9 ; x++){
			for(int y = 0 ; y < 9 ; y++){
				grilleInitiale.getCase(x, y).setValue(valeurs.getCase(x, y).getValue());
				//PROBLEME vueGrilleInitial.getCaseViews()[x][y].setValueView(valeurs.getCase(x, y).getValue())

			}
		}
				
		vueGrilleInitial.revalidate();
		}
}

