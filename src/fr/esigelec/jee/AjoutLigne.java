package fr.esigelec.jee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class AjoutLigne {

	public void Ajout1Ligne(int code, String descr, String login) {
		String FILENAME;
		BufferedWriter bw = null;
		FileWriter fw = null;	
		 //recupere le dernier fichier dans le repertoire
		File fl = new File("C:/Users/steph/eclipse-workspace/Medicament2_G3/WebContent/log/");
	    File[] files = fl.listFiles(new FilenameFilter() {          
	        public boolean accept(File file, String name) {
	            return name.contains(".txt");
	        }
	    });
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    for (File file : files) {
	        if (file.lastModified() > lastMod) {
	            choice = file;
	            lastMod = file.lastModified();
	        }
	    }
	    
	    FILENAME=""+choice;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			InetAddress IP = InetAddress.getLocalHost();

			String content = "" + code + ";" + descr + ";" + format.format(calendar.getTime()) + ";" + login + ";"+ IP.getHostAddress() + "\n";

			// true pour ne pas ecraser les lignes deja présentes
			fw = new FileWriter(FILENAME, true);
			bw = new BufferedWriter(fw);
			bw.write(content);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}