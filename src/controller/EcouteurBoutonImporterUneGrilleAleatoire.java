package controller;

//Imports
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import model.GrilleImpl;
import model.utils.SsReaderImpl;
import view.GrilleViewImpl;

public class EcouteurBoutonImporterUneGrilleAleatoire implements ActionListener{

	//Variables
	private GrilleViewImpl vueGrille;
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private SsReaderImpl ssReaderImpl;
	private Random random;
	private GrilleImpl valeurs;
	
	public EcouteurBoutonImporterUneGrilleAleatoire(GrilleViewImpl vueGrille, GrilleImpl grilleInitiale)
	{
		this.grilleFinale = vueGrille.getGrilleImpl();
		this.vueGrille = vueGrille;
		this.grilleInitiale = grilleInitiale;
		this.ssReaderImpl = new SsReaderImpl();
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		random = new Random();
		//On parcours les fichiers du repertoire "grille" et on choisi un ".ss" aleatoirement
		File repertoire = new File("./grilles");
		String [] listeFichiers;
		listeFichiers=repertoire.list(); 
		int i = random.nextInt(listeFichiers.length);
		if(listeFichiers[i].endsWith(".ss")==true)
		{
			valeurs = ssReaderImpl.lireSs("./grilles/"+listeFichiers[i]);
		}
		System.out.println("Selection de la grille : "+listeFichiers[i]);
				
		//La grille Initiale Copie profonde
		for(int x = 0 ; x < 9 ; x++)
		{
			for(int y = 0 ; y < 9 ; y++)
			{
				//Changement du domaine de la case
				grilleInitiale.getCase(x, y).setDomain(valeurs.getCase(x, y).getDomain());
				grilleFinale.getCase(x, y).setDomain(valeurs.getCase(x, y).getDomain());
								
				grilleInitiale.getCase(x, y).setValue(valeurs.getCase(x, y).getValue());
				grilleFinale.getCase(x, y).setValue(valeurs.getCase(x, y).getValue());
				vueGrille.getCaseViews()[x][y].setCaseImplValue(valeurs.getCase(x, y).getValue());
			}
		}
		//actualisation forcee
		for(int x = 0 ; x < 9 ; x++)
		{
			for(int y = 0 ; y < 9 ; y++)
			{
				if(valeurs.getCase(x, y).getValue()!=0)
				{
					vueGrille.getCaseViews()[x][y].getValueView().setText(
							Integer.toString(valeurs.getCase(x, y).getValue()));
				}
				else
				{
					vueGrille.getCaseViews()[x][y].getValueView().setText(
							" ");
				}
				
			}
		}		
		vueGrille.revalidate();
	}
	
}

