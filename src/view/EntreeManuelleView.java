package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EntreeManuelleView extends JFrame{

	//Variables 
	private JPanel panelPrincipal;
	private BorderLayout layoutPanelPrincipal;
	
	private JPanel panelGrille;
	private GridLayout layoutPanelGrille;
	
	private JPanel panelSousGrille;
	
	public EntreeManuelleView(){
		
	//Options de la fen�tre
	this.setTitle("ENTRER MANUELLEMENT UNE GRILLE");
	
	//ContentPane
	this.getContentPane().setBackground(Color.decode("#FFEBCD"));
			
	//Le panel principal
	panelPrincipal = new JPanel();
	layoutPanelPrincipal = new BorderLayout();
	panelPrincipal.setLayout(layoutPanelPrincipal);
	panelPrincipal.setBackground(Color.WHITE);
	this.getContentPane().add(panelPrincipal);
	
	//Le panel de la grille
	panelGrille = new JPanel();
	layoutPanelGrille = new GridLayout(3,3);
	panelGrille.setLayout(layoutPanelPrincipal);
	panelGrille.setBackground(Color.WHITE);
	panelGrille.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
	panelPrincipal.add(panelGrille, BorderLayout.CENTER);
	
	//Le panel de la grille
	panelSousGrille = new JPanel();
	layoutPanelGrille = new GridLayout(3,3);
	panelSousGrille.setLayout(layoutPanelPrincipal);
	panelSousGrille.setBackground(Color.WHITE);
	panelSousGrille.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
	panelGrille.add(panelSousGrille);
	
	this.setVisible(true);
	this.revalidate();
	
	}
}
