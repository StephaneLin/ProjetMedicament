package modele;

import java.sql.Date;

public class CIS_CIP {
	private int cis;
	private int cip7;
	private String presentation;
	private String statut;
	private String etat;
	private Date date;
	private String cip13;
	private String agrement;
	private String taux;
	private String prix;
	private String indication;
	
	public CIS_CIP(int cis, int cip7, String p, String s, String e, Date d, String cip13, String a, String t, String prix, String i) {
		this.cis = cis;
		this.cip7= cip7;
		this.presentation = p;
		this.statut = s;
		this.etat = e;
		this.date = d;
		this.cip13 = cip13;
		this.agrement = a;
		this.taux = t;
		this.prix = prix;
		this.indication = i;
	}

	public int getCis() {
		return cis;
	}

	public void setCis(int cis) {
		this.cis = cis;
	}

	public int getCip7() {
		return cip7;
	}

	public void setCip7(int cip7) {
		this.cip7 = cip7;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCip13() {
		return cip13;
	}

	public void setCip13(String cip13) {
		this.cip13 = cip13;
	}

	public String getAgrément() {
		return agrement;
	}

	public void setAgrément(String agrément) {
		this.agrement = agrément;
	}

	public String getTaux() {
		return taux;
	}

	public void setTaux(String taux) {
		this.taux = taux;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}
	
}
