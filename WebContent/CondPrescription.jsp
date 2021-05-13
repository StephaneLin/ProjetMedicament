<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modele.*"%>
<%@ page import="controller.*"%>
<%@ page import="java.util.List"%>
<%@ page import="fr.esigelec.jee.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Produit</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<header>
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="AVISASMR" />
	</jsp:include>
</header>
<%
if(null == session.getAttribute("LoginTrue")){
	  // User is not logged in.
	AjoutLigne ajoutLog = new AjoutLigne();
	ajoutLog.Ajout1Ligne(5, "tentative non autorisée d'accès a la page des avis de prescription et de délivrance d'un médicament", null);
	response.sendRedirect("index.jsp");
	}else{
	  // User IS logged in.
	  String login = (String) session.getAttribute("Login");
	
		  AjoutLigne ajoutLog = new AjoutLigne();
		  ajoutLog.Ajout1Ligne(1, "visite de la page des avis de prescription et de délivrance d'un médicament ", login);
	  
	 
	  
	}
%>
<body class="text-center">

	<%
		int CIS = Integer.parseInt(request.getParameter("cis"));

		//liste des presentation
		String url = getServletContext().getInitParameter("url");
		String username = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("password");
		// on cré un objet dao puis on effectue la connection a la bdd
		DAO dao = new DAO(url, username, password);

		List<CIS_CPD_bdpm> listePresentation = dao.getListCondition(CIS);
	%>

	<div class="cover-container d-flex h-100 p-3 mx-auto flex-column  ">

		&nbsp; &nbsp;
		<h1>Condition de prescription et de délivrance associées à un
			médicament :</h1>
		<table class="table table-bordered table-dark">
			<thead>
				<tr>
					<th scope="col">Condition de prescription/délivrance</th>

				</tr>
			</thead>
			<tbody>
				<%
					for (CIS_CPD_bdpm compo : listePresentation) {
				%>
				<tr>
					<td><%=compo.getCode_cis()%></td>

				</tr>
				<%
					}
					if (listePresentation.size() == 0) {
				%>
				<tr>
					<td>/</td>
					<td>Aucun lien n'est disponible</td>
				</tr>
				<%
					}
				%>

			</tbody>
		</table>


		<footer class="mastfoot mt-auto">
			<div class="inner">
				<p class="font-italic" style="color: mediumblue">
					<a>@Copyright</a> by <a>Zekinatous Stephane Khalil Jean-Joseph
						Samuel</a>
				</p>
			</div>
		</footer>
	</div>


	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>
