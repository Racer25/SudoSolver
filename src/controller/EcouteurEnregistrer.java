package controller;

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;
import model.GrilleImpl;
import view.GrilleViewImpl;

public class EcouteurEnregistrer implements ActionListener{

	//Variables
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private JTextField[][] tabJTextField;
	private JFrame frame;
	private GrilleViewImpl vueGrille;
	
	public EcouteurEnregistrer(GrilleViewImpl vueGrille, JTextField[][] tabJTextField, GrilleImpl grilleInitiale, GrilleImpl grilleFinale, JFrame frame)
	{
		this.grilleInitiale = grilleInitiale;
		this.grilleFinale = grilleFinale;
		this.tabJTextField = tabJTextField;
		this.frame = frame;
		this.vueGrille = vueGrille;
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				if(!tabJTextField[i][j].getText().equals(""))
				{
					grilleInitiale.getCase(i, j).setValue(Integer.parseInt(tabJTextField[i][j].getText()));
					grilleFinale.getCase(i, j).setValue(Integer.parseInt(tabJTextField[i][j].getText()));
				}
				else
				{
					grilleInitiale.getCase(i, j).setValue(0);
					grilleFinale.getCase(i, j).setValue(0);
				}
			}		
		}
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
				grilleFinale.getCase(x, y).setDomain(new ArrayList());
				grilleInitiale.getCase(x, y).setDomain(new ArrayList());
				for(int k = 0 ; k < 9 ; k++)
				{
					grilleFinale.getCase(x, y).getDomain().add(k+1);
					grilleInitiale.getCase(x, y).getDomain().add(k+1);
				}
			}
		}		
		vueGrille.revalidate();
		frame.dispose();
	}
}
