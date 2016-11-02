package view;

//Imports
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
	
@SuppressWarnings("serial")
public class BarreMenu extends JMenuBar{
	
	//Variables
	private JMenuItem exporterPDF;
	private JMenuItem ajouterUneGrilleManuellement;
	private JMenuItem importerUneNouvelleGrille;
	private JMenuItem importerUneGrilleAleatoire;
	private JMenuItem option;
	private JMenuItem quitter;
	private JMenu fichier;
	private JMenu options;
	
	//Constructeur
	public BarreMenu(){
	
		//Cr�ation du menu fichier
		fichier = new JMenu("Fichier");
		this.add(fichier);
		quitter = new JMenuItem("Quitter");
		fichier.add(quitter);
		
		//Cr�ation du menu fichier
		options = new JMenu("Options");
		this.add(options);
		exporterPDF = new JMenuItem("Exporter en PDF",'E');
		exporterPDF.setMnemonic('E');
		exporterPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
		options.add(exporterPDF);
		ajouterUneGrilleManuellement = new JMenuItem("Ajouter manuellement une grille",'M');
		ajouterUneGrilleManuellement.setMnemonic('M');
		ajouterUneGrilleManuellement.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
		options.add(ajouterUneGrilleManuellement);
		importerUneNouvelleGrille = new JMenuItem("Importer une nouvelle grille",'I');
		importerUneNouvelleGrille.setMnemonic('I');
		importerUneNouvelleGrille.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
		options.add(importerUneNouvelleGrille);
		importerUneGrilleAleatoire = new JMenuItem("Importer une grille aleatoire",'A');
		importerUneGrilleAleatoire.setMnemonic('A');
		importerUneGrilleAleatoire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
		options.add(importerUneGrilleAleatoire);
		option = new JMenuItem("Options",'O');
		option.setMnemonic('O');
		option.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
		options.add(option);

		this.setVisible(true);
				
	}

	public JMenuItem getExporterPDF() {
		return exporterPDF;
	}

	public void setExporterPDF(JMenuItem exporterPDF) {
		this.exporterPDF = exporterPDF;
	}

	public JMenuItem getAjouterUneGrilleManuellement() {
		return ajouterUneGrilleManuellement;
	}

	public void setAjouterUneGrilleManuellement(JMenuItem ajouterUneGrilleManuellement) {
		this.ajouterUneGrilleManuellement = ajouterUneGrilleManuellement;
	}

	public JMenuItem getImporterUneNouvelleGrille() {
		return importerUneNouvelleGrille;
	}

	public void setImporterUneNouvelleGrille(JMenuItem importerUneNouvelleGrille) {
		this.importerUneNouvelleGrille = importerUneNouvelleGrille;
	}

	public JMenuItem getQuitter() {
		return quitter;
	}

	public void setQuitter(JMenuItem quitter) {
		this.quitter = quitter;
	}

	public JMenuItem getImporterUneGrilleAleatoire() {
		return importerUneGrilleAleatoire;
	}

	public void setImporterUneGrilleAleatoire(JMenuItem importerUneGrilleAleatoire) {
		this.importerUneGrilleAleatoire = importerUneGrilleAleatoire;
	}

	public JMenuItem getOption() {
		return option;
	}

	public void setOption(JMenuItem option) {
		this.option = option;
	}
	
	
	
}
