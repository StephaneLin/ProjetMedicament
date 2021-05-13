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
public class Lecture_Composition {
	
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
        InputStream flux=new FileInputStream("CIS_COMPO_bdpm.txt"); 
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        try{
            
            String ligne;
          //  while ((ligne=buff.readLine())!=null){
            while ( ((ligne=buff.readLine())!=null) ) {
            	
            	
                //Split the resulting line to have 10 fields
               String parts[] = ligne.split("\t", 12);
              // System.out.println(parts[11]);
               
               int CIS = Integer.parseInt(parts[0]) ;
               
               String typeC = parts[1];
               typeC = typeC.replace("'", " ");
               
               int code = Integer.parseInt(parts[2]) ;
               
               String nomC = parts[3];
               nomC = nomC.replace("'", " ");
               
               String dosage = parts[4];
               dosage = dosage.replace("'", " ");
               
               String referenceDose = parts[5];
               referenceDose = referenceDose.replace("'", " ");
               
               String natureComp = parts[6];
               natureComp = natureComp.replace("'", " ");
               
               int numLiaison = Integer.parseInt(parts[7]) ;
               
           
               String requete= "INSERT INTO `compositions`(`CIS`, `typeC`, `code`, `nomC`, `dosage`, `referenceDose`, `natureComp`, `numLiaison`) VALUES ('"+CIS+"','"+typeC+"','"+code+"','"+nomC+"','"+dosage+"','"+referenceDose+"','"+natureComp+"','"+numLiaison+"') ";
               System.out.println(requete);
            // create a Statement from the connection
               
               Statement statement = con.createStatement();
               
               try {
               	// insert the data
               	   statement.executeUpdate(requete);
                  }catch(Exception e) {
                  }
               i++;
               
                
            }
            //System.out.print("nombre de ligne du fichier "+i);
        
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
 