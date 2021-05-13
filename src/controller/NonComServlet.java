package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.DAO;
import modele.*;

/**
 * Servlet implementation class Recherche
 */
@WebServlet(name ="/NonComServlet",
urlPatterns= {"/NonComServlet"})
public class NonComServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public NonComServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = getServletContext().getInitParameter("url");
		String username = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("password");
		// on cré un objet dao puis on effectue la connection a la bdd
		DAO dao = new DAO(url, username, password);
		List<CIS_bdpm> listMed = new ArrayList<CIS_bdpm>();
		listMed = dao.getListMedLess();
		
		request.setAttribute("listeMed", listMed);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/NonComPage.jsp");
		rd.forward(request, response);
		
		response.sendRedirect("NonComPage.jsp");

	}

}
