
abstract class Person{
	abstract String getName();
}

class Employe extends Person{
	private String nom;
	private int naissance;
	private String bureau;
	private String address;
	public  Employe(String nom,int naissance,String bureau) {
		this.nom=nom;
		this.naissance=naissance;
		this.bureau=bureau;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}
	
	public String getName() {
		return nom;
	}
	public String getRoom() {
		return bureau;
	}
	public String getAddress() {
		return address;
	}
}

class Etudiant extends Person{
	private String nom;
	private int naissance;
	private String ID;
	public Etudiant(String nom,int naissance,String ID) {
		this.nom=nom;
		this.naissance=naissance;
		this.ID=ID;
	}

	public String getName() {
		return nom;
	}
}


public class MainPers {
	public static void main(String[] args) {
		Employe emp=new Employe("Staff",1980,"D100");
		System.out.println(emp.getName());
		System.out.println(emp.getRoom());
		emp.setAddress("10 rue de versailles");
		
		Etudiant etu=new Etudiant("John",1990,"id1990");
		System.out.println(etu.getName());
	}
}
