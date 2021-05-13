package fr.esigelec.jee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import modele.*;


public class lecturelog {
	
	public ArrayList<InfoLog>  lecture (String login){
		ArrayList<InfoLog> retour = new ArrayList<InfoLog>();
		
		File file = new File ("C:/Users/steph/eclipse-workspace/Medicament2_G3/WebContent/log/log1.txt");
		BufferedReader bufferedReader = null;
		
		try {

			FileReader fileReader = new  FileReader(file);
			bufferedReader = new BufferedReader (fileReader);
			String line = new String() ;
			String tab[];
			
			while ((line = bufferedReader.readLine())!= null) {
				
				if(line != null) {
					tab = line.split(";");
					if(tab != null) {
						if(tab[3].equals(login)) {
							retour.add(new InfoLog(Integer.parseInt(tab[0]), tab[1], tab[2], tab[3], tab[4])); 
						}
					}
					
				}
				
				
				
			}
			
		} catch (FileNotFoundException e) {
			 System.err.printf ("le fichier n'a pas été trouvé" + file.toString());
          } catch (IOException e){
        	  System.err.printf ("impossible de lire le fichier" + file.toString());
          }
		try {
			if (bufferedReader != null) {
			bufferedReader.close();
			}
		} catch (IOException e) {
			System.err.println ("Impossible de fermer le fichier" + file.toString());
		} 

		
		
		return retour;
		
	}
}
