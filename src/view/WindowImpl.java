package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GrilleImpl;

public class WindowImpl extends JFrame
{
	private static final long serialVersionUID = 2061491136713215502L;

	//Variables
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	
	private JPanel panelPrincipal;
	private GridLayout layoutPanelPrincipal;
	
	public WindowImpl(){
		
		//Création du panel principal
		layoutPanelPrincipal = new GridLayout(1,2);
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(layoutPanelPrincipal);
		panelPrincipal.setBackground(Color.decode("#FEFEE0"));
		this.getContentPane().add(panelPrincipal,BorderLayout.CENTER);
		
		//Création du
		
	}
	
}
