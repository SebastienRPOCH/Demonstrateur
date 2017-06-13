<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
           
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
		</p>
		</div>
        <div class="content">
        	<h3>Profil de l'utilisateur ${utilName} :</h3>
        	inconnu
        </div>
        
    </body>
</html>
