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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modele.CIS_bdpm;
import modele.DataTableGenMed;

/**
 * Servlet implementation class GroupeServlet2
 */
@WebServlet("/GroupeServlet2")
public class GroupeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupeServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String url = getServletContext().getInitParameter("url");
		String username = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("password");
		// on cré un objet dao puis on effectue la connection a la bdd
		DAO dao = new DAO(url, username, password);
		
		int cis = Integer.parseInt(request.getParameter("cis"));
		
		String lib;
		List<Integer> listCIS = new ArrayList<Integer>();
		
		lib = dao.getGenLib(cis);

		listCIS = dao.getGenCis(lib);
		List<CIS_bdpm> listGrMeds = new ArrayList<CIS_bdpm>();
		
		for (int i = 0; i < listCIS.size(); i++) {
			listGrMeds.add(dao.getGrMeds(listCIS.get(i)));
		}
		
		DataTableGenMed dataTableObject = new DataTableGenMed();
		dataTableObject.setGenData(listGrMeds);
		System.out.println(listGrMeds);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		out.print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
