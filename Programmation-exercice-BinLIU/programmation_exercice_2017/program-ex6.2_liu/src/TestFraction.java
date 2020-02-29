import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)

public class TestFraction {
	private int para1;  
    private int para2;    
    private int expected1;
    private double expected2;
    
    public TestFraction(int para1,int para2,int expected1,double expected2){    
        this.para1 = para1;    
        this.para2 = para2;    
        this.expected1 = expected1;    
        this.expected2 = expected2; 
    } 
    
	@Parameters     
    public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { 
			{ 3,6,3,0.5 }, 	
		});    
    }    
        
    
	    
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
	@Test
	public void testCalculMaxDiv() {	
		int result1 =frac.calculMaxDiv(para1,para2);
		System.out.println(result1);
		assertEquals(expected1,result1);
	}
	
	@Test
	public void testReduit() {	
		double result2 =frac.Reduit(para1,para2);
		System.out.println(result2);
		assertEquals(expected2,result2,0);
	}
}
