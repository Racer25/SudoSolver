package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.GrilleImpl;

public class EcouteurEnregistrerOption implements ActionListener{

	//Variables
	private int algorithme;
	private int visuel;
	private JFrame frame;
	
	public EcouteurEnregistrerOption(JFrame frame, int algorithme,int visuel){
		this.algorithme = algorithme;
		this.visuel = visuel;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		frame.dispose();
	}

}