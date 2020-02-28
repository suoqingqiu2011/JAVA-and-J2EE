package form;
import org.apache.struts.action.ActionForm;

public class JoueurActionForm extends ActionForm{
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
}
