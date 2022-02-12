/** 
 * TP n¡ã V n¡ã : 1
 * 
 * Titre du TP : jointure
 * 
 * Date : 30 Octobre 2021
 * 
 * Nom : LIU
 * Pr¨¦nom : Bin
 * N¡ã d'¨¦tudiant : 22118363
 * 
 * email : happybin1989@gmail.com
 * 
 * Remarques : tests unitaires
 */

package test;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import LiuBin_TP1_V01.Operations;

public class LiuBinTP1jointureTest {
	Operations Otest;
	
	char[] test1;
	char[] tests;
	char[] test3;
	
	@Before
	public void setUp() throws Exception {
		Otest = new Operations();
	
		test1 = new char[10];
		tests = new char[10];	
		test3 = new char[10];
		
		for (int i = 0; i < test3.length; i++) {
			test3[i]=' ';
		}	
	}
	
	/**********************************************************/
	char[] r_test = {'A','Z','G','J','U','K','E','B','V','D'};
	char[] s_test = {'B','U','Z','K','X','V','N','L','M','E'};
	
	@Test
	public void testreadFile() throws IOException{	
		assertArrayEquals(r_test,Otest.readFile(test1, "R.txt",'A'));
	}
	
	/**********************************************************/
	char[] rs_test = {'Z','U','K','E','B','V',' ',' ',' ',' '}; 
	
	@Test
	public void testjoin() throws IOException {
		test3 = Otest.join(test3,r_test,s_test);
		//System.out.println(rs_test); System.out.println(test3);
		
		for (int i=0;i< test3.length;i++) {
			if(rs_test[i]!=' ' && test3[i]!= ' ') {
				assertEquals(rs_test[i],test3[i]);
			}
        }
	}
	
	/**********************************************************/
	/* "RS.txt" est g¨¦n¨¦r¨¦ par le table 'r' en index 'A' et le table 's' en index 'B' */
	@Test
	public void testwriteFile() throws IOException{	
		assertEquals(rs_test,Otest.writeFile(rs_test,"RS.txt"));
	}

}
