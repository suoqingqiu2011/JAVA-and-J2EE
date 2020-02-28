package entity;

public class Etudiant {
	private String nomEtu;
	private String prenom;
	//private String numEtu;

	public String toString(){
		return this.nomEtu + this.prenom;
	}
	public void setNom(String nomEtu){
		this.nomEtu=nomEtu;
	}
	public String getNom(){
		return this.nomEtu;
	}
	public void setPrenom(String prenom){
		this.prenom=prenom;
	}
	public String getPrenom(){
		return this.prenom;
	}
	/*public void setNumEtu(String numEtu){
		this.numEtu=numEtu;
	}
	public String getNumEtu(){
		return this.numEtu;
	}*/
	public Etudiant(String nomEtu,String prenom){
		super();
		this.nomEtu=nomEtu;
		this.prenom=prenom;
		
	}
}
