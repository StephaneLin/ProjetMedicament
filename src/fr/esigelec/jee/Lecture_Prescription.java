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
public class Lecture_Prescription {
	 
	static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    static String url = "jdbc:mysql://localhost/medicament2-g3?serverTimezone=Europe/Paris";
    static String user = "root";
    static String password = "root";
   
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
        InputStream flux=new FileInputStream("CIS_CPD_bdpm.txt"); 
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        try{
            
            String ligne;
            ArrayList<String> liste = new ArrayList<String>();
          //  while ((ligne=buff.readLine())!=null){
            while ( ((ligne=buff.readLine())!=null)  ) {
            	
            	
                //Split the resulting line to have 10 fields
               String parts[] = ligne.split("\t", 2);
              // System.out.println(parts[11]);
          
               int CIS = Integer.parseInt(parts[0]) ;
               
               String conditionPresc = parts[1];
               conditionPresc = conditionPresc.replace("'", " ");
                 
          
           
               String requete= "INSERT INTO `conditionprescription`(`CIS`, `conditionPresc`) VALUES ('"+CIS+"','"+conditionPresc+"') ";
               System.out.println(requete);
            // create a Statement from the connection
               Statement statement = con.createStatement();
             //dans try/catch car certaines lignes referent  a des medicaments qui n'existe pas, les contrainte empecheront l'ajout de la ligne
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
 