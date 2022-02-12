package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import LiuBin_TP02_v01.HashJoin;

public class HashJoinTest {
	HashJoin HJ ;
	
	char[] KeyRS ;
	int[] ValuesRS_R ;
	int[] ValuesRS_S ;
	
	char[] K_remove_test ;
	int[] Val_remove_test1 ;
	int[] Val_remove_test2 ;
	
	@Before
	public void setUp() throws Exception {
		HJ = new HashJoin();
		
		KeyRS = new char[10] ;
		int t = 0;
		while(t < 10) {  KeyRS[t]=' '; t++;}
		
		ValuesRS_R = new int[10];
		ValuesRS_S = new int[10];
		
		 K_remove_test = new char[9] ;
		 Val_remove_test1 = new int[9] ;;
		 Val_remove_test2 = new int[9] ;;
		
	}
	
	char[] K_put_test = {'B', 'B', ' ',' ',' ',' ',' ',' ',' ',' '};
	int[] Val_put_testR = {16,16,0,0,0,0,0,0,0,0};
	int[] Val_put_testS = {2,20,0,0,0,0,0,0,0,0};
	@Test
	public void test_put() throws IOException {
		
		HJ.put(KeyRS, ValuesRS_R, ValuesRS_S, 'B' , 16 , 2, 0 );		
		HJ.put(KeyRS, ValuesRS_R, ValuesRS_S, 'B' , 16 , 20, 1 );	
	
		assertArrayEquals(K_put_test,KeyRS);	
		assertArrayEquals(Val_put_testR,ValuesRS_R);
		assertArrayEquals(Val_put_testS,ValuesRS_S);
	}
	
	
	char[] K_join_jtest = {'B','B','B','B','E','K','U','U','V'};
	int[] V1_join_jtest = { 16, 16, 20, 20, 19, 14, 22, 22, 26 };
	int[] V2_join_jtest = { 2, 1, 2, 1, 8, 9, 6, 7, 13 };
	@Test
	public void test_get() throws IOException { 
		int valueReturn1[][] = HJ.get(K_join_jtest,V1_join_jtest,V2_join_jtest,'U');
		
		assertEquals(22,valueReturn1[0][0]);
		assertEquals(6,valueReturn1[0][1]);
		
		assertEquals(22,valueReturn1[1][0]);
		assertEquals(7,valueReturn1[1][1]);
	}
	
	char[] K_remove = {'B', 'B', 'B', 'B', 'E', 'K', ' ', ' ', 'V'};
	int[] Val1_remove = {16,16,20,20,19,14,0,0,26};
	int[] Val2_remove = {2,1,2,1,8,9,0,0,13};
	
	
	@Test
	public void test_remove() throws IOException {
		K_join_jtest = HJ.remove(K_join_jtest,V1_join_jtest,V2_join_jtest,'U'); 
	
		assertArrayEquals(K_remove,K_join_jtest);
		assertArrayEquals(Val1_remove,V1_join_jtest);
		assertArrayEquals(Val2_remove,V2_join_jtest);
	}
	
	char[] KeyR = {'A', 'B', 'G', 'J', 'U', 'K', 'E', 'Z', 'V', 'B'};
	char[] KeyS = {'B', 'U', 'E', 'K', 'X', 'V', 'N', 'B', 'M', 'U'};
	int[] ValuesR = {12, 16, 20, 19, 17, 15, 14, 22, 26, 28};
	int[] ValuesS = {2, 1, 8, 9, 11, 3, 6, 7, 13, 5};
	
	@Test
	public void test_hashjoin() throws IOException {
		int tab_size = HJ.hashjoin (KeyR, KeyS, ValuesR, ValuesS, KeyRS,ValuesRS_R,ValuesRS_S);
				
		assertEquals(9,tab_size);
		char[] K_RS = Arrays.copyOf(KeyRS, tab_size) ;
		int[] V_RS_R = Arrays.copyOf(ValuesRS_R, tab_size) ;
		int[] V_RS_S = Arrays.copyOf(ValuesRS_S, tab_size) ;
		
		assertArrayEquals(K_RS,K_join_jtest);
		assertArrayEquals(V_RS_R,V1_join_jtest);
		assertArrayEquals(V_RS_S,V2_join_jtest);
	}

	
}
