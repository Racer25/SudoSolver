package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GrilleImpl;
import model.PDFGenerator;

public class EcouteurExporterPDF implements ActionListener{

	//Variables 
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	
	public EcouteurExporterPDF(GrilleImpl grilleInitiale, GrilleImpl grilleFinale){
		this.grilleInitiale = grilleInitiale;
		this.grilleFinale = grilleFinale;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		PDFGenerator p = new PDFGenerator(grilleInitiale,grilleFinale);
		p.createDocument();
	}

}
