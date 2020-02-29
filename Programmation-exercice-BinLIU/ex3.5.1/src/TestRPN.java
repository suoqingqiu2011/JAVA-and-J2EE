import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;


public class TestRPN {

	 SaisieRPN sais=new SaisieRPN();
	 MoteurRPN RPN= new MoteurRPN();
	 
	@Test
	public void testTransfer() {
		ArrayList<String> st= new ArrayList<String>(); 
		 
		 st.add("2");
		 st.add("3");
		 st.add("+");
		 st.add("2");
		 st.add("*");
		 String expected1="(2*(3+2))";
		 
		String strings=RPN.transfer(st);
		assertEquals(expected1,strings);
	}
	@Test
	public void testEval() {	
		ArrayList<String> st= new ArrayList<String>(); 
		 
		 st.add("2");
		 st.add("3");
		 st.add("+");
		 st.add("2");
		 st.add("*");
					
		 double expected2=10.0;
		 
		double result=RPN.eval(st);		
		assertEquals(expected2,result,0);
	}
	
}