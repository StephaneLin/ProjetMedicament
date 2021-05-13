package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Modele_Reponse;

/**
 * Servlet implementation class RecupIdSujet
 */
@WebServlet("/RecupIdSujet")
public class RecupIdSujet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecupIdSujet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String url = getServletContext().getInitParameter("url");
		String username = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("password");
		// on cré un objet dao puis on effectue la connection a la bdd
		HttpSession session = request.getSession();
		DAO dao = new DAO(url, username, password);
		int id;
		if(session.getAttribute("id")!=null && request.getParameter("id")!=null) {
			id = Integer.parseInt(request.getParameter("id"));
		}else {
			id=(int) session.getAttribute("id");
		}
		System.out.println(id);
		session.setAttribute( "id", id);
		request.setAttribute("listeReponse",dao.getListReponseBySubject(id));
		request.getRequestDispatcher("/listeRepMessage.jsp").forward(request,response);
		
		/*
		String id = request.getParameter("id");
		System.out.println(id);
		HttpSession session = request.getSession();
		session.setAttribute( "idMessage", id);
		response.sendRedirect("listeRepMessage.jsp");
		*/
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
