<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- Directive chargeant le bean de l'utilisateur en session -->
<jsp:useBean id="joueur" class="stage.devops.beans.Joueur"
	scope="session" />


<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body>
	<div class="header">
		<h1>DevMode</h1>
	</div>
	<div class="userbox">
		<h1>Connecté en tant que : ${joueur.login} (${joueur.profil})</h1>
	</div>
	<div class="menu">
		<p>
		<form method="post" action="ajoutami">
			<label for="ajoutami"> Ajouter un ami : <br /> <input
				type="text" name="ajoutami" id="ajoutami" />
			</label><input type="submit" value="Valider" />
		</form>
			<br>
			<br>
			score : ${joueur.score}
			<br>
			${msg}<br>
		</p>
	</div>
	<div class="content">
	<form method="post" action="repondrea">
			<input type="submit" value="Repondre a ->" />
		</form>
		<form method="post" action="ladder">
			<input type="submit" value="Ladder" />
		</form>
        	<h1>Dessiner pour :</h1><br>
        	<c:forEach items="${amiList}" var="a">
        		<form method="post" action="dessinerpourami?ami=${a.login}">
        			<h4><c:out value="${a.login} :"></c:out> </h4> <input type="submit" name="valider" value="Ouvrir"/><br>
        		</form>
        	</c:forEach>
	</div>
</body>
</html>
