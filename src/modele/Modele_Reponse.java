package modele;

import java.sql.Date;

public class Modele_Reponse {
		
		
		private String reponse;
		private int idSujet;
		private String dateReponse;
		
		public Modele_Reponse(String reponse, int idSujet,String dateReponse) {
			super();
			this.reponse = reponse;
			this.idSujet = idSujet;
			this.dateReponse=dateReponse;
		}

		public String getReponse() {
			return reponse;
		}

		public void setReponse(String reponse) {
			this.reponse = reponse;
		}

		public int getIdSujet() {
			return idSujet;
		}

		public void setIdSujet(int idSujet) {
			this.idSujet = idSujet;
		}
		
		
		public String getDateRepons() {
			return dateReponse;
		}

		public void setDateRepons(String dateReponse) {
			this.dateReponse = dateReponse;
		}
		
	
}
