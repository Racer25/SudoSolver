package app;

import model.GrilleImpl;
import model.PDFGenerator;
import view.WindowImpl;

public class MainClass 
{
	public static void main(String[] args) 
	{
		GrilleImpl grilleImpl = new GrilleImpl();
		WindowImpl w = new WindowImpl(grilleImpl);
	}
}
