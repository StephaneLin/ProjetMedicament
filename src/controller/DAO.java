package controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.commons.text.StringEscapeUtils;

import modele.*;

/**
 * La classe permet de faire la connection avec la base de données. Elle
 * possèdent différentes methodes pour pouvoir requeter les différentes tables
 * de la base de données et ensuite créer des objets grâce aux resultats des
 * requetes.
 * 
 * @author Groupe 3
 * @version 1
 */
public class DAO {
	// on charge le driver en statique
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
// preparation des parametres de connection
	private String url;
	private String username;
	private String password;
	Connection conn = null;

	/**
	 * Les paramètres de connection sont recupérer dans un servlet puis transmit a
	 * la classe dao par l'intermediaire de ce constructeur.
	 * 
	 * @param url2      un string représentant l'url pour ce connecter à la base de
	 *                  donnée, contient aussi le nom de la base de données cible
	 * @param username2 un string représentant le login à utiliser lors de la
	 *                  connection
	 * @param password2 un string représentant le mot de passe à utiliser pour la
	 *                  connection
	 */
	public DAO(String url2, String username2, String password2) {
		url = url2;
		username = username2;
		password = password2;
	}

	/**
	 * Permet d'ouvrir une connection avec la base de donnée
	 * 
	 * @return true/false un booleen indiquant si la connection à reussi ou non
	 */
	public boolean connect() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/**
	 * Permet de fermer la connection ouverte avec la base de donnée
	 * 
	 */
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Get list of med that are still commercialized
	 * 
	 * @return list of meds
	 */
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

