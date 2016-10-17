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
			CaseController caseController=new CaseController(grille.getCases()[i], 
					grilleView.getCaseViews()[i]);
		}
	}

}
