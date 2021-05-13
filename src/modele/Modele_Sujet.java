package modele;

import java.sql.Date;

public class Modele_Sujet {

	
		
		private int idMessage;
		private String pseudoMessage;
		private String adresse_mailMessage;
		private String message;
		private Date dateMessage; 
		private String lienk;
		
		public Modele_Sujet(int id, String pseudo, String adresse_mail, String messages, Date date ) {
			
			
			this.idMessage = id;
			this.pseudoMessage = pseudo;
			this.adresse_mailMessage = adresse_mail;
			this.message = messages;
			this.dateMessage = date;
			lienk = "<button class=\"login100-form-btn\" type=\"submit\" onclick=\"window.open('listeRepMessage.jsp?id="+idMessage+"','_blank')\" target=\"_blank\">en voir plus</button>";
				
		}
		/**
		 * get the code cis of this composition
		 * @return  code cis of this composition
		 */
		public int get_idMessage() {
			return idMessage;
		}
		
		/**
		 * get pharmaceutical element's name
		 * @return  pharmaceutical element's name
		 */
		public String getPseudoMessage() {
			return pseudoMessage;
		}

		/**
		 * 
		 * @param designation
		 */
		public void setPseudoMessage(String pseudo) {
			this.pseudoMessage = pseudo;
		}

		/**
		 * get substance's code
		 * @return substance's code
		 */
		public String getMessage () {
			return message ;
		}

		/**
		 * 
		 * @param code
		 */
		public void setMessage(String messages) {
			this. message = messages;
		}
		
		/**
		 * get substance's code
		 * @return substance's code
		 */
		public String getAdresseMailMessage () {
			return adresse_mailMessage ;
		}

		/**
		 * 
		 * @param code
		 */
		public void setAdresseMailMessage(String adresse_mail) {
			this. adresse_mailMessage = adresse_mail;
		}

		/**
		 * get substance's denomination
		 * @return substance's denomination
		 */
		public Date getDateMessage() {
			return dateMessage;
		}

		/**
		 * 
		 * @param denomination
		 */
		public void setDateMessage(Date date) {
			this.dateMessage = date;
		}
		
	
}
