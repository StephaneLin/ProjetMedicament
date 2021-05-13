<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="modele.CIS_bdpm"%>
<%@ page import="controller.DAO"%>
<%@ page import="fr.esigelec.jee.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%
if(null == session.getAttribute("LoginTrue")){
	  // User is not logged in.
	AjoutLigne ajoutLog = new AjoutLigne();
	ajoutLog.Ajout1Ligne(5, "tentative non autorisée d'accès a la page contenant la liste des médicaments appartenant à un groupe générique choisi", null);
	response.sendRedirect("index.jsp");
	}else{
	  // User IS logged in.
	  String login = (String) session.getAttribute("Login");
	
		  AjoutLigne ajoutLog = new AjoutLigne();
		  ajoutLog.Ajout1Ligne(1, "visite de la page contenant la liste des médicaments appartenant à un groupe générique choisi", login);
	  
	 
	  
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

<!--titre de la page-->
<title>Présentation des médicaments</title>

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
<body class="text-center">
	<%
		String url = getServletContext().getInitParameter("url");
		String username = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("password");
		// on crée un objet dao puis on effectue la connection a la bdd
		DAO dao = new DAO(url, username, password);
		//on récupère la variable envoyé
		int cis = Integer.parseInt(request.getParameter("cis"));
		//on crée nos variables
		CIS_bdpm med;
		List<CIS_bdpm> listGrMed = new ArrayList<CIS_bdpm>();
		String lib;
		List<Integer> listCIS = new ArrayList<Integer>();

		lib = dao.getGenLib(cis);
		listCIS = dao.getGenCis(lib);
		List<CIS_bdpm> listGrMeds = new ArrayList<CIS_bdpm>();
		for (int i = 0; i < listCIS.size(); i++) {
			listGrMeds.add(dao.getGrMeds(listCIS.get(i)));
		}
	%>
	<div class="cover-container d-flex h-100 p-3 mx-auto flex-column  ">
		<h1 class="h2">Liste des Medicaments d'un groupe générique</h1>
		<main >
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom"></div>

			<div class="table-responsive">
				<table id="listGen" class="table table-bordered table-dark">
					<thead>
						<tr>
							<th>Denomination</th>
							<th>Forme pharmaceutique</th>
							<th>Voix d'administration</th>
							<th>Titulaire</th>
							<th>Consulter les présentation(s) et composition(s) d'un médicament</th>
						</tr>
					</thead>
					<tbody>
						<%
						try{
							for (int i = 0; i < listGrMeds.size(); i++) {
						%>
						<tr>
							<td><%=listGrMeds.get(i).getDenominationMedicament()%></td>
							<td><%=listGrMeds.get(i).getFormePharmaceutique()%></td>
							<td><%=listGrMeds.get(i).getVoieAdministration()%></td>
							<td><%=listGrMeds.get(i).getTitulaire()%></td>
							<td><%=listGrMeds.get(i).getLink()%></td>
						</tr>
						<%
							}
						}catch(NullPointerException e){
							e.printStackTrace();
						}
						%>
					</tbody>
				</table>

			</div>
		</main>
	</div>
	<footer class="mastfoot mt-auto">
		<div class="inner">
			<p class="font-italic" style="color: mediumblue">
				<a>@Copyright</a> by <a>Zekinatou Stephane Khalil Jean-Joseph
					Samuel</a>

</body>
</html>
