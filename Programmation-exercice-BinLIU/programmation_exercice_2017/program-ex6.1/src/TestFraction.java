package ab;

import org.junit.Test;


public class TestFraction {
	    
	Fraction frac= new Fraction(3,6);
	@Test
	public void testNumerat() {	
		frac.numerat();
	}
	@Test
	public void testDenomi() {	
		frac.denomi();
	}
	@Test
	public void testCombiner_fraction() {	
		frac.combiner_fraction();
	}
	
}
