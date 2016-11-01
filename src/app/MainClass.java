package app;

import model.GrilleImpl;
import model.utils.PDFGenerator;
import view.WindowImpl;

public class MainClass 
{
	
	//Variables 
	private static int algorithme; //0 = fordwardChecking et 1 = arc consistency7
	private static int visuel;//0 = affichage des etapes et 1 = affichage du resultat uniquement
	
	public static void main(String[] args) 
	{
		algorithme = 0;
		visuel = 0;
		GrilleImpl grilleImpl = new GrilleImpl();
		WindowImpl w = new WindowImpl(grilleImpl,algorithme,visuel);
	}
}
