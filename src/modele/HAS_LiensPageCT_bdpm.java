package modele;

/**
 * @author utilisateur
 *
 */
public class HAS_LiensPageCT_bdpm {
	
	private String code_HAS;
	private String lienAvis;
	
	
	public HAS_LiensPageCT_bdpm(String lien, String CODEHAS) {
		
		this.code_HAS = CODEHAS;
		this.lienAvis = lien;
		
	}
	/**
	 * get cis's code
	 * @return cis's code
	 */
	public String getCode_HAS() {
		return code_HAS;
	}
	/**
	 * 
	 * @param cis
	 */
	public void setCode_cis(String CODEHAS) {
		this.code_HAS = CODEHAS;
	}
	/**
	 * get advice's link
	 * @return advice's link
	 */
	public String getLienAvis() {
		return lienAvis;
	}


	/**
	 * 
	 * @param lien
	 */
	public void setLienAvis(String lien) {
		this.lienAvis = lien;
	}


}
