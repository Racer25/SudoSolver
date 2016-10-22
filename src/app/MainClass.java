package app;

import model.GrilleImpl;
import view.WindowImpl;

public class MainClass 
{
	public static void main(String[] args) 
	{
		GrilleImpl grilleImpl = new GrilleImpl();
		grilleImpl.affichage();
		WindowImpl w = new WindowImpl(grilleImpl);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grilleImpl.affichage();
		
	}
}
