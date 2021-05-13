package fr.esigelec.jee;


import java.text.DateFormat;
import java.text.SimpleDateFormat; 

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
public class Lecture_Presentation {
	
	static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    static String url = "jdbc:mysql://localhost/medicament2-g3?serverTimezone=Europe/Paris";
    static String user = "root";
    static String password = "";
   
    public static void main(String[] args) throws FileNotFoundException {
    	Connection con=null;
    	try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("reussite connection");
        } catch (SQLException e) {
            System.out.println("echec connection");
            e.printStackTrace();
           
        }
        int i=0;
        InputStream flux=new FileInputStream("CIS_CIP_bdpm.txt"); 
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        try{
            
            String ligne;
            ArrayList<String> liste = new ArrayList<String>();
          //  while ((ligne=buff.readLine())!=null){
            while ( ((ligne=buff.readLine())!=null)) {
            	
            	
                //Split the resulting line to have 10 fields
               String parts[] = ligne.split("\t", 13);
              // System.out.println(parts[11]);
               SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
             
               int CIS = Integer.parseInt(parts[0]) ;
               
               int CIP7 = Integer.parseInt(parts[1]) ;
               
               String nomP = parts[2];
               nomP = nomP.replace("'", " ");
               
               String status = parts[3];
               status = status.replace("'", " ");
               
               String etatCom = parts[4];
               etatCom = etatCom.replace("'", " ");
               
               DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

               Date date = sdf.parse(parts[5]);
               String dateCom = new SimpleDateFormat("yyyy-MM-dd").format(date);
        
               String CIP13 = parts[6];
               CIP13 = CIP13.replace("'", " ");
        
               
               
               String agrement = parts[7];
               agrement = agrement.replace("'", " ");
               
               String taux = parts[8];
               taux = taux.replace("'", " ");
               
               String prix_bas = parts[9];
               prix_bas = prix_bas.replace("'", " ");
               
               String prix_haut = parts[10];
               prix_haut = prix_haut.replace("'", " ");
               
               String prix = prix_bas.concat("-").concat(prix_haut);
               //le troisieme chiffre(parts[11]) est l'écart entre prix haut et prix bas
               
               String indicRemb = parts[12];
               indicRemb = indicRemb.replace("'", " ");
        
               String requete= "INSERT INTO `presentations`(`CIS`, `CIP7`, `nomP`, `status`, `etatCom`, `dateCom`, `CIP13`, `agrement`, `taux`, `prix`, `indicRemb`) VALUES ('"+CIS+"','"+CIP7+"','"+nomP+"','"+status+"','"+etatCom+"','"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"','"+CIP13+"','"+agrement+"','"+taux+"','"+prix+"','"+indicRemb+"') ";
               System.out.println(requete);

               
            // create a Statement from the connection
               Statement statement = con.createStatement();
               
               
               //dans try/catch car certaines lignes referent a des medicaments qui n'existe pas, les contrainte empecheront l'ajout de la ligne
               try {
            	// insert the data
            	   statement.executeUpdate(requete);
               }catch(Exception e) {
            	   liste.add(requete);
               }
               

               i++;
               
                
            }
            System.out.print(liste);
        
        }        
        // fermeture des fluxs 
        catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            try {
                buff.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

 


            try {
                flux.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }    


    }


}
 