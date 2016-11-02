package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.WindowImpl;


public class EcouteurEnregistrerOption implements ActionListener{

	//Variables
	private int algorithme;
	private int visuel;
	private JFrame frame;
	private WindowImpl windowImpl;
	
	public EcouteurEnregistrerOption(WindowImpl windowImpl, JFrame frame, int algorithme,int visuel){
		this.algorithme = algorithme;
		this.visuel = visuel;
		this.frame = frame;
		this.windowImpl = windowImpl;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		windowImpl.setAlgorithme(algorithme);
		windowImpl.setVisuel(visuel);
		frame.dispose();
	}

}
