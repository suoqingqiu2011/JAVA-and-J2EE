package ab;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class Fraction {
	public int numerateur;
	public int denominateur;
	
	public Fraction(int numerateur,int denominateur){
		this.numerateur=numerateur;
		this.denominateur=denominateur;
	}
	@Test
	public int numerat(){
		return numerateur;
	}
	@Test
	public int denomi(){
		return denominateur;
	}
	@Test
	 public String combiner_fraction(){
		if(denominateur== 0||denominateur==1){
			String numerat=String.valueOf(numerateur);
			return numerat;
		}
		return numerat() + "/" + denomi();
	}
	
	 public static void separation(String p) {
		 String[] q = p.split("/");
	    	for (int t = 0; t < q.length; t++) {
	    		if(t==0) {
	    		    System.out.print(p+": son numerateur est "+q[t]+";\t");
	    		}else {
	    			System.out.println("son denominateur est "+q[t]);	
	    		}
	 	}
	    	
	 }
	 
	 /* Obtenir le plus grand commun diviseur entre numerateur et denominateur */

	public static int getPgcd(int a, int b) {
        int t = 0;  
        if(a < b){  
        t = a;  
        a = b;  
        b = t;  
        }  
        int c = a % b;  
        if(c == 0){  
            return b;  
        }else{  
            return getPgcd(b, c);  
        }  
    }  
	
	
	public static void main(String[] args) {
		
		Fraction fra0= new Fraction(0,1);
		System.out.println("Zero:"+fra0.combiner_fraction());
		
		Fraction fra1= new Fraction(1,1);
		System.out.println("Un:"+fra1.combiner_fraction());
		
		Fraction frac= new Fraction(12,29);
		System.out.println("Un fraction:"+frac.combiner_fraction());
		
		String p = frac.combiner_fraction();
		separation((String) p);
    	
		double nb_decimal= 3.62;
		String decimal=String.valueOf(nb_decimal);
  
        String[] array = new String[2];  
        array = decimal.split("\\.");  
        int a = Integer.parseInt(array[0]);// obtenir le numerateur
        int b = Integer.parseInt(array[1]);//obtenir le denominateur  
        int length = array[1].length();  
        int Numerateur = (int) (a * Math.pow(10, length) + b);  
        int Denominateur = (int) Math.pow(10, length);  
        int MaxDivisuer = getPgcd(Numerateur, Denominateur);  
        //System.out.println(Numerateur +" "+ Denominateur);
        System.out.println(nb_decimal + ":" + Numerateur / MaxDivisuer + "/" + Denominateur / MaxDivisuer); 
        
       
        String nu=Integer.toString(Numerateur / MaxDivisuer);  
        String de=Integer.toString(Denominateur / MaxDivisuer);     
        ArrayList<String> list = new ArrayList<String>();
        
        for(int i = 0;  i < nu.length(); i++) {
           list.add(nu.substring(i, (i+1)));
        }
        list.add("/");
        for(int j = 0;  j < de.length(); j++) {
            list.add(de.substring(j, (j+1)));
        }
        System.out.println("Chaine de caractere: "+list);
        

    }
	
}
