package controller;

//Imports
import model.GrilleImpl;
import view.GrilleViewImpl;
import view.WindowImpl;

public class GrilleController
{
	//Variables
	@SuppressWarnings("unused")
	private GrilleImpl grille;
	@SuppressWarnings("unused")
	private GrilleViewImpl grilleView;
	private WindowImpl frame;
	
	public GrilleController(GrilleImpl grille, GrilleViewImpl grilleView, WindowImpl frame) 
	{
		this.grille = grille;
		this.grilleView = grilleView;
		this.frame=frame;
		
		for(int i=0; i<grille.getCases().length; i++)
		{
			for(int j=0; j<grille.getCases()[i].length; j++)
			{
				@SuppressWarnings("unused")
				CaseController caseController=new CaseController(grille.getCases()[i][j], 
						grilleView.getCaseViews()[i][j], this.frame);
			}
		}
	}

}
