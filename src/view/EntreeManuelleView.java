package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.EcouteurSaisie;
import model.CaseSaisie;

public class EntreeManuelleView extends JFrame{

	//Variables 
	private JPanel panelPrincipal;
	private GridLayout layoutPanelPrincipal;
	
	private JPanel panelSousGrille;
	private GridLayout layoutPanelSousGrille;
	
	public EntreeManuelleView(){
		
	//Options de la fen�tre
	this.setTitle("ENTRER MANUELLEMENT UNE GRILLE");
	this.setSize(new Dimension(500,500));
	this.setLocationRelativeTo(null);
	
	//ContentPane
	this.getContentPane().setBackground(Color.decode("#FFEBCD"));
			
	//Le panel principal
	panelPrincipal = new JPanel();
	layoutPanelPrincipal = new GridLayout(3,3);
	panelPrincipal.setLayout(layoutPanelPrincipal);
	panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
	panelPrincipal.setBackground(Color.PINK);
	this.getContentPane().add(panelPrincipal);
	
	//Le panel des sous-grilles
	for(int n = 0 ; n < 9 ; n++){
		panelSousGrille = new JPanel();
		layoutPanelSousGrille = new GridLayout(3,3);
		panelSousGrille.setLayout(layoutPanelSousGrille);
		panelSousGrille.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		panelSousGrille.setBackground(Color.WHITE);
		panelPrincipal.add(panelSousGrille);
		
		//Ajout des JTextField
		for(int i = 0 ; i < 9 ; i++){
				JTextField jTextField = new JTextField();
				jTextField.setBackground(Color.WHITE);
				jTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
				jTextField.addKeyListener(new EcouteurSaisie(jTextField));
				panelSousGrille.add(jTextField);
		}
	}
	
	
	this.setVisible(true);
	this.revalidate();
	
	}
}
