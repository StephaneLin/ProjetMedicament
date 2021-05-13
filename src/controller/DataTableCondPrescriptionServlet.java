package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modele.CIS_bdpm;
import modele.DataTableMedoc;

/**
 * Servlet implementation class DataTableCondPrescriptionServlet
 */
@WebServlet("/DataTableCondPrescriptionServlet")
public class DataTableCondPrescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataTableCondPrescriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 System.out.println("Textavant");
		response.setContentType("application/json");
		  PrintWriter out = response.getWriter();
		  String url = getServletContext().getInitParameter("url");
			String username = getServletContext().getInitParameter("username");
			String password = getServletContext().getInitParameter("password");
			// on cré un objet dao puis on effectue la connection a la bdd
			DAO dao = new DAO(url, username, password);

		  List<CIS_bdpm> listOfMedoc = dao.getAllMedicaments();
		   
		  DataTableMedoc dataTableObject = new DataTableMedoc();
		  dataTableObject.setmedocData(listOfMedoc);
			  
		  Gson gson = new GsonBuilder().setPrettyPrinting().create();
		  String json = gson.toJson(dataTableObject);
		  out.print(json);
		  System.out.println("Text"+json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
