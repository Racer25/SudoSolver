package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.GrilleImpl;
import view.GrilleViewImpl;
import view.Option;
import view.WindowImpl;

public class EcouteurBoutonOption implements ActionListener{

	//Variables
	private WindowImpl frame;
	private int algorithme;
	private int visuel;
	
	public EcouteurBoutonOption(WindowImpl frame, int algorithme, int visuel){
		this.frame = frame;
		this.algorithme = algorithme;
		this.visuel = visuel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Option option = new Option(frame, algorithme, visuel);
	}

}
