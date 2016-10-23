package controller;

import model.GrilleImpl;
import view.GrilleViewImpl;

public class GrilleController
{
	private GrilleImpl grille;
	private GrilleViewImpl grilleView;
	
	public GrilleController(GrilleImpl grille, GrilleViewImpl grilleView) 
	{
		this.grille = grille;
		this.grilleView = grilleView;
		
		for(int i=0; i<grille.getCases().length; i++)
		{
			for(int j=0; j<grille.getCases()[i].length; j++)
			{
				CaseController caseController=new CaseController(grille.getCases()[i][j], 
						grilleView.getCaseViews()[i][j]);
			}
			
		}
	}

}
