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

import controller.EcouteurAnnuler;
import controller.EcouteurEnregistrer;
import controller.EcouteurEnregistrerOption;
import controller.EcouteurSaisie;

public class Option extends JFrame{

	private static final long serialVersionUID = -3382111681595993976L;
	//Variables
	private GridLayout layoutPanelPrincipal;
	private JPanel panelPrincipal;
	private JPanel panelBoutons;
	private GridLayout layoutPanelBoutons;
	private JButton annuler;
	private JButton enregistrer;
	private EcouteurEnregistrerOption ecouteurEnregistrerOption;
	
	//Le panel Algorithme
	private JPanel panelAlgorithme;
	private JLabel labelAlgorithme;
	private GridLayout layoutPanelAlgorithme;
	
	//Le panel Visuel
	private JPanel panelVisuel;
	private JLabel labelVisuel;
	private GridLayout layoutPanelVisuel;
	private WindowImpl frame;
	
	public Option(WindowImpl frame)
	{
		this.frame = frame;
		
		//Options de la fen�tre
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
		layoutPanelAlgorithme = new GridLayout(4,1);
		panelAlgorithme.setLayout(layoutPanelAlgorithme);
		labelAlgorithme = new JLabel("Choisir l'algorithme de r�solution :");
		panelPrincipal.add(panelAlgorithme);
		panelAlgorithme.add(labelAlgorithme);
		
		ButtonGroup groupe = new ButtonGroup();
		 
		JRadioButton forwardChecking = new JRadioButton("forward checking");
		forwardChecking.setSelected(frame.getAlgorithme() == 0);
		groupe.add(forwardChecking);
		panelAlgorithme.add(forwardChecking);
		
		JRadioButton arcConsistency = new JRadioButton("arc consistency");
		arcConsistency.setSelected(frame.getAlgorithme() == 1);
		groupe.add(arcConsistency);
		panelAlgorithme.add(arcConsistency);
		
		JRadioButton sansPropagationDeContrainte = new JRadioButton("sans propagation de contraintes");
		sansPropagationDeContrainte.setSelected(frame.getAlgorithme() == 2);
		groupe.add(sansPropagationDeContrainte);
		panelAlgorithme.add(sansPropagationDeContrainte);
				
		//Le panel Visuel
		panelVisuel = new JPanel();
		layoutPanelVisuel = new GridLayout(3,1);
		panelVisuel.setLayout(layoutPanelVisuel);
		labelVisuel = new JLabel("Afficher les �tapes de la r�solution ?");
		panelPrincipal.add(panelVisuel);
		panelVisuel.add(labelVisuel);
		
		ButtonGroup groupe2 = new ButtonGroup();
		 
		JRadioButton oui = new JRadioButton("oui");
		oui.setSelected(frame.getVisuel() == 0);
		groupe2.add(oui);
		panelVisuel.add(oui);
		
		JRadioButton non = new JRadioButton("non");
		non.setSelected(frame.getVisuel() == 1);
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
		EcouteurAnnuler ecouteurAnnuler = new EcouteurAnnuler(this);
		annuler.addActionListener(ecouteurAnnuler);
		
		//Le bouton Enregistrer
		enregistrer = new JButton("Enregistrer");
		ecouteurEnregistrerOption = new EcouteurEnregistrerOption(frame,this,groupe,groupe2);
		panelBoutons.add(enregistrer);
		enregistrer.addActionListener(ecouteurEnregistrerOption);
		
		
		this.setVisible(true);
		this.revalidate();
	}	
	
}
