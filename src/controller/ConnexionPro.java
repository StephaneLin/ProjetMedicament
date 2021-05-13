package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.esigelec.jee.AjoutLigne;
import modele.ConInfo;

/**
 * Servlet implementation class ConnexionPro
 */
@WebServlet("/ConnexionPro")
public class ConnexionPro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminDAO myDao = new AdminDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionPro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("username");
		String pass = request.getParameter("pass");

		String ip = InetAddress.getLocalHost().toString();

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strDate = formatter.format(date);

		if (myDao.authentify(login, pass)) {
			AjoutLigne ajoutLog = new AjoutLigne();
			ajoutLog.Ajout1Ligne(2, "connexion de l'utilisateur", login);

			HttpSession session = request.getSession();
			ConInfo ci = new ConInfo(login, ip, strDate, true);
			myDao.addConInfo(ci);
			session.setAttribute("LoginTrue", "True");
			session.removeAttribute("infoCon");
			session.setAttribute("Login", login);
			response.sendRedirect("index.jsp");
		} else {
			AjoutLigne ajoutLog = new AjoutLigne();
			ConInfo ci = new ConInfo(login, ip, strDate, false);
			myDao.addConInfo(ci);
			ajoutLog.Ajout1Ligne(4, "tentative ratée de connexion", login);
			HttpSession session = request.getSession();
			session.setAttribute("infoCon", "identifiant de connexion ou mot de passe invalide");
			response.sendRedirect("connexion.jsp");
		}
	}

}
