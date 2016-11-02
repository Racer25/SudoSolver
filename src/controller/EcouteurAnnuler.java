package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.Option;

public class EcouteurAnnuler implements ActionListener{

	private JFrame frame;
	
	public EcouteurAnnuler(JFrame frame) 
	{
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		frame.dispose();
	}

}
