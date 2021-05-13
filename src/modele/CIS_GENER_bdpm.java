package modele;

/**
 * @author utilisateur
 *
 */
public class CIS_GENER_bdpm {

	private int code_cis;
	private int identifiantGroupeGenerique;
	private String libelleGroupeGenerique;
	private int typeGenerique;
	private int numeroTri;
	private String lien;

	public CIS_GENER_bdpm(int cis, int identifiant, String libelle, int type, int numero) {
		this.identifiantGroupeGenerique = identifiant;
		this.libelleGroupeGenerique = libelle;
		this.typeGenerique = type;
		this.numeroTri = numero;
		this.code_cis = cis;
		this.lien = "<Form action=\"GroupePage2.jsp\" id=\"codeForm\" Method=\"GET\"><input type=\"hidden\" name=\"cis\" id=\"cis\"> <input class=\"login100-form-btn\" type=\"button\" id=\""
				+ cis + "\"value=\"en voir plus\" onclick=\"buttonCode(this.id)\"></Form>";
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
	 * get generic group's identifier
	 * 
	 * @return generic group's identifier
	 */
	public int getIdentifiantGroupeGenerique() {
		return identifiantGroupeGenerique;
	}

	/**
	 * 
	 * @param identifiant
	 */
	public void setIdentifiantGroupeGenerique(int identifiant) {
		this.identifiantGroupeGenerique = identifiant;
	}

	/**
	 * get generic group's wording
	 * 
	 * @return
	 */
	public String getLibelleGroupeGenerique() {
		return libelleGroupeGenerique;
	}

	/**
	 * 
	 * @param libelle
	 */
	public void setLibelleGroupeGenerique(String libelle) {
		this.libelleGroupeGenerique = libelle;
	}

	/**
	 * get generic's type
	 * 
	 * @return generic's type
	 */
	public int getTypeGenerique() {
		return typeGenerique;
	}

	/**
	 * 
	 * @param type
	 */
	public void setTypeGenerique(int type) {
		this.typeGenerique = type;
	}

	/**
	 * get sort's number
	 * 
	 * @return sort's number
	 */
	public int getNumeroTri() {
		return numeroTri;
	}

	/**
	 * 
	 * @param numero
	 */
	public void setNumeroTri(int numero) {
		this.numeroTri = numero;
	}

}
