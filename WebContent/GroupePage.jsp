<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="modele.CIS_GENER_bdpm"%>
<%@ page import="controller.DAO"%>
<%@ page import="fr.esigelec.jee.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%
	if (null == session.getAttribute("LoginTrue")) {
		// User is not logged in.
		AjoutLigne ajoutLog = new AjoutLigne();
		ajoutLog.Ajout1Ligne(5,
				"tentative non autorisée d'accès a la page affichant la liste des groupes génériques", null);
		response.sendRedirect("index.jsp");
	} else {
		// User IS logged in.
		String login = (String) session.getAttribute("Login");

		AjoutLigne ajoutLog = new AjoutLigne();
		ajoutLog.Ajout1Ligne(1, "visite de la page affichant la liste des groupes génériques", login);

	}
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<!--titre de la page-->
<title>Présentation des médicaments</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">
<link href="css/datatables.min.css" rel="stylesheet">
<link href="css/datatablemodif.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap4.min.js"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="js/liste.js"></script>
</head>
<header>
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="AVISASMR" />
	</jsp:include>
</header>
<body class="text-center">
	<div class="cover-container d-flex h-100 p-3 mx-auto flex-column  ">
		<h1 class="h2">Liste des Groupes génériques</h1>
		<main role="main">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom"></div>

			<div class="table-responsive">
				<table id="listeGen" class="table table-bordered table-dark">
					<thead>
						<tr>
							<th>Libellé</th>
							<th>Afficher les médicaments de ce groupe</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
						<tr>
							<th>Libellé</th>
							<th>Afficher les médicaments de ce groupe</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</main>
	</div>
	<footer class="mastfoot mt-auto">
		<div class="inner">
			<p class="font-italic" style="color: mediumblue">
				<a>@Copyright</a> by <a>Zekinatou Stephane Khalil Jean-Joseph
					Samuel</a>
			</p>
		</div>
	</footer>
</body>
</html>
