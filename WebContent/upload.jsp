<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
           
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- Directive chargeant le bean de l'utilisateur en session -->
<jsp:useBean id="utilisateur" class="fr.istic.gla.tp.beans.Utilisateur" scope="session"/>
<jsp:useBean id="fil" class="fr.istic.gla.tp.beans.Fil" scope="session"/>


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
        	
        	<img src="${image}" width="60"><br>
        	<form method="POST" action="upload" enctype="multipart/form-data">
        	File:
            <input type="file" name="file" id="file" /> <br/>
            </br>
            <input type="submit" value="Upload" name="upload" id="upload" />
			</form>
			Taille max du fichier 10000.
        </div>
    </body>
</html>
