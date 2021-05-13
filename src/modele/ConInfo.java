package modele;

import java.sql.Date;

public class ConInfo {
	private String name;
	private String ip;
	private String date;
	private boolean status;
	
	public ConInfo(String n, String ip, String date, boolean st) {
		this.name = n;
		this.ip = ip;
		this.date = date;
		this.status = st;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean st) {
		this.status = st;
	}
}
