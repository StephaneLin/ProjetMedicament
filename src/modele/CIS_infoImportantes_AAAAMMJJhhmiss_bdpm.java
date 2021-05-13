package modele;

import java.sql.Date;

/**
 * @author utilisateur
 *
 */
public class CIS_infoImportantes_AAAAMMJJhhmiss_bdpm{
	private int code_cis;
	private Date dateDebutInformationSecurite;
	private Date dateFinInformationSecurite;
	private String texteAfficherLienInformationSecurite;
	
	public CIS_infoImportantes_AAAAMMJJhhmiss_bdpm(int cis, Date dateDebut, Date dateFin, String texte) {
		this.code_cis = cis;
		this.dateDebutInformationSecurite = dateDebut;
		this.dateFinInformationSecurite = dateFin;
		this.texteAfficherLienInformationSecurite = texte;
		
	}
	/**
	 * get cis' code
	 * @return cis' code
	 */
	public int getCode_cis() {
		return code_cis;
	}
	/**
	 * 
	 * @param cis
	 */
	public void setCode_cis(int cis) {
		this.code_cis = cis;
	}
	/**
	 * get the date of beginning of securitie's information
	 * @return the date of beginning of securitie's information
	 */
	public Date getDateDebutInformationSecurite() {
		return dateDebutInformationSecurite;
	}
	/**
	 * 
	 * @param dateDebut
	 */
	public void setDateDebutInformationSecurite(Date dateDebut) {
		this.dateDebutInformationSecurite = dateDebut;
	}
/**
 * get  the date of ending of securitie's information
 * @return the date of ending of securitie's information
 */
	public Date getDateFinInformationSecurite() {
		return dateFinInformationSecurite;
	}
	/**
	 * 
	 * @param dateFin
	 */
	public void setDateFinInformationSecurite(Date dateFin) {
		this.dateFinInformationSecurite = dateFin;
	}
	/**
	 * get display text information security's link
	 * @return display text information security's link
	 */
	public String getTexteAfficherLienInformationSecurite() {
		return texteAfficherLienInformationSecurite;
	}
	/**
	 * 
	 * @param texte
	 */
	public void setTexteAfficherLienInformationSecurite(String texte) {
		this.texteAfficherLienInformationSecurite = texte;
	}

}
