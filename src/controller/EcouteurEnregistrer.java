package controller;

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import model.GrilleImpl;

public class EcouteurEnregistrer implements ActionListener{

	//Variables
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private JTextField[][] tabJTextField;
	private JFrame frame;
	
	public EcouteurEnregistrer(JTextField[][] tabJTextField, GrilleImpl grilleInitiale, GrilleImpl grilleFinale, JFrame frame)
	{
		this.grilleInitiale = grilleInitiale;
		this.grilleFinale = grilleFinale;
		this.tabJTextField = tabJTextField;
		this.frame = frame;
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
		frame.dispose();
	}

}
