package controller;

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EcouteurQuitter implements ActionListener 
{
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent arg0) 
	{
		//Creation d'une boite de dialogue pour confimer le choix de l'utilisateur
		JOptionPane jOptionPane =  new JOptionPane();
		int reponseQuitter = jOptionPane.showConfirmDialog(null, "Etes-vous sure de vouloir quitter ?","ATTENTION !",JOptionPane.WARNING_MESSAGE);
		if(reponseQuitter==0)
		{
			System.exit(0);	
		}
	}

}
