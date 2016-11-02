package controller;

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class EcouteurAnnuler implements ActionListener{

	//Variables
	private JFrame frame;
	
	public EcouteurAnnuler(JFrame frame) 
	{
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		frame.dispose();
	}

}