	/**
	 * Get list of compositions
	 * 
	 * @return list of composition
	 */
	public ArrayList<CIS_COMPO_bdpm> getCompo() {
		ArrayList<CIS_COMPO_bdpm> result = new ArrayList<CIS_COMPO_bdpm>();
		CIS_COMPO_bdpm comp;
		if (connect()) {
			String query = "SELECT * FROM `compositions` GROUP BY `code` ";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					comp = new CIS_COMPO_bdpm(rs.getInt("CIS"), StringEscapeUtils.escapeHtml4(rs.getString("typeC")),
							rs.getInt("code"), StringEscapeUtils.escapeHtml4(rs.getString("nomC")),
							StringEscapeUtils.escapeHtml4(rs.getString("dosage")),
							StringEscapeUtils.escapeHtml4(rs.getString("referenceDose")),
							StringEscapeUtils.escapeHtml4(rs.getString("natureComp")), rs.getInt("numLiaison"));
					result.add(comp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return result;
	}

	/**
	 * Get a list of all the cis code linked to the subtance code
	 * 
	 * @return list of cis
	 */
	public ArrayList<Integer> getCISCompo(int code) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (connect()) {
			String query = "SELECT * FROM `compositions` WHERE `code`=? GROUP BY CIS ";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, code);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result.add(rs.getInt("CIS"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return result;
	}

	/**
	 * Get a medicament by his cis code
	 * 
	 * @return a medicament
	 */
	public CIS_bdpm getMedicament(int cis) {
		CIS_bdpm comp = null;
		if (connect()) {
			String query = "select * from medicaments WHERE CIS=? ";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, cis);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					return new CIS_bdpm(rs.getInt("cis"), StringEscapeUtils.escapeHtml4(rs.getString("nom")),
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
		return comp;
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

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<CIS_CIP> getPresentation(int id) {
		List<CIS_CIP> listPres = new ArrayList<CIS_CIP>();
		CIS_CIP present;
		if (connect()) {
			String query = "SELECT * FROM presentations WHERE CIS =?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, id);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					present = new CIS_CIP(rs.getInt("cis"), rs.getInt("cip7"),
							StringEscapeUtils.escapeHtml4(rs.getString("nomP")),
							StringEscapeUtils.escapeHtml4(rs.getString("status")),
							StringEscapeUtils.escapeHtml4(rs.getString("etatCom")), rs.getDate("dateCom"),
							StringEscapeUtils.escapeHtml4(rs.getString("cip13")),
							StringEscapeUtils.escapeHtml4(rs.getString("agrement")),
							StringEscapeUtils.escapeHtml4(rs.getString("taux")),
							StringEscapeUtils.escapeHtml4(rs.getString("prix")),
							StringEscapeUtils.escapeHtml4(rs.getString("indicRemb")));
					listPres.add(present);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return listPres;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<CIS_COMPO_bdpm> getCompo(int id) {
		List<CIS_COMPO_bdpm> listComp = new ArrayList<CIS_COMPO_bdpm>();
		CIS_COMPO_bdpm comp;
		if (connect()) {
			String query = "SELECT * FROM compositions WHERE CIS =?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, id);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					comp = new CIS_COMPO_bdpm(rs.getInt("cis"), StringEscapeUtils.escapeHtml4(rs.getString("typeC")),
							rs.getInt("code"), StringEscapeUtils.escapeHtml4(rs.getString("nomC")),
							StringEscapeUtils.escapeHtml4(rs.getString("dosage")),
							StringEscapeUtils.escapeHtml4(rs.getString("referenceDose")),
							StringEscapeUtils.escapeHtml4(rs.getString("natureComp")), rs.getInt("numliaison"));
					listComp.add(comp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return listComp;
	}

	/**
	 * Get list of all med
	 * 
	 * @return list of all meds
	 */
	public ArrayList<CIS_bdpm> getAllMedicaments() {
		ArrayList<CIS_bdpm> result = new ArrayList<CIS_bdpm>();
		CIS_bdpm med;
		if (connect()) {
			String query = "select * from medicaments";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
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

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<CIS_infoImportantes_AAAAMMJJhhmiss_bdpm> getListInfo(int id) {
		List<CIS_infoImportantes_AAAAMMJJhhmiss_bdpm> listInfo = new ArrayList<CIS_infoImportantes_AAAAMMJJhhmiss_bdpm>();
		CIS_infoImportantes_AAAAMMJJhhmiss_bdpm info;
		if (connect()) {
			String query = "SELECT * FROM infos WHERE CIS =?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, id);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					info = new CIS_infoImportantes_AAAAMMJJhhmiss_bdpm(rs.getInt("CIS"), rs.getDate("dateDebut"),
							rs.getDate("dateFin"), rs.getString("contenu"));

					listInfo.add(info);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return listInfo;
	}

	/**
	 * Display the list of generic group
	 * 
	 * @return
	 */
	public List<CIS_GENER_bdpm> getGen() {
		List<CIS_GENER_bdpm> listGen = new ArrayList<CIS_GENER_bdpm>();
		CIS_GENER_bdpm gen;
		if (connect()) {
			String query = "SELECT * FROM groupe GROUP BY libelleG";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					gen = new CIS_GENER_bdpm(rs.getInt("cis"), rs.getInt("identifiantG"),
							StringEscapeUtils.escapeHtml4(rs.getString("libelleG")), rs.getInt("typeG"),
							rs.getInt("num"));
					listGen.add(gen);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listGen;
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

	/**
	 * Get list of med that are still commercialized
	 * 
	 * @return list of meds
	 */
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
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<HAS_LiensPageCT_bdpm> getListAvis(String id) {
		ArrayList<HAS_LiensPageCT_bdpm> listAvis1 = new ArrayList<HAS_LiensPageCT_bdpm>();
		HAS_LiensPageCT_bdpm avis1;
		if (connect()) {
			String query = "SELECT * FROM lienhas WHERE codeHAS =?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setString(1, StringEscapeUtils.escapeHtml4(id));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					avis1 = new HAS_LiensPageCT_bdpm(StringEscapeUtils.escapeHtml4(rs.getString("codeHAS")),
							rs.getString("lien"));
					listAvis1.add(avis1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return listAvis1;
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

	/**
	 * 
	 * @param id
	 * @return
	 */

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

	public List<CIS_HAS_SMR> getAvissmr(int id) {
		List<CIS_HAS_SMR> listavis = new ArrayList<CIS_HAS_SMR>();
		CIS_HAS_SMR avis;
		if (connect()) {
			String query = "SELECT * FROM avissmr WHERE CIS =?";
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

	public CIS_GENER_bdpm getGener(int id) {
		CIS_GENER_bdpm gen = null;
		if (connect()) {
			String query = "SELECT * FROM groupe WHERE CIS = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					gen = new CIS_GENER_bdpm(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return gen;
	}

	public void insertFile(String login, Part filePart) {
		InputStream inputStream = null;
		try {
			if (filePart != null) {
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());
				inputStream = filePart.getInputStream();
			}
			if (connect()) {
				String query = "INSERT INTO `doc`(`user`,`title`,`doc`) VALUES (?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, login);
				ps.setString(2, filePart.getName());
				if (inputStream != null) {
					ps.setBlob(3, inputStream);
				}
				ps.executeUpdate();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Modele_Sujet> getMessa() {
		List<Modele_Sujet> listMessa = new ArrayList<Modele_Sujet>();
		Modele_Sujet mess;
		if (connect()) {
			String query = "SELECT * FROM sujets ORDER BY dateMessage DESC;";
			try {
				PreparedStatement ps = conn.prepareStatement(query);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					mess = new Modele_Sujet(rs.getInt("idSujet"), StringEscapeUtils.escapeHtml4(rs.getString("pseudo")),
							StringEscapeUtils.escapeHtml4(rs.getString("adrmail")),
							StringEscapeUtils.escapeHtml4(rs.getString("messag")), rs.getDate("dateMessage"));
					listMessa.add(mess);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return listMessa;
	}

	public void ajouterReponse(Modele_Reponse reponse) {
		if (connect()) {
			try {
				String query = "INSERT INTO `reponse`(`idSujet`,`rep`,`dateReponse`) VALUES (?,?,?)";
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, reponse.getIdSujet());
					ps.setString(2, reponse.getReponse());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
					java.util.Date datefinale = null;
					try {
						datefinale = sdf.parse(reponse.getDateRepons());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ps.setObject(3, datefinale.toInstant().atZone(ZoneId.of("Europe/Paris")).toLocalDate());

					ps.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} catch (SQLException er) {
				er.printStackTrace();
			} finally {
				close();
			}
		}
	}

	public List<Modele_Reponse> getListReponseBySubject(int idSujet) {
		List<Modele_Reponse> listReponse = new ArrayList<Modele_Reponse>();
		if (connect()) {
			String query = "SELECT * FROM reponse WHERE idSujet=?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				try {
					ps.setInt(1, idSujet);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {

					listReponse.add(
							new Modele_Reponse(rs.getString("rep"), rs.getInt("idSujet"), rs.getString("dateReponse")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return listReponse;
	}

	public boolean insertArticle(String user, String title, String corps) {
		boolean result = false;
		if (connect()) {

			String query = "INSERT INTO doc (user, title, corps) VALUES(?,?,?)";
			try {

				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, user);
				ps.setString(2, title);
				ps.setString(3, corps);

				ps.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			result = true;

		}
		return result;
	}
	
	public boolean insertQuestion(String user, String mail, String corps) {
		boolean result = false;
		if (connect()) {

			String query = "INSERT INTO sujets (pseudo, adrmail, messag,dateMessage) VALUES(?,?,?,?)";
			try {
				
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, StringEscapeUtils.escapeHtml4(user));
				ps.setString(2, StringEscapeUtils.escapeHtml4(mail));
				ps.setString(3, StringEscapeUtils.escapeHtml4(corps));
				
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));

				ps.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			result = true;

		}
		return result;
	}

	public ArrayList<Article> getArticles() {
		ArrayList<Article> res = new ArrayList<Article>();
		if (connect()) {

			String query = "SELECT * FROM doc";
			try {

				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					res.add(new Article(StringEscapeUtils.escapeHtml4(rs.getString("title")),
							StringEscapeUtils.escapeHtml4(rs.getString("corps"))));
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return res;
	}

}