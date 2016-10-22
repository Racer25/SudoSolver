package controller;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.SsReaderImpl;
import view.GrilleViewImpl;

public class EcouteurBoutonImporterUneNouvelleGrille implements ActionListener{

	//Variables
	private JPanel panelGrilleInitiale;
	private SsReaderImpl ssReaderImpl;
	private Random random;
	private Integer[][] valeurs;
	
	public EcouteurBoutonImporterUneNouvelleGrille(JPanel panelGrilleInitiale){
		
		this.panelGrilleInitiale = panelGrilleInitiale;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		panelGrilleInitiale.removeAll();
		random = new Random();
		int i = random.nextInt(5);
		
		//A RETOUCHER PARCOURIR LE DOSSIER
		ssReaderImpl = new SsReaderImpl();
		valeurs = ssReaderImpl.lireSs("./grilles/3271469"+(30+i)+"-v3-29-L1.ss");
		
		System.out.println("Selection de la grille : "+(30+i));
		
		//Le label de la grille initiale
		JLabel labelGrilleInitiale = new JLabel("Grille initiale :");
		GridBagConstraints contraintesLabels = new GridBagConstraints();
		contraintesLabels.gridx=0;
		contraintesLabels.gridy=0;
		panelGrilleInitiale.add(labelGrilleInitiale, contraintesLabels);
				
		//La grille Initiale
		GrilleViewImpl grilleInitiale = new GrilleViewImpl(valeurs);
		GridBagConstraints contraintesGrilles = new GridBagConstraints();
		contraintesGrilles.gridx=0;
		contraintesGrilles.gridy=1;
		panelGrilleInitiale.add(grilleInitiale, contraintesGrilles);
				
		panelGrilleInitiale.revalidate();
	}

}
