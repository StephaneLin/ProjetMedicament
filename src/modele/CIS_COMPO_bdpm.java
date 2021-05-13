package modele;

/**
 * @author utilisateur
 *
 */


// Classe de composition des médicaments

public class CIS_COMPO_bdpm {
	
	private int code_cis;
	private String designationElementPharmaceutique;
	private int codeSubstance;
	private String denominationSubstance;
	private String dosageSubstance;
	private String referenceDosage;
	private String natureComposant;
	private int numeroLiaison;
	private String lien;
	
	public CIS_COMPO_bdpm(int cis, String designation, int code, String denomination, String dosage, String reference, String nature, int numero) {
		
		this.code_cis = cis;
		this.designationElementPharmaceutique = designation;
		this.codeSubstance = code;
		this.denominationSubstance=denomination;
		this.dosageSubstance = dosage;
		this.referenceDosage = reference;
		if(nature.equals("SA")) {
			this.natureComposant = "Principe Actif";
		}else {
			this.natureComposant = "Fraction Thérapeutique ";
		}
		this.numeroLiaison = numero;
		lien = "<Form action=\"RecupCodeSubstance\" id=\"codeForm\" Method=\"GET\"><input type=\"hidden\" name=\"code\" id=\"code\"> <input class=\"login100-form-btn\" type=\"button\" id=\""+code+"\"value=\"en voir plus\" onclick=\"buttonCodeCompo(this.id)\"></Form>";
	
	}
	/**
	 * get the code cis of this composition
	 * @return  code cis of this composition
	 */
	public int getCIS() {
		return code_cis;
	}
	
	/**
	 * get pharmaceutical element's name
	 * @return  pharmaceutical element's name
	 */
	public String getDesignationElementPharmaceutique() {
		return designationElementPharmaceutique;
	}

	/**
	 * 
	 * @param designation
	 */
	public void setDesignationElementPharmaceutique(String designation) {
		this.designationElementPharmaceutique = designation;
	}

	/**
	 * get substance's code
	 * @return substance's code
	 */
	public int getCodeSubstance() {
		return codeSubstance;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCodeSubstance(int code) {
		this.codeSubstance = code;
	}

	/**
	 * get substance's denomination
	 * @return substance's denomination
	 */
	public String getDenominationSubstance() {
		return denominationSubstance;
	}

	/**
	 * 
	 * @param denomination
	 */
	public void setDenominationSubstance(String denomination) {
		this.denominationSubstance = denomination;
	}

	/**
	 * get substance's dosage
	 * @return substance's dosage
	 */
	public String getDosageSubstance() {
		return dosageSubstance;
	}

	/**
	 * 
	 * @param dosage
	 */
	public void setDosageSubstance(String dosage) {
		this.dosageSubstance = dosage;
	}

	/**
	 * get dosage's reference
	 * @return dosage's reference
	 */
	public String getReferenceDosage() {
		return referenceDosage;
	}

	/**
	 * 
	 * @param reference
	 */
	public void setReferenceDosage(String reference) {
		this.referenceDosage = reference;
	}

	/**
	 * get component's nature
	 * @return component's nature
	 */
	public String getNatureComposant() {
		return natureComposant;
	}

	/**
	 * 
	 * @param nature
	 */
	public void setNatureComposant(String nature) {
		this.natureComposant = nature;
	}

	/**
	 * get link's number
	 * @return link's number
	 */
	public int getNumeroLiaison() {
		return numeroLiaison;
	}

	/**
	 * 
	 * @param numero
	 */
	public void setNumeroLiaison(int numero) {
		this.numeroLiaison = numero;
	}
	
}




	
	

	




