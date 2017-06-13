package stage.devops.beans;

import java.util.List;

public class Joueur {

	private int id;
	
	private String login;
	
	private String password;
	
	private String profil;
	
	private String image;
	
	private int score;
	
	private List<Ami> listAmi;

	public String getLogin() {
		return login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public List<Ami> getListAmi() {
		return listAmi;
	}

	public void setListAmi(List<Ami> listAmi) {
		this.listAmi = listAmi;
	}
}
