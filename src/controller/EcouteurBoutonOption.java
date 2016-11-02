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
	
	public EcouteurBoutonOption(WindowImpl frame){
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Option option = new Option(frame);
	}

}
