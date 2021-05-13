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
public class Lecture_medicament {
	 
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
        InputStream flux=new FileInputStream("CIS_bdpm.txt"); 
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        try{
            
            String ligne;
          //  while ((ligne=buff.readLine())!=null){
            while ( ((ligne=buff.readLine())!=null)  ) {
            	
            	
                //Split the resulting line to have 10 fields
               String parts[] = ligne.split("\t", 12);
              // System.out.println(parts[11]);
               SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
               int CIS = Integer.parseInt(parts[0]) ;
               String nom = parts[1];
               nom = nom.replace("'", " ");
               
               String forme = parts[2];
               forme = forme.replace("'", " ");
               
               String Vadmin = parts[3];
               Vadmin = Vadmin.replace("'", " ");
               
               String statutAMM = parts[4];
               statutAMM = statutAMM.replace("'", " ");
               
               String typeAMM = parts[5];
               typeAMM = typeAMM.replace("'", " ");
              
               String etatCom = parts[6];
               etatCom = etatCom.replace("'", " ");
               
            	   
            	  // Date  dateAMM_avant =  dateFormat.parse(parts[7]);
            	
            	   DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                   Date date = sdf.parse(parts[7]);
            String dateAMM = new SimpleDateFormat("yyyy-MM-dd").format(date);
                  
               
               
            	 
              
               
               String statutBDM = parts[8];
               statutBDM = statutBDM.replace("'", " ");
               
               String numero = parts[9];
               numero = numero.replace("'", " ");
               
               String Titulaire = parts[10];
               Titulaire = Titulaire.replace("'", " ");
               
               String surveillanceRef = parts[11];
               surveillanceRef = surveillanceRef.replace("'", " ");
           
               String requete= "INSERT INTO `medicaments`(`CIS`, `nom`, `forme`, `Vadmin`, `statutAMM`, `typeAMM`, `etatCom`, `dateAMM`, `statutBDM`, `numAuth`, `Titulaire`, `surveillanceRenf`) VALUES ('"+CIS+"','"+nom+"','"+forme+"','"+Vadmin+"','"+statutAMM+"','"+typeAMM+"','"+etatCom+"','"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"','"+statutBDM+"','"+numero+"','"+Titulaire+"','"+surveillanceRef+"') ";
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
 