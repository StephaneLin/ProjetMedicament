package modele;

import controller.AdminDAO;

public class Utilisateur {
	private AdminDAO dao;
	private int id;
	private String user;
	private String mdp;
	
	public Utilisateur(int i, String u, String pass) {
		this.id = i;
		this.user = u;
		this.mdp = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
}
