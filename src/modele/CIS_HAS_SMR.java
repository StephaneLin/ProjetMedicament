package modele;

import java.sql.Date;

/**
 * @author utilisateur
 *
 */
public class CIS_HAS_SMR {
	
	private int code_cis;
	private String codeDossierHAS;
	private String motifEvaluation;
	private Date dateAvisCommissionTransparence;
	private String valeurSMR;
	private String libelleSMR;
	
	
	public CIS_HAS_SMR(int cis, String code, String motif, Date date, String valeur,String libelle) {
		this.code_cis = cis;
		this.codeDossierHAS = code;
		this.motifEvaluation = motif;
		this.dateAvisCommissionTransparence = date;
		this.valeurSMR = valeur;
		this.libelleSMR = libelle;
		
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
	 * get HAS file's code
	 * @return HAS file's code
	 */
	public String getCodeDossierHAS() {
		return codeDossierHAS;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCodeDossierHAS(String code) {
		this.codeDossierHAS = code;
	}

	/**
	 * get evaluation's reason
	 * @return  evaluation's reason
	 */
	public String getMotifEvaluation() {
		return motifEvaluation;
	}

	/**
	 * 
	 * @param motif
	 */
	public void setMotifEvaluation(String motif) {
		this.motifEvaluation = motif;
	}

	/**
	 * get commission advice's date
	 * @return commission advice's date
	 */
	public Date getDateAvisCommissionTransparence() {
		return dateAvisCommissionTransparence;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDateAvisCommissionTransparence(Date date) {
		this.dateAvisCommissionTransparence = date;
	}

	/**
	 * get SMR's value
	 * @return  SMR's value
	 */
	public String getValeurSMR() {
		return valeurSMR;
	}

	/**
	 * 
	 * @param valeur
	 */
	public void setValeurSMR(String valeur) {
		this.valeurSMR = valeur;
	}

	/**
	 * get SMR's wording
	 * @return  SMR's wording
	 */
	public String getLibelleSMR() {
		return libelleSMR;
	}

	/**
	 * 
	 * @param libelle
	 */
	public void setLibelleSMR(String libelle) {
		this.libelleSMR = libelle;
	}


}
