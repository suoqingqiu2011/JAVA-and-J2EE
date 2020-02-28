package entity;

public class Produit {

	private String nom;
	private float prix;

	public String toString(){
		return this.nom + this.prix;
	}
	public void setNom(String nom){
		this.nom=nom;
	}
	public String getNom(){
		return this.nom;
	}
	public void setPrix(float prix){
		this.prix=prix;
	}
	public float getPrix(){
		return this.prix;
	}
	public Produit(String nom,float prix){
		super();
		this.nom=nom;
		this.prix=prix;
	}
	

}
