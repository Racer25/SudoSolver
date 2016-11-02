package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;

import view.WindowImpl;


public class EcouteurEnregistrerOption implements ActionListener{

	//Variables
	private JFrame frame;
	private WindowImpl windowImpl;
	private ButtonGroup groupeAlgorithme;
	private ButtonGroup groupeVisuel;
	
	public EcouteurEnregistrerOption(WindowImpl windowImpl, JFrame frame, 
			ButtonGroup groupeAlgorithme, ButtonGroup groupeVisuel)
	{
		this.groupeAlgorithme=groupeAlgorithme;
		this.groupeVisuel=groupeVisuel;
		
		Enumeration<AbstractButton> enumBoutonsAlgo=this.groupeAlgorithme.getElements();
		Enumeration<AbstractButton> enumBoutonsVisuel=this.groupeVisuel.getElements();
		if(enumBoutonsAlgo.nextElement().isSelected())
		{
			windowImpl.setAlgorithme(0);
		}
		else if(enumBoutonsAlgo.nextElement().isSelected())
		{
			windowImpl.setAlgorithme(1);
		}
		else if(enumBoutonsAlgo.nextElement().isSelected())
		{
			windowImpl.setAlgorithme(2);
		}
		if(enumBoutonsVisuel.nextElement().isSelected())
		{
			windowImpl.setVisuel(0);
		}
		else if(enumBoutonsVisuel.nextElement().isSelected())
		{
			windowImpl.setVisuel(1);
		}
		this.frame = frame;
		this.windowImpl = windowImpl;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Enumeration<AbstractButton> enumBoutonsAlgo=this.groupeAlgorithme.getElements();
		Enumeration<AbstractButton> enumBoutonsVisuel=this.groupeVisuel.getElements();
		if(enumBoutonsAlgo.nextElement().isSelected())
		{
			windowImpl.setAlgorithme(0);
		}
		else if(enumBoutonsAlgo.nextElement().isSelected())
		{
			windowImpl.setAlgorithme(1);
		}
		else if(enumBoutonsAlgo.nextElement().isSelected())
		{
			windowImpl.setAlgorithme(2);
		}
		if(enumBoutonsVisuel.nextElement().isSelected())
		{
			windowImpl.setVisuel(0);
		}
		else if(enumBoutonsVisuel.nextElement().isSelected())
		{
			windowImpl.setVisuel(1);
		}
		System.out.println("Algo: "+windowImpl.getAlgorithme());
		System.out.println("Visuel: "+windowImpl.getVisuel());
		frame.dispose();
	}

}
