package fr.istic.gla.tp.beans;

public class Message {

	private int id;
	
	private String nomAuteur;
	
	private String texte;
	
	private String idFil;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNomAuteur(String nomAuteur) {
		this.nomAuteur = nomAuteur;
	}

	public String getNomAuteur() {
		return nomAuteur;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getTexte() {
		return texte;
	}
	public String getIdFil() {
		return idFil;
	}

	public void setIdFil(String idFil) {
		this.idFil = idFil;
	}
	
}
