package entity;

public class Attribution {
	private String etu;
	private String nomCours;

	public String toString(){
		return this.etu + this.nomCours;
	}
	public void setEtu(String etu){
		this.etu=etu;
	}
	public String getEtu(){
		return this.etu;
	}
	public void setNomCours(String nomCours){
		this.nomCours=nomCours;
	}
	public String getNomCours(){
		return this.nomCours;
	}
	
	public Attribution(String etu,String nomCours){
		super();
		this.etu=etu;
		this.nomCours=nomCours;
	}
}
