
import java.util.ArrayList;
import java.util.List;

abstract class composant {
	String nom;
	int taille;
	public composant(String nom,int taille){
        this.nom = nom;
        this.taille = taille;
    }
	public abstract String getName();
    public abstract int calcultaille(); 
}

class Fichier extends composant{
	public Fichier(String nom,int taille){
        super(nom, taille);
    }
	
	public String getName(){
		return nom;
	}
	public int calcultaille() {
		return taille;
	}
} 

public class Repertoire extends composant{
	private static ArrayList <Fichier> fichs;
	public Repertoire(String nom,int taille){
        super(nom, taille);
        Repertoire.fichs=new ArrayList <Fichier>();
    }
		
	public void ajouter(Fichier f){
		Repertoire.fichs.add(f);	
	}

	public void retirer(Fichier f){
		Repertoire.fichs.remove(f);
	}

	public String getName(){
		return nom;
	}
	
	public int calcultaille(){
		taille=0;
		for(int i = 0;i<Repertoire.fichs.size();i++){
			taille+=Repertoire.fichs.get(i).calcultaille();
		}
		return this.taille;
	}
	
	public void test_ajout_luimeme(List<Fichier> f)
	{
		if(Repertoire.fichs.addAll((List<Fichier>) f)==Repertoire.fichs.addAll((List<Fichier>) fichs)){
			System.out.println("Repertoire ne puisse pas ajoute a lui_meme .");
		}else{
			System.out.println("Repertoire ne puisse pas ajoute comme un descentant de lui meme");
		}
	}
	
	public static void main(String[] args) 
    {
    	Repertoire rep= new Repertoire("repertoire",0);
    	Fichier f1 = new Fichier("f1",12);
    	Fichier f2 = new Fichier("f2",15);
    	Fichier f3 = new Fichier("f3",18);
    	
    	rep.ajouter(f1);
    	rep.ajouter(f2);
    	rep.ajouter(f3);
    	
    	for (int i=0;i<((List<Fichier>) fichs).size();i++) 
    	{ 
    		System.out.println("fiche"+i+":"+((List<Fichier>) fichs).get(i).getName()+" size:"+ ((List<Fichier>) fichs).get(i).calcultaille()+" kb"); 
    	} 
    	System.out.println("Taille de repertoire :"+rep.calcultaille()+" kb");
    	
    	ArrayList <Fichier> fichs1=new ArrayList <Fichier>();
    	rep.test_ajout_luimeme((List<Fichier>) fichs);
    	rep.test_ajout_luimeme((List<Fichier>) fichs1);
    	
    	
    }
	
}

