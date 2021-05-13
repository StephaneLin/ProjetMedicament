package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modele.*;

/**
 * Servlet implementation class MedocDataServlet
 */
@WebServlet("/MedocDataServletMedCompo")
public class MedocDataServletMedCompo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedocDataServletMedCompo() {
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
		  String url = getServletContext().getInitParameter("url");
			String username = getServletContext().getInitParameter("username");
			String password = getServletContext().getInitParameter("password");
			// on cré un objet dao puis on effectue la connection a la bdd
			DAO dao = new DAO(url, username, password);
			HttpSession session = request.getSession();
			int code= Integer.parseInt((String) session.getAttribute("codeSubstance"));
		
	
		List<Integer> listCIS = dao.getCISCompo(code);
		
		ArrayList<CIS_bdpm> listOfMedoc = new ArrayList<CIS_bdpm>(); 
		
		for(Integer cis : listCIS) {
			listOfMedoc.add(dao.getMedicament(cis));
		}

		  DataTableMedoc dataTableObject = new DataTableMedoc();
		  dataTableObject.setmedocData(listOfMedoc);
			  
		  Gson gson = new GsonBuilder().setPrettyPrinting().create();
		  String json = gson.toJson(dataTableObject);
		  out.print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
