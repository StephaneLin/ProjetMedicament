<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.esigelec.jee.*"%>
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

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->


<link href="css/datatables.min.css" rel="stylesheet">
<link href="css/datatablemodif.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap4.min.js"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>


</head>
<header>
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="Upload" />
	</jsp:include>
</header>
<body >
<%
if(null == session.getAttribute("LoginTrue")){
	  // User is not logged in.
	AjoutLigne ajoutLog = new AjoutLigne();
	ajoutLog.Ajout1Ligne(1, "tentative de la page de création de question", null);
	}else{
	  // User IS logged in.
	  String login = (String) session.getAttribute("Login");
	
		  AjoutLigne ajoutLog = new AjoutLigne();
		  ajoutLog.Ajout1Ligne(1, "visite de la page de création de question", login);
	  
	 
	  
	}
%>
	<!-- Section: form gradient -->
<section class="form-gradient mb-5">

  <!--Form with header-->
  <div class="card">

    <!--Header-->
    <div class="header peach-gradient">

      <div class="row d-flex justify-content-center">
        <h3 class="white-text mb-0 py-5 font-weight-bold">Création d'une Question</h3>
      </div>

    </div>
    <!--Header-->

    <div class="card-body mx-4">
<form action="UploadQuestionServlet" method="POST">
      <div class="md-form">
       <% 
       String questionupload = (String) session.getAttribute("questionupload");
       if( ("" != questionupload) && (null != questionupload) ){ %>
       		<% if(questionupload == "fail"){ %>
					<div class="alert alert-danger" role="alert">
  						Echec de l'upload de la question.
					</div>
			<% }else{ %>
					<div class="alert alert-success" role="alert">
  						Réussite de l'upload de la question.
					</div>
			<% } %>
		
		<%
		session.setAttribute("questionupload", "");
		
       } %>
		
		<input name="pseudo" type="text" id="form104" class="form-control" placeholder="Veuillez rentrer un pseudo" autofocus required>
        			<br><br>
        <input name="mail" type="email" id="form104" class="form-control" placeholder="Veuillez rentrer une adresse mail pour recevoir par mail les retours" required>
        
      </div>
	<br><br>
	
      <div class="md-form">
        
        <textarea name="corps" id="form107" class="md-textarea form-control" rows="20" placeholder="Développer votre question..." required ></textarea>
        
      </div>


      <!--Grid row-->
      <div class="row d-flex align-items-center mb-3 mt-4">

        <!--Grid column-->
        <div class="col-md-12">
          <div class="text-center">
            <button type="submit" class="btn btn-grey btn-rounded z-depth-1a">Envoyer la question</button>
          </div>
        </div>
        <!--Grid column-->

      </div>
      </form>
      <!--Grid row-->
    </div>

  </div>
  <!--/Form with header-->

</section>
		
		
		
		<footer class="mastfoot mt-auto text-center" >
			<div class="inner">
				<p class="font-italic" style="color: mediumblue">
					<a>@Copyright by Zekinatou Stephane Khalil Jean-Joseph Samuel</a>
				</p>
			</div>
		</footer>

</body>

</html>