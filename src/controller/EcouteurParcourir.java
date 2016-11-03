package controller;

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.GrilleImpl;
import model.utils.SsReaderImpl;
import view.GrilleViewImpl;

public class EcouteurParcourir implements ActionListener
{	
	//Variables 
	private JFrame frame;
	private GrilleViewImpl vueGrille;
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private SsReaderImpl ssReaderImpl;
	private GrilleImpl valeurs;
	
	public EcouteurParcourir(JFrame frame,GrilleViewImpl vueGrille, GrilleImpl grilleInitiale)
	{
		this.frame = frame;
		this.ssReaderImpl = new SsReaderImpl();
		this.grilleFinale = vueGrille.getGrilleImpl();
		this.vueGrille = vueGrille;
		this.grilleInitiale = grilleInitiale;
	}
	
	@SuppressWarnings({ "static-access", "rawtypes" })
	public void actionPerformed(ActionEvent arg0) 
	{
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Fichiers .ss", "ss");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(frame);
	    if(returnVal == JFileChooser.APPROVE_OPTION)
	    {
	    	if(chooser.getSelectedFile().getName().endsWith(".ss")==true)
	    	{
	    		String file = chooser.getSelectedFile().getPath().replace('\\', '/');
	    		valeurs = ssReaderImpl
					.lireSs(file);
	    		System.out.println("Selection de la grille : "+chooser.getSelectedFile().getName());
				//La grille Initiale Copie profonde
		for(int x = 0 ; x < 9 ; x++)
		{
			for(int y = 0 ; y < 9 ; y++)
			{
				//Changement du domaine de la case
				grilleInitiale.getCase(x, y).setDomain(valeurs.getCase(x, y).getDomain());
				grilleFinale.getCase(x, y).setDomain(valeurs.getCase(x, y).getDomain());
								
				grilleInitiale.getCase(x, y).setValue(valeurs.getCase(x, y).getValue());
				grilleFinale.getCase(x, y).setValue(valeurs.getCase(x, y).getValue());
				vueGrille.getCaseViews()[x][y].setCaseImplValue(valeurs.getCase(x, y).getValue());
			
				grilleFinale.getCase(x, y).setDomain(new ArrayList());
				grilleInitiale.getCase(x, y).setDomain(new ArrayList());
				for(int k = 0 ; k < 9 ; k++)
				{
					grilleFinale.getCase(x, y).getDomain().add(k+1);
					grilleInitiale.getCase(x, y).getDomain().add(k+1);
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
				
			}
		}			
		vueGrille.revalidate();
	    	}
	    	else
	    	{
				JOptionPane jOptionPane =  new JOptionPane();
				jOptionPane.showMessageDialog(frame,"Vous devez choisir un fichier au format .ss",null, JOptionPane.WARNING_MESSAGE);
				actionPerformed(arg0);
	    	}
	    	
		    
	   }
	}
		
}
