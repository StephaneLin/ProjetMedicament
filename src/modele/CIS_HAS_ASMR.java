package modele;

import java.sql.Date;

/**
 * @author utilisateur
 *
 */
public class CIS_HAS_ASMR {

	private int code_cis;
	private String motifEvaluation;
	private Date dateAvisCommission;
	private String valeurASMR;
	private String libelleASMR;
	private String codeHAS;

	public CIS_HAS_ASMR(int cis, String codeHAS, String motif, Date date, String valeur, String libelle) {
		this.code_cis = cis;
		this.motifEvaluation = motif;
		this.dateAvisCommission = date;
		this.valeurASMR = valeur;
		this.libelleASMR = libelle;
		this.codeHAS = codeHAS;

	}

	/**
	 * get cis' code
	 * 
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
	 * get evlaluation's cause
	 * 
	 * @return
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
	 * get date of commission's advice
	 * 
	 * @return date of commission's advice
	 */
	public Date getDateAvisCommission() {
		return dateAvisCommission;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDateAvisCommission(Date date) {
		this.dateAvisCommission = date;
	}

	/**
	 * get ASMR's value
	 * 
	 * @return ASMR's value
	 */
	public String getValeurASMR() {
		return valeurASMR;
	}

	/**
	 * 
	 * @param valeur
	 */
	public void setValeurASMR(String valeur) {
		this.valeurASMR = valeur;
	}

	/**
	 * get ASMR's wording
	 * 
	 * @return ASMR's wording
	 */
	public String getLibelleASMR() {
		return libelleASMR;
	}

	/**
	 * 
	 * @param libelle
	 */
	public void setLibelleASMR(String libelle) {
		this.libelleASMR = libelle;
	}

	/**
	 * get code
	 * 
	 * @return ASMR's wording
	 */
	public String getcodeHAS() {
		return codeHAS;
	}

	/**
	 * 
	 * @param code
	 */
	public void setcodeHAS(String codeHAS) {
		this.codeHAS = codeHAS;
	}

}
