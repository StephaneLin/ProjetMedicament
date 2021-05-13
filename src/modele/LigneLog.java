package modele;

import java.util.Date;

public class LigneLog {
	private int id;
	private String descr;
	private Date horaireEnreg;
	private String login;
	private String ip;

	public LigneLog(int id,String descr,Date horaireEnreg,String login,String ip) {
		this.id=id;
		this.descr=descr;
		this.horaireEnreg=horaireEnreg;
		this.login=login;
		this.ip=ip;
	}
	public int getId() {
		return id;
	}
	public String getDescr() {
		return descr;
	}
	public Date getHoraireEnreg() {
		return horaireEnreg;
	}
	public String getLogin() {
		return login;
	}
	public String getIp() {
		return ip;
	}

}
