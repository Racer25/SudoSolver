package view;

//Imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Chronometre extends JPanel
{
	private static final long serialVersionUID = 2649187737448274951L;
	
	//Variables
	private static int heure=0,minute=0,seconde=0;
	private Timer timer1;
	private JLabel label1;
	
	public Chronometre()
	{		
		/* Le timer */
		int delais=1000;
		ActionListener tache_timer;

		/* creation des composants */
		//label1 = new JLabel(heure+":"+minute+":"+seconde+":"+milliseconde); /* declarer final car une classe interne va acceder a ce composant */
		label1 = new JLabel("00:00:00"); /* declarer final car une classe interne va acceder a ce composant */
		this.setSize(new Dimension(20,20));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		this.setBackground(Color.DARK_GRAY);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT,new File("./fonts/DS-DIGI.TTF"));
			ge.registerFont(font);
			font = font.deriveFont(Font.TRUETYPE_FONT,50);
			label1.setFont(font);
		} 
		catch (FontFormatException | IOException e2) 
		{
			e2.printStackTrace();
		}
	
		@SuppressWarnings("unused")
		JPanel Panel1 = new JPanel();

		/* Action realise par le timer */
		tache_timer= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				seconde++;
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
				String h;
				if(heure<10)
				{
					h = "0"+heure;
				}
				else
				{
					h = ""+heure;
				}
				String m;
				if(minute<10)
				{
					m = "0"+minute;
				}
				else
				{
					m = ""+minute;
				}
				String s;
				if(seconde<10)
				{
					s = "0"+seconde;
				}
				else
				{
					s = ""+seconde;
				}
				label1.setText(h+":"+m+":"+s);/* rafraichir le label */
			}
		};
		/* instanciation du timer */
		timer1= new Timer(delais,tache_timer);

		/* Ajout des composants aux conteneurs avec formatage */
		label1.setForeground(Color.decode("#E6E6FA"));
		this.add(label1,"Center");
		
	}
	
	public void tare()
	{
		heure=0;
		minute=0;
		seconde=0;
		label1.setText("00:00:00");
	}
	
	public void lancer()
	{
		timer1.start();
	}
	
	public void arreter()
	{
		timer1.stop();
		System.out.println("Temps final : "+(heure*3600+minute*60+seconde)+" secondes");
	}
	
	
	
}