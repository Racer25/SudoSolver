package app;

import model.GrilleImpl;
import view.WindowImpl;

public class MainClass 
{
	
	//Variables 
	private static int algorithme; //0 = fordwardChecking, 1 = arc consistency et 2 = sans propagation de contraintes
	private static int visuel;//0 = affichage des etapes et 1 = affichage du resultat uniquement
	
	public static void main(String[] args) 
	{
		algorithme = 0;
		visuel = 0;
		GrilleImpl grilleImpl = new GrilleImpl();
		@SuppressWarnings("unused")
		WindowImpl w = new WindowImpl(grilleImpl, algorithme, visuel);
	}
}
