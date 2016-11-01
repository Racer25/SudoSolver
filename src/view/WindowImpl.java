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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;

import controller.EcouteurExporterPDF;
import controller.EcouteurParcourir;
import controller.EcouteurBoutonEntrerManuellementUneGrille;
import controller.EcouteurBoutonImporterUneGrilleAleatoire;
import controller.EcouteurBoutonImporterUneNouvelleGrille;
import controller.EcouteurBoutonOption;
import controller.EcouteurBoutonReset;
import controller.EcouteurBoutonStartPause;
import controller.EcouteurQuitter;
import controller.GrilleController;
import model.GrilleImpl;
import model.utils.PDFGenerator;

public class WindowImpl extends JFrame
{
	private static final long serialVersionUID = 2061491136713215502L;

	//Les variables 
	
	//Algo
	private int algorithme;
	private int visuel;
	
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
	
	//Le panel des boutons2
	private JPanel panelBoutons2;
	private GridBagLayout layoutPanelBoutons2;
	
	//Chronometre
	private Chronometre chronometre;
	private GridBagConstraints contraintesChronometre;
	
	//Les grilles
	private GrilleController grilleController;
	private GridBagConstraints contraintesGrilles;
	private GrilleViewImpl vueGrille;
	private GridLayout layoutGrille;
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	
	//Le bouton Start/Pause
	private JButton boutonStartPause;
	private ImageIcon imageBoutonStartPause;
	private GridBagConstraints contraintesStartPause;
	private EcouteurBoutonStartPause ecouteurBoutonStartPause;
	
	//Le bouton Reset
	private JButton boutonReset;
	private ImageIcon imageBoutonReset;
	private GridBagConstraints contraintesReset;
	private EcouteurBoutonReset ecouteurBoutonReset;
	
	//Le bouton Importer une nouvelle grille
	private JButton boutonImporterUneNouvelleGrille;
	private ImageIcon imageBoutonImporterUneNouvelleGrille;
	private GridBagConstraints contraintesImporterUneNouvelleGrille;
	private EcouteurParcourir ecouteurBoutonImporterUneNouvelleGrille;
	
	//Le bouton Importer une grille aleatoire
	private JButton boutonImporterUneGrilleAleatoire;
	private ImageIcon imageBoutonImporterUneGrilleAleatoire;
	private GridBagConstraints contraintesImporterUneGrilleAleatoire;
	private EcouteurBoutonImporterUneGrilleAleatoire ecouteurBoutonImporterUneGrilleAleatoire;
		
	//Le bouton Entrer manuellement une grille
	private JButton boutonEntrerManuellementUneGrille;
	private ImageIcon imageBoutonEntrerManuellementUneGrille;
	private GridBagConstraints contraintesEntrerManuellementUneGrille;
	private EcouteurBoutonEntrerManuellementUneGrille ecouteurBoutonEntrerManuellementUneGrille;
	
	//Le bouton ExportPDF
	private JButton boutonExportPDF;
	private ImageIcon imageBoutonExportPDF;
	private GridBagConstraints contraintesExportPDF;
	private EcouteurExporterPDF ecouteurExportPDF;
	
	//Le bouton Reset
	private JButton boutonOption;
	private ImageIcon imageBoutonOption;
	private GridBagConstraints contraintesOption;
	private EcouteurBoutonOption ecouteurBoutonOption;
		
	//Le generateur de PDF
	private PDFGenerator p;
	
	//Console
	private JTextArea jTextArea;
	private Console console;
	private PrintStream printStream;
	
	//Le panel Console
	private JScrollPane panelConsole;
	private ScrollPaneLayout layoutPanelConsole;
	
