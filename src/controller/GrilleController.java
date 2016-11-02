package controller;

import model.GrilleImpl;
import view.GrilleViewImpl;

public class GrilleController
{
	private GrilleImpl grille;
	private GrilleViewImpl grilleView;
	private int visuel;
	
	public GrilleController(GrilleImpl grille, GrilleViewImpl grilleView, int visuel) 
	{
		this.grille = grille;
		this.grilleView = grilleView;
		this.visuel=visuel;
		
		for(int i=0; i<grille.getCases().length; i++)
		{
			for(int j=0; j<grille.getCases()[i].length; j++)
			{
				CaseController caseController=new CaseController(grille.getCases()[i][j], 
						grilleView.getCaseViews()[i][j], visuel);
			}
			
		}
	}

	public int getVisuel() {
		return visuel;
	}

	public void setVisuel(int visuel) {
		this.visuel = visuel;
	}

}
