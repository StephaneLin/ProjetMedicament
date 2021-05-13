package controller;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;

import modele.CIS_CPD_bdpm;
import modele.CIS_GENER_bdpm;
import modele.CIS_HAS_ASMR;
import modele.CIS_HAS_SMR;
import modele.CIS_bdpm;
import modele.ConInfo;
import modele.Utilisateur;

public class AdminDAO {
	// Définition des paramètres permettant la connexion à la base de données
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	private final String url = "jdbc:mysql://localhost/medicament2-G3?serverTimezone=Europe/Paris";
	private final String user = "root";
	private final String password = "root";
	private final String table = "users";
	Connection conn = null;

	/**
	 * Réalise la connexion avec la base de donnée
	 * 
	 * @return boolean connexion réussie : true, connexion echouée : false
	 */
	private boolean connect() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Ferme la connexion avec la base de donnée
	 */
	private void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList getLogin(String id) {
		ArrayList login = new ArrayList();
		if (connect()) {
			String query = "SELECT * FROM " + table + " WHERE name =?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setString(1, StringEscapeUtils.escapeHtml4(id));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					login.add(StringEscapeUtils.escapeHtml4(rs.getString("name")));
					login.add(StringEscapeUtils.escapeHtml4(rs.getString("mdp")));
					login.add(rs.getInt("admin"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return login;
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public byte[] getSHA(String str) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return digest.digest(str.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * 
	 * @param pass
	 * @return
	 */
	public String toString(byte[] pass) {
		BigInteger num = new BigInteger(1, pass);
		StringBuilder str = new StringBuilder(num.toString(16));
		while (str.length() < 32) {
			str.insert(0, '0');
		}

		return str.toString();
	}

	/**
	 * Check if the parameters correspond to the one in the database
	 * 
	 * @param id
	 * @param mdp
	 * @return
	 */
	public boolean authentify(String id, String mdp) {
		// securite faite dans getlogin
		ArrayList login = getLogin(id);
		boolean signIn = false;
		if (connect()) {
			try {
				String pass = toString(getSHA(StringEscapeUtils.escapeHtml4(mdp)));
				if (login.isEmpty() == false) {
					if (pass.equals(login.get(1))) {
						signIn = true;
					}
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return signIn;
	}

	/**
	 * Insert into database a new user
	 * 
	 * @param id  name of the new user
	 * @param mdp password
	 * @return boolean true : user registered
	 */
	public boolean register(String id, String mdp) {
		boolean register = false;
		String pass;
		if (connect()) {
			try {
				pass = toString(getSHA(StringEscapeUtils.escapeHtml4(mdp)));
				String query = "INSERT INTO `users`(`name`,`mdp`,`admin`) VALUES (?,?,?)";
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					int admin = 0;
					ps.setString(1, StringEscapeUtils.escapeHtml4(id));
					ps.setString(2, StringEscapeUtils.escapeHtml4(pass));
					ps.setInt(3, admin);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				if (ps.executeUpdate() != 0) {
					register = true;
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (SQLException er) {
				er.printStackTrace();
			} finally {
				close();
			}
		}
		return register;
	}

	/**
	 * delete user from the database
	 * 
	 * @param name
	 * @return boolean true if deleted
	 */
	public boolean delete(String name) {
		boolean delete = false;
		if (connect()) {
			try {
				String query = "DELETE FROM users WHERE name =?";
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setString(1, StringEscapeUtils.escapeHtml4(name));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				if (ps.executeUpdate() != 0) {
					delete = true;
				}
			} catch (SQLException er) {
				er.printStackTrace();
			} finally {
				close();
			}
		}
		return delete;
	}

	public boolean getStatus(String name) {
		boolean admin = false;
		if (connect()) {
			try {
				String query = "SELECT admin FROM users WHERE name =?";
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setString(1, StringEscapeUtils.escapeHtml4(name));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					if (rs.getInt("admin") == 1) {
						admin = true;
					}
				}
			} catch (SQLException er) {
				er.printStackTrace();
			} finally {
				close();
			}
		}
		return admin;
	}

	/**
	 * Get the list of users from the database
	 * 
	 * @return list of users
	 */
	public ArrayList<Utilisateur> getUserList() {
		ArrayList<Utilisateur> userList = new ArrayList<Utilisateur>();
		Utilisateur user;
		if (connect()) {
			String query = "SELECT * FROM " + table;
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					user = new Utilisateur(rs.getInt("id"), StringEscapeUtils.escapeHtml4(rs.getString("name")),
							StringEscapeUtils.escapeHtml4(rs.getString("mdp")));
					userList.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return userList;
	}

	public boolean addConInfo(ConInfo ci) {
		boolean added = false;
		if (connect()) {
			try {
				String query = "INSERT INTO `connection`(`name`,`ip`,`date`,`connected`) VALUES (?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setString(1, StringEscapeUtils.escapeHtml4(ci.getName()));
					ps.setString(2, StringEscapeUtils.escapeHtml4(ci.getIp()));
					ps.setString(3, StringEscapeUtils.escapeHtml4(ci.getDate()));
					ps.setBoolean(4, ci.getStatus());
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				if (ps.executeUpdate() != 0) {
					added = true;
				}
			} catch (SQLException er) {
				er.printStackTrace();
			} finally {
				close();
			}
		}
		return added;
	}

	public ArrayList<CIS_bdpm> getAllMedicaments() {
		ArrayList<CIS_bdpm> medicamentList = new ArrayList<CIS_bdpm>();
		CIS_bdpm med;
		if (connect()) {
			String query = "select * from medicaments";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					med = new CIS_bdpm(rs.getInt("cis"), StringEscapeUtils.escapeHtml4(rs.getString("nom")),
							rs.getString("forme"), rs.getString("Vadmin"), rs.getString("statutAMM"),
							rs.getString("typeAMM"), rs.getString("etatCom"), rs.getDate("dateAMM"),
							rs.getString("statutBDM"), rs.getString("numAuth"), rs.getString("Titulaire"),
							rs.getString("surveillanceRenf"));
					medicamentList.add(med);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return medicamentList;
	}

	public ArrayList<ConInfo> getConInfo(String id) {
		ArrayList<ConInfo> infoUser = new ArrayList<ConInfo>();
		ConInfo user1;
		if (connect()) {
			String query = "SELECT * FROM connection WHERE name = '" + id + "'";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					user1 = new ConInfo(rs.getString("name"), rs.getString("ip"), rs.getString("date"),
							rs.getBoolean("connected"));
					infoUser.add(user1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return infoUser;
	}

	public ArrayList<CIS_GENER_bdpm> getGenerList() {
		ArrayList<CIS_GENER_bdpm> genList = new ArrayList<CIS_GENER_bdpm>();
		CIS_GENER_bdpm gen = null;
		if (connect()) {
			String query = "SELECT * FROM groupe GROUP BY libelleG";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					gen = new CIS_GENER_bdpm(rs.getInt("cis"), rs.getInt("identifiantG"),
							StringEscapeUtils.escapeHtml4(rs.getString("libelleG")), rs.getInt("typeG"),
							rs.getInt("num"));
					genList.add(gen);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return genList;
	}

	public String getGenLib(int cis) {
		String lib = null;
		if (connect()) {
			String query = "SELECT libelleG FROM groupe WHERE CIS =?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, cis);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					lib = rs.getString("libelleG");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return lib;
	}
	
	public CIS_bdpm getGenerMedList(int cis) {
		CIS_bdpm med = null;
		if (connect()) {
			String query = "select * from medicaments WHERE CIS=?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, cis);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					med = new CIS_bdpm(rs.getInt("cis"), StringEscapeUtils.escapeHtml4(rs.getString("nom")),
							StringEscapeUtils.escapeHtml4(rs.getString("forme")),
							StringEscapeUtils.escapeHtml4(rs.getString("Vadmin")),
							StringEscapeUtils.escapeHtml4(rs.getString("statutAMM")),
							StringEscapeUtils.escapeHtml4(rs.getString("typeAMM")),
							StringEscapeUtils.escapeHtml4(rs.getString("etatCom")), rs.getDate("dateAMM"),
							StringEscapeUtils.escapeHtml4(rs.getString("statutBDM")),
							StringEscapeUtils.escapeHtml4(rs.getString("numAuth")),
							StringEscapeUtils.escapeHtml4(rs.getString("Titulaire")),
							StringEscapeUtils.escapeHtml4(rs.getString("surveillanceRenf")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return med;
	}

	public List<Integer> getGenCis(String name) {
		List<Integer> list = new ArrayList<Integer>();
		if (connect()) {
			String query = "SELECT CIS FROM groupe WHERE libelleG =?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setString(1, name);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					list.add(rs.getInt("CIS"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return list;
	}

	public CIS_bdpm getGrMeds(int cis) {
		CIS_bdpm med = null;
		if (connect()) {
			String query = "select * from medicaments WHERE CIS=?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, cis);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					med = new CIS_bdpm(rs.getInt("cis"), StringEscapeUtils.escapeHtml4(rs.getString("nom")),
							StringEscapeUtils.escapeHtml4(rs.getString("forme")),
							StringEscapeUtils.escapeHtml4(rs.getString("Vadmin")),
							StringEscapeUtils.escapeHtml4(rs.getString("statutAMM")),
							StringEscapeUtils.escapeHtml4(rs.getString("typeAMM")),
							StringEscapeUtils.escapeHtml4(rs.getString("etatCom")), rs.getDate("dateAMM"),
							StringEscapeUtils.escapeHtml4(rs.getString("statutBDM")),
							StringEscapeUtils.escapeHtml4(rs.getString("numAuth")),
							StringEscapeUtils.escapeHtml4(rs.getString("Titulaire")),
							StringEscapeUtils.escapeHtml4(rs.getString("surveillanceRenf")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return med;
	}

	/**
	 * Calculate if the parameter date is less than 3 years old
	 * 
	 * @param sql.Date date
	 * @return boolean date is before : false date is after : true
	 */
	@SuppressWarnings("deprecation")
	public boolean dateCalcul(Date date) {
		java.util.Date dbDate = toUtilDate(date);
		java.util.Date compareDate = new java.util.Date();
		compareDate.setYear(compareDate.getYear() - 3);
		if (dbDate.before(compareDate)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Convert java.sql.Date to util.Date
	 * 
	 * @param sql.Date date
	 * @return util.Date utilDate
	 */
	public java.util.Date toUtilDate(Date date) {
		java.util.Date utilDate = new java.util.Date(date.getTime());
		return utilDate;
	}

	/**
	 * Get the list of med that are not comercialized anymore since 3 years ago
	 * 
	 * @return list of med
	 */
	public List<CIS_bdpm> getListMedLess() {
		List<CIS_bdpm> listMedLess = new ArrayList<CIS_bdpm>();
		CIS_bdpm med;
		if (connect()) {
			String query = "SELECT * FROM medicaments WHERE etatCom=?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					String value = "Non commercialisée";
					ps.setString(1, value);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					if (dateCalcul(rs.getDate("dateAMM"))) {
						med = new CIS_bdpm(rs.getInt("cis"), StringEscapeUtils.escapeHtml4(rs.getString("nom")),
								StringEscapeUtils.escapeHtml4(rs.getString("forme")),
								StringEscapeUtils.escapeHtml4(rs.getString("Vadmin")),
								StringEscapeUtils.escapeHtml4(rs.getString("statutAMM")),
								StringEscapeUtils.escapeHtml4(rs.getString("typeAMM")),
								StringEscapeUtils.escapeHtml4(rs.getString("etatCom")), rs.getDate("dateAMM"),
								StringEscapeUtils.escapeHtml4(rs.getString("statutBDM")),
								StringEscapeUtils.escapeHtml4(rs.getString("numAuth")),
								StringEscapeUtils.escapeHtml4(rs.getString("Titulaire")),
								StringEscapeUtils.escapeHtml4(rs.getString("surveillanceRenf")));
						listMedLess.add(med);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return listMedLess;
	}

	public List<CIS_HAS_SMR> getAvissmr(int id) {
		List<CIS_HAS_SMR> listavis = new ArrayList<CIS_HAS_SMR>();
		CIS_HAS_SMR avis;
		if (connect()) {
			String query = "SELECT * FROM avissmr WHERE CIS = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, id);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					avis = new CIS_HAS_SMR(rs.getInt("CIS"), StringEscapeUtils.escapeHtml4(rs.getString("codeHAS")),
							StringEscapeUtils.escapeHtml4(rs.getString("motif")), rs.getDate("dateSMR"),
							StringEscapeUtils.escapeHtml4(rs.getString("valeur")),
							StringEscapeUtils.escapeHtml4(rs.getString("libelle")));
					listavis.add(avis);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return listavis;
	}

	public List<CIS_HAS_ASMR> getAvisasmr(int id) {
		List<CIS_HAS_ASMR> listavis = new ArrayList<CIS_HAS_ASMR>();
		CIS_HAS_ASMR avis;
		if (connect()) {
			String query = "SELECT * FROM avisasmr WHERE CIS =?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, id);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					avis = new CIS_HAS_ASMR(rs.getInt("cis"), StringEscapeUtils.escapeHtml4(rs.getString("codeHAS")),
							StringEscapeUtils.escapeHtml4(rs.getString("motif")), rs.getDate("dateASMR"),
							StringEscapeUtils.escapeHtml4(rs.getString("valeur")),
							StringEscapeUtils.escapeHtml4(rs.getString("libelle")));
					listavis.add(avis);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return listavis;
	}

	public List<CIS_CPD_bdpm> getListCondition(int id) {
		List<CIS_CPD_bdpm> listCondition = new ArrayList<CIS_CPD_bdpm>();
		CIS_CPD_bdpm condition;
		if (connect()) {
			String query = "SELECT * FROM conditionprescription WHERE CIS =?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, id);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					condition = new CIS_CPD_bdpm(StringEscapeUtils.escapeHtml4(rs.getString("CIS")),
							StringEscapeUtils.escapeHtml4(rs.getString("conditionPresc")));

					listCondition.add(condition);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return listCondition;
	}

	public ArrayList<CIS_bdpm> getMedicaments() {
		ArrayList<CIS_bdpm> result = new ArrayList<CIS_bdpm>();
		CIS_bdpm med;
		if (connect()) {
			String query = "select * from medicaments WHERE etatCom=? ";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					String value = "Commercialisée";
					ps.setString(1, value);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					med = new CIS_bdpm(rs.getInt("cis"), StringEscapeUtils.escapeHtml4(rs.getString("nom")),
							StringEscapeUtils.escapeHtml4(rs.getString("forme")),
							StringEscapeUtils.escapeHtml4(rs.getString("Vadmin")),
							StringEscapeUtils.escapeHtml4(rs.getString("statutAMM")),
							StringEscapeUtils.escapeHtml4(rs.getString("typeAMM")),
							StringEscapeUtils.escapeHtml4(rs.getString("etatCom")), rs.getDate("dateAMM"),
							StringEscapeUtils.escapeHtml4(rs.getString("statutBDM")),
							StringEscapeUtils.escapeHtml4(rs.getString("numAuth")),
							StringEscapeUtils.escapeHtml4(rs.getString("Titulaire")),
							StringEscapeUtils.escapeHtml4(rs.getString("surveillanceRenf")));
					result.add(med);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return result;
	}
}