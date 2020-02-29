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

	public static void main(String[] args) {
		
		Fraction fra0= new Fraction(0,1);
		System.out.println("Zero:"+fra0.combiner_fraction());
		
		Fraction fra1= new Fraction(1,1);
		System.out.println("Un:"+fra1.combiner_fraction());
		
		Fraction frac= new Fraction(12,29);
		System.out.println("Un fraction:"+frac.combiner_fraction());
		
		String p = frac.combiner_fraction();
		separation((String) p);
    }
	
}
