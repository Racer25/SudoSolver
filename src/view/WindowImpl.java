package view;

import java.awt.BorderLayout;
//Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;

import controller.EcouteurExporterPDF;
import controller.EcouteurBoutonEntrerManuellementUneGrille;
import controller.EcouteurBoutonImporterUneNouvelleGrille;
import controller.EcouteurBoutonReset;
import controller.EcouteurBoutonStartPause;
import controller.EcouteurQuitter;
import controller.GrilleController;
import model.BarreMenu;
import model.Console;
import model.GrilleImpl;

public class WindowImpl extends JFrame
{
	private static final long serialVersionUID = 2061491136713215502L;

	//Les variables 
	
	//La barre de menu
	private BarreMenu barreMenu;
	private EcouteurQuitter ecouteurQuitter;
	private EcouteurExporterPDF ecouteurExporterPDF;
	
	private JPanel panelPrincipal;
	private GridLayout layoutPanelPrincipal;
	
	private JPanel panelGauche;
	private GridLayout layoutPanelGauche;
	
	private JPanel panelDroit;
	private GridLayout layoutPanelDroit;
	
	private JPanel panelGrilleInitiale;
	private GridBagLayout layoutPanelGrilleInitiale;
	
	private JPanel panelGrilleFinale;
	private GridBagLayout layoutPanelGrilleFinale;
	
	private JPanel panelBoutons;
	private GridLayout layoutPanelBoutons;
	
	private GrilleViewImpl vueGrilleInitiale;
	private GridLayout layoutGrilleInitiale;
	
	private GrilleImpl grilleInitiale;
	
	private GridBagConstraints contraintesGrilles;
	private GridBagConstraints contraintesLabels;
	
	private JLabel labelGrilleInitiale;
	
	private GrilleViewImpl vueGrilleFinale;
	private GridLayout layoutGrilleFinale;
	
	private GrilleImpl grilleFinale;
	
	private JLabel labelGrilleFinale;
	
	private JButton boutonStartPause;
	private ImageIcon imageBoutonStartPause;
	private EcouteurBoutonStartPause ecouteurBoutonStartPause = new EcouteurBoutonStartPause();
	
	private JButton boutonReset;
	private ImageIcon imageBoutonReset;
	private EcouteurBoutonReset ecouteurBoutonReset = new EcouteurBoutonReset();
	
	private JButton boutonImporterUneNouvelleGrille;
	private ImageIcon imageBoutonImporterUneNouvelleGrille;
	private EcouteurBoutonImporterUneNouvelleGrille ecouteurBoutonImporterUneNouvelleGrille;
	
	private JButton boutonEntrerManuellementUneGrille;
	private ImageIcon imageBoutonEntrerManuellementUneGrille;
	private EcouteurBoutonEntrerManuellementUneGrille ecouteurBoutonEntrerManuellementUneGrille;
	
	//Console
	private JTextArea jTextArea;
	private Console console;
	private PrintStream printStream;
	
	//Le panel Console
	private JScrollPane panelConsole;
	private ScrollPaneLayout layoutPanelConsole;
	
