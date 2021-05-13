package modele;

public class InfoLog {
	
	private int numero;
	private String libele;
	private String date;
	private String login;
	private String ipAdress;
	public InfoLog(int numero, String libele, String date, String login, String ipAdress) {
		super();
		this.numero = numero;
		this.libele = libele;
		this.date = date;
		this.login = login;
		this.ipAdress = ipAdress;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getLibele() {
		return libele;
	}
	public void setLibele(String libele) {
		this.libele = libele;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	@Override
	public String toString() {
		return "InfoLog [numero=" + numero + ", libele=" + libele + ", date=" + date + ", login=" + login
				+ ", ipAdress=" + ipAdress + "]";
	}
	
	

}
