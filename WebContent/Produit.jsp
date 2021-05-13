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
<body class="text-center">
	<%
		if (null == session.getAttribute("LoginTrue")) {
			// User is not logged in.
			AjoutLigne ajoutLog = new AjoutLigne();
			ajoutLog.Ajout1Ligne(1, "visite de la page des compositions et présentations d'un médicament",
					"patient non identifié");
		} else {
			// User IS logged in.
			String login = (String) session.getAttribute("Login");

			AjoutLigne ajoutLog = new AjoutLigne();
			ajoutLog.Ajout1Ligne(1, "visite de la page des compositions et présentations d'un médicament", login);

		}
	%>
	<%
		int CIS = Integer.parseInt(request.getParameter("cis"));

		//liste des presentation
		String url = getServletContext().getInitParameter("url");
		String username = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("password");
		// on cré un objet dao puis on effectue la connection a la bdd
		DAO dao = new DAO(url, username, password);

		List<CIS_CIP> listePresentation = dao.getPresentation(CIS);

		List<CIS_COMPO_bdpm> listeCompo = dao.getCompo(CIS);

		CIS_GENER_bdpm generique = dao.getGener(CIS);
	%>

	&nbsp; &nbsp;
	<h1>Liste des compositions disponibles pour ce médicament :</h1>
	<table class="table table-bordered table-dark">
		<thead>
			<tr>
				<th scope="col">Désignation de l'élément pharmaceutique</th>
				<th scope="col">Code de la substance</th>
				<th scope="col">Type de substance</th>
				<th scope="col">Nature du composant</th>
				<th scope="col">Dosage</th>
				<th scope="col">Numéro de liaison</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (CIS_COMPO_bdpm compo : listeCompo) {
			%>
			<tr>
				<td><%=compo.getDenominationSubstance()%></td>
				<td><%=compo.getCodeSubstance()%></td>
				<td><%=compo.getDesignationElementPharmaceutique()%></td>
				<td><%=compo.getNatureComposant()%></td>
				<td><%=compo.getDosageSubstance()%> pour <%=compo.getReferenceDosage()%>
				</td>
				<td><%=compo.getNumeroLiaison()%></td>
			</tr>
			<%
				}
				if (listeCompo.size() == 0) {
			%>
			<tr>
				<td>Aucune composition n'est disponible</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<h1>Liste des présentations disponibles pour ce médicament :</h1>
	<table class="table table-bordered table-dark">
		<thead>
			<tr>
				<th scope="col">nom de la présentation</th>
				<th scope="col">Gamme de prix (euros)</th>
				<th scope="col">taux de remboursement</th>
				<th scope="col">Agrément aux collectivités</th>
				<th scope="col">Indication pour remboursement</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (CIS_CIP pres : listePresentation) {
			%>
			<tr>
				<td><%=pres.getPresentation()%></td>
				<td><%=pres.getPrix()%></td>
				<td><%=pres.getTaux()%></td>
				<td><%=pres.getAgrément()%></td>
				<td><%=pres.getIndication()%></td>
			</tr>
			<%
				}
				if (listePresentation.size() == 0) {
			%>
			<tr>
				<td>Aucune présentation n'est disponible</td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>

	<h1>Groupe Générique:</h1>
	<table class="table table-bordered table-dark">
		<thead>
			<tr>
				<th scope="col">Libelle</th>
			</tr>
		</thead>
		<tbody>
			<%
				try {
			%>
			<tr>
				<td><%=generique.getLibelleGroupeGenerique()%></td>
			</tr>
			<%
				} catch (NullPointerException e) {
			%>
			<tr>
				<td>n'appartient à aucun groupe générique enregistré</td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>

	<footer class="mastfoot mt-auto">
		<div class="inner">
			<p class="font-italic" style="color: mediumblue">
				<a>@Copyright</a> by <a>Zekinatou Stephane Khalil Jean-Joseph
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
