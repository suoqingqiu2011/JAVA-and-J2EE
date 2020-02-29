import java.util.ArrayList;


public class Fraction {
	public int numerateur;
	public int denominateur;
	
	public Fraction(int numerateur,int denominateur){
		this.numerateur=numerateur;
		this.denominateur=denominateur;
	}
	
	public int numerat(){
		return numerateur;
	}
	
	public int denomi(){
		return denominateur;
	}

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
        
        
        Fraction frac1= new Fraction(3,2);
        int MaxDivisuer1=getPgcd(frac1.numerat(), frac1.denomi());
        int Numerat_reduit1=frac1.numerat()/MaxDivisuer1;
        int Denomi_reduit1=frac1.denomi()/MaxDivisuer1;
        
        Fraction frac2= new Fraction(9,6);
        int MaxDivisuer2=getPgcd(frac2.numerat(), frac2.denomi());
        int Numerat_reduit2=frac2.numerat()/MaxDivisuer2;
        int Denomi_reduit2=frac2.denomi()/MaxDivisuer2;
        
        System.out.print(Numerat_reduit1+"/"+Denomi_reduit1+"et"+Numerat_reduit2+"/"+Denomi_reduit2+":\t");
        if(Numerat_reduit1==Numerat_reduit2&&Denomi_reduit1==Denomi_reduit2){
        	 System.out.println("Les deux fractions sont egales.");
        }else{
       	     System.out.println("Les deux fractions sont inegales.");
        }
        
        /* si tous les 2 denominateurs sont egales a 'frac1.denomi()*frac2.denomi()'. */
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
