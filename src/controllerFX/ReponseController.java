package controllerFX;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.DAO;
import modele.*;

/**
 * Servlet implementation class MedocDataServlet
 */
@WebServlet("/ReponseController")
public class ReponseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String post;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReponseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("application/json");
		  PrintWriter out = response.getWriter();
		  
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
        String url = getServletContext().getInitParameter("url");
		String username = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("password");
		// on cré un objet dao puis on effectue la connection a la bdd
		DAO dao = new DAO(url, username, password);
		HttpSession session = request.getSession();

		int IdMessage = Integer.parseInt((String)session.getAttribute("idMessage"));
		
		if(post == "vrai") {
			System.out.println("new message to add :(valeur expecte a la suite):  "+request.getParameter("Commentaire"));
		}
		
		if(null != request.getParameter("Commentaire")) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
			Modele_Reponse reponse = new Modele_Reponse(request.getParameter("Commentaire"),IdMessage,new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
			dao.ajouterReponse(reponse);
			post="";
			response.sendRedirect("listeRepMessage.jsp?id="+IdMessage);
		}
		List<Modele_Reponse> listemessages = dao.getListReponseBySubject(IdMessage);
		
		 DataTableRep dataTableObject = new DataTableRep();
		 dataTableObject.setRepData(listemessages);
			  
		  Gson gson = new GsonBuilder().setPrettyPrinting().create();  
		  String json = gson.toJson(dataTableObject);
		  out.print(json);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		post="vrai";
		doGet(request,response);
	}

}
