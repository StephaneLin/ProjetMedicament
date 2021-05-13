<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.esigelec.jee.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<title>Connexion</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link href="css/cover.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<%
	String infoCon="";
	if (null == session.getAttribute("infoCon")) {
		
	}else{
		infoCon = (String) session.getAttribute("infoCon");
		session.setAttribute("infoCon", "");
	}

	if (null == session.getAttribute("LoginTrue")) {
		// User is not logged in.
		AjoutLigne ajoutLog = new AjoutLigne();
		ajoutLog.Ajout1Ligne(1, "visite de la page de connexion", "patient non identifié");
	} else {
		// User IS logged in.
		String login = (String) session.getAttribute("Login");

		AjoutLigne ajoutLog = new AjoutLigne();
		ajoutLog.Ajout1Ligne(1, "visite de la page de connexion", login);

	}
%>
<body>
	<div class="cover-container d-flex h-100 p-3 mx-auto flex-column  ">
		<div class="limiter">

			<nav class="navbar navbar-dark bg-dark"></nav>
			<div class="container-login100">
				<div class="wrap-login100">
					<div class="login100-form-title"
						style="background-image: url(image/bg-01.jpg);">
						<span class="login100-form-title-1"> CONNEXION </span>
					</div>
					<% if(infoCon != ""){ %>
					<div class="alert alert-danger" role="alert">
  						<%= infoCon %>
					</div>
					<% } %>
					<form action="ConnexionPro" method="post"
						class="login100-form validate-form">
						<div class="wrap-input100 validate-input m-b-26"
							data-validate="Username is required">
							<span class="label-input100">Utilisateur</span> <input
								class="input100" type="text" name="username"
								placeholder="Enter username"> <span
								class="focus-input100"></span>
						</div>

						<div class="wrap-input100 validate-input m-b-18"
							data-validate="Password is required">
							<span class="label-input100">Mot de passe</span> <input
								class="input100" type="password" name="pass"
								placeholder="Enter password"> <span
								class="focus-input100"></span>
						</div>
						&nbsp;

						<div class="container-login100-form-btn">
							<button class="login100-form-btn" class="btn btn-primary">
								Valider</button>
						</div>
					</form>
					<center>
						<a href="index.jsp" class="btn btn-secondary btn-lg active"
							role="button" aria-pressed="true"> Retour sur la page
							d'accueil</a>
					</center>
					&nbsp;
				</div>
			</div>
		</div>
		<center>
			<footer class="mastfoot mt-auto">
				<div class="inner">
					<p class="font-italic" style="color: mediumblue">
						<a>@Copyright</a> by <a>Zekinatou Stephane Khalil Jean-Joseph
							Samuel</a>
					</p>
				</div>
			</footer>
		</center>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')
</script>
<script src="../../assets/js/vendor/popper.min.js"></script>
<script src="../../dist/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
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