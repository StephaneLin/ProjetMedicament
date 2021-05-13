<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.jee.*"%>
<!DOCTYPE html>
<html>

<head>
 <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script>document.getElementsByTagName("html")[0].className += " js";</script>
  <link rel="stylesheet" href="assets/css/style.css">
  <title>FAQ </title>
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
            <jsp:param name="title" value="Statistiques"/>
        </jsp:include> 
  </header>
<body>
  <%
if(null == session.getAttribute("LoginTrue")){
	  // User is not logged in.
	AjoutLigne ajoutLog = new AjoutLigne();
	ajoutLog.Ajout1Ligne(1, "visite de la page faq", "patient non identifié");
	}else{
	  // User IS logged in.
	  String login = (String) session.getAttribute("Login");
	
		  AjoutLigne ajoutLog = new AjoutLigne();
		  ajoutLog.Ajout1Ligne(1, "visite de la page faq", login);

	}
%>

<section class="cd-faq js-cd-faq container max-width-md margin-top-lg margin-bottom-lg">
	<ul class="cd-faq__categories">
		<li><a class="cd-faq__category cd-faq__category-selected truncate" href="#basics"> Général</a></li>
		
		<li><a class="cd-faq__category truncate" href="#account">Informations</a></li>
		
	</ul>

	<div class="cd-faq__items">
		<ul id="basics" class="cd-faq__group">
			<li class="cd-faq__title"><h2>Général</h2></li>
			<li class="cd-faq__item">
				<a class="cd-faq__trigger" href="#0"><span>Qu'est ce qu'un référencement de médicament ?</span></a>
				<div class="cd-faq__content">
          <div class="text-component">
            <p>L’ESIGELEC a été sollicitée pour développer une application permettant de faciliter l’accès à un maximum d’informations relatives aux médicaments vendus en France.
				Cette application pourra être utilisée à la fois par des professionnels de santé (médecins, pharmaciens, laboratoires pharmaceutiques, étudiants en médecine / pharmacie) ainsi que par les malades.
				</p>
          </div>
				</div> 
			</li>

			
		<ul id="mobile" class="cd-faq__group">
			<li class="cd-faq__title"><h2>Informations</h2></li>
			<li class="cd-faq__item">
				<a class="cd-faq__trigger" href="#0"><span>D'où proviennent les informations de ce site?</span></a>
				<div class="cd-faq__content">
          <div class="text-component">
            <p>Les informations du site proviennent du site du gouvernement.</p>
          </div>
				</div> 
			</li>


			<li class="cd-faq__item">
				<a class="cd-faq__trigger" href="#0"><span>Pourquoi ce site a été créé?</span></a>
				<div class="cd-faq__content">
          <div class="text-component">
            <p>Ce site a été créé pour faciliter l’accès à un maximum d’informations relatives aux médicaments vendus en France.</p>
          </div>
				</div> 
			</li>

			<li class="cd-faq__item">
				<a class="cd-faq__trigger" href="#0"><span>A qui est destiné ce site ?</span></a>
				<div class="cd-faq__content">
          <div class="text-component">
            <p> L’application est utilisé par  : <br>
			Des médecins <br>
			 Des pharmaciens <br>
			 Des laboratoires pharmaceutiques <br>
			 Des étudiants en médecine / pharmacie <br>			
	         Les malades
				</p>
          </div>
				</div> 
			</li>

			<li class="cd-faq__item">
				<a class="cd-faq__trigger" href="#0"><span>Qui peut répondre au forum?</span></a>
				<div class="cd-faq__content">
          <div class="text-component">
            <p>Malheureusement, seuls les professionnels de santé sont habilités à répondre au  forum </p>
          </div>
				</div> 
			</li>

			<li class="cd-faq__item">
				<a class="cd-faq__trigger" href="#0"><span>A qui dois-je m'adresser en cas de problème sur le site</span></a>
				<div class="cd-faq__content">
          <div class="text-component">
            <p> Si vous rencontrez un problème sur le site merci de bien vouloir envoyer un mail à l'adresse suivante: zekinathamoussa@gmail.com</p>
          </div>
				</div> 
			</li>
		</ul> 

	<a href="#0" class="cd-faq__close-panel text-replace">Close</a>
  
  <div class="cd-faq__overlay" aria-hidden="true"></div>
</section>
<div class="card-footer text-muted">
    <div class="inner">
				<p class="font-italic" style="color: mediumblue">
					<a>@Copyright by Zekinatou Stephane Khalil Jean-Joseph Samuel</a>
				</p>
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
		
<script src="assets/js/util.js"></script> 
<script src="assets/js/main.js"></script> 
</body>
</html>