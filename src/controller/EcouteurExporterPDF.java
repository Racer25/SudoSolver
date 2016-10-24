package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GrilleImpl;
import model.PDFGenerator;

public class EcouteurExporterPDF implements ActionListener{

	//Variables 
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private PDFGenerator p;
	
	public EcouteurExporterPDF(GrilleImpl grilleInitiale, GrilleImpl grilleFinale,PDFGenerator p){
		this.grilleInitiale = grilleInitiale;
		this.grilleFinale = grilleFinale;
		this.p = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		p.createDocument();
	}

}
