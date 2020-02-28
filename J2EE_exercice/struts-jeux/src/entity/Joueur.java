package entity;

public class Joueur {
	private String nom;
	private String prenom;
	private String poste;
	private int age;
	private float salaire;

	public String toString(){
		return this.nom + this.prenom;
	}
	public void setNom(String nom){
		this.nom=nom;
	}
	public String getNom(){
		return this.nom;
	}
	public void setPrenom(String prenom){
		this.prenom=prenom;
	}
	public String getPrenom(){
		return this.prenom;
	}
	
	public void setPoste(String poste){
		this.poste=poste;
	}
	public String getPoste(){
		return this.poste;
	}
	public void setAge(int age){
		this.age=age;
	}
	public int getAge(){
		return this.age;
	}
	
	public void setSalaire(float salaire){
		this.salaire=salaire;
	}
	public float getSalaire(){
		return this.salaire;
	}
	
	public Joueur(String nom,String prenom,String poste,int age,float salaire){
		super();
		this.nom=nom;
		this.prenom=prenom;
		this.poste=poste;
		this.age=age;
		this.salaire=salaire;
		
	}
}
