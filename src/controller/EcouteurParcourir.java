package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.GrilleImpl;
import model.utils.SsReaderImpl;
import view.GrilleViewImpl;

public class EcouteurParcourir implements ActionListener{
	
	//Variables 
	private JFrame frame;
	private GrilleViewImpl vueGrille;
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private SsReaderImpl ssReaderImpl;
	private Random random;
	private GrilleImpl valeurs;
	
	public EcouteurParcourir(JFrame frame,GrilleViewImpl vueGrille, GrilleImpl grilleInitiale){
		this.frame = frame;
		this.grilleFinale = vueGrille.getGrilleImpl();
		this.vueGrille = vueGrille;
		this.grilleInitiale = grilleInitiale;
		
	}
	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Fichiers .ss", ".ss", ".ss");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(frame);
		    if(returnVal == JFileChooser.APPROVE_OPTION){
		    
		    if(chooser.getSelectedFile().getName().endsWith(".ss")==true){
		    	System.out.println(chooser.getSelectedFile().getAbsolutePath());
				valeurs = ssReaderImpl.lireSs(chooser.getSelectedFile().getAbsolutePath());
			}
		    	
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
				}
			}
			
			vueGrille.revalidate();
		}
		}
}
