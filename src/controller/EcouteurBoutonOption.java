package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.GrilleImpl;
import view.GrilleViewImpl;
import view.Option;

public class EcouteurBoutonOption implements ActionListener{

	//Variables
	private JFrame frame;
	private int algorithme;
	private int visuel;
	
	public EcouteurBoutonOption(JFrame frame, int algorithme, int visuel){
		this.frame = frame;
		this.algorithme = algorithme;
		this.visuel = visuel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Option option = new Option(algorithme, visuel);
	}

}
