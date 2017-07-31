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
		
	</div>
	<div class="content">
	<form method="post" action="dessinerPour">
			<input type="submit" value="Retour" />
		</form>
        	<h1>Ladder :</h1><br>
        	<c:set var="count" value="1" />
        	<c:forEach items="${list}" var="l" step="1">
				<c:out value="${count}- ${l.login} | Score : ${l.score}"></c:out><br>
				<c:set var="count" value="${count+1}" />
        	</c:forEach>
	</div>
</body>
</html>
