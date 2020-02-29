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
	@Test
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
	@Test
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
	
	public int calculMaxDiv(int p,int q) {
		return getPgcd(p,q);
	}
	
	public double Reduit(int p,int q) {
		return (double)p/q;
	}
	
	public static void main(String[] args) {
		
	
       
        Fraction frac1= new Fraction(3,2);
        int MaxDivisuer1=frac1.calculMaxDiv(frac1.numerat(), frac1.denomi());
        double Numerat_reduit1=frac1.Reduit(frac1.numerat(),MaxDivisuer1);
        double Denomi_reduit1=frac1.Reduit(frac1.denomi(),MaxDivisuer1);
        
        Fraction frac2= new Fraction(9,6);
        int MaxDivisuer2=frac2.calculMaxDiv(frac2.numerat(), frac2.denomi());
        double Numerat_reduit2=frac2.Reduit(frac2.numerat(),MaxDivisuer2);
        double Denomi_reduit2=frac2.Reduit(frac2.denomi(),MaxDivisuer2);
        
        
        
        System.out.print((int)Numerat_reduit1+"/"+(int)Denomi_reduit1+"et"+(int)Numerat_reduit2+"/"+(int)Denomi_reduit2+":\t");
        if(Numerat_reduit1==Numerat_reduit2&&Denomi_reduit1==Denomi_reduit2){
        	 System.out.println("Les deux fractions sont egales.");
        }else{
       	     System.out.println("Les deux fractions sont inegales.");
        }
        
  
        int multiple_Numerat1=frac1.numerat()*frac2.denomi();
        int multiple_Numerat2=frac2.numerat()*frac1.denomi();
        if(multiple_Numerat1> multiple_Numerat2){
        	System.out.println(frac1.numerat() + "/" + frac1.denomi()+"est plus grande.");
        }else if( multiple_Numerat1 < multiple_Numerat2){
        	System.out.println(frac2.numerat() + "/" + frac2.denomi()+"est plus grande.");
        }else{
        	System.out.println("Les deux fractions "+ frac1.numerat() + "/" + frac1.denomi() +" et "+ frac2.numerat() + "/" + frac2.denomi() +" sont egales.");
        }
    }
	
}
