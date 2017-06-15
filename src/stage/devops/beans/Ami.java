package stage.devops.beans;

import java.util.List;

public class Ami {
	
	private String login;
	
	private boolean dessinerpour;
	
	private boolean repondre;
	
	private String repondreTexte;
	
	private String repondreImage;
	
	private String commentaire;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public boolean getDessinerpour() {
		return dessinerpour;
	}

	public void setDessinerpour(boolean dessinerpour) {
		this.dessinerpour = dessinerpour;
	}
	
	public boolean getRepondre() {
		return repondre;
	}
	
	public void setRepondre(boolean repondre){
		this.repondre = repondre;
	}

	public String getRepondreTexte() {
		return repondreTexte;
	}

	public void setRepondreTexte(String repondreTexte) {
		this.repondreTexte = repondreTexte;
	}
	public String getRepondreImage() {
		return repondreImage;
	}

	public void setRepondreImage(String repondreImage) {
		this.repondreImage = repondreImage;
	}
	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
}
