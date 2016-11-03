package view;

//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.EcouteurAnnuler;
import controller.EcouteurEnregistrer;
import controller.EcouteurSaisie;
import model.GrilleImpl;

public class EntreeManuelleView extends JFrame
{

	private static final long serialVersionUID = -4048563176694901418L;
	
	//Variables 
	private GrilleARemplirViewImpl panelGrille;
	private JPanel panelBoutons;
	private GridLayout layoutPanelBoutons;
	private JButton annuler;
	private JButton enregistrer;
	private EcouteurEnregistrer ecouteurEnregistrer;
	
	//Le panel Message 
	private JPanel panelMessage;
	private JLabel message;
	@SuppressWarnings("unused")
	private GridLayout layoutPanelMessage;
	
	public EntreeManuelleView(GrilleImpl grilleFinale, GrilleImpl grilleInitiale){
		
	//Options de la fenetre
	this.setTitle("ENTRER MANUELLEMENT UNE GRILLE");
	this.setSize(new Dimension(500,500));
	this.setLocationRelativeTo(null);
	
	//ContentPane
	this.getContentPane().setBackground(Color.decode("#E6E6FA"));
	
	//Le panel Messages
	panelMessage = new JPanel();
	layoutPanelMessage = new GridLayout(1,1);
	message = new JLabel();
	message.setBackground(Color.WHITE);
	message.setForeground(Color.RED);
	this.getContentPane().add(panelMessage, BorderLayout.NORTH);
	panelMessage.add(message);
			
	//Le panel principal
	panelGrille = new GrilleARemplirViewImpl();
	this.getContentPane().add(panelGrille, BorderLayout.CENTER);
	for(int i = 0 ; i < 9 ; i++){
		for(int j = 0 ; j < 9 ; j++){
			panelGrille.getTabJTextField()[i][j].addKeyListener(new EcouteurSaisie(panelGrille.getTabJTextField(),i,j,message,panelMessage));
		}
	}
	
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
	panelBoutons.add(enregistrer);
	ecouteurEnregistrer = new EcouteurEnregistrer(panelGrille.getTabJTextField(), grilleInitiale, grilleFinale,this);
	enregistrer.addActionListener(ecouteurEnregistrer);
	
	this.setVisible(true);
	this.revalidate();
	
	}
}
