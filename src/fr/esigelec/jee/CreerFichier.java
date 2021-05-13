package fr.esigelec.jee;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CreerFichier {

	public void creerFichier() {

		try {
			//recup les noms
			String nomFichier = null;
			String cheminDest= "C:/Users/steph/eclipse-workspace/Medicament2_G3/WebContent/log/";
			File fl = new File(cheminDest);
		    File[] files = fl.listFiles(new FilenameFilter() {          
		        public boolean accept(File file, String name) {
		            return name.contains(".txt");
		        }
		    });
		    String nom;
		    //on verifie si il y a au moins 2 fichiers car log.txt n'a aucun numero pour la comparaison
		    if(files.length >1) {
		    	ArrayList<Integer> listeNombre = new ArrayList<Integer>();
		    	for (File numfile : files) {
			    	nom= numfile.getName();
			    	String parts[] = nom.split("\\.");
			    	String nomSansExtension=parts[0];
			    	String sousChaine = nomSansExtension.substring(3, nomSansExtension.length());
			    	if(sousChaine.equals("")) {
			    	}else {
			    		try {
			    			int numero = Integer.parseInt(sousChaine);
				    		listeNombre.add(numero);
			    		}catch(Exception e) {
			    			
			    		}
			    		
			    	}
			    	
			    	
			    }
		    	//trie la liste dans ordre decroissant
		    	Collections.sort(listeNombre, Collections.reverseOrder());
		    	nomFichier= "log"+(listeNombre.get(0)+1)+".txt";
		    	
		    }else {
		    	nomFichier="log1.txt";
		    }
		    
		    
		    
			String FILENAME = cheminDest + nomFichier;
			
			File fichier = new File(FILENAME);

			if (fichier.createNewFile()) {

				System.out.println("Fichier créé");
			}
			else {
				System.out.println("Fichier non créé");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
