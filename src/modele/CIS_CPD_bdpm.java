package modele;
/**
 * @author utilisateur
 *
 */
public class CIS_CPD_bdpm{

	private String code_cis;
	private String conditionDelivrance;
	
	
	public CIS_CPD_bdpm(String condition, String cis) {
		
		this.conditionDelivrance = condition;
		this.code_cis = cis;
			
	}
	/**
	 * get deliverance's conditions
	 * @return deliverance's conditions
	 */
	public String getConditionDelivrance() {
		return conditionDelivrance;
	}

	/**
	 * 
	 * @param condition
	 */
	public void setConditionDelivrance(String condition) {
		this.conditionDelivrance = condition;
	}
	/**
	 * get cis' code
	 * @return cis' code
	 */
	public String getCode_cis() {
		return code_cis;
	}
	/**
	 * 
	 * @param cis
	 */
	public void setCode_cis(String cis) {
		this.code_cis = cis;
	}

	

}
