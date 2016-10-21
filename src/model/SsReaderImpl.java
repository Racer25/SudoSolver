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
		int nombreDeCaracteres = 11*11;
		int index = 0;
		ligne = bufferReader.readLine(); 
		System.out.println(ligne);
		int ind = ligne.indexOf(" ");
		for(int i = 0; i > 9 ; i++){
			for(int j = 0 ; j < 9 ; j++){
				while(ligne.charAt(index) == '!' || ligne.charAt(index) == '-'){
					index++;
				}
				if(ligne.charAt(index) == '.'){
					grille[i][j] = null;
					index++;
				}
				else{
					grille[i][j] = Integer.parseInt(ligne, ligne.charAt(index));
					index++;
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
