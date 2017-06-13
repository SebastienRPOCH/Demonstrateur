<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- Directive chargeant le bean de l'utilisateur en session -->
<jsp:useBean id="utilisateur" class="fr.istic.gla.tp.beans.Utilisateur" scope="session"/>


<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
    </head>
    <body>
        <div class="header">
            <h1>Forum de discussion TP GLA</h1>
        </div>
        <div class="userbox">
            <h1>Connecté en tant que : ${utilisateur.login} (${utilisateur.profil})</h1>
        </div>
        <div class="menu">
        	<p>
        		<a href="accueil">Accueil</a><br>
	            <a href="auth">Connexion</a><br>
	            <a href="deconnexion">Déconnexion</a><br>
	            <a href="createFil">Créer un fil</a><br>
            </p>
        </div>
        <div class="content">
        	<form method="post" action="accueil">
				<br /> <input type="submit" name="retour" value="Retour"/>
        	</form>
        	<h1>Liste des Messages dans le fil ${currentFil.nom} :</h1><br>
        	
        	
        	
        	<c:forEach items="${messages}" var="message">
        		<B><c:out value="${message.nomAuteur} :"></c:out></B> <c:out value="${message.texte}"></c:out><br>
        		<a href="supprMessage?idM=${message.id}">Supprimer</a><br>
        		<HR>
        	</c:forEach>
        	
			
        </div>
    </body>
</html>
