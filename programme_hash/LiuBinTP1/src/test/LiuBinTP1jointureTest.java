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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import LiuBin_TP1_V01.Operations;

public class LiuBinTP1jointureTest {
	Operations Otest;
	
	char[] test1;
	char[] tests;

	List<Character> test3;
	
	StringBuilder sb;
	
	@Before
	public void setUp() throws Exception {
		Otest = new Operations();
	
		test1 = new char[10];
		tests = new char[10];
		
		test3 = new ArrayList<>();
		
		sb = new StringBuilder();
	}
	
	/**********************************************************/
	char[] r_test = {'A','Z','G','J','U','K','E','B','V','D'};
	char[] s_test = {'B','U','Z','K','X','V','N','L','M','E'};
	
	@Test
	public void testreadFile() throws IOException{	
		assertArrayEquals(r_test,Otest.readFile(test1, "R.txt"));
	}
	
	/**********************************************************/
	char[] rs_test = {'Z','U','K','E','B','V'}; 
	//List<Character> rs_test= Arrays.asList('Z','U','K','E','B','V');   
	
	@Test
	public void testjoin() throws IOException {
		//assertEquals(rs_test,Otest.join(test3,r_test,s_test));   //or comparer les 2 listes 'List<Character>'
		test3 = Otest.join(test3,r_test,s_test);
		
        for (Character ch: test3) {
            sb.append(ch);
        }
		assertArrayEquals(rs_test,sb.toString().toCharArray());
	}
	
	/**********************************************************/
	List<Character> rs_test1= Arrays.asList('Z','U','K','E','B','V');
	
	@Test
	public void testwriteFile() throws IOException{	
		assertEquals(rs_test1,Otest.writeFile(rs_test1,"RS.txt"));
	}

}
