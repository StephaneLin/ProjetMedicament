<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>header</title>
</head>


<div class="cover-container d-flex h-100 p-3 mx-auto flex-column  ">
	<nav class="navbar navbar-dark bg-dark">
		<%
			String login = (String) session.getAttribute("LoginTrue");
		%>

		<div class="cover-container d-flex h-100 p-3 mx-auto flex-column  ">

			<header class="masthead mb-auto">
				<div class="inner">
					<ul class="nav justify-content-end">
						<li class="nav-item"><a class="nav-link" href="index.jsp">ACCUEIL</a>
						</li>
						</li>
						<li class="nav-item drop"><a class="nav-link dropdown-toggle"
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="true" aria-expanded="false">MEDICAMENT</a>
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
								%>
								<div class="dropdown-divider"></div>
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
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="true" aria-expanded="false">MON COMPTE </a>
							<div class="dropdown-menu">
								<a class="dropdown-item"
									href="/Medicament2_G3/HistoriqueProServlet"> MON HISTORIQUE</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="logout"> DECONNEXION </a>
							</div></li>
						<%
							} else {
						%>
						<li class="nav-item"><a class="nav-link" href="connexion.jsp">CONNEXION</a></li>
						<%
							}
						%>

						<li class="nav-item"><a class="nav-link" href="faq.jsp">FAQ</a>
					</ul>
				</div>
			</header>
		</div>
	</nav>
</div>
</html>