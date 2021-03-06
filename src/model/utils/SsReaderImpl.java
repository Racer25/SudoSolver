package model.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import model.CaseImpl;
import model.GrilleImpl;

public class SsReaderImpl 
{	
	//Variables
	private CaseImpl[][] caseImpl;
	private GrilleImpl grilleImpl;
	
	//Lecteur
	//La fonction "lireSs" change les valeurs de la variable "grille" en fonction de celles d'un fichier .ss
	public GrilleImpl lireSs(String file)
	{
		int[][] grille = new int[9][9]; 
		
		try 
		{
			System.out.println(file);
			InputStream inputStream = new FileInputStream(file); 
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream); 
			BufferedReader bufferReader = new BufferedReader(inputStreamReader); 
			
			boolean fichierValide=true;
			String ligne;
			int i = 0;
			int j = 0;
			while((ligne = bufferReader.readLine()) != null && fichierValide)
			{
				if(ligne.length()!=11)
				{
					fichierValide=false;
					break;
				}
				for(int index = 0 ; index < 11 ; index++)
				{
					if(ligne.charAt(index) == '.')
					{
						grille[i][j] = 0;
						j++;
						if(j == 9)
						{
							j = 0;
							i++;
						}
					}
					else if("123456789".contains(String.valueOf(ligne.charAt(index))))
					{
						grille[i][j] = Character.getNumericValue(ligne.charAt(index));
						j++;
						if(j == 9)
						{
							j = 0;
							i++;
						}
					}
					else if(ligne.charAt(index)!='!' && ligne.charAt(index)!='-')
					{
						fichierValide=false;
					}
				}
			}
			
			if(fichierValide)
			{
				caseImpl = new CaseImpl[9][9];
				
				for(int x = 0 ; x < 9 ; x++)
				{
					for(int y = 0 ; y < 9 ; y++)
					{
						caseImpl[x][y] = new CaseImpl(grille[x][y],x,y);
					}
				}
				
				grilleImpl = new GrilleImpl(caseImpl);
			}
			else
			{
				//Sortie console WindowImpl
				System.out.println("Grille invalide");
				caseImpl = new CaseImpl[9][9];
				
				for(int x = 0 ; x < 9 ; x++)
				{
					for(int y = 0 ; y < 9 ; y++)
					{
						caseImpl[x][y] = new CaseImpl(0,x,y);
					}
				}
				
				grilleImpl = new GrilleImpl(caseImpl);
			}
			
			
			bufferReader.close();
		} 
			
		catch (Exception e) 
		{ 
			//Sortie console WindowImpl
			System.out.println(e.toString()); 
		}
		
		return grilleImpl;
	}	
	
}
