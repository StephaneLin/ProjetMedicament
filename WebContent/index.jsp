<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.jee.*"%>
<%@ page import="modele.*"%>
<%@ page import="controller.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Accueil</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/cover.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body class="text-center">
	<%
		String login = (String) session.getAttribute("LoginTrue");
	%>

	<div class="maincontener">

		<header class="masthead mb-auto">
			<div class="inner">
				<h3 class="masthead-brand">S8</h3>

				<ul class="nav justify-content-end">
					<li class="nav-item drop"><a class="nav-link dropdown-toggle"
						data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
						aria-expanded="false">MEDICAMENT</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="ComPage.jsp">Consulter la
								liste des médicaments commercialisés</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="NonComPage.jsp"> Consulter la
								liste des médicaments en arrêt de commercialisation</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="CompoPage.jsp"> Consulter la
								liste des médicaments par principe actif</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="PoserQuestion.jsp"> Poser une question aux professionnels</a>
							<%
								if (null != login) {
							%><div class="dropdown-divider"></div>
							<a class="dropdown-item" href="GroupePage.jsp"> Consulter la
								liste des groupes génériques</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="InfoAvisASMR.jsp"> Consulter
								les avis ASMR de la HAS</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="InfoAvisSMR.jsp"> Consulter
								les avis SMR de la HAS</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="infoMed.jsp"> Consulter la
								liste des informations des médicaments</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="AvisComi.jsp"> Consulter les
								avis de la commission ASMR et SMR</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="PageCondPrescription.jsp">
								Consulter les conditions de prescription et de delivrance
								assossié à un medicament </a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="Statistiques.jsp"> Consulter
									les statistiques </a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="Sujet.jsp"> Consulter la liste
								des messages</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="UploadPage.jsp"> Créer un
								nouvel article </a>
							<%
								}
							%>
						</div></li>
					<%
						if (null != login) {
					%>

					<li class="nav-item drop"><a class="nav-link dropdown-toggle"
						data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
						aria-expanded="false">MON COMPTE </a>
						<div class="dropdown-menu">
							<a class="dropdown-item"
								href="/Medicament2_G3/HistoriqueProServlet"> MON HISTORIQUE</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="logout"> DECONNEXION </a>
						</div></li>
					<%
						} else {
					%>
					<li class="nav-item"><a class="nav-link" href="connexion.jsp">CONNEXION</a>

					</li>
					<%
						}
					%>

					<li class="nav-item"><a class="nav-link" href="faq.jsp">FAQ</a>
				</ul>
			</div>
		</header>

		<%
			if (null == session.getAttribute("LoginTrue")) {
				// User is not logged in.
				AjoutLigne ajoutLog = new AjoutLigne();
				ajoutLog.Ajout1Ligne(1, "visite de la page d'accueil", "patient non identifié");
			} else {
				// User IS logged in.
				String login2 = (String) session.getAttribute("Login");
				AjoutLigne ajoutLog = new AjoutLigne();
				ajoutLog.Ajout1Ligne(1, "visite de la page d'accueil", login2);

			}
		%>
		<main role="main" class="inner cover" id="titrePrinc">
			<h2 class="cover-heading">Référencement de médicament.</h2>
			<h3>BIENVENUE!</h3>
			<h4 class="font-weight-normal">
				<strong> Vous retrouverez sur ce site web l'ensemble des
					médicaments qui sont commercialisés et qui ont été commercialisés
					en France </strong>
			</h4>
		</main>
		<div class="divscroll" id="style-2">
			<%
				String url = getServletContext().getInitParameter("url");
				String username = getServletContext().getInitParameter("username");
				String password = getServletContext().getInitParameter("password");
				// on cré un objet dao puis on effectue la connection a la bdd
				DAO dao = new DAO(url, username, password);

				ArrayList<Article> listeArticles = dao.getArticles();

				for (Article article : listeArticles) {
					//pour convertir les saut de ligne java en saut de ligne html
					String str = article.getCorps();
					str = str.replaceAll("(\r\n|\n)", "<br>");
			%>
			<div class="fond">
				<h4>
					<%=article.getTitle()%></h4>
				<hr class="hrArticle">
				<div class="corpsArticle">
					<%=str%>
				</div>
			</div>
			<%
				}
			%>
		</div>
		<footer class="mastfoot mt-auto">
			<div class="inner">
				<p class="font-italic" style="color: mediumblue">
					<a>@Copyright byZekinatou Stephane Khalil Jean-JosephSamuel</a>
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
