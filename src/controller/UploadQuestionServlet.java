package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadQuestionServlet")
public class UploadQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = getServletContext().getInitParameter("url");
		String username = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("password");
		// on cré un objet dao puis on effectue la connection a la bdd
		DAO dao = new DAO(url, username, password);
		
		String pseudo = request.getParameter("pseudo");
		String title = request.getParameter("mail");
		String corps = request.getParameter("corps");
		
		
		boolean reussite = dao.insertQuestion(pseudo, title, corps);
		HttpSession session = request.getSession();

		if(reussite == true) {
			session.setAttribute("questionupload", "success");
			
		}else {
			session.setAttribute("questionupload", "fail");
		}
		response.sendRedirect("PoserQuestion.jsp");
		
	}

}
