<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="modele.CIS_bdpm"%>
<%@ page import="controller.DAO"%>
<%@ page import="fr.esigelec.jee.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">
<!--titre de la page-->
<title>Liste des principes actifs</title>

<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">

<link href="css/datatables.min.css" rel="stylesheet">
<link href="css/datatablemodif.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap4.min.js"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script type="text/javascript" src="js/liste.js"></script>


</head>
<header> <jsp:include page="header.jsp" >
            <jsp:param name="title" value="AVISASMR"/>
        </jsp:include> 
  </header>
   <%
if(null == session.getAttribute("LoginTrue")){
	  // User is not logged in.
	AjoutLigne ajoutLog = new AjoutLigne();
	ajoutLog.Ajout1Ligne(1, "visite de la page contenant la liste des substance actives et un lien vers les médicaments la contenant", "patient non identifié");
	}else{
	  // User IS logged in.
	  String login = (String) session.getAttribute("Login");
	
		  AjoutLigne ajoutLog = new AjoutLigne();
		  ajoutLog.Ajout1Ligne(1, "visite de la page contenant la liste des substance actives et un lien vers les médicaments la contenant", login);
	  
	 
	  
	}
%>
<body class="text-center">

		<h1 class="h2">Liste des Principes actifs </h1>
		<main role="main" >
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom"></div>

			<div class="table-responsive">
				<table id="listeCompo" class="table table-striped table-bordered table-dark" style="width:100%">
					<thead>
						<tr>
							<th>Denomination substance</th>
							<th>Nature de la substance</th>
							<th>Consulter la liste des médicaments contenant cette substance</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
						<tr>
							<th>Denomination substance</th>
							<th>Nature de la substance</th>
							<th>Consulter la liste des médicaments contenant cette substance</th>
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
