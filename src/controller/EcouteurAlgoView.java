package controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import model.GrilleImpl;
import model.SolverImpl;
import model.utils.BooleanObservable;
import view.Chronometre;
import view.GrilleViewImpl;

public class EcouteurAlgoView implements Observer
{
	private JButton start;
	private JButton reset;
	private JButton options;
	private JButton exportPDF;
	private JButton importerUneNouvelleGrille;
	private JButton importerUneGrilleAleatoire;
	private JButton entrerManuellementUneGrille;
	private Chronometre chrono;
	private GrilleImpl grille;
	private GrilleViewImpl grilleView;
	
	public EcouteurAlgoView(JButton start, JButton reset, JButton options, 
			JButton exportPDF, JButton importerUneNouvelleGrille, 
			JButton importerUneGrilleAleatoire, JButton entrerManuellementUneGrille,
			Chronometre chrono, GrilleImpl grille, GrilleViewImpl grilleView) 
	{
		this.start=start;
		this.reset=reset;
		this.options=options;
		this.exportPDF=exportPDF;
		this.importerUneNouvelleGrille=importerUneNouvelleGrille;
		this.importerUneGrilleAleatoire=importerUneGrilleAleatoire;
		this.entrerManuellementUneGrille=entrerManuellementUneGrille;
		this.chrono=chrono;
		this.grille=grille;
		this.grilleView=grilleView;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(((boolean)arg1)==true)
		{
			this.start.setEnabled(false);
			this.reset.setEnabled(false);
			this.options.setEnabled(false);
			this.exportPDF.setEnabled(false);
			this.importerUneGrilleAleatoire.setEnabled(false);
			this.importerUneNouvelleGrille.setEnabled(false);
			this.entrerManuellementUneGrille.setEnabled(false);
			this.chrono.tare();
			this.chrono.lancer();
		}
		else
		{
			this.start.setEnabled(true);
			this.reset.setEnabled(true);
			this.options.setEnabled(true);
			this.exportPDF.setEnabled(true);
			this.importerUneGrilleAleatoire.setEnabled(true);
			this.importerUneNouvelleGrille.setEnabled(true);
			this.entrerManuellementUneGrille.setEnabled(true);
			this.chrono.arreter();
			//actualisation forcée
			for(int x = 0 ; x < 9 ; x++)
			{
				for(int y = 0 ; y < 9 ; y++)
				{
					if(this.grille.getCase(x, y).getValue()!=0)
					{
						this.grilleView.getCaseViews()[x][y].getValueView().setText(
								Integer.toString(this.grille.getCase(x, y).getValue()));
					}
					else
					{
						this.grilleView.getCaseViews()[x][y].getValueView().setText(
								" ");
					}
					
				}
			}
		}
		
	}

}
