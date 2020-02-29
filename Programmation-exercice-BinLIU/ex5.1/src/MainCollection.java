import java.util.ArrayList;
import java.util.List;


abstract class application{
	abstract String getInfo();
	/*abstract void ajouter();
	abstract void lister();
	abstract void rechercher();*/
}


class CD extends application{
	private String titreCD;
	private String nom;
	private int nbTitre;
	
	public CD(String titreCD,String nom,int nbTitre){
		this.titreCD=titreCD;
		this.nom=nom;
		this.nbTitre=nbTitre;
	}
	
	public String getInfo(){
		return titreCD+" : "+nom+" a "+nbTitre+" titres";
	}

	/*
	public void ajouter(){};
	public void lister(){};
	public void rechercher(){}*/

}

class DVD extends application{
	private String titreDVD;
	private String realisateur;
	private int annee; 
	
	public DVD(String titreDVD,String realisateur,int annee){
		this.titreDVD=titreDVD;
		this.realisateur=realisateur;
		this.annee=annee;
	}
	
	public String getInfo(){
		return titreDVD+" : "+realisateur+" publie en "+annee;
	}
	
	/*public void ajouter(){};
	public void lister(){};
	public void rechercher(){};*/
}

public class MainCollection {
	 public static void main(String[] args) {
		CD cd=new CD("Black or White","Michael Jackson",10);
		DVD dvd=new DVD("Transformers","Michael Bay",2017);
		
		ArrayList<application> collect = new ArrayList<application>() ;
		collect.add(cd);
		collect.add(dvd);
		
		for(int i=0;i<((List<application>) collect).size();i++){ 
    		System.out.println(collect.get(i).getInfo()); 
    	} 
		
		
	 }
}
