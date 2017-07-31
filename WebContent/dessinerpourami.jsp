
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<!-- Directive chargeant le bean de l'utilisateur en session -->
<jsp:useBean id="joueur" class="stage.devops.beans.Joueur"
	scope="session" />


<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="paint.js"></script>
</head>

<body>
	<div class="header">
		<h1>DevMode</h1>
	</div>
	<div class="userbox">
		<h1>Connecté en tant que : ${joueur.login} (${joueur.profil})</h1>
	</div>
	<div class="menu">
		<form method="post" action="dessinerPour">
			<input type="submit" value="Retour" />
		</form>
	</div>
	<div class="content">

		Dessine "${dessin}"
	
	<canvas id="canvas" width="800" height="500"></canvas>
		
		<ul id="couleurs">
			<li><a href="#" data-couleur="#000000" class="actif">Noir</a></li>
			<li><a href="#" data-couleur="#ffffff">Blanc</a></li>
			<li><a href="#" data-couleur="#ff0000">Rouge</a></li>
			<li><a href="#" data-couleur="brown">Marron</a></li>
			<li><a href="#" data-couleur="orange">Orange</a></li>
			<li><a href="#" data-couleur="yellow">Jaune</a></li>
			<li><a href="#" data-couleur="green">Vert</a></li>
			<li><a href="#" data-couleur="cyan">Cyan</a></li>
			<li><a href="#" data-couleur="blue">Bleu</a></li>
			<li><a href="#" data-couleur="indigo">Indigo</a></li>
			<li><a href="#" data-couleur="Violet">Violet</a></li>
			<li><a href="#" data-couleur="pink">Rose</a></li>
		</ul>
		
		<form id="largeurs_pinceau">
			<label for="largeur_pinceau">Largeur du pinceau :</label>
			<input id="largeur_pinceau" type="range" min="2" max="50" value="5" />
			<output id="output">5 pixels</output>
			
			<br/>
			
			<input type="reset" id="reset" value="Réinitialiser" />
			<input type="button" id="save" value="Sauvergarder mon image" />
		</form>
	</div>
	
	
</body>
</html>
