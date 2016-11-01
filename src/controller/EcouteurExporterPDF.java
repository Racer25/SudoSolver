package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.GrilleImpl;
import model.utils.PDFGenerator;

public class EcouteurExporterPDF implements ActionListener{

	//Variables 
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private PDFGenerator p;
	private JFrame frame;
	
	public EcouteurExporterPDF(JFrame frame, GrilleImpl grilleInitiale, GrilleImpl grilleFinale,PDFGenerator p){
		this.grilleInitiale = grilleInitiale;
		this.grilleFinale = grilleFinale;
		this.p = p;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		p.createDocument();
	}

}
