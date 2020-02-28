package entity;

public class Gestion {
	private String equipe;
	private String joueur;
	
	public String toString(){
		return this.equipe+this.joueur;
	}
	public void setEquipe(String equipe){
		this.equipe=equipe;
	}
	public String getEquipe(){
		return this.equipe;
	}
	public void setJoueur(String joueur){
		this.joueur=joueur;
	}
	public String getJoueur(){
		return this.joueur;
	}
	public Gestion(String equipe,String joueur){
		super();
		this.equipe=equipe;
		this.joueur=joueur;	
	}
}
