package model.utils;

//Imports
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import model.GrilleImpl;

public class PDFGenerator 
{
	//Variables
	private GrilleImpl grilleInitiale;
	private GrilleImpl grilleFinale;
	private String[][] grille1;
	private String[][] grille2;
	private static int numero = 0;
	private JFrame frame;
	
	public PDFGenerator(JFrame frame, GrilleImpl grilleInitiale, GrilleImpl grilleFinale)
	{
		this.grilleFinale=grilleFinale;
		this.grilleInitiale=grilleInitiale;
		this.frame = frame;
		
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public void createDocument()
	{
		final PDPage singlePage = new PDPage();
		final PDFont font = PDType1Font.COURIER;
		final int fontSize = 12;
		Calendar calendar = Calendar.getInstance();
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "PDF", "pdf");
		fc.setFileFilter(filter);
		fc.setSelectedFile(new File("exportPDF_"+numero+".pdf"));
		 int val_retour = fc.showSaveDialog(frame);
		 System.out.println(fc.getFileFilter().getDescription()=="PDF");
		 if(fc.getSelectedFile().getName().endsWith(".pdf")==false && fc.getFileFilter().getDescription()=="PDF")
		 {
			 fc.setSelectedFile(new File(fc.getSelectedFile().getPath()+".pdf"));
		 }
         if (val_retour == JFileChooser.APPROVE_OPTION) 
         {
        	 if(fc.getSelectedFile().getName().endsWith(".pdf")==true)
        	 {
	            File fichier = fc.getSelectedFile();
	            //afficher le chemin absolu du fichier
	            System.out.println("Chemin absolu : "+fichier.getAbsolutePath()+"\n");
	            
	            try (final PDDocument document = new PDDocument())
	    		{
	    			document.addPage(singlePage);
	    		    final PDPageContentStream contentStream = new PDPageContentStream(document, singlePage);
	    		    
	    		    contentStream.beginText();
	    		    contentStream.setFont(font, fontSize);
	    		    contentStream.newLineAtOffset(150, 700);
	    		    contentStream.showText("GRILLE INITIALE :");
	    		    contentStream.endText();
	    		    
	    		    grille1 = new String[9][9];
	    		    for(int i = 0 ; i < 9 ; i++)
	    		    {
	    		    	for(int j = 0 ; j < 9 ; j++)
	    		    	{
	    		    		System.out.println(grilleInitiale.getCase(i, j).getValue());
	    		    		if(grilleInitiale.getCase(i, j).getValue()!=0)
	    		    		{
	    		    			grille1[i][j]=Integer.toString(grilleInitiale.getCase(i, j).getValue());
	    		    		}
	    		    		else
	    		    		{
	    		    			grille1[i][j]="";
	    		    		}
	    			    }
	    		    }
	    		    drawTable(singlePage, contentStream, 670, 210, grille1);
	    		    
	    		    contentStream.beginText();
	    		    contentStream.setFont(font, fontSize);
	    		    contentStream.newLineAtOffset(150, 450);
	    		    contentStream.showText("GRILLE FINALE :");
	    		    contentStream.endText();
	    		    
	    		    grille2 = new String[9][9];
	    		    for(int i = 0 ; i < 9 ; i++)
	    		    {
	    		    	for(int j = 0 ; j < 9 ; j++)
	    		    	{
	    		    		if(grilleFinale.getCase(i, j).getValue()!=0)
	    		    		{
	    		    			grille2[i][j]=Integer.toString(grilleFinale.getCase(i, j).getValue());
	    		    		}
	    		    		else{
	    		    			grille2[i][j]="";
	    		    		}
	    			    }
	    		    }
	    		    drawTable(singlePage, contentStream, 420, 210, grille2);
	    		    
	    		    contentStream.setLineWidth(1);
	    		    contentStream.setLineDashPattern (new float[]{3}, 0);
	    			contentStream.drawLine(50, 170, 550, 170);
	    			
	    		    contentStream.beginText();
	    		    contentStream.setFont(font, fontSize);
	    		    contentStream.newLineAtOffset(150, 150);
	    		    contentStream.showText("Exportation PDF faite le "+calendar.getTime()+".");
	    		    contentStream.endText();
	    		    
	    		    contentStream.close();
	    		    numero++;
	    		    document.save(fichier.getAbsolutePath());
	    		}
	            catch (IOException e) 
	            {
	    		   	e.printStackTrace();
	    		}
        	 }
        	 else
        	 {
        		JOptionPane jOptionPane =  new JOptionPane();
 				jOptionPane.showMessageDialog(frame,"Vous devez enregistrer un fichier PDF",null, JOptionPane.WARNING_MESSAGE);
 				createDocument();
        	 }
            
         } 
         else 
         {
              System.out.println("L'enregistrement est annule\n");
         }
	}
	
	@SuppressWarnings("deprecation")
	public static void drawTable(PDPage page, PDPageContentStream contentStream, 
            float y, float margin, 
            String[][] content) throws IOException 
	{
		final float rowHeight = 20f;
		final float tableWidth = page.getMediaBox().getWidth() - margin - margin;
		final float tableHeight = rowHeight * 9;
		final float colWidth = tableWidth/9;
		final float cellMargin=5f;
		
		float nexty = y ;
		for (int i = 0; i < 10; i++)
		{
			if(i%3==0)
			{
				contentStream.setLineWidth(3);
				contentStream.drawLine(margin, nexty, margin+tableWidth, nexty);
			}
			else
			{
				contentStream.setLineWidth(1);
				contentStream.drawLine(margin, nexty, margin+tableWidth, nexty);
			}
			nexty-= rowHeight;
		}
		
		float nextx = margin;
		for (int i = 0; i < 10; i++) 
		{
			if(i%3==0)
			{
				contentStream.setLineWidth(3);
				contentStream.drawLine(nextx, y+1, nextx, y-tableHeight-1);
			}
			else
			{
				contentStream.setLineWidth(1);
				contentStream.drawLine(nextx, y, nextx, y-tableHeight);
			}
		
			nextx += colWidth;
		}
		      
		contentStream.setFont( PDType1Font.HELVETICA_BOLD , 12 );        
		
		float textx = margin+cellMargin;
		float texty = y-15;        
		for(int i = 0; i < content.length; i++)
		{
			for(int j = 0 ; j < content[i].length; j++)
			{
				String text = content[i][j];
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx,texty);
				contentStream.drawString(text);
				contentStream.endText();
				textx += colWidth;
			}
		texty-=rowHeight;
		textx = margin+cellMargin;
		}
		
	}

}
