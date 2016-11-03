package controller;

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.GrilleImpl;
import view.Chronometre;
import view.GrilleViewImpl;

public class EcouteurBoutonReset implements ActionListener
{
	//Variables 
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private Chronometre chronometre;
	private GrilleViewImpl vueGrille;
	
	public EcouteurBoutonReset(GrilleViewImpl vueGrille, Chronometre chronometre,GrilleImpl grilleInitiale, GrilleImpl grilleFinale)
	{
		this.vueGrille = vueGrille;
		this.grilleFinale = grilleFinale;
		this.grilleInitiale = grilleInitiale;
		this.chronometre = chronometre;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actionPerformed(ActionEvent arg0) 
	{
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				grilleFinale.getCase(i, j).setValue(grilleInitiale.getCase(i, j).getValue());
				/*grilleFinale.getCase(i, j).setDomain(new ArrayList());
				for(int k = 0 ; k < grilleInitiale.getCase(i, j).getDomain().size() ; k++)
				{
					grilleFinale.getCase(i, j).getDomain().add(grilleInitiale.getCase(i, j).getDomain().get(k));
				}*/
			}
		}
		chronometre.tare();
		for(int x = 0 ; x < 9 ; x++)
		{
			for(int y = 0 ; y < 9 ; y++)
			{
				if(grilleInitiale.getCase(x, y).getValue()!=0)
				{
					vueGrille.getCaseViews()[x][y].getValueView().setText(
							Integer.toString(grilleInitiale.getCase(x, y).getValue()));
				}
				else
				{
					vueGrille.getCaseViews()[x][y].getValueView().setText(
							" ");
				}
				
			}
		}		
		vueGrille.revalidate();
	}

}
