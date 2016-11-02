package controller;

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.utils.PDFGenerator;

public class EcouteurExporterPDF implements ActionListener
{
	//Variables 
	private PDFGenerator p;
	
	public EcouteurExporterPDF(PDFGenerator p)
	{
		this.p = p;
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		p.createDocument();
	}

}