	public WindowImpl(GrilleImpl grilleFinale)
	{
		
		this.grilleFinale = grilleFinale;
		
		//Options de la fen�tre
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("SUDOKU");
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ContentPane
		this.getContentPane().setBackground(Color.decode("#E6E6FA"));
				
		//Le panel principal
		panelPrincipal = new JPanel();
		layoutPanelPrincipal = new GridLayout(1,2);
		panelPrincipal.setLayout(layoutPanelPrincipal);
		panelPrincipal.setBackground(Color.WHITE);
		this.getContentPane().add(panelPrincipal);

		//La barre de menu
		barreMenu = new BarreMenu();
		this.getContentPane().add(barreMenu,BorderLayout.NORTH);
		EcouteurQuitter ecouteurQuitter = new EcouteurQuitter();
		barreMenu.getQuitter().addActionListener(ecouteurQuitter);
		ecouteurExporterPDF = new EcouteurExporterPDF();
		(barreMenu.getExporterPDF()).addActionListener(ecouteurExporterPDF);
						
				
		//Le panel gauche
		panelGauche = new JPanel();
		layoutPanelGauche = new GridLayout(2,1);
		panelGauche.setLayout(layoutPanelGauche);
		panelPrincipal.add(panelGauche);
		
		//Le panel de la grille initiale
		panelGrilleInitiale = new JPanel();
		layoutPanelGrilleInitiale = new GridBagLayout();
		panelGrilleInitiale.setLayout(layoutPanelGrilleInitiale);
		panelGrilleInitiale.setBackground(Color.decode("#E6E6FA"));
		panelGauche.add(panelGrilleInitiale);
		
		//Le label de la grille initiale
		labelGrilleInitiale = new JLabel("Grille initiale :");
		contraintesLabels = new GridBagConstraints();
		contraintesLabels.gridx=0;
		contraintesLabels.gridy=0;
		panelGrilleInitiale.add(labelGrilleInitiale, contraintesLabels);
		
		//La grille Initiale
		grilleInitiale = new GrilleImpl();
		vueGrilleInitiale = new GrilleViewImpl(grilleFinale);
		GrilleController grilleController=new GrilleController(grilleFinale, vueGrilleInitiale);
		contraintesGrilles = new GridBagConstraints();
		contraintesGrilles.gridx=0;
		contraintesGrilles.gridy=1;
		panelGrilleInitiale.add(vueGrilleInitiale, contraintesGrilles);
		
		//Le panel de la grille finale
		panelGrilleFinale = new JPanel();
		layoutPanelGrilleFinale = new GridBagLayout();
		panelGrilleFinale.setLayout(layoutPanelGrilleFinale);
		panelGrilleFinale.setBackground(Color.decode("#E6E6FA"));
		panelGauche.add(panelGrilleFinale);
		
		//Le label de la grille finale
		labelGrilleFinale = new JLabel("Grille finale :");
		panelGrilleFinale.add(labelGrilleFinale, contraintesLabels);
		
		//La grille Finale
		vueGrilleFinale = new GrilleViewImpl(grilleFinale);
		panelGrilleFinale.add(vueGrilleFinale, contraintesGrilles);
		
		//Le panel droit
		panelDroit = new JPanel();
		layoutPanelDroit = new GridLayout(5,1);
		panelDroit.setLayout(layoutPanelDroit);
		panelDroit.setBackground(Color.decode("#E6E6FA"));
		panelPrincipal.add(panelDroit);
		
		//Panel Vide 
		JPanel panelVide = new JPanel();
		panelVide.setBackground(Color.decode("#E6E6FA"));
		panelDroit.add(panelVide);
		
		//Le panel des boutons
		panelBoutons = new JPanel();
		layoutPanelBoutons = new GridLayout(1,4);
		panelBoutons.setLayout(layoutPanelBoutons);
		panelBoutons.setBackground(Color.decode("#E6E6FA"));
		panelDroit.add(panelBoutons);
		
		//Le bouton "start/pause"
		boutonStartPause = new JButton();
		boutonStartPause.setBackground(Color.WHITE);
		boutonStartPause.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonStartPause = new ImageIcon("./images/start.png" );
		boutonStartPause.setIcon(imageBoutonStartPause);
		panelBoutons.add(boutonStartPause);
		ecouteurBoutonStartPause.setGrille(grilleFinale);
		boutonStartPause.addActionListener(ecouteurBoutonStartPause);
		
		//Le bouton "reset"
		boutonReset = new JButton();
		boutonReset.setBackground(Color.WHITE);
		boutonReset.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonReset = new ImageIcon("./images/reset.png" );
		boutonReset.setIcon(imageBoutonReset);
		panelBoutons.add(boutonReset);
		boutonReset.addActionListener(ecouteurBoutonReset);
		
		//Le bouton "importer une nouvelle grille"
		boutonImporterUneNouvelleGrille = new JButton();
		boutonImporterUneNouvelleGrille.setBackground(Color.WHITE);
		boutonImporterUneNouvelleGrille.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonImporterUneNouvelleGrille = new ImageIcon("./images/importerunenouvellegrille.png" );
		boutonImporterUneNouvelleGrille.setIcon(imageBoutonImporterUneNouvelleGrille);
		panelBoutons.add(boutonImporterUneNouvelleGrille);
		ecouteurBoutonImporterUneNouvelleGrille = new EcouteurBoutonImporterUneNouvelleGrille(vueGrilleInitiale, vueGrilleFinale);
		boutonImporterUneNouvelleGrille.addActionListener(ecouteurBoutonImporterUneNouvelleGrille);
			
		//Le bouton "entrer manuellement une grille"
		boutonEntrerManuellementUneGrille = new JButton();
		boutonEntrerManuellementUneGrille.setBackground(Color.WHITE);
		boutonEntrerManuellementUneGrille.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonEntrerManuellementUneGrille = new ImageIcon("./images/entrerunegrillemanuellement.png" );
		boutonEntrerManuellementUneGrille.setIcon(imageBoutonEntrerManuellementUneGrille);
		panelBoutons.add(boutonEntrerManuellementUneGrille);
		ecouteurBoutonEntrerManuellementUneGrille = new EcouteurBoutonEntrerManuellementUneGrille(vueGrilleInitiale, vueGrilleFinale);
		boutonEntrerManuellementUneGrille.addActionListener(ecouteurBoutonEntrerManuellementUneGrille);
		
		//Console
		jTextArea = new JTextArea(8,1);
		jTextArea.setEditable(false);
		jTextArea.setBackground(Color.WHITE);
		jTextArea.setAutoscrolls(true);
		jTextArea.setText("Console :\n");
		console = new Console(jTextArea);
		printStream = new PrintStream(console);
		System.setOut(printStream);
		
		//Panel Console 
		panelConsole = new JScrollPane(jTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		layoutPanelConsole = new ScrollPaneLayout();
		panelConsole.setLayout(layoutPanelConsole);
		panelConsole.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
		this.getContentPane().add(panelConsole,BorderLayout.SOUTH);
		
		
		this.setVisible(true);
		this.revalidate();
		
	}
}
