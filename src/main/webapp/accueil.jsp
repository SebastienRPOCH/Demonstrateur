<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h1></h1>
	</div>
	<div class="menu">
	</div>
	<div class="content">
	
	<form method="post" action="accueil">
		<label for="identifiant"> Identifiant : <br /> <input
			type="text" name="identifiant" id="identifiant" />
		</label> <br /> <br /> <label for="mdp"> Mot de passe : <br />
			<input type="password" name="mdp" id="mdp" />
		</label> <br /> <br /> <input type="submit" value="Valider"/>
	</form>
	<form method="post" action="creercompte">
	<br /> <br />
	<br /> <br />
		<label for="identifiant"> Identifiant : <br /> <input
			type="text" name="identifiant" id="identifiant" />
		</label> <br /> <br /> <label for="mdp"> Mot de passe : <br />
			<input type="password" name="mdp" id="mdp" />
		</label> <br /> <br /> <label for="mdp"> Confirmation mot de passe : <br />
			<input type="password" name="mdpConf" id="mdpConf" />
		</label> <br /> <br />
		<input type="submit" value="Créer un compte"/>
	</form>
	</div>
</body>
</html>
