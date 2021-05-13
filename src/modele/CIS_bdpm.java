package modele;
import java.sql.Date;
/**
 * @author utilisateur
 *
 */

public class CIS_bdpm {

	private int code_cis;
	private String link ;
	private String link4 ;
	private String link5 ;
	private String link2 ;
	private String link3;
	private String linka;
	private String denominationMedicament;
	private String formePharmaceutique;
	private String voieAdministration;
	private String statutAdministratifAmm;
	private String typeProcedureAmm;
	private String etatCommercialisation;
	private Date dateAmm;
	private String statutBdm;
	private String numeroAutorisationEuropeenne;
	private String titulaire;
	private String surveillanceRenforcee;

	public CIS_bdpm(int cis, String denomination, String forme, String voie, String statut, String type, String etat, Date date,
			String statutB, String numero, String titu, String surveillance) {
		this.code_cis = cis;
		this.denominationMedicament = denomination;
		this.formePharmaceutique = forme;
		this.voieAdministration = voie;
		this.statutAdministratifAmm = statut;
		this.typeProcedureAmm = type;
		this.etatCommercialisation = etat;
		this.statutBdm = statutB;
		this.numeroAutorisationEuropeenne = numero;
		this.titulaire = titu;
		this.surveillanceRenforcee = surveillance;
		this.dateAmm = date;
		this.link = "<button class=\"login100-form-btn\" type=\"submit\" onclick=\"window.open('Produit.jsp?cis="+cis+"','_blank')\" target=\"_blank\">en voir plus</button>"; 
		this.link4  = "<button class=\"login100-form-btn\" type=\"submit\" onclick=\"window.open('LienASMR.jsp?cis="+cis+"','_blank')\" target=\"_blank\">en voir plus</button>"; 
		this.link2  = "<button class=\"login100-form-btn\" type=\"submit\" onclick=\"window.open('Information.jsp?cis="+cis+"','_blank')\" target=\"_blank\">en voir plus</button>"; 
		this.link3  = "<button class=\"login100-form-btn\" type=\"submit\" onclick=\"window.open('LienAvis.jsp?cis="+cis+"','_blank')\" target=\"_blank\">en voir plus</button>"; 
		this.linka  = "<button class=\"login100-form-btn\" type=\"submit\" onclick=\"window.open('CondPrescription.jsp?cis="+cis+"','_blank')\" target=\"_blank\">en voir plus</button>"; 
		this.link5  = "<button class=\"login100-form-btn\" type=\"submit\" onclick=\"window.open('LienSMR.jsp?cis="+cis+"','_blank')\" target=\"_blank\">en voir plus</button>"; 
	}

	/**
	 * get cis code of a medecine
	 * 
	 * @return cis code of medecine
	 */
	public int getCode_cis() {
		return code_cis;
	}

	/**
	 * 
	 * @param code_cis
	 */
	public void setCode_cis(int cis) {
		this.code_cis = cis;
	}

	/**
	 * get denomination of medecine
	 * 
	 * @return denomination of medecine
	 */
	public String getDenominationMedicament() {
		return denominationMedicament;
	}

	/**
	 * 
	 * @param denominationMedicament
	 */
	public void setDenominationMedicament(String denomination) {
		this.denominationMedicament = denomination;
	}

	/**
	 * get the pharmaceutical form
	 * 
	 * @return pharmaceutical form
	 */
	public String getFormePharmaceutique() {
		return formePharmaceutique;
	}

	/**
	 * 
	 * @param formePharmaceutique
	 */
	public void setFormePharmaceutique(String forme) {
		this.formePharmaceutique = forme;
	}

	/**
	 * get administration way
	 * 
	 * @return administration way
	 */
	public String getVoieAdministration() {
		return voieAdministration;
	}

	/**
	 * 
	 * @param voieAdministration
	 */
	public void setVoieAdministration(String voie) {
		this.voieAdministration = voie;
	}

	/**
	 * get administration status
	 * 
	 * @return administration status
	 */
	public String getStatutAdministratifAmm() {
		return statutAdministratifAmm;
	}

	/**
	 * 
	 * @param statutAdministratifAmm
	 */
	public void setStatutAdministratifAmm(String statut) {
		this.statutAdministratifAmm = statut;
	}

	/**
	 * get procedure type
	 * 
	 * @return procedure type
	 */
	public String getTypeProcedureAmm() {
		return typeProcedureAmm;
	}

	/**
	 * 
	 * @param typeProcedureAmm
	 */
	public void setTypeProcedureAmm(String type) {
		this.typeProcedureAmm = type;
	}

	/**
	 * get marketing status
	 * 
	 * @return marketing status
	 */
	public String getEtatCommercialisation() {
		return etatCommercialisation;
	}

	/**
	 * 
	 * @param etatCommercialisation
	 */
	public void setEtatCommercialisation(String etat) {
		this.etatCommercialisation = etat;
	}

	/**
	 * get Bdm status
	 * 
	 * @return Bdm status
	 */
	public String getStatutBdm() {
		return statutBdm;
	}

	/**
	 * 
	 * @param statutBdm
	 */
	public void setStatutBdm(String statutB) {
		this.statutBdm = statutB;
	}

	/**
	 * get european authorization
	 * 
	 * @return european authorization
	 */
	public String getNumeroAutorisationEuropeenne() {
		return numeroAutorisationEuropeenne;
	}

	/**
	 * 
	 * @param numéroAutorisationEuropeenne
	 */
	public void setNuméroAutorisationEuropeenne(String numero) {
		this.numeroAutorisationEuropeenne = numero;
	}

	/**
	 * get holder
	 * 
	 * @return holder
	 */
	public String getTitulaire() {
		return titulaire;
	}

	/**
	 * 
	 * @param titulaire
	 */
	public void setTitulaire(String titu) {
		this.titulaire = titu;
	}

	/**
	 * get enforced monitoring
	 * 
	 * @return enforced monitoring
	 */
	public String isSurveillanceRenforcee() {
		return surveillanceRenforcee;
	}

	/**
	 * 
	 * @param surveillanceRenforcee
	 */
	public void setSurveillanceRenforcee(String surveillance) {
		this.surveillanceRenforcee = surveillance;
	}

	/**
	 * get date of AMM
	 * 
	 * @return date of AMM
	 */
	public Date getDateAmm() {
		return dateAmm;
	}

	/**
	 * 
	 * @param dateAmm
	 */
	public void setDateAmm(Date date) {
		this.dateAmm = date;
	}
	
	public String getLink() {
		return link;
	}

}
