package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SsReaderImpl {
	
	//Lecteur
	//La fonction "lireSs" change les valeurs de la variable "grille" en fonction de celles d'un fichier .ss
	public Integer[][] lireSs(String file){
		
		Integer[][] grille = new Integer[9][9]; 
		
		try {
		InputStream inputStream = new FileInputStream(file); 
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream); 
		BufferedReader bufferReader = new BufferedReader(inputStreamReader); 
		String ligne;
		int numeroLigne = 0;
		int i = 0;
		int j = 0;
		while((ligne = bufferReader.readLine()) != null){
			for(int index = 0 ; index < 11 ; index++){
				if(ligne.charAt(index) == '.'){
					grille[i][j] = null;
					j++;
					if(j == 9){
						j = 0;
						i++;
					}
				}
				else if(ligne.charAt(index) != '!' && ligne.charAt(index) != '-'){
					grille[i][j] = Character.getNumericValue(ligne.charAt(index));
					j++;
					if(j == 9){
						j = 0;
						i++;
					}
				}
			}
		}
		bufferReader.close(); 
		
		} 
		
		catch (Exception e) { 
		System.out.println(e.toString()); 
		}
		
		return grille;
	}
	
}