	public WindowImpl(GrilleImpl grilleFinale, int algorithme, int visuel)
	{
		this.algorithme = algorithme;
		this.visuel = visuel;
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
		
		//La barre de menu
		barreMenu = new BarreMenu();
		this.getContentPane().add(barreMenu,BorderLayout.NORTH);
		ecouteurQuitter = new EcouteurQuitter();
		barreMenu.getQuitter().addActionListener(ecouteurQuitter);
		p = new PDFGenerator(this, grilleInitiale,grilleFinale);
		ecouteurExporterPDF = new EcouteurExporterPDF(this,grilleInitiale,grilleFinale,p);
		(barreMenu.getExporterPDF()).addActionListener(ecouteurExporterPDF);
		
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
		contraintesStartPause = new GridBagConstraints();
		contraintesStartPause.gridx = 0;
		contraintesStartPause.gridy = 0;
		contraintesStartPause.insets = new Insets(10,10,20,20);
		panelBoutons.add(boutonStartPause,contraintesStartPause);
		ecouteurBoutonStartPause = new EcouteurBoutonStartPause(chronometre);
		ecouteurBoutonStartPause.setGrille(grilleFinale);
		boutonStartPause.addActionListener(ecouteurBoutonStartPause);
		
		//Le bouton "reset"
		boutonReset = new JButton();
		boutonReset.setPreferredSize(new Dimension(80,80));
		boutonReset.setBackground(Color.WHITE);
		boutonReset.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonReset = new ImageIcon("./images/reset.png" );
		boutonReset.setIcon(imageBoutonReset);
		contraintesReset = new GridBagConstraints();
		contraintesReset.gridx = 0;
		contraintesReset.gridy = 1;
		contraintesReset.insets = new Insets(10,10,20,20);
		panelBoutons.add(boutonReset,contraintesReset);
		ecouteurBoutonReset = new EcouteurBoutonReset(chronometre,grilleInitiale,grilleFinale);
		boutonReset.addActionListener(ecouteurBoutonReset);
		
		//Le bouton "ExportPDF"
		boutonExportPDF = new JButton();
		boutonExportPDF.setPreferredSize(new Dimension(80,80));
		boutonExportPDF.setBackground(Color.WHITE);
		boutonExportPDF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonExportPDF = new ImageIcon("./images/exportpdf.png" );
		boutonExportPDF.setIcon(imageBoutonExportPDF);
		contraintesExportPDF = new GridBagConstraints();
		contraintesExportPDF.gridx = 0;
		contraintesExportPDF.gridy = 3;
		contraintesExportPDF.insets = new Insets(10,10,20,20);
		panelBoutons.add(boutonExportPDF,contraintesExportPDF);
		ecouteurExportPDF = new EcouteurExporterPDF(this,grilleInitiale,grilleFinale,p);
		boutonExportPDF.addActionListener(ecouteurExportPDF);
				
		//Le bouton "option"
		boutonOption = new JButton();
		boutonOption.setPreferredSize(new Dimension(80,80));
		boutonOption.setBackground(Color.WHITE);
		boutonOption.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonOption = new ImageIcon("./images/option.png" );
		boutonOption.setIcon(imageBoutonOption);
		contraintesOption = new GridBagConstraints();
		contraintesOption.gridx = 0;
		contraintesOption.gridy = 4;
		contraintesOption.insets = new Insets(10,10,20,20);
		panelBoutons.add(boutonOption,contraintesOption);
		ecouteurBoutonOption = new EcouteurBoutonOption(this,algorithme,visuel);
		boutonOption.addActionListener(ecouteurBoutonOption);
		//(barreMenu.getOption()).addActionListener(ecouteurBoutonOption);
	
		//Le panel des boutons2
		panelBoutons2 = new JPanel();
		layoutPanelBoutons2 = new GridBagLayout();
		panelBoutons2.setLayout(layoutPanelBoutons2);
		panelBoutons2.setBackground(Color.decode("#E6E6FA"));
		//panelGauche.add(panelBoutons);
		this.getContentPane().add(panelBoutons2, BorderLayout.EAST);
		
		//Le bouton "Importer une nouvelle grille"
		boutonImporterUneNouvelleGrille = new JButton();
		boutonImporterUneNouvelleGrille.setPreferredSize(new Dimension(80,80));
		boutonImporterUneNouvelleGrille.setBackground(Color.WHITE);
		boutonImporterUneNouvelleGrille.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonImporterUneNouvelleGrille = new ImageIcon("./images/importerunenouvellegrille.png" );
		boutonImporterUneNouvelleGrille.setIcon(imageBoutonImporterUneNouvelleGrille);
		contraintesImporterUneNouvelleGrille = new GridBagConstraints();
		contraintesImporterUneNouvelleGrille.gridx = 0;
		contraintesImporterUneNouvelleGrille.gridy = 1;
		contraintesImporterUneNouvelleGrille.insets = new Insets(10,10,20,20);
		panelBoutons2.add(boutonImporterUneNouvelleGrille,contraintesImporterUneNouvelleGrille);
		ecouteurBoutonImporterUneNouvelleGrille = new EcouteurParcourir(this,vueGrille,grilleInitiale);
		
		boutonImporterUneNouvelleGrille.addActionListener(ecouteurBoutonImporterUneNouvelleGrille);
		
		//Le bouton "importer une grille aleatoire"
		boutonImporterUneGrilleAleatoire = new JButton();
		boutonImporterUneGrilleAleatoire.setPreferredSize(new Dimension(80,80));
		boutonImporterUneGrilleAleatoire.setBackground(Color.WHITE);
		boutonImporterUneGrilleAleatoire.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonImporterUneGrilleAleatoire = new ImageIcon("./images/importerunegrillealeatoire.png" );
		boutonImporterUneGrilleAleatoire.setIcon(imageBoutonImporterUneGrilleAleatoire);
		contraintesImporterUneGrilleAleatoire = new GridBagConstraints();
		contraintesImporterUneGrilleAleatoire.gridx = 0;
		contraintesImporterUneGrilleAleatoire.gridy = 2;
		contraintesImporterUneGrilleAleatoire.insets = new Insets(10,10,20,20);
		panelBoutons2.add(boutonImporterUneGrilleAleatoire,contraintesImporterUneGrilleAleatoire);
		ecouteurBoutonImporterUneGrilleAleatoire = new EcouteurBoutonImporterUneGrilleAleatoire(vueGrille,grilleInitiale);
		boutonImporterUneGrilleAleatoire.addActionListener(ecouteurBoutonImporterUneGrilleAleatoire);

		//Le bouton "entrer manuellement une grille"
		boutonEntrerManuellementUneGrille = new JButton();
		boutonEntrerManuellementUneGrille.setPreferredSize(new Dimension(80,80));
		boutonEntrerManuellementUneGrille.setBackground(Color.WHITE);
		boutonEntrerManuellementUneGrille.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		imageBoutonEntrerManuellementUneGrille = new ImageIcon("./images/entrerunegrillemanuellement.png" );
		boutonEntrerManuellementUneGrille.setIcon(imageBoutonEntrerManuellementUneGrille);
		contraintesEntrerManuellementUneGrille = new GridBagConstraints();
		contraintesEntrerManuellementUneGrille.gridx = 0;
		contraintesEntrerManuellementUneGrille.gridy = 3;
		contraintesEntrerManuellementUneGrille.insets = new Insets(10,10,20,20);
		panelBoutons2.add(boutonEntrerManuellementUneGrille,contraintesEntrerManuellementUneGrille);
		ecouteurBoutonEntrerManuellementUneGrille = new EcouteurBoutonEntrerManuellementUneGrille(vueGrille,grilleInitiale);
		boutonEntrerManuellementUneGrille.addActionListener(ecouteurBoutonEntrerManuellementUneGrille);
		barreMenu.getAjouterUneGrilleManuellement().addActionListener(ecouteurBoutonEntrerManuellementUneGrille);
		
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
