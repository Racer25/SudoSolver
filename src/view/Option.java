package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.EcouteurEnregistrer;
import controller.EcouteurSaisie;

public class Option extends JFrame{

	//Variables
	private static int algorithme;
	private static int visuel;
	private GridLayout layoutPanelPrincipal;
	private JPanel panelPrincipal;
	private JPanel panelBoutons;
	private GridLayout layoutPanelBoutons;
	private JButton annuler;
	private JButton enregistrer;
	private EcouteurEnregistrer ecouteurEnregistrer;
	
	//Le panel Algorithme
	private JPanel panelAlgorithme;
	private JLabel labelAlgorithme;
	private GridLayout layoutPanelAlgorithme;
	
	//Le panel Visuel
	private JPanel panelVisuel;
	private JLabel labelVisuel;
	private GridLayout layoutPanelVisuel;
	
	public Option(int algorithme, int visuel){
		this.algorithme = algorithme;
		this.visuel = visuel;
		
		//Options de la fenï¿½tre
		this.setTitle("OPTIONS");
		this.setSize(new Dimension(500,200));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		//ContentPane
		this.getContentPane().setBackground(Color.decode("#E6E6FA"));
		panelPrincipal = new JPanel();
		layoutPanelPrincipal = new GridLayout(2,1);
		panelPrincipal.setLayout(layoutPanelPrincipal);
		this.getContentPane().add(panelPrincipal);
		
		//Le panel Algorithme
		panelAlgorithme = new JPanel();
		layoutPanelAlgorithme = new GridLayout(3,1);
		panelAlgorithme.setLayout(layoutPanelAlgorithme);
		labelAlgorithme = new JLabel("Choisir l'algorithme de résolution :");
		panelPrincipal.add(panelAlgorithme);
		panelAlgorithme.add(labelAlgorithme);
		
		ButtonGroup groupe = new ButtonGroup();
		 
		JRadioButton forwardChecking = new JRadioButton("forward checking");
		forwardChecking.setSelected(algorithme == 0);
		groupe.add(forwardChecking);
		panelAlgorithme.add(forwardChecking);
		
		JRadioButton arcConsistency = new JRadioButton("arc consistency");
		arcConsistency.setSelected(algorithme == 1);
		groupe.add(arcConsistency);
		panelAlgorithme.add(arcConsistency);
				
		//Le panel Visuel
		panelVisuel = new JPanel();
		layoutPanelVisuel = new GridLayout(3,1);
		panelVisuel.setLayout(layoutPanelVisuel);
		labelVisuel = new JLabel("Afficher les étapes de la résolution ?");
		panelPrincipal.add(panelVisuel);
		panelVisuel.add(labelVisuel);
		
		ButtonGroup groupe2 = new ButtonGroup();
		 
		JRadioButton oui = new JRadioButton("oui");
		oui.setSelected(visuel == 0);
		groupe2.add(oui);
		panelVisuel.add(oui);
		
		JRadioButton non = new JRadioButton("non");
		non.setSelected(visuel == 1);
		groupe2.add(non);
		panelVisuel.add(non);
		
		//Le panel des boutons
		panelBoutons = new JPanel();
		layoutPanelBoutons = new GridLayout(1,2);
		panelBoutons.setLayout(layoutPanelBoutons);
		this.getContentPane().add(panelBoutons, BorderLayout.SOUTH);
		
		//Le bouton Annuler
		annuler = new JButton("Annuler");
		panelBoutons.add(annuler);
		
		//Le bouton Enregistrer
		enregistrer = new JButton("Enregistrer");
		panelBoutons.add(enregistrer);
		enregistrer.addActionListener(ecouteurEnregistrer);
		
		
		this.setVisible(true);
		this.revalidate();
	}	
	
}
