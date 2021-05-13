<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modele.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Statistiques </title>
    <link href="css/bootstrap.min.css" rel="stylesheet"> 

  </head>
    <header> <jsp:include page="header.jsp" >
            <jsp:param name="title" value="Statistiques"/>
        </jsp:include> 
  </header>
  <body class="text-center">
  <h1 class="h2">Les Statistiques</h1>
 <iframe width="1500" height="900" src="https://app.powerbi.com/reportEmbed?reportId=984e6f68-69dc-400b-b7db-6c8a7344cc93&autoAuth=true&ctid=371cb156-9558-4286-a3cd-3059699b890c&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly93YWJpLWV1cm9wZS1ub3J0aC1iLXJlZGlyZWN0LmFuYWx5c2lzLndpbmRvd3MubmV0LyJ9" frameborder="0" allowFullScreen="true"></iframe>
  <footer class="mastfoot mt-auto">
			<div class="inner">
				<p class="font-italic" style="color: mediumblue">
					<a>@Copyright byZekinatou Stephane Khalil Jean-JosephSamuel</a>
				</p>
			</div>
		</footer>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script>
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>