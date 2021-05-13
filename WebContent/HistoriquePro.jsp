<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="fr.esigelec.jee.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%
if(null == session.getAttribute("LoginTrue")){
	  // User is not logged in.
	AjoutLigne ajoutLog = new AjoutLigne();
	ajoutLog.Ajout1Ligne(5, "tentative non autorisée d'accès a la page Historique", null);
	response.sendRedirect("index.jsp");
	}

%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Historique</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<header> <jsp:include page="header.jsp" >
            <jsp:param name="title" value="Historique"/>
        </jsp:include> 
  </header>
<body>
<div class="card text-center">
  <div class="card-header">
    Historiques
  </div>
  <div class="card-body">
    <h5 class="card-title">Tous les historiques</h5>
    
    
    
    
   
    <c:forEach items="${listInfos }" var="logInfo">
    <p class="card-text">
  		<b>Action éffectuée : </b> <c:out value="${logInfo.libele }"></c:out>  
  		<b> Date : </b>  <c:out value="${logInfo.date }"></c:out>
  		<b> Login : </b>  <c:out value="${logInfo.login }"></c:out> 
  		
    </p>
    </c:forEach>
    
    
    <a href="index.jsp" class="btn btn-primary">Retour</a>
  </div>
  
  
  
  
  
  
  
  <div class="card-footer text-muted">
    <div class="inner">
				<p class="font-italic" style="color: mediumblue">
					<a>@Copyright by Zekinatou Stephane Khalil Jean-Joseph Samuel</a>
				</p>
			</div>
  </div>
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