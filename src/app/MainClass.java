package app;

import model.CaseImpl;
import model.GrilleImpl;
import model.SsReaderImpl;
import view.CaseViewImpl;
import view.WindowImpl;

public class MainClass 
{
	public static void main(String[] args) 
	{
		CaseImpl[][] cases = new CaseImpl[9][9];
		for(int i = 0 ; i < 9 ; i++){
			for(int j = 0 ; j < 9 ; j++){
				cases[i][j] = new CaseImpl(0,i,j);
			}
		}
		GrilleImpl grilleImpl = new GrilleImpl();
		WindowImpl w = new WindowImpl(grilleImpl);
	}
}
