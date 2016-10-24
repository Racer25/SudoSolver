package model;

import java.lang.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Chronometre extends JPanel
{
	private static int heure=0,minute=0,seconde=0,milliseconde=0;
	
	public Chronometre()
	{
		/* Le timer */
		int delais=1;
		ActionListener tache_timer;

		/* création des composants */
		JLabel Label1 = new JLabel(heure+":"+minute+":"+seconde+":"+milliseconde); /* déclarer final car une classe interne va acceder à ce composant */
		this.setSize(new Dimension(20,20));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		this.setBackground(Color.DARK_GRAY);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT,new File("./fonts/DS-DIGI.TTF"));
			ge.registerFont(font);
			font = font.deriveFont(Font.TRUETYPE_FONT,50);
			Label1.setFont(font);
		} catch (FontFormatException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JButton debut = new JButton("Start");
		JButton fin = new JButton("Remise à zéro");
		JPanel Panel1 = new JPanel();

		/* Action réalisé par le timer */
		tache_timer= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				milliseconde++;
				if(milliseconde==10000){
					milliseconde++;
					seconde++;
				}
				if(seconde==60)
				{
					seconde=0;
					minute++;
				}
				if(minute==60)
				{
					minute=0;
					heure++;
				}
				Label1.setText(heure+":"+minute+":"+seconde+":"+milliseconde);/* rafraichir le label */
			}
		};
		/* instanciation du timer */
		Timer timer1= new Timer(delais,tache_timer);

		/* Ajout des composants aux conteneurs avec formatage */
		Label1.setForeground(Color.decode("#E6E6FA"));
		this.add(Label1,"Center");

		/* Action provoqué par l'utilisateur */
		/* Lors du clic sur le bouton debut */
		debut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String texte;
				texte=debut.getText();
				if(texte.compareTo("Start")==0)
				{
					debut.setText("Stop ");
					timer1.start();
				}
				else if(texte.compareTo("Stop ")==0)
				{
					debut.setText("Start");
					timer1.stop();
				}
			}
		});
		/* Lors du clic sur le bouton fin */
		fin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String texte;
				texte=debut.getText();
				if(texte.compareTo("Start")==0)
				{
					heure=0;
					minute=0;
					seconde=0;
					milliseconde=0;
					debut.setText("Start");
					Label1.setText(heure+":"+minute+":"+seconde+":"+milliseconde);
				}
			}
		});
	}
}