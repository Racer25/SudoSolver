package controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import model.SolverImpl;
import model.utils.BooleanObservable;
import view.Chronometre;

public class EcouteurAlgoBoutons implements Observer
{
	private JButton start;
	private JButton reset;
	private JButton options;
	private JButton exportPDF;
	private JButton importerUneNouvelleGrille;
	private JButton importerUneGrilleAleatoire;
	private JButton entrerManuellementUneGrille;
	private Chronometre chrono;
	
	public EcouteurAlgoBoutons(JButton start, JButton reset, JButton options, 
			JButton exportPDF, JButton importerUneNouvelleGrille, 
			JButton importerUneGrilleAleatoire, JButton entrerManuellementUneGrille,
			Chronometre chrono) 
	{
		this.start=start;
		this.reset=reset;
		this.options=options;
		this.exportPDF=exportPDF;
		this.importerUneNouvelleGrille=importerUneNouvelleGrille;
		this.importerUneGrilleAleatoire=importerUneGrilleAleatoire;
		this.entrerManuellementUneGrille=entrerManuellementUneGrille;
		this.chrono=chrono;
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
		}
		
	}

}
