package entity;

public class Enseignant {
	private String nomEnseign;
	private String codeEnseign;

	public String toString(){
		return this.nomEnseign + this.codeEnseign;
	}
	public void setNomEnseign(String nomEnseign){
		this.nomEnseign=nomEnseign;
	}
	public String getNomEnseign(){
		return this.nomEnseign;
	}
	public void setCodeEnseign(String codeEnseign){
		this.codeEnseign=codeEnseign;
	}
	public String getCodeEnseign(){
		return this.codeEnseign;
	}
	
	public Enseignant(String nomEnseign,String codeEnseign){
		super();
		this.nomEnseign=nomEnseign;
		this.codeEnseign=codeEnseign;
	}
}
