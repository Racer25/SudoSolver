package view;

import java.awt.BorderLayout;
//Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import model.Chronometre;
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
	
	//Le panel principal
	private JPanel panelPrincipal;
	private GridLayout layoutPanelPrincipal;
	
	//Le panel de la grille
	private JPanel panelGrille;
	private GridBagLayout layoutPanelGrille;
	
	//Le panel des boutons
	private JPanel panelBoutons;
	private GridBagLayout layoutPanelBoutons;
	
	//Chronometre
	private Chronometre chronometre;
	private GridBagConstraints contraintesChronometre;
	
	private GrilleViewImpl vueGrille;
	private GridLayout layoutGrille;
	
	private GrilleImpl grilleInitiale;
	private GrilleController grilleController;
	
	private GridBagConstraints contraintesGrilles;
	
	private GrilleImpl grilleFinale;
	
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
		this.setMinimumSize(new Dimension(800,520));
		this.setTitle("SUDOKU");
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ContentPane
		this.getContentPane().setBackground(Color.decode("#E6E6FA"));
				
		//Le panel principal
		panelPrincipal = new JPanel();
		layoutPanelPrincipal = new GridLayout(1,1);
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
		
		//Le panel de la grille initiale
		panelGrille = new JPanel();
		layoutPanelGrille = new GridBagLayout();
		panelGrille.setLayout(layoutPanelGrille);
		panelGrille.setBackground(Color.decode("#E6E6FA"));
		panelPrincipal.add(panelGrille);
		
		//La grille Initiale
		grilleInitiale = new GrilleImpl();
		vueGrille = new GrilleViewImpl(grilleFinale);
		grilleController = new GrilleController(grilleFinale, vueGrille);
		
		contraintesGrilles = new GridBagConstraints();
		contraintesGrilles.gridx=0;
		contraintesGrilles.gridy=0;
		contraintesGrilles.insets = new Insets(10,10,20,20);
		panelGrille.add(vueGrille, contraintesGrilles);
		
		//Le chronometre 
		chronometre = new Chronometre();
		contraintesChronometre = new GridBagConstraints();
		contraintesChronometre.gridx=0;
		contraintesChronometre.gridy=1;
		contraintesChronometre.insets = new Insets(10,10,20,20);
		panelGrille.add(chronometre, contraintesChronometre);
		
		//Le panel des boutons
		panelBoutons = new JPanel();
		layoutPanelBoutons = new GridBagLayout();
		panelBoutons.setLayout(layoutPanelBoutons);
		panelBoutons.setBackground(Color.decode("#E6E6FA"));
		//panelGauche.add(panelBoutons);
		this.getContentPane().add(panelBoutons, BorderLayout.WEST);
		
		//Le bouton "start/pause"
		boutonStartPause = new JButton();
		boutonStartPause.setPreferredSize(new Dimension(80,80));
		boutonStartPause.setBackground(Color.WHITE);
		boutonStartPause.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonStartPause = new ImageIcon("./images/start.png" );
		boutonStartPause.setIcon(imageBoutonStartPause);
		GridBagConstraints contraintesStartPause = new GridBagConstraints();
		contraintesStartPause.gridx = 0;
		contraintesStartPause.gridy = 0;
		contraintesStartPause.insets = new Insets(10,10,20,20);
		panelBoutons.add(boutonStartPause,contraintesStartPause);
		ecouteurBoutonStartPause.setGrille(grilleFinale);
		boutonStartPause.addActionListener(ecouteurBoutonStartPause);
		
		//Le bouton "reset"
		boutonReset = new JButton();
		boutonReset.setPreferredSize(new Dimension(80,80));
		boutonReset.setBackground(Color.WHITE);
		boutonReset.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonReset = new ImageIcon("./images/reset.png" );
		boutonReset.setIcon(imageBoutonReset);
		GridBagConstraints contraintesReset = new GridBagConstraints();
		contraintesReset.gridx = 0;
		contraintesReset.gridy = 1;
		contraintesReset.insets = new Insets(10,10,20,20);
		panelBoutons.add(boutonReset,contraintesReset);
		boutonReset.addActionListener(ecouteurBoutonReset);
		
		//Le bouton "importer une nouvelle grille"
		boutonImporterUneNouvelleGrille = new JButton();
		boutonImporterUneNouvelleGrille.setPreferredSize(new Dimension(80,80));
		boutonImporterUneNouvelleGrille.setBackground(Color.WHITE);
		boutonImporterUneNouvelleGrille.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonImporterUneNouvelleGrille = new ImageIcon("./images/importerunenouvellegrille.png" );
		boutonImporterUneNouvelleGrille.setIcon(imageBoutonImporterUneNouvelleGrille);
		GridBagConstraints contraintesImporterUneNouvelleGrille = new GridBagConstraints();
		contraintesImporterUneNouvelleGrille.gridx = 0;
		contraintesImporterUneNouvelleGrille.gridy = 2;
		contraintesImporterUneNouvelleGrille.insets = new Insets(10,10,20,20);
		panelBoutons.add(boutonImporterUneNouvelleGrille,contraintesImporterUneNouvelleGrille);
		ecouteurBoutonImporterUneNouvelleGrille = new EcouteurBoutonImporterUneNouvelleGrille(vueGrille,grilleInitiale);
		boutonImporterUneNouvelleGrille.addActionListener(ecouteurBoutonImporterUneNouvelleGrille);
			
		//Le bouton "entrer manuellement une grille"
		boutonEntrerManuellementUneGrille = new JButton();
		boutonEntrerManuellementUneGrille.setPreferredSize(new Dimension(80,80));
		boutonEntrerManuellementUneGrille.setBackground(Color.WHITE);
		boutonEntrerManuellementUneGrille.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonEntrerManuellementUneGrille = new ImageIcon("./images/entrerunegrillemanuellement.png" );
		boutonEntrerManuellementUneGrille.setIcon(imageBoutonEntrerManuellementUneGrille);
		GridBagConstraints contraintesEntrerManuellementUneGrille = new GridBagConstraints();
		contraintesEntrerManuellementUneGrille.gridx = 0;
		contraintesEntrerManuellementUneGrille.gridy = 3;
		contraintesEntrerManuellementUneGrille.insets = new Insets(10,10,20,20);
		panelBoutons.add(boutonEntrerManuellementUneGrille,contraintesEntrerManuellementUneGrille);
		ecouteurBoutonEntrerManuellementUneGrille = new EcouteurBoutonEntrerManuellementUneGrille(vueGrille,grilleInitiale);
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
